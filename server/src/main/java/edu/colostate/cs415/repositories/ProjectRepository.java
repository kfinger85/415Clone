package edu.colostate.cs415.repositories;

import edu.colostate.cs415.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    public Project findByName(String name);
}
