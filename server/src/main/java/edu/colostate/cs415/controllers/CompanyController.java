package edu.colostate.cs415.controllers;

import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.ProjectSize;
import edu.colostate.cs415.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/createWorker")
    public ResponseEntity<?> createWorker(@RequestParam String companyName, @RequestParam String name, @RequestBody Set<Qualification> qualifications, @RequestParam double salary) {
        try {
            return new ResponseEntity<>(companyService.createWorker(companyName, name, qualifications, salary), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createQualification")
    public ResponseEntity<?> createQualification(@RequestParam String companyName, @RequestParam String description) {
        try {
            return new ResponseEntity<>(companyService.createQualification(companyName, description), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createProject")
    public ResponseEntity<?> createProject(@RequestParam String companyName, @RequestParam String name, @RequestBody Set<Qualification> qualifications, @RequestParam ProjectSize size) {
        try {
            return new ResponseEntity<>(companyService.createProject(companyName, name, qualifications, size), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/startProject")
    public ResponseEntity<?> startProject(@RequestParam String companyName, @RequestParam String projectName) {
        try {
            companyService.startProject(companyName, projectName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/finishProject")
    public ResponseEntity<?> finishProject(@RequestParam String companyName, @RequestParam String projectName) {
        try {
            companyService.finishProject(companyName, projectName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/assignWorker")
    public ResponseEntity<?> assignWorkerToProject(@RequestParam String companyName, @RequestParam String workerName, @RequestParam String projectName) {
        try {
            companyService.assignWorkerToProject(companyName, workerName, projectName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/unassignWorker")
    public ResponseEntity<?> unassignWorkerFromProject(@RequestParam String companyName, @RequestParam String workerName, @RequestParam String projectName) {
        try {
            companyService.unassignWorkerFromProject(companyName, workerName, projectName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/unassignWorkerFromAllProjects")
    public ResponseEntity<?> unassignWorkerFromAllProjects(@RequestParam String companyName, @RequestParam String workerName) {
        try {
            companyService.unassignWorkerFromAllProjects(companyName, workerName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
