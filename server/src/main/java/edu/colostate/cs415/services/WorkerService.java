package edu.colostate.cs415.services;

import edu.colostate.cs415.repositories.WorkerRepository;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.repositories.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private QualificationRepository qualificationRepository;


    public Worker createWorker(String name, String username, Set<Qualification> qualifications, double salary, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        Worker worker = new Worker(name, qualifications, salary);
        worker.setPassword(hashedPassword);
        worker.setUsername(username);
        return workerRepository.save(worker);
    }


    @Transactional(readOnly = true)
    public List<WorkerDTO> getAllWorkers() {
        List<WorkerDTO> workers = workerRepository.findAll().stream()
                                                   .map(Worker::toDTO)
                                                   .collect(Collectors.toList());
        return workers;
    }

    @Transactional(readOnly = true)
    public WorkerDTO getWorker(String name) {
        Worker worker = workerRepository.findByName(name);
        if (worker == null) {
            throw new RuntimeException("Worker not found");
        }
        return worker.toDTO();
    }


    @Transactional
    public void assignQualificationToWorker(Long workerId, Long qualificationId) {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        Qualification qualification = qualificationRepository.findById(qualificationId)
                .orElseThrow(() -> new RuntimeException("Qualification not found"));

        worker.addQualification(qualification);
        qualification.getWorkers().add(worker);
        workerRepository.save(worker);
        qualificationRepository.save(qualification);
    }

    @Transactional
    public void updateSalary(Long workerId, double newSalary) {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));
        worker.setSalary(newSalary);
        workerRepository.save(worker);
    }

    // Add other methods as needed...
}

