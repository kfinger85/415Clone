package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import edu.colostate.cs415.dto.WorkerDTO;


import javax.persistence.*;


@Entity
@Table(name = "workers")
public class Worker {

	public static final int MAX_WORKLOAD = 12;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "salary")
	private double salary;

	@Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "workers_projects", 
        joinColumns = { @JoinColumn(name = "worker_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "project_id") }
		
    )
	private Set<Project> projects;

	@OneToMany(mappedBy = "worker")
private Set<WorkerProject> workerProjects;

    @ManyToMany
    @JoinTable(
        name = "workers_qualifications",
        joinColumns = @JoinColumn(name = "worker_id"),
        inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
	private Set<Qualification> qualifications;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

	protected Worker() {}

	public Worker(String name, Set<Qualification> qualifications, double salary) {
		checkArgValidity(name, qualifications, salary);
		this.name = name;
		this.qualifications = qualifications;
		this.salary = salary;
		this.projects = new HashSet<Project>();
		this.workerProjects = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	private void checkArgValidity(String name, Set<Qualification> qualifications, double salary){
		if(salary < 0){
			throw new IllegalArgumentException("Salary cannot be negative");
		}
		if(qualifications == null || qualifications.isEmpty()){
			throw new IllegalArgumentException("Worker must have at least one qualification");
		}
		Pattern pattern = Pattern.compile("^\\s*$");
		if(name == null || pattern.matcher(name).find()){
			throw new IllegalArgumentException("Name field must not be null or empty");
		}
	}


	@Override
	public boolean equals(Object other) {
		if (other == null)
		{
			return false;
		}

		if (this.getClass() != other.getClass())
		{
			return false;
		}
		return this.getName().equals(((Worker) other).getName());
	}

	public void setCompany(Company company) {
        this.company = company;
    }

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public String toString() {
		return this.name + ":" + this.projects.size() + ":" + this.qualifications.size() + ":" + (int)(this.salary);
	}

	public String getName() {
		return this.name;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return this.username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}

	public void setSalary(double salary) {
		if(salary < 0){
			throw new IllegalArgumentException("Salary cannot be negative");
		}
		this.salary = salary;
	}

	public Set<Qualification> getQualifications() {
		return new HashSet<Qualification>(this.qualifications);
	}

	public void addQualification(Qualification qualification) {
		if(qualification == null) {
			throw new IllegalArgumentException("qualification must not be null");
		}
		this.qualifications.add(qualification);
	}

	public Set<Project> getProjects() {
        return workerProjects.stream()
            .map(WorkerProject::getProject)
            .collect(Collectors.toSet());
    }

    public void addProject(Project project) {
        if(project == null) {
            throw new IllegalArgumentException("project must not be null");
        }
        WorkerProject workerProject = new WorkerProject(this, project);
        this.workerProjects.add(workerProject);
    }

    public void removeProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("project must not be null");
        }
        WorkerProject workerProject = this.workerProjects.stream()
            .filter(wp -> wp.getProject().equals(project))
            .findFirst()
            .orElse(null);
        if (workerProject != null) {
            this.workerProjects.remove(workerProject);
        }
    }

	public int getWorkload() {
		int workload = 0;

		for (WorkerProject wp : this.workerProjects) {
			Project p = wp.getProject();
			if(p.getStatus() == ProjectStatus.FINISHED) continue;
			workload += p.getSize().getValue();
		}

		return workload;
	}

	public boolean willOverload(Project project) {
		if(this.getProjects().contains(project)) return false;

		int workload_with_project = this.getWorkload() + project.getSize().getValue();
		if(workload_with_project > MAX_WORKLOAD) return true; 

		return false;
	}

	public boolean isAvailable() {
		if(this.getWorkload() >= MAX_WORKLOAD) return false;
		return true;
	}

	public WorkerDTO toDTO() {
		String[] qualification_strings = new String[this.qualifications.size()];
		String[] project_strings = new String[this.projects.size()];

		int i = 0;
		for(Qualification qual:this.qualifications){
			qualification_strings[i] = qual.toString();
			i++;
		}

		i = 0;
		for(Project proj:this.projects){
			project_strings[i] = proj.getName();
			i++;
		}

		return new WorkerDTO(this.name, this.salary, this.getWorkload(), project_strings, qualification_strings);
	}

	public Worker orElseThrow(Object object) {
		return null;
	}

	
}
