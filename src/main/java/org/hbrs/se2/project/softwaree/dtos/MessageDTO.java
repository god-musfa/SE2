package org.hbrs.se2.project.softwaree.dtos;


import org.hbrs.se2.project.softwaree.entities.Message;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Message} entity
 */
public class MessageDTO implements Serializable {
    private Integer id;
    private Date time_sent;
    private String message;
    private Integer studentID;
    private Integer companyID;
    private Integer jobID;

    public MessageDTO(Integer id, Date time_sent, String message, Integer studentID, Integer companyID, Integer jobID) {
        this.id = id;
        this.time_sent = time_sent;
        this.message = message;
        this.studentID = studentID;
        this.companyID = companyID;
        this.jobID = jobID;
    }

    public MessageDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime_sent() {
        return time_sent;
    }

    public void setTime_sent(Date timeSent) {
        this.time_sent = timeSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Integer getJobID() {
        return jobID;
    }

    public void setJobID(Integer jobID) {
        this.jobID = jobID;
    }
}