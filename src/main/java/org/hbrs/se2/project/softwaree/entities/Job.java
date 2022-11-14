package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_listing", schema = "coll")
public class Job {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="creation_date", nullable = false)
    private LocalDate creation_date;

    @Column(name="last_edit", nullable = false)
    private LocalDate last_edit;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "views", nullable = false)
    private Integer views;


    public Integer getId() {
        return id;
    }

    public String getTitle() {return title;}

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public LocalDate getLast_edit() {
        return last_edit;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Integer getViews() {
        return views;
    }


}
