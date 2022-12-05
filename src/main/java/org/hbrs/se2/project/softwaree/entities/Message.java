package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Message")
@Table(name = "message", schema = "coll")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time_sent", nullable = false)
    private Date timeSent;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "student_id", nullable = false)
    private Integer studentID;

    @Column(name = "company_id", nullable = false)
    private Integer companyID;

    @Column(name = "job_id")
    private Integer jobID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
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