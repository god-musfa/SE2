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
        UserDTO userdto = userRepository.findUserByEmail("info@sopra.de");
        AddressDTO addressdto = addressRepository.findAdress(userdto.getAddressId());
        assertEquals(addressdto.getStreet() , con.getAdressFromUser(userdto).getStreet());
        assertEquals(addressdto.getCity() , con.getAdressFromUser(userdto).getCity());
        assertEquals(addressdto.getPostalCode() , con.getAdressFromUser(userdto).getPostalCode());
    }

    @Test
    void getCompanyFromUser() {
        CompanyDTO companyDTO =  companyRepository.findCompany(483);
        UserDTO userDTO = userRepository.findUserByEmail("info@sopra.de");
        assertEquals(companyDTO.getName() , con.getCompanyFromUser(userDTO).getName());
        assertEquals(companyDTO.getContactPerson() , con.getCompanyFromUser(userDTO).getContactPerson());
        assertEquals(companyDTO.getField() , con.getCompanyFromUser(userDTO).getField());
        assertEquals(companyDTO.getId() , con.getCompanyFromUser(userDTO).getId());
    }


    @Test
    void getAvailableSkills() {

        List <SkillDTO> available = con.getAvailableSkills();
        SkillDTO tester = new SkillDTO(42,"Python");
        SkillDTO tester2 = new SkillDTO(38,"Java");
        assertTrue(available.contains(tester));
        assertTrue(available.contains(tester2));

        }


   @Test
    void getStudentSkills() {

        List <SkillDTO> available = con.getStudentSkills(userRepository.findUserByID(500));
        SkillDTO tester = new SkillDTO(39,"Perl");
        SkillDTO tester2 = new SkillDTO(42,"Python");
        assertTrue(available.contains(tester));
        assertTrue(available.contains(tester2));

    }

    @Test
    void createSkillSet() {

        Set<String> stringSet = new HashSet<>();
        assertTrue(stringSet.isEmpty());
        stringSet.add("Java");
        stringSet.add("Python");
        stringSet.add("Clisp");
        Set<Skill> tester = new HashSet<>();
        assertTrue(tester.isEmpty());
        tester   = con.createSkillSet(stringSet);
        assertFalse(tester.isEmpty());



    }

}