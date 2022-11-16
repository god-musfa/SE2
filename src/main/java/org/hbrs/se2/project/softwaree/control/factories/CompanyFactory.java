package org.hbrs.se2.project.softwaree.control.factories;
import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.entities.Company;


public class CompanyFactory {
    public static Company createCompany(CompanyDTO companyDTO){
        Company company = new Company();

        company.setId(companyDTO.getId());
        company.setName(companyDTO.getName());
        company.setPhoneNumber(companyDTO.getPhoneNumber());
        company.setWebsite(companyDTO.getWebsite());
        company.setField(companyDTO.getField());
        company.setSize(companyDTO.getSize());
        company.setContactPerson(companyDTO.getContactPerson());

            return company;
    }
}
