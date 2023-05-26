package edu.colostate.cs415.controllers;

import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;
    
    @GetMapping
    public ResponseEntity<List<WorkerDTO>> getAllWorkers() {
        List<WorkerDTO> workers = workerService.getAllWorkers();
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getWorker(@PathVariable String name) {
        System.out.println("name: " + name);
        try {
            return new ResponseEntity<>(workerService.getWorker(name),HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createWorker")
    public ResponseEntity<?> createWorker(@RequestParam String name, @RequestParam String username, @RequestBody Set<Qualification> qualifications, @RequestParam double salary, @RequestParam String password) {
        System.out.println("name: " + name);
        try {
            return new ResponseEntity<>(workerService.createWorker(name, username, qualifications, salary, password), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/assignQualification/{workerId}/{qualificationId}")
    public ResponseEntity<?> assignQualificationToWorker(@PathVariable Long workerId, @PathVariable Long qualificationId) {
        try {
            workerService.assignQualificationToWorker(workerId, qualificationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateSalary/{workerId}")
    public ResponseEntity<?> updateSalary(@PathVariable Long workerId, @RequestParam double newSalary) {
        try {
            workerService.updateSalary(workerId, newSalary);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // Add other endpoints as needed...
}
