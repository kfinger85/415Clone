package edu.colostate.cs415.repositories;
import edu.colostate.cs415.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    public Worker findByName(String name);
}


