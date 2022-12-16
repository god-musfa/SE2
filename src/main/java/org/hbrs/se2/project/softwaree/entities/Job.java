package org.hbrs.se2.project.softwaree.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "job_listing", schema = "coll")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="creation_date")
    private LocalDate creation_date;

    @Column(name="last_edit")
    private LocalDate last_edit;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "views")
    private Integer views;


    @NotNull
    @ManyToMany
    @JoinTable(name = "job_requirement", schema = "coll",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id", referencedColumnName = "id")
    )
    @Fetch(FetchMode.JOIN)
    private  Set<Requirement> requirements = new java.util.LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @ManyToMany
    @JoinTable(name = "job_benefit", schema = "coll",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "benefit_id", referencedColumnName = "id")
    )
    @Fetch(FetchMode.JOIN)
    private  Set<Benefit> benefits = new java.util.LinkedHashSet<>();

/*
    @NotNull
    @ManyToMany
    @JoinTable(name = "job_skills", schema = "coll",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id")
    )
    @Fetch(FetchMode.JOIN)
    private  Set<Skill> skills = new java.util.LinkedHashSet<>();

 */


    @NotNull
    @ManyToMany
    @JoinTable(name = "job_skills", schema = "coll",
            joinColumns = {
                    @JoinColumn(name = "job_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "skill_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    @Fetch(FetchMode.JOIN)
    private Set<Skill> skills = new HashSet<>();

    /*
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "job_skills", schema = "coll",
            joinColumns = {
                    @JoinColumn(name = "job_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "skill_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Skill> skills = new HashSet<>();

     */


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setRequirements(Set<Requirement> requirements) {
        this.requirements = requirements;
    }



    public Integer getId() {
        return id;
    }

    public String getTitle() {return title;}

    public LocalDate getCreationDate() {
        return creation_date;
    }


    public LocalDate getLastEdit() {
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

    public Set<Benefit> getBenefits() {
        return benefits;
    }


    // Verify that attributes are not Null before calling "toString()" method
    public String getLastEditAsString() {return last_edit!= null ?  last_edit.toString() : "-";}
    public String getCreationDateAsString() {return creation_date != null ?  creation_date.toString() : "-";}
    public String getDeadlineAsString() {return deadline!= null ?  deadline.toString() : "-";}
    public String getViewsAsString() { return views != null ? views.toString() : "-";}

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public String getRequirementDescription() {
        String description = "";
        Iterator<Requirement> it = this.getRequirements().iterator();
        while(it.hasNext()) {
            description+= it.next().getDescription() + "\n";
        }
        return description;
    }

    public String getBenefitsAsString() {
        String benefits = "";
        Iterator<Benefit> it = this.getBenefits().iterator();
        while(it.hasNext()) {
            benefits+= it.next().getDescription() + "\n";
        }
        return benefits;
    }

    public String getCompanyName() {
        return company.getName();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public void setLast_edit(LocalDate last_edit) {
        this.last_edit = last_edit;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Set<Skill> getSkills(){
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void setBenefits(Set<Benefit> benefits) {
        this.benefits = benefits;
    }
}