package org.hbrs.se2.project.softwaree.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ApplicationId implements Serializable {
    private static final long serialVersionUID = 6953657314104549566L;
    @NotNull
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @NotNull
    @Column(name = "job_id", nullable = false)
    private Integer jobId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ApplicationId entity = (ApplicationId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.jobId, entity.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, jobId);
    }

}