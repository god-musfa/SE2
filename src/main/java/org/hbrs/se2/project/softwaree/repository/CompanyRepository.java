package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}