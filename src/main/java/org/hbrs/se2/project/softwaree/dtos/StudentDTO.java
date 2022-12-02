package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.entities.Student;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * A DTO for the {@link Student} entity
 */
public class StudentDTO implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer semester;
    private LocalDate birthday;
    private String degree;
    private String university;
    private String subject;
    private Set<Skill> skills;


    public StudentDTO() {}

    public StudentDTO(Integer id, String firstName, String lastName, Integer semester, LocalDate birthday, String degree, String university, String subject) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
        this.birthday = birthday;
        this.degree = degree;
        this.university = university;
        this.subject = subject;
    }

    public StudentDTO(String firstName, String lastName, Integer semester, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
        this.birthday = birthday;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Skill> getSkills(){
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

}