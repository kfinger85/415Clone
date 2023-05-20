package edu.colostate.cs415.services;

import edu.colostate.cs415.repositories.WorkerRepository;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.repositories.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Transactional
    public Worker createWorker(String name, Set<Qualification> qualifications, double salary) {
        Worker worker = new Worker(name, qualifications, salary);
        return workerRepository.save(worker);
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

