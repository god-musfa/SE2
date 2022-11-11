package org.hbrs.se2.project.hellocar.dtos;

import org.hbrs.se2.project.hellocar.entities.Student;

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
    private String email;
    private String password;
    private String profilePic;

    public StudentDTO() {
    }

    public StudentDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentDTO(Integer id, String firstName, String lastName, Integer semester, LocalDate birthday, String email, String password, String profilePic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}