package org.hbrs.se2.project.softwaree.entities;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "benefit", schema = "coll")
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "benefits")
    @Fetch(FetchMode.JOIN)
    private Set<Job> jobs = new java.util.LinkedHashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

}


