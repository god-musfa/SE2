package org.hbrs.se2.project.softwaree.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * A DTO for the {@link org.hbrs.se2.project.softwaree.entities.Job} entity
 */

public class JobListDTO implements Serializable {

    public static final String ID_ALIAS = "j_id";
    public static final String TITLE_ALIAS = "j_title";
    public  static final String DESCRIPTION_ALIAS = "j_description";
    public static final String LOCATION_ALIAS = "j_location";
    public  static final String AVG_COMPANY_ALIAS = "j_avgcompanyrating";

    private static final String CREATION_DATE_ALIAS = "j_creation_date";
    private static final String VIEWS_ALIAS = "j_views";


    private Integer id;
    private String title;
    private LocalDate creation_date;
    private String description;
    private String location;
    private Double j_avgcompanyrating;
    private Integer views;

    private List<JobListSkillDTO> skills = new ArrayList<>();
    private JobListCompanyDTO company;

    // No Args Constructor (for the Hibernate ResultTransformer)
    public JobListDTO() {}

    public JobListDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
        this.id = (Integer) (tuple[aliasToIndexMap.get(ID_ALIAS)]);
        this.title = (String) (tuple[aliasToIndexMap.get(TITLE_ALIAS)]);
        this.description = (String) (tuple[aliasToIndexMap.get(DESCRIPTION_ALIAS)]);
        this.location = (String) (tuple[aliasToIndexMap.get(LOCATION_ALIAS)]);
        this.j_avgcompanyrating = (tuple[aliasToIndexMap.get(AVG_COMPANY_ALIAS)])!=null ? ((Number)(tuple[aliasToIndexMap.get(AVG_COMPANY_ALIAS)])).doubleValue() : 0;
        this.views = (Integer) (tuple[aliasToIndexMap.get(VIEWS_ALIAS)]);
        this.creation_date =((Date)(tuple[aliasToIndexMap.get(CREATION_DATE_ALIAS)])).toLocalDate();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getCompanyName() {
        return this.company.getName();
    }
    public String getCompanyWebsite() {
        return this.company.getWebsite();
    }

    public Double get_avgcompanyrating() {
        return j_avgcompanyrating;
    }
    public String getAvgCompanyRatingAsString() {return this.j_avgcompanyrating == null ? "-" : this.j_avgcompanyrating.toString();}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobListDTO entity = (JobListDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.title, entity.title) &&
                Objects.equals(this.creation_date, entity.creation_date) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.skills, entity.skills) &&
                Objects.equals(this.company, entity.company);
    }


    public void setJ_avgcompanyrating(Double j_avgcompanyrating) {
        this.j_avgcompanyrating = j_avgcompanyrating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creation_date, description, location, views);
    }

    public List<JobListSkillDTO> getSkills() {
        return this.skills;
    }

    public void setCompany(JobListCompanyDTO jobListCompanyDTO) {
        this.company = jobListCompanyDTO;
    }

    public Integer getViews() {
        return this.views;
    }
    public String getViewsAsString() {
        return this.views == null ? "0" : this.views.toString();
    }
}