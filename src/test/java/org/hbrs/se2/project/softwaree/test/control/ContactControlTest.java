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
        UserDTO userdto = userRepository.findUserByEmail("test");
        AddressDTO addressdto = addressRepository.findAdress(2);
        assertEquals(addressdto.getStreet(), con.getAddressFromUser(userdto).getStreet());
        assertEquals(addressdto.getCity(), con.getAddressFromUser(userdto).getCity());
        assertEquals(addressdto.getPostalCode(), con.getAddressFromUser(userdto).getPostalCode());

    }

    @Test
    void getCompanyFromCompanyID() {
        CompanyDTO companyDTO = companyRepository.findCompany(1);
        assertEquals(companyDTO.getName(), con.getCompanyFromCompanyID(1).getName());
        assertEquals(companyDTO.getField(), con.getCompanyFromCompanyID(1).getField());
        assertEquals(companyDTO.getContactPerson(), con.getCompanyFromCompanyID(1).getContactPerson());
        assertEquals(companyDTO.getOwJobList(), con.getCompanyFromCompanyID(1).getOwJobList());
    }

    @Test
    void getUserFromCompanyID() {
        UserDTO userDTO = userRepository.findUserByEmail("test");
        assertEquals(userDTO.getUserType(), con.getUserFromCompanyID(1).getUserType());
        assertEquals(userDTO.getEmail(), con.getUserFromCompanyID(1).getEmail());
        assertEquals(userDTO.getAddressId(), con.getUserFromCompanyID(1).getAddressId());

    }

    @Test
    void getStudentFromUser() {
        Optional<Student> studentDTO = studentRepository.findStudentById(188);
        UserDTO userDTO = userRepository.findUserByID(studentDTO.get().getId());
        assertEquals(studentDTO.get().getFirstName(), con.getStudentFromUser(userDTO).getFirstName());
        assertEquals(studentDTO.get().getLastName(), con.getStudentFromUser(userDTO).getLastName());
        assertEquals(studentDTO.get().getDegree(), con.getStudentFromUser(userDTO).getDegree());
        assertEquals(studentDTO.get().getBirthday(), con.getStudentFromUser(userDTO).getBirthday());
        assertEquals(studentDTO.get().getSemester(), con.getStudentFromUser(userDTO).getSemester());
    }

    @Test
    void getJobByID() {

        JobDTO jobDTO = jobRepository.findJobWithTitleDescriptionLocation(3);
        assertEquals(jobDTO.getDescription(), con.getJobByID(jobDTO.getId()).getDescription());
        assertEquals(jobDTO.getTitle(), con.getJobByID(jobDTO.getId()).getTitle());
        assertEquals(jobDTO.getCreationDateAsString(), con.getJobByID(jobDTO.getId()).getCreationDateAsString());
        assertEquals(jobDTO.getId(), con.getJobByID(jobDTO.getId()).getId());


    }

    @Test
    void getFullName() {

        StudentDTO studentDTO = studentRepository.findStudentWithBirthdayAndSemester(188);
        String s1 = studentDTO.getLastName() + ", " + studentDTO.getFirstName();
        String s2 = ContactControl.getFullName(studentDTO);
        assertEquals(s1, s2);

    }

    @Test
    void getFullAddress() {
        AddressDTO addressDTO = addressRepository.findAdress(3);
        String s1 = addressDTO.getStreet() + " " + addressDTO.getNumber() + ", " +
                addressDTO.getPostalCode() + " " + addressDTO.getCity();
        String s2 = ContactControl.getFullAddress(addressDTO);
        assertEquals(s1, s2);


    }

    @Test
    void getBirthdayString() {
        StudentDTO studentDTO = studentRepository.findStudentWithBirthdayAndSemester(188);
        String s1 = "Geboren am: " + "02.03.1989";
        String s2 = ContactControl.getBirthdayString(studentDTO);
        assertEquals(s1, s2);

    }

    @Test
    void getSemesterString() {
        StudentDTO studentDTO = studentRepository.findStudentWithBirthdayAndSemester(188);
        String s1 = "Fachsemester: " + studentDTO.getSemester();
        String s2 = ContactControl.getSemesterString(studentDTO);
        assertEquals(s1, s2);

    }

    @Test
    void getDegreeString() {
        StudentDTO studentDTO = studentRepository.findStudentWithBirthdayAndSemester(188);
        String s1 = "Abschluss: " + studentDTO.getDegree();
        String s2 = ContactControl.getDegreeString(studentDTO);
        assertEquals(s1, s2);
    }

    @Test
    void getUniSubString() {
        StudentDTO studentDTO = studentRepository.findStudentWithBirthdayAndSemester(188);
        String s1 = studentDTO.getUniversity() + ", " + studentDTO.getSubject();
        String s2 = ContactControl.getUniSubString(studentDTO);
        assertEquals(s1, s2);
    }

    @Test
    void getLocationString() {
        JobDTO jobDTO = jobRepository.findJobWithTitleDescriptionLocation(2);
        String s1 =  "Standort: " + jobDTO.getLocation();
        String s2 = ContactControl.getLocationString(jobDTO);
        assertEquals(s1, s2);
    }

}