package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.CompanyRatingDTO;
import org.hbrs.se2.project.softwaree.entities.CompanyRating;



public class CompanyRatingFactory {

    public static CompanyRatingDTO createDTO(CompanyRating companyRating) {
        return new CompanyRatingDTO(
                CompanyRatingIDFactory.createDTO(companyRating.getRatingID()),
                companyRating.getRating(),
                StudentFactory.createDTO(companyRating.getStudent()),
                CompanyFactory.createDTO(companyRating.getCompany())
        );
    }

    public static CompanyRating createRating(CompanyRatingDTO companyRatingDTO) {
        CompanyRating newCompanyRating = new CompanyRating();
        newCompanyRating.setCompany(CompanyFactory.createCompany(companyRatingDTO.getCompany()));
        newCompanyRating.setStudent(StudentFactory.createStudent(companyRatingDTO.getStudent()));
        newCompanyRating.setRatingID(CompanyRatingIDFactory.createRatingID(companyRatingDTO.getRatingID()));
        newCompanyRating.setRating(companyRatingDTO.getRating());
        return newCompanyRating;
    }




}
