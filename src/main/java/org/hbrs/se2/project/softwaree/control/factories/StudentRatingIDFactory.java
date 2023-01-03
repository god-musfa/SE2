package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.StudentRatingIDDTO;
import org.hbrs.se2.project.softwaree.entities.StudentRatingID;

public class StudentRatingIDFactory {

    public static StudentRatingID createRatingID(StudentRatingIDDTO studentRatingIDDTO) {
        StudentRatingID newID = new StudentRatingID();
        newID.setStudent(StudentFactory.createStudent(studentRatingIDDTO.getStudent()));
        newID.setCompany(CompanyFactory.createCompany(studentRatingIDDTO.getCompany()));
        return newID;
    }

    public static StudentRatingIDDTO createDTO(StudentRatingID studentRatingID) {
        return new StudentRatingIDDTO(
                StudentFactory.createDTO(studentRatingID.getStudent()),
                CompanyFactory.createDTO(studentRatingID.getCompany())
        );
    }
}
