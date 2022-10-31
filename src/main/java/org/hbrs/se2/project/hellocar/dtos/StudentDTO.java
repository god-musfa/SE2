package org.hbrs.se2.project.hellocar.dtos;

import org.hbrs.se2.project.hellocar.entities.Student;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link Student} entity
 */
public class StudentDTO implements Serializable {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Integer semester;
    private final LocalDate birthday;

    public StudentDTO(Integer id, String firstName, String lastName, Integer semester, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getSemester() {
        return semester;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO entity = (StudentDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.semester, entity.semester) &&
                Objects.equals(this.birthday, entity.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, semester, birthday);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "semester = " + semester + ", " +
                "birthday = " + birthday + ")";
    }
}