package org.hbrs.se2.project.softwaree.entities;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Job;

@Entity
@Table(name = "requirement", schema = "coll")
public class Requirement {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy = "requirements")
    @Fetch(FetchMode.JOIN)
    Set<Job> job = new java.util.HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Job> getJob() {return job;}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}