package edu.colostate.cs415.controllers;

import edu.colostate.cs415.services.QualificationService;
import edu.colostate.cs415.model.Qualification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qualifications")
public class QualificationController {

    private final QualificationService qualificationService;

    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @GetMapping("")
    public List<Qualification> getAllQualifications() {
        return qualificationService.getAllQualifications();
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
