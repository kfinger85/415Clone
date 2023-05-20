package edu.colostate.cs415.controllers;

import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping("/create")
    public ResponseEntity<?> createWorker(@RequestParam String name, @RequestBody Set<Qualification> qualifications, @RequestParam double salary) {
        try {
            return new ResponseEntity<>(workerService.createWorker(name, qualifications, salary), HttpStatus.CREATED);
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
