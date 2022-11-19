package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Company;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;


/**
 * A DTO for the {@link org.hbrs.se2.project.softwaree.entities.Job} entity
 */
public class JobDTO implements Serializable {
    private final Integer id;
    private final String title;
    private final LocalDate creation_date;
    private final LocalDate last_edit;
    private final LocalDate deadline;
    private final String description;
    private final String location;
    private final Integer views;

    public final Company company;


    public JobDTO(Integer id, String title, LocalDate creation_date, LocalDate last_edit, LocalDate deadline,
                  String description, String location, Integer views, Company company) {
        this.id = id;
        this.title = title;
        this.creation_date = creation_date;
        this.last_edit = last_edit;
        this.deadline = deadline;
        this.description = description;
        this.location = location;
        this.views = views;
        this.company = company;
    }


    public String getTitle() {
        return title;
    }

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

    public Company getCompany(){
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobDTO entity = (JobDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.title, entity.title) &&
                Objects.equals(this.creation_date, entity.creation_date) &&
                Objects.equals(this.last_edit, entity.last_edit) &&
                Objects.equals(this.deadline, entity.deadline) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.views, entity.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creation_date, last_edit, deadline, description, location, views, company);
    }
}