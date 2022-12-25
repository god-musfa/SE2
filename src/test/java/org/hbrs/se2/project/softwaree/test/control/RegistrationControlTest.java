package org.hbrs.se2.project.softwaree.test.control;

import org.hbrs.se2.project.softwaree.control.RegistrationControl;
import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.repository.AddressRepository;
import org.hbrs.se2.project.softwaree.repository.CompanyRepository;
import org.hbrs.se2.project.softwaree.repository.StudentRepository;
import org.hbrs.se2.project.softwaree.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationControlTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    RegistrationControl registrationControl;

    @Test
    void save() {

        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("UniquePassword");
        userDTO.setEmail("Studentsave@test.de");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("Test");
        addressDTO.setNumber("a");
        addressDTO.setCity("Tester");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("teststudent");

        assertTrue(registrationControl.save(userDTO,studentDTO,addressDTO));
        assertFalse(registrationControl.save(userDTO,studentDTO,addressDTO));

        int userid = userRepository.findUserByEmail("Studentsave@test.de").getId();
        int addressid = userRepository.findUserByEmail("Studentsave@test.de").getAddressId();

        studentRepository.deleteById(userid);
        userRepository.deleteById(userid);
        addressRepository.deleteById(addressid);







    }

    @Test
    void saveC() {

        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("UniquePassword");
        userDTO.setEmail("companysave@test.de");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("Test");
        addressDTO.setNumber("b");
        addressDTO.setCity("Tester");

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName("Tester Ltd");

        assertTrue(registrationControl.saveC(userDTO,companyDTO,addressDTO));
        assertFalse(registrationControl.saveC(userDTO,companyDTO,addressDTO));

        int userid = userRepository.findUserByEmail("companysave@test.de").getId();
        int addressid = userRepository.findUserByEmail("companysave@test.de").getAddressId();

        companyRepository.deleteById(userid);
        userRepository.deleteById(userid);
        addressRepository.deleteById(addressid);

    }

    @Test
    void emailExists() {

        assertTrue(registrationControl.emailExists("sascha"));
        assertFalse(registrationControl.emailExists("Sascha"));
    }
}