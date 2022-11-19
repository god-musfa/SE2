package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Message;
import org.hbrs.se2.project.softwaree.entities.StudentCompanyMessage;

import java.io.Serializable;

/**
 * A DTO for the {@link StudentCompanyMessage} entity
 */
public class StudentCompanyMessageDTO implements Serializable {
    private Integer studentID;
    private Integer companyID;
    private Integer messageID;

    public StudentCompanyMessageDTO(Integer studentID, Integer companyID, Integer messageID) {
        this.studentID = studentID;
        this.companyID = companyID;
        this.messageID = messageID;
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

    public Integer getMessageID() {
        return messageID;
    }

    public void setMessageID(Integer messageID) {
        this.messageID = messageID;
    }

}