package org.hbrs.se2.project.softwaree.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Skill")
@Table(name = "skill", schema = "coll")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_sequence")
    @SequenceGenerator(name = "skill_sequence", sequenceName = "skill_id_seq", schema = "coll")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    // ManyToMany with Student:

    @ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}