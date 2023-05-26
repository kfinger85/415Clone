package edu.colostate.cs415.repositories;

import edu.colostate.cs415.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    Qualification findByName(String name);
}