package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
public class CompanyRatingID implements Serializable {

    @ManyToOne(targetEntity = Company.class)
    public Company company;

    @ManyToOne(targetEntity = Student.class)
    public Student student;


    @Transient
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Transient
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
