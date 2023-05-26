package edu.colostate.cs415.services;

import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.ProjectSize;


import edu.colostate.cs415.repositories.CompanyRepository;
import edu.colostate.cs415.repositories.WorkerRepository;
import edu.colostate.cs415.repositories.ProjectRepository;
import edu.colostate.cs415.repositories.QualificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private QualificationRepository qualificationRepository;


    @Autowired
    public CompanyService(CompanyRepository companyRepository,
                          WorkerRepository workerRepository,
                          ProjectRepository projectRepository) {
        this.companyRepository = companyRepository;
        this.workerRepository = workerRepository;
        this.projectRepository = projectRepository;
    }
    

    public Worker createWorker(String companyName, String name, Set<Qualification> qualifications, double salary) {
        // 1. Fetch the company from the repository
        Company company = companyRepository.findByName(companyName);
        if (company == null) {
            throw new IllegalArgumentException("Company does not exist");
        }
    
        // 2. Create the worker and add it to the company
        Worker newWorker = new Worker(name, qualifications, salary);
        if (!company.getQualifications().containsAll(qualifications)) {
            throw new IllegalArgumentException("Company does not have all required qualifications");
        }
        company.getEmployedWorkers().add(newWorker);
    
        // 3. Save the worker and the company back to their respective repositories
        companyRepository.save(company);
        return workerRepository.save(newWorker);
    }
    
    public Qualification createQualification(String companyName, String description) {
        // 1. Fetch the company from the repository
        Company company = companyRepository.findByName(companyName);
        if (company == null) {
            throw new IllegalArgumentException("Company does not exist");
        }
    
        // 2. Create the qualification and add it to the company
        Qualification newQualification = new Qualification(description);
        company.getQualifications().add(newQualification);
    
        // 3. Save the qualification and the company back to their respective repositories
        companyRepository.save(company);
        return qualificationRepository.save(newQualification);
    }
    
    public Project createProject(String companyName, String name, Set<Qualification> qualifications, ProjectSize size) {
        // 1. Fetch the company from the repository
        Company company = companyRepository.findByName(companyName);
        if (company == null) {
            throw new IllegalArgumentException("Company does not exist");
        }
    
        // 2. Create the project and add it to the company
        Project newProject = new Project(name, qualifications, size);
        if (!company.getQualifications().containsAll(qualifications)) {
            throw new IllegalArgumentException("Company does not have all required qualifications");
        }
        company.getProjects().add(newProject);
    
        // 3. Save the project and the company back to their respective repositories
        companyRepository.save(company);
        return projectRepository.save(newProject);
    }
    

    public void startProject(String companyName, String projectName) {
        // Fetch the company and project from the repository
        Company company = companyRepository.findByName(companyName);
        Project project = projectRepository.findByName(projectName);

        if (company == null || project == null) {
            throw new IllegalArgumentException("Company or Project not found");
        }

        // Start the project
        company.start(project);

        // Save the company and project back to their respective repositories
        companyRepository.save(company);
        projectRepository.save(project);
    }

    public void finishProject(String companyName, String projectName) {
        // Fetch the company and project from the repository
        Company company = companyRepository.findByName(companyName);
        Project project = projectRepository.findByName(projectName);

        if (company == null || project == null) {
            throw new IllegalArgumentException("Company or Project not found");
        }

        // Finish the project
        company.finish(project);

        // Save the company and project back to their respective repositories
        companyRepository.save(company);
        projectRepository.save(project);
    }

    public void assignWorkerToProject(String companyName, String workerName, String projectName) {
        // Fetch the company, worker and project from the repository
        Company company = companyRepository.findByName(companyName);
        Worker worker = workerRepository.findByName(workerName);
        Project project = projectRepository.findByName(projectName);

        if (company == null || worker == null || project == null) {
            throw new IllegalArgumentException("Company, Worker, or Project not found");
        }

        // Assign the worker to the project
        company.assign(worker, project);

        // Save the company, worker and project back to their respective repositories
        companyRepository.save(company);
        workerRepository.save(worker);
        projectRepository.save(project);
    }

    public void unassignWorkerFromProject(String companyName, String workerName, String projectName) {
        // Fetch the company, worker and project from the repository
        Company company = companyRepository.findByName(companyName);
        Worker worker = workerRepository.findByName(workerName);
        Project project = projectRepository.findByName(projectName);

        if (company == null || worker == null || project == null) {
            throw new IllegalArgumentException("Company, Worker, or Project not found");
        }

        // Unassign the worker from the project
        company.unassign(worker, project);

        // Save the company, worker and project back to their respective repositories
        companyRepository.save(company);
        workerRepository.save(worker);
        projectRepository.save(project);
    }

    public void unassignWorkerFromAllProjects(String companyName, String workerName) {
        // Fetch the company and worker from the repository
        Company company = companyRepository.findByName(companyName);
        Worker worker = workerRepository.findByName(workerName);

        if (company == null || worker == null) {
            throw new IllegalArgumentException("Company or Worker not found");
        }

        // Unassign the worker from all projects
        company.unassignAll(worker);

        // Save the company and worker back to their respective repositories
        companyRepository.save(company);
        workerRepository.save(worker);
    }
}
