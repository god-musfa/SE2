package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Blacklist")
@Table(name = "blacklist", schema = "coll")
@IdClass(Blacklist.class)
public class Blacklist implements Serializable {

    @Id
    @Column(name="student_id", nullable = false)
    private Integer studentID;

    @Id
    @Column(name="company_id", nullable = false)
    private Integer companyID;

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
