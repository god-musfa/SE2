package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Student;

import java.time.LocalDate;

/**
 * A DTO for the {@link Student} entity
 */
public class StudentDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer semester;
    private LocalDate birthday;
    private String profilePic;

    public StudentDTO(String firstName, String lastName, Integer semester, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
        this.birthday = birthday;
    }

    public StudentDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}