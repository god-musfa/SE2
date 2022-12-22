package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
public class RatingID implements Serializable {

    @ManyToOne(targetEntity = Student.class)
    public Student student;

    @ManyToOne(targetEntity = Company.class)
    public Company company;

    @Transient
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Transient
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


}
