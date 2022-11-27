package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Blacklist")
@Table(name = "blacklist", schema = "coll")
@IdClass(Blacklist.class)
public class Blacklist implements Serializable {

    @Id
    @Column(name="student_id", nullable = false)
    private Integer student_id;

    @Id
    @Column(name="company_id", nullable = false)
    private Integer company_id;

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

}
