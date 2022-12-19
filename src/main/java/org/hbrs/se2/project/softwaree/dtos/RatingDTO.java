package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.control.factories.CompanyFactory;
import org.hbrs.se2.project.softwaree.control.factories.RatingFactory;
import org.hbrs.se2.project.softwaree.control.factories.RatingIDFactory;
import org.hbrs.se2.project.softwaree.control.factories.StudentFactory;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.RatingID;
import org.hbrs.se2.project.softwaree.entities.Student;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.hbrs.se2.project.softwaree.entities.Rating} entity
 */
public class RatingDTO implements Serializable {
    private final RatingIDDTO ratingID;
    private final StudentDTO student;
    private final CompanyDTO company;
    private int rating;

    public RatingDTO(RatingIDDTO ratingID, int rating, StudentDTO student, CompanyDTO company) {
        this.ratingID = ratingID;
        this.rating = rating;
        this.student = student;
        this.company = company;
    }

    public RatingDTO(RatingID ratingID, int rating, Student student, Company company) {
        this.ratingID = RatingIDFactory.createDTO(ratingID);
        this.student = StudentFactory.createDTO(student);
        this.company = CompanyFactory.createDTO(company);
        this.rating = rating;
    }

    public RatingIDDTO getRatingID() {
        return ratingID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingDTO entity = (RatingDTO) o;
        return Objects.equals(this.ratingID, entity.ratingID) &&
                Objects.equals(this.rating, entity.rating) &&
                Objects.equals(this.student, entity.student) &&
                Objects.equals(this.company, entity.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingID, rating, student, company);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "ratingID = " + ratingID + ", " +
                "rating = " + rating + ", " +
                "student = " + student + ", " +
                "company = " + company + ")";
    }
}