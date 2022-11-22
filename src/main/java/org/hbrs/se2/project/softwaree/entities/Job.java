package org.hbrs.se2.project.softwaree.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

/*@NamedNativeQuery(name="Job.findAllJobsNative",
                    query = "SELECT j.title, j.creation_date, j.description, j.location  FROM coll.job_listing j WHERE j.id = 1",
                    resultSetMapping = "Mapping.JobDTO")

@SqlResultSetMapping(name = "Mapping.JoDTO",
                    classes = @ConstructorResult(targetClass = org.hbrs.se2.project.softwaree.dtos.JobDTO.class,
                                columns = {
                                        @ColumnResult(name = "title", type=String.class),
                                        @ColumnResult(name = "creation_date", type = LocalDate.class),
                                        //@ColumnResult(name = "last_edit", type = LocalDate.class),
                                        //@ColumnResult(name = "deadline", type = LocalDate.class),
                                        @ColumnResult(name = "description", type = String.class),
                                        @ColumnResult(name = "location", type = String.class)
                                        //@ColumnResult(name = "views", type = Integer.class),
                                }
                                ))*/
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
    public Set<Benefit> getBenefits() {
        return benefits;
    }

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
}
