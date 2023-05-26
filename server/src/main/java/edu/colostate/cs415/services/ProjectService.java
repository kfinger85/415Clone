package edu.colostate.cs415.services;

import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.ProjectSize;
import edu.colostate.cs415.model.ProjectStatus;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.repositories.CompanyRepository;
import edu.colostate.cs415.repositories.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;


    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    private CompanyRepository companyRepository;
    

    @Transactional(readOnly = true)
    public List<ProjectDTO> getAllProjects() {
        List<ProjectDTO> projects = projectRepository.findAll().stream()
                                                   .map(Project::toDTO)
                                                   .collect(Collectors.toList());
        return projects;
    }

    public ProjectDTO createProject(String name, Set<Qualification> qualifications, ProjectSize size) {
        Project project = new Project(name, qualifications, size);
        projectRepository.save(project); 
        return project.toDTO();
    }

    public Project getProject(String name) {
        Project project = projectRepository.findByName(name);
        return project;
    }


    public void deleteProject(String name) {
        Project project = projectRepository.findByName(name);
        if(project == null)
            throw new IllegalArgumentException("Project with name " + name + " not found");
        unassignAllWorkers(name);
        projectRepository.deleteById(project.getId());
    }

    public ProjectDTO assignWorker(String projectName, String workerName) {
        Project project = getProject(projectName);
        Company company = companyRepository.findByName(project.getCompany().getName());  
        if(company == null)
            throw new IllegalArgumentException("Company with name " + project.getCompany().getName() + " not found");
        
        Optional<Worker> workerToAddOpt = company.getEmployedWorkers().stream()
        .filter(worker -> worker.getName().equals(workerName))
        .findFirst();
        
        if (!workerToAddOpt.isPresent()) {
            throw new IllegalArgumentException("Worker with name " + workerName + " not found");
        }
        
        Worker workerToAdd = workerToAddOpt.get();
        project.addWorker(workerToAdd);
        workerToAdd.addProject(project);
        projectRepository.save(project);
        return project.toDTO();
    }

    public ProjectDTO unassignAllWorkers(String name) {
        Project project = getProject(name)
        .orElseThrow((Supplier<RuntimeException>) () -> new IllegalArgumentException("Project with id " + name + " not found"));
        project.removeAllWorkers();
        projectRepository.save(project);
        return project.toDTO();
    }

    public ProjectDTO unassignWorker(String projectName, String workerName) {
        Project project = getProject(projectName);
        if (project == null) {
            throw new IllegalArgumentException("Project with name " + projectName + " not found");
        }
        
        Worker workerToRemove = null;
        for (Worker worker : project.getWorkers()) {
            if (worker.getName().equals(workerName)) {
                workerToRemove = worker;
                break;
            }
        }
        if (workerToRemove == null) {
            throw new IllegalArgumentException("Worker with name " + workerName + " not found in project");
        }
    
        project.removeWorker(workerToRemove);
        workerToRemove.removeProject(project);
        projectRepository.save(project);
        return project.toDTO();
        
    }

    public ProjectDTO start(String name) {
        Project project = getProject(name);
        if (project == null) {
            throw new IllegalArgumentException("Project with name " + name + " not found");
        }
        project.setStatus(ProjectStatus.ACTIVE);
        projectRepository.save(project);
        return project.toDTO();
    }

    public ProjectDTO finish(String name) {
        Project project = getProject(name);
        if (project == null) {
            throw new IllegalArgumentException("Project with name " + name + " not found");
        }
        project.setStatus(ProjectStatus.FINISHED);
        projectRepository.save(project);
        return project.toDTO();
    }
    
}
