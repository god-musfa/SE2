package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.CompanyRatingDTO;
import org.hbrs.se2.project.softwaree.entities.CompanyRating;
import org.hbrs.se2.project.softwaree.entities.CompanyRatingID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRatingRepository extends JpaRepository<CompanyRating, CompanyRatingID> {
    @Query("select new org.hbrs.se2.project.softwaree.dtos.CompanyRatingDTO(r.companyRatingID, r.rating, r.student, r.company) from CompanyRating r where r.student.id = ?1 and r.company.id = ?2")
    Optional<CompanyRatingDTO> findByIDs(Integer student_id, Integer company_id);

    @Query("select r from CompanyRating r where r.companyRatingID = ?1")
    Optional<CompanyRating> getRatingByID(CompanyRatingID companyRatingID);

}