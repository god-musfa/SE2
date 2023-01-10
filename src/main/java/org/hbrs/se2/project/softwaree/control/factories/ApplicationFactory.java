package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.entities.*;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.hbrs.se2.project.softwaree.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationFactory {

    private ApplicationFactory(){}

    public static Application createApplication(Student s, Job j){
        Application application = new Application();
        application.setStudent(s);
        application.setJob(j);
        ApplicationId applicationId = new ApplicationId();
        applicationId.setStudentId(s.getId());
        applicationId.setJobId(j.getId());
        application.setId(applicationId);

        return application;

    }
}
