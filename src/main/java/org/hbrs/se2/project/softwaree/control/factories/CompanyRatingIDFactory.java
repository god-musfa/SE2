package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.CompanyRatingIDDTO;
import org.hbrs.se2.project.softwaree.entities.CompanyRatingID;

public class CompanyRatingIDFactory {

    public static CompanyRatingID createRatingID(CompanyRatingIDDTO companyRatingIDDTO) {
        CompanyRatingID newID = new CompanyRatingID();
        newID.setStudent(StudentFactory.createStudent(companyRatingIDDTO.getStudent()));
        newID.setCompany(CompanyFactory.createCompany(companyRatingIDDTO.getCompany()));
        return newID;
    }

    public static CompanyRatingIDDTO createDTO(CompanyRatingID companyRatingID) {
        return new CompanyRatingIDDTO(
                StudentFactory.createDTO(companyRatingID.getStudent()),
                CompanyFactory.createDTO(companyRatingID.getCompany())
        );
    }
}
