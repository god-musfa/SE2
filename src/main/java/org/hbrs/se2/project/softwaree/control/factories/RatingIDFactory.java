package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.RatingIDDTO;
import org.hbrs.se2.project.softwaree.entities.RatingID;

public class RatingIDFactory {

    public static RatingID createRatingID(RatingIDDTO ratingIDDTO) {
        RatingID newID = new RatingID();
        newID.setStudent(StudentFactory.createStudent(ratingIDDTO.getStudent()));
        newID.setCompany(CompanyFactory.createCompany(ratingIDDTO.getCompany()));
        return newID;
    }

    public static RatingIDDTO createDTO(RatingID ratingID) {
        return new RatingIDDTO(
                StudentFactory.createDTO(ratingID.getStudent()),
                CompanyFactory.createDTO(ratingID.getCompany())
        );
    }
}
