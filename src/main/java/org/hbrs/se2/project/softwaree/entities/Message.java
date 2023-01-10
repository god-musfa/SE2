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

    @Column(name = "sender_id", nullable = false)
    private Integer senderID;

    @Column(name = "recipient_id", nullable = false)
    private Integer recipientID;

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

    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer studentID) {
        this.senderID = studentID;
    }

    public Integer getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(Integer companyID) {
        this.recipientID = companyID;
    }

    public Integer getJobID() {
        return jobID;
    }

    public void setJobID(Integer jobID) {
        this.jobID = jobID;
    }

}