package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.RequirementDTO;
import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

    @Query("select r from Requirement r where r.description = ?1")
    Optional<Requirement> findByDescription(String description);

    @Query("select new org.hbrs.se2.project.softwaree.dtos.RequirementDTO(r.description) from Requirement r where r.id = ?1")
    RequirementDTO getDescription(int jobID);

    @Query("select r from Requirement r where r.id = ?1")
    RequirementDTO getFullRequirement(int jobID);
}