package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.control.factories.MessageFactory;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.Message;
import org.hbrs.se2.project.softwaree.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Date;

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
        return repoStudent.findStudent(user.getId());
    }

    @Autowired
    JobRepository repoJob;
    public JobDTO getJobByID(int jobID) {
        return repoJob.findJobWithTitleDescriptionLocation(jobID);
    }

    public static String getFullName(StudentDTO student) {
        return student.getLastName() + ", " + student.getFirstName();
    }

    public static String getFullAddress(AddressDTO address) {
        return address.getStreet() + " " + address.getNumber() + ", " + address.getPostalCode() + " " + address.getCity();
    }

    public static String getBirthdayString(StudentDTO student) {
        return "Geboren am: " + student.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    }

    public static String getSemesterString(StudentDTO student) {
        return "Fachsemester: " + student.getSemester();
    }

    public static String getDegreeString(StudentDTO student) {
        return "Abschluss: " + student.getDegree();
    }

    public static String getUniSubString(StudentDTO student) {
        return student.getUniversity() + ", " + student.getSubject();
    }

    public static String getLocationString(JobDTO job) {
        return "Standort: " + job.getLocation();
    }

    @Autowired
    private MessageRepository repoMessage;
    public void createContact(int studentid, int companyid, int jobid, MessageDTO message) {
        Message messageEntity = MessageFactory.createMessage(new Date(),message.getMessage(), studentid, companyid, jobid);
        repoMessage.save(messageEntity);

    }
}
