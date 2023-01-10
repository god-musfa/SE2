package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.StudentRatingDTO;
import org.hbrs.se2.project.softwaree.entities.StudentRating;


public class StudentRatingFactory {

    public static StudentRatingDTO createDTO(StudentRating studentRating) {
        return new StudentRatingDTO(
                StudentRatingIDFactory.createDTO(studentRating.getRatingID()),
                studentRating.getRating(),
                StudentFactory.createDTO(studentRating.getStudent()),
                CompanyFactory.createDTO(studentRating.getCompany())
        );
    }

    public static StudentRating createRating(StudentRatingDTO studentRatingDTO) {
        StudentRating newStudentRating = new StudentRating();
        newStudentRating.setCompany(CompanyFactory.createCompany(studentRatingDTO.getCompany()));
        newStudentRating.setStudent(StudentFactory.createStudent(studentRatingDTO.getStudent()));
        newStudentRating.setRatingID(StudentRatingIDFactory.createRatingID(studentRatingDTO.getRatingID()));
        newStudentRating.setRating(studentRatingDTO.getRating());
        return newStudentRating;
    }




}
