package org.hbrs.se2.project.softwaree.test.control;

import org.hbrs.se2.project.softwaree.control.EditProfileControl;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EditProfileControlTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EditProfileControl con ;

    @Test
    void getStudentFromUser() {
        Optional<Student> studentDTO = studentRepository.findStudentById(188);
        UserDTO userDTO = userRepository.findUserByID(studentDTO.get().getId());
        assertEquals(studentDTO.get().getSemester() , con.getStudentFromUser(userDTO).getSemester());
        assertEquals(studentDTO.get().getLastName() , con.getStudentFromUser(userDTO).getLastName());
        assertEquals(studentDTO.get().getBirthday() , con.getStudentFromUser(userDTO).getBirthday());
        assertEquals(studentDTO.get().getId() , con.getStudentFromUser(userDTO).getId());
    }

    @Test
    void getAdressFromUser() {
        UserDTO userdto = userRepository.findUserByEmail("test");
        AddressDTO addressdto = addressRepository.findAdress(userdto.getAddressId());
        assertEquals(addressdto.getStreet() , con.getAdressFromUser(userdto).getStreet());
        assertEquals(addressdto.getCity() , con.getAdressFromUser(userdto).getCity());
        assertEquals(addressdto.getPostalCode() , con.getAdressFromUser(userdto).getPostalCode());
    }

    @Test
    void getCompanyFromUser() {
        CompanyDTO companyDTO =  companyRepository.findCompany(1);
        UserDTO userDTO = userRepository.findUserByEmail("test");
        assertEquals(companyDTO.getName() , con.getCompanyFromUser(userDTO).getName());
        assertEquals(companyDTO.getContactPerson() , con.getCompanyFromUser(userDTO).getContactPerson());
        assertEquals(companyDTO.getField() , con.getCompanyFromUser(userDTO).getField());
        assertEquals(companyDTO.getId() , con.getCompanyFromUser(userDTO).getId());
    }


    @Test
    void getAvailableSkills() {

        List <SkillDTO> available = con.getAvailableSkills();
        SkillDTO tester = new SkillDTO(3,"Python");
        SkillDTO tester2 = new SkillDTO(7,"Docker");
        assertTrue(available.contains(tester));
        assertTrue(available.contains(tester2));

        }


    @Test
    void getStudentSkills() {

        List <SkillDTO> available = con.getStudentSkills(userRepository.findUserByID(188));
        SkillDTO tester = new SkillDTO(6,"Englisch (A1)");
        SkillDTO tester2 = new SkillDTO(8,"Microsoft Active Directory");
        assertTrue(available.contains(tester));
        assertTrue(available.contains(tester2));

    }

   /* @Test
    void createSkillSet() {

        Skill skill1 = skillRepository.getOne(3);
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Python");
        set.add("Clisp");
        Set<Skill> tester  = con.createSkillSet(set);
        assertTrue(tester.contains(skill1));

    }*/

    @Test
    void saveSkill() {
        Skill test = new Skill();
        test.setDescription("TestSkill");
        con.saveSkill(test);
        assertEquals("TestSkill" , skillRepository.findByDescription("TestSkill").get().getDescription());
        skillRepository.deleteById(skillRepository.findByDescription("TestSkill").get().getId());


    }

}