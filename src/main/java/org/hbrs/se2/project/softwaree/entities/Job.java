package org.hbrs.se2.project.softwaree.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
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

    @ManyToMany(mappedBy = "ownedJobs")
    @Fetch(FetchMode.JOIN)
    Set<Company> company = new java.util.LinkedHashSet<>();

    public void setCompany(Set<Company> company) {
        this.company = company;
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

    public Set<Company> getCompany() { return  company; }

    public String getAllCompanyNames() {
        String names = "";
        Iterator<Company> it = this.getCompany().iterator();
        while(it.hasNext()) {
            names += it.next().getName() + ", ";
        }
        return names;
    }


}
