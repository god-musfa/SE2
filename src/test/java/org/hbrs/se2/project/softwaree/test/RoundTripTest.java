package org.hbrs.se2.project.softwaree.test;

import org.hbrs.se2.project.softwaree.entities.Address;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.entities.User;
import org.hbrs.se2.project.softwaree.repository.AddressRepository;
import org.hbrs.se2.project.softwaree.repository.CompanyRepository;
import org.hbrs.se2.project.softwaree.repository.StudentRepository;
import org.hbrs.se2.project.softwaree.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoundTripTest {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    /**
     * Round Triping Test mit einer einfachen Strecke (C-R-Ass-D).
     * Dieses Muster für Unit-Tests wird in der Vorlesung SE-2 eingeführt (Kapitel 6).
     *
     */
    void createReadAndDeleteAUser() {

        // Schritt 1: C = Create (hier: Erzeugung und Abspeicherung mit der Method save()
        // Anlegen von 2 Users. Eine ID wird automatisch erzeugt durch JPA
        User user1 = new User();
        User user2 = new User();

        // setEmails von Users
        user1.setEmail("test-st@myserver.de");
        user2.setEmail("test-co@myserver.de");
        user1.setPassword("aStrongPassword");
        user2.setPassword("aStrongPassword");
        user1.setProfilePic("aNicePic");
        user2.setProfilePic("aNicePic");


        //User als Student bzw. Company festlegen
        Student student =  new Student();
        Company company =  new Company();
        student.setId(user1.getId());
        company.setId(user2.getId());
        student.setUser(user1);
        company.setUser(user2);

        // setAddress von User1
        Address user1Address = new Address();
        user1Address.setCity("Bonn");
        user1Address.setNumber("189A");
        user1Address.setPostalCode("21651");
        user1Address.setStreet("Musterstrasse");
        user1.setAddress(user1Address);
        user1.setStudent(student);




        // Student-Attribute
        student.setFirstName("Max");
        student.setLastName("Mustermann");
        LocalDate local1 = LocalDate.parse("1997-08-12");
        student.setBirthday(local1);
        student.setSemester(3);


        // Company-Attribute
        company.setField("IT Solutions");
        company.setName("Alpha GmbH");
        company.setSize("L");
        company.setWebsite("www.Alphasolutions.com");
        company.setPhoneNumber("+492215555");


        // und ab auf die DB damit (save!)

        addressRepository.save(user1Address);
        studentRepository.save(student);
        userRepository.save(user1);
        companyRepository.save(company);
        userRepository.save(user2);


        // Da die ID auto-generiert wurde, müssen wir uns die erzeugte ID nach dem Abspeichern merken:
        int idUser1Tmp = user1.getId();
        int idUser2Tmp = user2.getId();
        int idStudentTmp = student.getId();
        int idCompanyTmp = company.getId();
        int idAddress1Tmp = user1Address.getId();

        // Schritt 2: R = Read (hier: Auslesen über die Methode find()
        Optional<User> wrapperUser1 = userRepository.findById(idUser1Tmp);
        Optional<User> wrapperUser2 = userRepository.findById(idUser2Tmp);
        Optional<Student> wrapperStudent = studentRepository.findById(idStudentTmp);
        Optional<Company> wrapperCompany = companyRepository.findById(idCompanyTmp);
        Optional<Address> wrapperAddress = addressRepository.findById(idAddress1Tmp);

        User user1AfterCreate = null;
        User user2AfterCreate = null;
        Student studentAfterCreate = null;
        Company companyAfterCreate = null;
        Address addressAfterCreate = null;

        if (wrapperUser1.isPresent()) {
            user1AfterCreate = wrapperUser1.get();
        }

        if (wrapperUser2.isPresent()) {
            user2AfterCreate = wrapperUser2.get();
        }

        if (wrapperStudent.isPresent()) {
            studentAfterCreate = wrapperStudent.get();
        }

        if (wrapperCompany.isPresent()) {
            companyAfterCreate = wrapperCompany.get();
        }

        if (wrapperAddress.isPresent()) {
            addressAfterCreate = wrapperAddress.get();
        }

        // Schritt 3: Ass = Assertion: Vergleich der vorhandenen Objekte auch Gleichheit...
        assertEquals(user1AfterCreate.getEmail(), "test-st@myserver.de");
        assertEquals(user1AfterCreate.getProfilePic(), "aNicePic");
        assertEquals(user1AfterCreate.getPassword(), "aStrongPassword");
        assertNotSame(user1AfterCreate.getAddress(), user1Address);
        assertEquals(user2AfterCreate.getEmail(), "test-co@myserver.de");
        assertEquals(user2AfterCreate.getProfilePic(), "aNicePic");
        assertEquals(user2AfterCreate.getPassword(), "aStrongPassword");
        assertNotSame(user1, user1AfterCreate);
        assertNotSame(user2, user2AfterCreate);
        assertNotSame(studentAfterCreate.getUser(), user1);
        assertEquals(studentAfterCreate.getLastName(), "Mustermann");
        assertEquals(studentAfterCreate.getFirstName(), "Max");
        assertEquals(studentAfterCreate.getSemester(), 3);
        assertNotSame(student, studentAfterCreate);
        assertNotSame(companyAfterCreate.getUser(), user2);
        assertEquals(companyAfterCreate.getField(), "IT Solutions");
        assertEquals(companyAfterCreate.getName(), "Alpha GmbH");
        assertEquals(companyAfterCreate.getSize(), "L");
        assertNotSame(company, companyAfterCreate);
        assertEquals( addressAfterCreate.getCity() ,"Bonn");
        assertEquals( addressAfterCreate.getNumber() ,"189A");
        assertEquals( addressAfterCreate.getPostalCode() ,"21651");
        assertEquals( addressAfterCreate.getStreet() ,"Musterstrasse");
        assertNotSame( user1Address , addressAfterCreate );




        // Schritt 4: D = Deletion, also Löschen des Users, um Datenmüll zu vermeiden
        studentRepository.deleteById(idStudentTmp);
        companyRepository.deleteById(idCompanyTmp);
        userRepository.deleteById(idUser1Tmp);
        userRepository.deleteById(idUser2Tmp);
        addressRepository.deleteById(idAddress1Tmp);



        // Schritt 4.1: Wir sind vorsichtig und gucken, ob der User wirklich gelöscht wurde ;-)
        Optional<User> wrapperUser1AfterDelete = userRepository.findById(idUser1Tmp);
        Optional<User> wrapperUser2AfterDelete = userRepository.findById(idUser2Tmp);
        Optional<Student> wrapperStudentAfterDelete = studentRepository.findById(idStudentTmp);
        Optional<Company> wrapperCompanyAfterDelete = companyRepository.findById(idCompanyTmp);
        Optional<Address> wrapperAddressAfterDelete = addressRepository.findById(idAddress1Tmp);
        System.out.println("wrapperUser1AfterDelete: " + wrapperUser1AfterDelete + "\n" +
                "wrapperUser2AfterDelete: " + wrapperUser2AfterDelete + "\n" +
                "wrapperStudentAfterDelete: " + wrapperStudentAfterDelete + "\n" +
                "wrapperCompanyAfterDelete: " + wrapperCompanyAfterDelete + "\n" +
                "wrapperAddressAfterDelete: " + wrapperAddressAfterDelete);
        assertFalse(wrapperUser1AfterDelete.isPresent());
        assertFalse(wrapperUser2AfterDelete.isPresent());
        assertFalse(wrapperStudentAfterDelete.isPresent());
        assertFalse(wrapperCompanyAfterDelete.isPresent());
        assertFalse(wrapperAddressAfterDelete.isPresent());
    }

}
