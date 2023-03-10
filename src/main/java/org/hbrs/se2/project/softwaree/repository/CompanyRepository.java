package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.CompanyDTO(c.id,c.name,c.phoneNumber,c.website,c.field,c.size,c.contactPerson) FROM Company c WHERE id = ?1")
    CompanyDTO findCompany(Integer id);

    @Query("select c from Company c where c.id = ?1")
    Optional<CompanyDTO> getCompanyDTO(Integer id);

    @Query("SELECT new org.hbrs.se2.project.softwaree.dtos.CompanyDTO(c.id,c.name,c.phoneNumber,c.website,c.field,c.size,c.contactPerson) from Company c where c.id = ?1")
    Optional<CompanyDTO> getCompanyDTOByID(Integer id);

}