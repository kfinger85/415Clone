package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//import org.evosuite.shaded.org.hibernate.jpa.boot.spi.ProviderChecker;

import java.util.regex.Pattern;

public class Company {

	private String name;
	private Set<Worker> employees;
	private Set<Worker> available;
	private Set<Worker> assigned;
	private Set<Project> projects;
	private Set<Qualification> qualifications;

	//no workers, projects, or qualifications to begin with : might need more checks
	public Company(String name) {
		if(name == null || name.isEmpty() || isAllBlankSpace(name)){
			throw new IllegalArgumentException("name must not be null or empty");
		}
		this.name = name;
		this.employees = new HashSet<Worker>();
		this.available = new HashSet<Worker>();
		this.assigned = new HashSet<Worker>();
		this.projects = new HashSet<Project>();
		this.qualifications = new HashSet<Qualification>();
	}
	private boolean isAllBlankSpace(String name) {
		Pattern pattern = Pattern.compile("^\\s*$");
		return pattern.matcher(name).find();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if(this.getClass() != other.getClass()){
			return false;
		}
		Company company = (Company) other;
		return company.name.equals(this.name);
	}


	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return this.name + ":" + this.available.size() + ":" + this.projects.size();
	}

	public String getName() {
		return this.name;
	}

	public Set<Worker> getEmployedWorkers() {
		return new HashSet<>(this.employees);
	}

	public Set<Worker> getAvailableWorkers() {
		return new HashSet<>(this.available);
	}

	public Set<Worker> getUnavailableWorkers() {
		return new HashSet<>(this.employees.stream().filter(q -> !this.available.contains(q)).collect(Collectors.toSet()));//I think already makes a copy but now it's extra copied.
	}

	public Set<Worker> getAssignedWorkers() {
		return new HashSet<>(this.assigned);
	}

	public Set<Worker> getUnassignedWorkers() {
		return new HashSet<>(this.employees.stream().filter(q -> !this.assigned.contains(q)).collect(Collectors.toSet()));
	}

	public Set<Project> getProjects() {
		return new HashSet<>(this.projects);
	}

	public Set<Qualification> getQualifications() {
		return new HashSet<>(this.qualifications);
	}

	public Worker createWorker(String name, Set<Qualification> qualifications, double salary) {
		Worker newWorker;
		try {
			newWorker = new Worker(name, qualifications, salary);
			if(!this.qualifications.containsAll(qualifications)) {
				return null;
			}
		}catch (Exception e){
			return null;
		}

			this.employees.add(newWorker);
			this.available.add(newWorker);

			Set<Qualification> toRemove = new HashSet<Qualification>();
			Set<Qualification> toAdd = new HashSet<Qualification>();

			for (Qualification companyQual : this.qualifications){
				for (Qualification workerQual : qualifications){
					if(workerQual.equals(companyQual)){
						toRemove.add(companyQual);
						Qualification newQual = companyQual;
						newQual.addWorker(newWorker);
						toAdd.add(newQual);
					}
				}
			}
			this.qualifications.removeAll(toRemove);
			this.qualifications.addAll(toAdd);

			return newWorker;
	}

	public Qualification createQualification(String description) {
		Qualification newQualification;
		try{
			newQualification = new Qualification(description);
		}catch (Exception e){
			return null;
		}
		if(this.qualifications.add(newQualification))
			return newQualification;
		return null;
	}


	public Project createProject(String name, Set<Qualification> qualifications, ProjectSize size) {
		if(name == null|| name.isEmpty()|| isAllBlankSpace(name)){
			return null;
		}
		if(qualifications == null || qualifications.isEmpty()){
			return null;
		}
		if(size == null){
			return null;
		}
		//make sure Company has all of the qualifications the company needs.
		for(Qualification q : qualifications){
			if(getQualifications().contains(q)){
				continue;
			} else {
				return null;
			}
		}
			Project project = new Project(name, qualifications, size);//Since the set of qualifications is set automatically i think this is all it needs
			this.projects.add(project);
			return project;
	}

	public void start(Project project) {
		if(project.getStatus().equals(ProjectStatus.ACTIVE) ||
		   project.getStatus().equals(ProjectStatus.FINISHED)){
			throw new IllegalArgumentException();
		}
		if(!this.projects.contains(project)){
			throw new IllegalArgumentException();
		}
		if(!project.getMissingQualifications().isEmpty()){
			throw new IllegalArgumentException();
		}
		project.setStatus(ProjectStatus.ACTIVE);
	}

	public void finish(Project project) {
		if(project.getStatus().equals(ProjectStatus.SUSPENDED) ||
				project.getStatus().equals(ProjectStatus.FINISHED)  ||
				project.getStatus().equals(ProjectStatus.PLANNED)){
			throw new IllegalArgumentException("Project is not active");
		}
		if(!this.projects.contains(project)){
			throw new IllegalArgumentException();
		}
		
		// Make copy of workers, otherwise ConcurrentModificationException
		// ie trying to remove from a set while iterating over it
		Set<Worker> workersCopy = new HashSet<>(project.getWorkers());
		//free workers
		//remove project from workers
		
		for (Worker worker : workersCopy) {
			unassign(worker, project);
		}
		

		project.setStatus(ProjectStatus.FINISHED);
	
	}
	
	public void assign(Worker worker, Project project) {
		isWorkerOrProjectNull(worker, project);
		isWorkerAndProjectInCompany(worker, project);
		isProjectStatusValid(project);
		isAvailable(worker, project);
		
		if(!canAssign(worker, project)){
			throw new IllegalArgumentException("Cannot assign worker");
		}
		
		addWorkerToAssignedAndProject(worker, project);
		removeWorkerFromAvailableIfAtFullCapacity(worker);
	}

	private void isWorkerAndProjectInCompany(Worker worker, Project project){
		if(!this.getProjects().contains(project) || !this.getEmployedWorkers().contains(worker)) {
			throw new IllegalArgumentException("project and Worker must be in the Company you would like to assign to");
		}
	}

	private void isProjectStatusValid(Project project){
		if(project.getStatus() == ProjectStatus.ACTIVE || project.getStatus() == ProjectStatus.FINISHED) {
			throw new IllegalArgumentException("Cannot assign to an ACTIVE or FINISHED project");
		}
	}

	private void isAvailable(Worker worker, Project project){
		if(!available.contains(worker) || worker.getProjects().contains(project)) {
			throw new IllegalArgumentException("Worker must be available and not already be in the project you would like to assign to");
		}
	}

	// TODO: Can this code be shortened / made cleaner ? 
	private boolean canAssign(Worker worker, Project project) {
		if (!worker.willOverload(project) && project.isHelpful(worker)) {  // worker will not be overloaded and is helpful
			return true;
		}
		return false;
	}

	// do i need both worker.addProject() and project.addWorker() ???
	private void addWorkerToAssignedAndProject(Worker worker, Project project){
		this.assigned.add(worker);
		worker.addProject(project);
		project.addWorker(worker);
	}

	private void removeWorkerFromAvailableIfAtFullCapacity(Worker worker){
		if(worker.getWorkload() == 12){
			available.remove(worker);
		}
	}

    // TODO: " Also think about other situations for the available and assigned pools. " 
	public void unassign(Worker worker, Project project) {
		isWorkerOrProjectNull(worker, project);
		if(!this.getProjects().contains(project) || !this.getEmployedWorkers().contains(worker)) {
			throw new IllegalArgumentException("project and worker must be in the Company you would like to unassign from");
		}
		ifWorkerIsInAssignedRemoveFromProject(worker, project);
		checksForAvailableAndAssignedPools(worker);
		shouldStatusStillBeActive(project);
	}

	private void isWorkerOrProjectNull(Worker worker, Project project){
		if(worker == null) {
			throw new IllegalArgumentException("worker must not be null");
		}
		if(project == null) {
			throw new IllegalArgumentException("project must not be null");
		}
	}

	private void shouldStatusStillBeActive(Project project){
		if(project.getStatus().equals(ProjectStatus.ACTIVE) && project.getMissingQualifications().size() > 0){
			project.setStatus(ProjectStatus.SUSPENDED);
		}
	}

	// it seems like this has to work both ways. project.removeWorker() and worker.removeProject() ? 
	private void ifWorkerIsInAssignedRemoveFromProject(Worker worker, Project project) {
		if(getAssignedWorkers().contains(worker)){
			project.removeWorker(worker);
			worker.removeProject(project);
		}
		else{
			throw new IllegalArgumentException("worker must not have been assigned");
		}
	}

	//TODO: anything else to check for?
	private void checksForAvailableAndAssignedPools(Worker worker) {
		if(worker.getProjects().size() == 0){
			assigned.remove(worker);
		}
		if(!available.contains(worker)){
				available.add(worker);
		}
	}

	public void unassignAll(Worker worker) {
		if(worker == null) {
			throw new IllegalArgumentException("worker must not be null");
		}
		// what do you think? seems like this if statement would make sure unassign() can correctly be called? 
		if(!this.employees.contains(worker)) {
			throw new IllegalArgumentException("Worker is not in company");
		}
			for(Project p : worker.getProjects()){
				unassign(worker, p);
			}
		}
	}
