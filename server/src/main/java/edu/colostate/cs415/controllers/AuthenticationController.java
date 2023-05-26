package edu.colostate.cs415.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.repositories.WorkerRepository;

@RestController
public class AuthenticationController {


@Autowired
private BCryptPasswordEncoder passwordEncoder;

@Autowired
private WorkerRepository workerRepository;

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Worker worker) {
    Worker existingWorker = workerRepository.findByUsername(worker.getUsername());
    
    if (existingWorker != null && passwordEncoder.matches(worker.getPassword(), existingWorker.getPassword())) {
        WorkerDTO workerDTO = new WorkerDTO();
        
        // Convert the 'existingWorker' object to a 'workerDTO' object.
        workerDTO.setName(existingWorker.getName());
        workerDTO.setSalary(existingWorker.getSalary());
        workerDTO.setWorkload(existingWorker.getWorkload());

        // Assuming 'getProjects()' returns a List of Project objects, and each Project has a 'name' property
        if (existingWorker.getProjects() != null) {
            String[] projectNames = existingWorker.getProjects().stream()
                .map(project -> project.getName())
                .toArray(String[]::new);
            workerDTO.setProjects(projectNames);
        }

        // Assuming 'getQualifications()' returns a List of Qualification objects, and each Qualification has a 'description' property
        if (existingWorker.getQualifications() != null) {
            String[] qualificationDescriptions = existingWorker.getQualifications().stream()
                .map(qualification -> qualification.getName())
                .toArray(String[]::new);
            workerDTO.setQualifications(qualificationDescriptions);
        }

        return ResponseEntity.ok(workerDTO);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}

    
}
