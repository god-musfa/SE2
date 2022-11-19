package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.entities.StudentCompanyMessage;

public class StudentCompanyMessageFactory {
    public static StudentCompanyMessage create(int studentid, int companyid, int jobid, Integer messageid) {
        StudentCompanyMessage scm = new StudentCompanyMessage();

        scm.setStudent_id(studentid);
        scm.setCompany_id(companyid);
        //scm.setJob_id(jobid);
        //scm.setMessage_id(messageid);

        return scm;
    }
}
