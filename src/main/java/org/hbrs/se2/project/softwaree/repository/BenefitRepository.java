package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.BenefitDTO;
import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BenefitRepository extends JpaRepository<Benefit, Integer> {

    @Query("select b from Benefit b where b.description = ?1")
    Optional<Benefit> findByDescription(String description);

    @Query("select new org.hbrs.se2.project.softwaree.dtos.BenefitDTO(b.description) from Benefit b where b.id = ?1")
    BenefitDTO getDescription(int jobID);

    @Query("select b from Benefit b where b.id = ?1")
    BenefitDTO getFullBenefit(int jobID);
}
