package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.RatingDTO;
import org.hbrs.se2.project.softwaree.entities.Rating;
import org.hbrs.se2.project.softwaree.entities.RatingID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, RatingID> {
    @Query("select new org.hbrs.se2.project.softwaree.dtos.RatingDTO(r.ratingID, r.rating, r.student, r.company) from Rating r where r.student.id = ?1 and r.company.id = ?2")
    Optional<RatingDTO> findByIDs(Integer student_id, Integer company_id);

    @Query("select r from Rating r where r.ratingID = ?1")
    Optional<Rating> getRatingByID(RatingID ratingID);

}