package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.StudentRatingID;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link StudentRatingID} entity
 */
public class CompanyRatingIDDTO implements Serializable {
    private final StudentDTO student;
    private final CompanyDTO company;

    public CompanyRatingIDDTO(StudentDTO student, CompanyDTO company) {
        this.student = student;
        this.company = company;
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
        CompanyRatingIDDTO entity = (CompanyRatingIDDTO) o;
        return Objects.equals(this.student, entity.student) &&
                Objects.equals(this.company, entity.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, company);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "student = " + student + ", " +
                "company = " + company + ")";
    }
}