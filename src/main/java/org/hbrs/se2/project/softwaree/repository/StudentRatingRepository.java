package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.StudentRatingDTO;
import org.hbrs.se2.project.softwaree.entities.StudentRating;
import org.hbrs.se2.project.softwaree.entities.StudentRatingID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRatingRepository extends JpaRepository<StudentRating, StudentRatingID> {
    @Query("select new org.hbrs.se2.project.softwaree.dtos.StudentRatingDTO(r.studentRatingID, r.rating, r.student, r.company) from Rating r where r.student.id = ?1 and r.company.id = ?2")
    Optional<StudentRatingDTO> findByIDs(Integer student_id, Integer company_id);

    @Query("select r from Rating r where r.studentRatingID = ?1")
    Optional<StudentRating> getRatingByID(StudentRatingID studentRatingID);

}