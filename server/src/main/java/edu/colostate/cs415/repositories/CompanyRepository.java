package edu.colostate.cs415.repositories;

import edu.colostate.cs415.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
Company findByName(String name);
}
