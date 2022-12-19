package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.RatingDTO;
import org.hbrs.se2.project.softwaree.dtos.RatingIDDTO;
import org.hbrs.se2.project.softwaree.entities.Rating;
import org.hbrs.se2.project.softwaree.entities.RatingID;

public class RatingFactory {

    public static RatingDTO createDTO(Rating rating) {
        return new RatingDTO(
                RatingIDFactory.createDTO(rating.getRatingID()),
                rating.getRating(),
                StudentFactory.createDTO(rating.getStudent()),
                CompanyFactory.createDTO(rating.getCompany())
        );
    }

    public static Rating createRating(RatingDTO ratingDTO) {
        Rating newRating = new Rating();
        newRating.setCompany(CompanyFactory.createCompany(ratingDTO.getCompany()));
        newRating.setStudent(StudentFactory.createStudent(ratingDTO.getStudent()));
        newRating.setRatingID(newRating.getRatingID());
        return newRating;
    }




}
