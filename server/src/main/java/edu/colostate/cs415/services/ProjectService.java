package edu.colostate.cs415.services;

import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.ProjectSize;
import edu.colostate.cs415.model.ProjectStatus;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(String name, Set<Qualification> qualifications, ProjectSize size) {
        Project project = new Project(name, qualifications, size);
        return projectRepository.save(project);
    }

    public Optional<Project> getProject(Long id) {
        return projectRepository.findById(id);
    }

    public Project updateProject(Long id, String name, Set<Qualification> qualifications, ProjectSize size,
            ProjectStatus status) {
        Project project = getProject(id)
                .orElseThrow(() -> new IllegalArgumentException("Project with id " + id + " not found"));
        project.setName(name);
        project.setQualifications(qualifications);
        project.setSize(size);
        project.setStatus(status);
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Project with id " + id + " not found");
        }
    }

    public Project assignWorker(Long projectId, Worker worker) {
        Project project = getProject(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with id " + projectId + " not found"));
        project.addWorker(worker);
        return projectRepository.save(project);
    }

    public Project removeWorker(Long projectId, Worker worker) {
        Project project = getProject(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with id " + projectId + " not found"));
        project.removeWorker(worker);
        return projectRepository.save(project);
    }
}
