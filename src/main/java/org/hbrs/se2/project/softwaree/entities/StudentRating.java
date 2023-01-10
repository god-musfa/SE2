package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;

@Entity(name="Rating")
@Table(name="student_rating", schema = "coll")
public class StudentRating {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EmbeddedId
    @AssociationOverrides({
            @AssociationOverride(name = "student", joinColumns = @JoinColumn(name = "student_id")),
            @AssociationOverride(name = "company", joinColumns = @JoinColumn(name = "company_id"))
    })
    private StudentRatingID studentRatingID;

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

    public StudentRatingID getRatingID() {
        return studentRatingID;
    }

    public void setRatingID(StudentRatingID studentRatingID) {
        this.studentRatingID = studentRatingID;
    }
}
