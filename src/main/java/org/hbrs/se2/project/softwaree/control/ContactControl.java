package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.control.factories.MessageFactory;
import org.hbrs.se2.project.softwaree.control.factories.StudentFactory;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.Message;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Component
public class ContactControl {
    @Autowired
    AddressRepository repoAddress;
    public AddressDTO getAddressFromUser(UserDTO user) {
        return repoAddress.findAdress(user.getAddressId());
    }

    @Autowired
    CompanyRepository repoCompany;
    public CompanyDTO getCompanyFromCompanyID(int companyid) {
        return repoCompany.findCompany(companyid);
    }

    @Autowired
    UserRepository repoUser;
    public UserDTO getUserFromCompanyID(int companyid) {
        return repoUser.findUserByID(companyid);
    }

    @Autowired
    StudentRepository repoStudent;
    public StudentDTO getStudentFromUser(UserDTO user) {
        Optional<Student> studentFromDB = repoStudent.findStudentById(user.getId());
        return studentFromDB.isPresent() ? StudentFactory.createDTO(studentFromDB.get()) : null;
    }

    @Autowired
    JobRepository repoJob;
    public JobDTO getJobByID(int jobID) {
        return repoJob.findJobWithTitleDescriptionLocation(jobID);
    }

    public static String getFullName(StudentDTO student) {
        return ((student.getLastName() != null) ? student.getLastName() + ", " : "")
                + ((student.getFirstName() != null) ? student.getFirstName() : "");
    }

    public static String getFullAddress(AddressDTO address) {
        return ((address.getStreet() != null) ? address.getStreet() + " " : "")
                + ((address.getNumber() != null) ? address.getNumber() + ", " : "")
                + ((address.getPostalCode() != null) ? address.getPostalCode() + " " : "")
                + ((address.getCity() != null) ? address.getCity() : "");
    }

    public static String getBirthdayString(StudentDTO student) {
        return (student.getBirthday() != null) ? "Geboren am: " + student.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.uuuu")) : "";
    }

    public static String getSemesterString(StudentDTO student) {
        return (student.getSemester() != null) ? "Fachsemester: " + student.getSemester() : "";
    }

    public static String getDegreeString(StudentDTO student) {
        return ((student.getDegree() != null) ? "Abschluss: " + student.getDegree() : "");
    }

    public static String getUniSubString(StudentDTO student) {
        return ((student.getUniversity() != null) ? student.getUniversity() + ", " : "")
                + ((student.getSubject() != null) ? student.getSubject() : "");
    }

    public static String getLocationString(JobDTO job) {
        return (job.getLocation() != null) ? "Standort: " + job.getLocation() : "";
    }

    @Autowired
    private MessageRepository repoMessage;
    public void createContact(int studentid, int companyid, int jobid, MessageDTO message) {
        Message messageEntity = MessageFactory.createMessage(new Date(),message.getMessage(), studentid, companyid, jobid);
        repoMessage.save(messageEntity);
    }
}
