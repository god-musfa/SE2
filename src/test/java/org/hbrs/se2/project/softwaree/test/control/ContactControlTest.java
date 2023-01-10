package org.hbrs.se2.project.softwaree.test.control;

import org.hbrs.se2.project.softwaree.control.ContactControl;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactControlTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private MessageRepository MessageRepository;
    @Autowired
    ContactControl con = new ContactControl();
    @Autowired
    JobRepository jobRepository;


    @Test
    void getAddressFromUser() {
        UserDTO userdto = userRepository.findUserByEmail("info@sopra.de");
        AddressDTO addressdto = addressRepository.findAdress(275);
        assertEquals(addressdto.getStreet(), con.getAddressFromUser(userdto).getStreet());
        assertEquals(addressdto.getCity(), con.getAddressFromUser(userdto).getCity());
        assertEquals(addressdto.getPostalCode(), con.getAddressFromUser(userdto).getPostalCode());

    }

    @Test
    void getCompanyFromCompanyID() {
        CompanyDTO companyDTO = companyRepository.findCompany(483);
        assertEquals(companyDTO.getName(), con.getCompanyFromCompanyID(483).getName());
        assertEquals(companyDTO.getField(), con.getCompanyFromCompanyID(483).getField());
        assertEquals(companyDTO.getContactPerson(), con.getCompanyFromCompanyID(483).getContactPerson());
        assertEquals(companyDTO.getOwJobList(), con.getCompanyFromCompanyID(483).getOwJobList());
    }

    @Test
    void getUserFromCompanyID() {
        UserDTO userDTO = userRepository.findUserByEmail("info@sopra.de");
        assertEquals(userDTO.getUserType(), con.getUserFromCompanyID(483).getUserType());
        assertEquals(userDTO.getEmail(), con.getUserFromCompanyID(483).getEmail());
        assertEquals(userDTO.getAddressId(), con.getUserFromCompanyID(483).getAddressId());

    }

    @Test
    void getStudentFromUser() {
        Optional<Student> studentDTO = studentRepository.findStudentById(500);
        UserDTO userDTO = userRepository.findUserByID(studentDTO.get().getId());
        assertEquals(studentDTO.get().getFirstName(), con.getStudentFromUser(userDTO).getFirstName());
        assertEquals(studentDTO.get().getLastName(), con.getStudentFromUser(userDTO).getLastName());
        assertEquals(studentDTO.get().getDegree(), con.getStudentFromUser(userDTO).getDegree());
        assertEquals(studentDTO.get().getBirthday(), con.getStudentFromUser(userDTO).getBirthday());
        assertEquals(studentDTO.get().getSemester(), con.getStudentFromUser(userDTO).getSemester());
    }

    @Test
    void getJobByID() {

        JobDTO jobDTO = jobRepository.findJobWithTitleDescriptionLocation(498);
        assertEquals(jobDTO.getDescription(), con.getJobByID(jobDTO.getId()).getDescription());
        assertEquals(jobDTO.getTitle(), con.getJobByID(jobDTO.getId()).getTitle());
        assertEquals(jobDTO.getCreationDateAsString(), con.getJobByID(jobDTO.getId()).getCreationDateAsString());
        assertEquals(jobDTO.getId(), con.getJobByID(jobDTO.getId()).getId());


    }

    @Test
    void getFullName() {

        StudentDTO studentDTO = studentRepository.findStudentWithBirthdayAndSemester(500);
        String s1 = studentDTO.getLastName() + ", " + studentDTO.getFirstName();
        String s2 = ContactControl.getFullName(studentDTO);
        assertEquals(s1, s2);

    }

    @Test
    void getFullAddress() {
        AddressDTO addressDTO = addressRepository.findAdress(275);
        String s1 = addressDTO.getStreet() + " " + addressDTO.getNumber() + ", " +
                addressDTO.getPostalCode() + " " + addressDTO.getCity();
        String s2 = ContactControl.getFullAddress(addressDTO);
        assertEquals(s1, s2);


    }

    @Test
    void getBirthdayString() {
        StudentDTO studentDTO = studentRepository.findStudentWithBirthdayAndSemester(500);
        String s1 = "Geboren am: " + "15.10.1998";
        String s2 = ContactControl.getBirthdayString(studentDTO);
        assertEquals(s1, s2);

    }

    @Test
    void getSemesterString() {
        StudentDTO studentDTO = studentRepository.findStudentWithBirthdayAndSemester(500);
        String s1 = "Fachsemester: " + studentDTO.getSemester();
        String s2 = ContactControl.getSemesterString(studentDTO);
        assertEquals(s1, s2);

    }

    @Test
    void getDegreeString() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setDegree("Master");
        String s1 = "Abschluss: " + "Master";
        String s2 = ContactControl.getDegreeString(studentDTO);
        assertEquals(s1, s2);
    }

    @Test
    void getUniSubString() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setUniversity("HBRS");
        studentDTO.setSubject("Mathe");
        String s1 = studentDTO.getUniversity() + ", " + studentDTO.getSubject();
        String s2 = ContactControl.getUniSubString(studentDTO);
        assertEquals(s1, s2);
    }

    @Test
    void getLocationString() {
        JobDTO jobDTO = jobRepository.findJobWithTitleDescriptionLocation(498);
        String s1 =  "Standort: " + jobDTO.getLocation();
        String s2 = ContactControl.getLocationString(jobDTO);
        assertEquals(s1, s2);
    }

}