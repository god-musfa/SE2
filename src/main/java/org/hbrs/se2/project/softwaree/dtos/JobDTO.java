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
    private LocalDate creationDate;
    private LocalDate lastEdit;
    private LocalDate deadline;
    private String description;
    private String location;
    private Integer views;

    public Company company;
    private Set<Benefit> benefits;
    private Set<Requirement> requirements;
    private Set<Skill> skills;


    public JobDTO(Integer id, String title, LocalDate creationDate, LocalDate lastEdit, LocalDate deadline,
                  String description, String location, Integer views, Company company) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.lastEdit = lastEdit;
        this.deadline = deadline;
        this.description = description;
        this.location = location;
        this.views = views;
        this.company = company;
    }

    public JobDTO(Integer id, String title, LocalDate creationDate, String description, Integer views) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
        this.views = views;

    }

    public JobDTO(Integer id, String title, LocalDate creationDate, LocalDate deadline,
                  String description, String location) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.description = description;
        this.location = location;
    }

    public JobDTO(Integer id, String title, LocalDate creationDate, LocalDate lastEdit, LocalDate deadline,
                  String description, String location, Integer views, Company company, Set<Benefit> benefits, Set<Requirement> requirements) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.lastEdit = lastEdit;
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
        this.creationDate = LocalDate.now();
    }

    public JobDTO(Integer id, String title, LocalDate creationDate, LocalDate lastEdit, LocalDate deadline, String description,
                  String location, Integer views, Company company, Set<Benefit> benefits, Set<Requirement> requirements, Set<Skill> skills) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.lastEdit = lastEdit;
        this.deadline = deadline;
        this.description = description;
        this.location = location;
        this.views = views;
        this.company = company;
        this.benefits = benefits;
        this.requirements = requirements;
        this.skills = skills;
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
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setLastEdit(LocalDate lastEdit) {
        this.lastEdit = lastEdit;
    }
    public LocalDate getLastEdit() {
        return lastEdit;
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

    public void setBenefits(Set<Benefit> benefits) {
        this.benefits = benefits;
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
                Objects.equals(this.creationDate, entity.creationDate) &&
                Objects.equals(this.lastEdit, entity.lastEdit) &&
                Objects.equals(this.deadline, entity.deadline) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.views, entity.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creationDate, lastEdit, deadline, description, location, views, company);
    }

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company){
        this.company = company;
    }

    // Verify that attributes are not Null before calling "toString()" method
    public String getLastEditAsString() {return lastEdit != null ?  lastEdit.toString() : "-";}
    public String getCreationDateAsString() {return creationDate != null ?  creationDate.toString() : "-";}
    public String getDeadlineAsString() {return deadline!= null ?  deadline.toString() : "-";}
    public String getViewsAsString() { return views != null ? views.toString() : "-";}

    public Set<Skill> getSkills(){
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void setRequirement(Set<Requirement> requirements) {
        this.requirements = requirements;
    }


}