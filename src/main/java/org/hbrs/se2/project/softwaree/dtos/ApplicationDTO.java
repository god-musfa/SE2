package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Application;


import java.time.LocalDate;

/**
 * A DTO for the {@link Application} entity
 */
public class ApplicationDTO  {

    private String companyName;
    private Integer studentId;
    private String firstName;
    private String lastName;
    private Integer semester;
    private String degree;
    private String university;
    private String subject;
    private String title;
    private LocalDate deadline;
    private Integer jobId;
    private Integer companyID;


    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public ApplicationDTO() {
    }

    public ApplicationDTO(Integer studentId, String firstName, String lastName, Integer semester, String degree, String university, String subject, Integer jobId, String title, LocalDate deadline) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
        this.degree = degree;
        this.university = university;
        this.subject = subject;
        this.title = title;
        this.deadline = deadline;
        this.jobId = jobId;
    }

    public ApplicationDTO(String firstName, String lastName, String title, LocalDate deadline,Integer jobId,Integer studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.deadline = deadline;
        this.jobId =jobId;
        this.studentId = studentId;
    }

    public ApplicationDTO(String title, String companyName, LocalDate deadline, Integer jobId, Integer studentId, Integer companyID) {
        this.companyName = companyName;
        this.title = title;
        this.deadline = deadline;
        this.jobId =jobId;
        this.studentId = studentId;
        this.companyID = companyID;
    }
}