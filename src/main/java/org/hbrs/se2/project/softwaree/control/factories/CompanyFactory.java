package org.hbrs.se2.project.softwaree.control.factories;
import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class CompanyFactory {
    @Autowired
    private CompanyRepository companyRepository;

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

    public static CompanyDTO createDTO(Company company) {
        return new CompanyDTO(
                company.getId(),
                company.getName(),
                company.getPhoneNumber(),
                company.getWebsite(),
                company.getField(),
                company.getSize(),
                company.getContactPerson()
        );
    }

}
