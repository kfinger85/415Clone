package edu.colostate.cs415.repositories;

import edu.colostate.cs415.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
Company findByName(String name);
}
