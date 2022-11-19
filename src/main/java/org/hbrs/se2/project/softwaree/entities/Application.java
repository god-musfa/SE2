package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;

@Entity(name = "Application")
@Table(name = "application", schema = "coll")
public class Application {
    @EmbeddedId
    private ApplicationId id;

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @MapsId("jobId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    public ApplicationId getId() {
        return id;
    }

    public void setId(ApplicationId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

}