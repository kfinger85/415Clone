package edu.colostate.cs415.controllers;

import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.ProjectSize;
import edu.colostate.cs415.model.ProjectStatus;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestParam String name, @RequestBody Set<Qualification> qualifications, @RequestParam ProjectSize size) {
        try {
            return new ResponseEntity<>(projectService.createProject(name, qualifications, size), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) {
        Optional<Project> project = projectService.getProject(id);
        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Project with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestParam String name, @RequestBody Set<Qualification> qualifications, @RequestParam ProjectSize size, @RequestParam ProjectStatus status) {
        try {
            return new ResponseEntity<>(projectService.updateProject(id, name, qualifications, size, status), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/assignWorker/{projectId}")
    public ResponseEntity<?> assignWorker(@PathVariable Long projectId, @RequestBody Worker worker) {
        try {
            return new ResponseEntity<>(projectService.assignWorker(projectId, worker), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeWorker/{projectId}")
    public ResponseEntity<?> removeWorker(@PathVariable Long projectId, @RequestBody Worker worker) {
        try {
            return new ResponseEntity<>(projectService.removeWorker(projectId, worker), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
