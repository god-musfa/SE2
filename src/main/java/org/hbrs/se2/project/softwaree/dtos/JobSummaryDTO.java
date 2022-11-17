package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Company;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link org.hbrs.se2.project.softwaree.entities.Job} entity
 */
public class JobSummaryDTO implements Serializable {
    private final String title;
    private final LocalDate last_edit;
    private final String description;
    private final String location;
    private final Set<Company> company;

    public JobSummaryDTO(String title, LocalDate last_edit, String description, String location, Set<Company> company) {
        this.title = title;
        this.last_edit = last_edit;
        this.description = description;
        this.location = location;
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getLast_edit() {
        return last_edit;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Set<Company> getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobSummaryDTO entity = (JobSummaryDTO) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.last_edit, entity.last_edit) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.company, entity.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, last_edit, description, location, company);
    }
}