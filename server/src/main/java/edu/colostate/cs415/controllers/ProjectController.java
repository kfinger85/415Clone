package edu.colostate.cs415.controllers;

import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.ProjectSize;
import edu.colostate.cs415.model.ProjectStatus;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.repositories.QualificationRepository;
import edu.colostate.cs415.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private QualificationRepository qualificationRepository;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = (projectService.getAllProjects());
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }


    @PostMapping("/{name}")
    public ResponseEntity<?> createProject(@RequestBody ProjectResponseDTO projectRequest) {
        String name = projectRequest.getName();
        Set<Qualification> qualifications = projectRequest.getQualifications();
        ProjectSize size = projectRequest.getSize();
        

        qualifications = qualifications.stream()
        .map(qualification -> {
            Qualification existingQualification = qualificationRepository.findByName(qualification.getName());
            return existingQualification != null ? existingQualification : qualificationRepository.save(qualification);
        })
        .collect(Collectors.toSet());

        try {
            return new ResponseEntity<>(projectService.createProject(name, qualifications, size), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getProject(@PathVariable String name) {
        Project project = projectService.getProject(name);
        if (project != null) {
            return new ResponseEntity<>(project.toDTO(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Project with id " + name + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProject(@RequestBody String name) {
        try {
            projectService.deleteProject(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/assign")
    public ResponseEntity<?> assignWorker(@RequestBody ProjectWorkerDTO projectWorker) {
        String project = projectWorker.getProject();
        String worker = projectWorker.getWorker();
        try {
            return new ResponseEntity<>(projectService.assignWorker(project, worker), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //workerName, projectName

    @PutMapping("/unassign")
    public ResponseEntity<?> removeWorker(@RequestBody ProjectWorkerDTO projectWorker) {
        String project = projectWorker.getProject();
        String worker = projectWorker.getWorker();
        try {
            return new ResponseEntity<>(projectService.unassignWorker(project, worker), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/start")
    public ResponseEntity<?> startProject(@RequestBody ProjectRequest projectRequest) {
        try {
            return new ResponseEntity<>(projectService.start(projectRequest.getName()), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public static class ProjectWorkerDTO {
        private String project;
        private String worker;
    
        public ProjectWorkerDTO(String project, String worker) {
            
            this.project = project;
            this.worker = worker;
        }
        public String getProject() {
            return project;
        }
        public String getWorker() {
            return worker;
        }
    }

    public static class ProjectRequest {
        private String name;
    
        // getters and setters
        public String getName() {
            return this.name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProjectResponseDTO {
        private String name;
        private Set<Qualification> qualifications;
        private ProjectSize size;
    
        // getters and setters
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public Set<Qualification> getQualifications() {
            return qualifications;
        }
    
        public void setQualifications(Set<Qualification> qualifications) {
            this.qualifications = qualifications;
        }
    
        public ProjectSize getSize() {
            return size;
        }
    
        public void setSize(ProjectSize size) {
            this.size = size;
        }
    }
}
