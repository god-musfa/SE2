package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "StudentCompanyMessage")
@Table(name = "student_company_message", schema = "coll")
public class StudentCompanyMessage {

    @Column(name = "student_id", nullable = false)
    private Integer student_id;

    @Column(name = "company_id", nullable = false)
    private Integer company_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id") //, nullable = false @ManyToOne @JoinColumn(name = "id") //todo
    private Integer message_id;


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

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

}