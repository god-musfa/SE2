package org.hbrs.se2.project.softwaree.entities;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "requirement", schema = "coll")
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requirement_sequence")
    @SequenceGenerator(name = "requirement_sequence", sequenceName = "requirement_id_seq", schema = "coll")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy = "requirements",fetch = FetchType.LAZY)
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