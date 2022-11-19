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
    private Date time_sent;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "student_id", nullable = false)
    private Integer student_id;

    @Column(name = "company_id", nullable = false)
    private Integer company_id;

    @Column(name = "job_id")
    private Integer job_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeSent() {
        return time_sent;
    }

    public void setTimeSent(Date time_sent) {
        this.time_sent = time_sent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

}