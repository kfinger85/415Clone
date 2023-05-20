package edu.colostate.cs415.services;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Worker;

import edu.colostate.cs415.repositories.QualificationRepository;
import edu.colostate.cs415.repositories.WorkerRepository;

@Service
public class QualificationService {
    
    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Transactional(readOnly = true)
    public Qualification getQualificationById(Long qualificationId) {
        return qualificationRepository.findById(qualificationId)
                .orElseThrow(() -> new RuntimeException("Qualification not found"));
    }

    @Transactional
    public ResponseEntity<Qualification> updateQualificationDescription(Long qualificationId, String newDescription) {
        Qualification qualification = qualificationRepository.findById(qualificationId)
                .orElseThrow(() -> new RuntimeException("Qualification not found"));
        qualification.setDescription(newDescription);
        qualificationRepository.save(qualification);
        return ResponseEntity.ok(qualification);
    }

    @Transactional
    public void assignWorkerToQualification(Long qualificationId, Long workerId) {
        Qualification qualification = qualificationRepository.findById(qualificationId)
                .orElseThrow(() -> new RuntimeException("Qualification not found"));

        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        qualification.addWorker(worker);
        worker.getQualifications().add(qualification);
        qualificationRepository.save(qualification);
        workerRepository.save(worker);
    }

    @Transactional
    public void removeWorkerFromQualification(Long qualificationId, Long workerId) {
        Qualification qualification = qualificationRepository.findById(qualificationId)
                .orElseThrow(() -> new RuntimeException("Qualification not found"));

        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        qualification.removeWorker(worker);
        worker.getQualifications().remove(qualification);
        qualificationRepository.save(qualification);
        workerRepository.save(worker);
    }

    @Transactional
    public Qualification addQualification(String description) {
        Qualification qualification = new Qualification(description);
        return qualificationRepository.save(qualification);
    }

    @Transactional
    public ResponseEntity<Void> deleteQualification(Long qualificationId) {
        qualificationRepository.deleteById(qualificationId);
        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    public List<Qualification> getAllQualifications() {
        return qualificationRepository.findAll();
    }




}

