package edu.colostate.cs415.controllers;

import edu.colostate.cs415.services.QualificationService;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.model.Qualification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qualifications")
public class QualificationController {

    private final QualificationService qualificationService;

    @GetMapping
    public ResponseEntity<List<QualificationDTO>> getAllProjects() {
        List<QualificationDTO> projects = (qualificationService.getAllProjects());
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }



    @GetMapping("/{id}")
    public Qualification getQualificationById(@PathVariable Long id) {
        return qualificationService.getQualificationById(id);
    }

    @PostMapping("")
    public Qualification createQualification(@RequestBody String description) {
        return qualificationService.addQualification(description);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Qualification> updateQualification(@PathVariable Long id, @RequestBody String newDescription) {
        return qualificationService.updateQualificationDescription(id, newDescription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualification(@PathVariable Long id) {
        return qualificationService.deleteQualification(id);
    }
}
