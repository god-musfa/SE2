package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.hbrs.se2.project.softwaree.entities.Skill;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;


/**
 * A DTO for the {@link org.hbrs.se2.project.softwaree.entities.Job} entity
 */
public class JobDTO implements Serializable {
    private Integer id;
    private String title;
    private LocalDate creation_date;
    private LocalDate last_edit;
    private LocalDate deadline;
    private String description;
    private String location;
    private Integer views;

    public Company company;

    private Set<Benefit> benefits;
    private Set<Requirement> requirements;
    private Set<Skill> skills;


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

    public JobDTO(Integer id, String title, LocalDate creation_date, LocalDate deadline,
                  String description, String location) {
        this.id = id;
        this.title = title;
        this.creation_date = creation_date;
        this.deadline = deadline;
        this.description = description;
        this.location = location;
    }

    public JobDTO(Integer id, String title, LocalDate creation_date, LocalDate last_edit, LocalDate deadline,
                  String description, String location, Integer views, Company company, Set<Benefit> benefits, Set<Requirement> requirements) {
        this.id = id;
        this.title = title;
        this.creation_date = creation_date;
        this.last_edit = last_edit;
        this.deadline = deadline;
        this.description = description;
        this.location = location;
        this.views = views;
        this.company = company;
        this.benefits = benefits;
        this.requirements = requirements;
    }

    public JobDTO(Integer id, String title, String description, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public JobDTO() {
        this.creation_date = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }
    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setLast_edit(LocalDate last_edit) {
        this.last_edit = last_edit;
    }
    public LocalDate getLast_edit() {
        return last_edit;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Set<Benefit> getBenefits() {
        return this.benefits;
    }

    public Set<Requirement> getRequirements() {
        return this.requirements;
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

    public Company getCompany() {
        return company;
    }

    // Verify that attributes are not Null before calling "toString()" method
    public String getLastEditAsString() {return last_edit!= null ?  last_edit.toString() : "-";}
    public String getCreationDateAsString() {return creation_date != null ?  creation_date.toString() : "-";}
    public String getDeadlineAsString() {return deadline!= null ?  deadline.toString() : "-";}

    public Set<Skill> getSkills(){
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}