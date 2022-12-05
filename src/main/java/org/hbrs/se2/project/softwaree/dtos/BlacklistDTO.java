package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Blacklist;

/**
 * A DTO for the {@link Blacklist} entity
 */
public class BlacklistDTO {

    private Integer studentID;
    private Integer companyID;

    public BlacklistDTO(Integer studentID, Integer companyID) {
        this.studentID = studentID;
        this.companyID = companyID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

}
