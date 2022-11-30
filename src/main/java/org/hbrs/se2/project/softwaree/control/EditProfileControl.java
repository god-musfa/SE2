package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.control.factories.AddressFactory;
import org.hbrs.se2.project.softwaree.control.factories.CompanyFactory;
import org.hbrs.se2.project.softwaree.control.factories.StudentFactory;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.Address;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.repository.AddressRepository;
import org.hbrs.se2.project.softwaree.repository.CompanyRepository;
import org.hbrs.se2.project.softwaree.repository.SkillRepository;
import org.hbrs.se2.project.softwaree.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditProfileControl {
    @Autowired
    StudentRepository repo;

    @Autowired
    SkillRepository skillRepo;

    public StudentDTO getStudentFromUser(UserDTO userDTO) {
        return repo.findStudent(userDTO.getId());
    }

    @Autowired
    AddressRepository repoA;
    public AddressDTO getAdressFromUser(UserDTO userDTO){
        return repoA.findAdress(userDTO.getAddressId());
    }

    @Autowired
    CompanyRepository repoC;
    public CompanyDTO getCompanyFromUser(UserDTO userDTO) {
        return repoC.findCompany(userDTO.getId());
    }

    public void createStudent(StudentDTO studentDTO){
        Student studentEntity = StudentFactory.createStudent(studentDTO);
        repo.save(studentEntity);
    }

    public void createAddress(AddressDTO addressDTO, UserDTO userDTO){
        Address addressEntity = AddressFactory.createAddress(addressDTO);
        repoA.save(addressEntity);
    }

    public void createCompany(CompanyDTO companyDTO){
        Company companyEntity = CompanyFactory.createCompany(companyDTO);
        repoC.save(companyEntity);
    }

    public List<SkillDTO> getAvailableSkills() {
        return skillRepo.getAvailable();
    }

}
