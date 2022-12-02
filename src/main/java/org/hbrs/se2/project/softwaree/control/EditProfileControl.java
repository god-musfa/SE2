package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.control.factories.AddressFactory;
import org.hbrs.se2.project.softwaree.control.factories.CompanyFactory;
import org.hbrs.se2.project.softwaree.control.factories.SkillFactory;
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
import org.hbrs.se2.project.softwaree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class EditProfileControl {
    @Autowired
    StudentRepository repo;
    @Autowired
    UserRepository repoU;

    @Autowired
    SkillRepository skillRepo;

    public StudentDTO getStudentFromUser(UserDTO userDTO) {
        Optional<Student> studentFromDB = repo.findStudentById(userDTO.getId());
        if (studentFromDB.isPresent()) {
            return StudentFactory.createDTO(studentFromDB.get());
        } else {
            return null;
        }
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
        return skillRepo.findAll().stream()
                .map(SkillFactory::getDTO)
                .collect(Collectors.toList());
    }

    public List<SkillDTO> getStudentSkills(UserDTO userDTO) {
        Optional<Student> targetStudent = repo.findStudentById(userDTO.getId());
        if (targetStudent.isPresent()) {
            return targetStudent.get().getSkills().stream()
                    .map(SkillFactory::getDTO)
                    .collect(Collectors.toList());
        } else {
            System.out.println(String.format("Cannot get skills for user with id %d", userDTO.getId()));
            return new ArrayList<>();       // Return empty list, if user cannot be found.
        }
    }

    public Set<Skill> createSkillSet(Set<String> skillNames) {
        Set<Skill> returnSet = new HashSet<>();

        for (String skillName : skillNames) {
            Optional<Skill> skillFromDB = skillRepo.findByDescription(skillName);
            if (skillFromDB.isPresent()) {
                // Take Skill from DB and add to list:
                returnSet.add(skillFromDB.get());
            } else {
                // Create empty skill and add description from combobox:
                Skill newSkill = new Skill();
                newSkill.setDescription(skillName);
                returnSet.add(newSkill);
            }
        }

        return returnSet;
    }

    public void saveSkill(Skill skill) {
        skillRepo.save(skill);
    }
    @Transactional
    public void deleteAccount(UserDTO user){
        repoU.deleteUser(user.getId());
        repoA.deleteById(user.getAddressId());
        System.out.println("Account Deleted");
    }
}
