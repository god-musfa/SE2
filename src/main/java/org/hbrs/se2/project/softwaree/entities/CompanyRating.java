package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;

@Entity(name="CompanyRating")
@Table(name="company_rating", schema = "coll")
public class CompanyRating {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EmbeddedId
    @AssociationOverrides({
            @AssociationOverride(name = "company", joinColumns = @JoinColumn(name = "company_id")),
            @AssociationOverride(name = "student", joinColumns = @JoinColumn(name = "student_id"))
    })
    private CompanyRatingID companyRatingID;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public CompanyRatingID getRatingID() {
        return companyRatingID;
    }

    public void setRatingID(CompanyRatingID companyRatingID) {
        this.companyRatingID = companyRatingID;
    }
}
