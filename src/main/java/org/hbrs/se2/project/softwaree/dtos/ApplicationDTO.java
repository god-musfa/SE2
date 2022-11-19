package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.entities.Application;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link Application} entity
 */
public class ApplicationDTO  {
    private String firstName;
    private String lastName;
    private Integer semester;
    private String degree;
    private String university;
    private String subject;
    private String title;
    private LocalDate deadline;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }


    public ApplicationDTO() {
    }

    public ApplicationDTO(String firstName, String lastName, Integer semester, String degree, String university, String subject, String title, LocalDate deadline) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
        this.degree = degree;
        this.university = university;
        this.subject = subject;
        this.title = title;
        this.deadline = deadline;
    }
}