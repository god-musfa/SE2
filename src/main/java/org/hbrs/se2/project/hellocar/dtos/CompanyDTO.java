package org.hbrs.se2.project.hellocar.dtos;

import org.hbrs.se2.project.hellocar.entities.Company;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Company} entity
 */
public class CompanyDTO implements Serializable {
    private final Integer id;
    private final String name;
    private final String phoneNumber;
    private final String website;
    private final String field;
    private final String size;

    public CompanyDTO(Integer id, String name, String phoneNumber, String website, String field, String size) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.field = field;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public String getField() {
        return field;
    }

    public String getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDTO entity = (CompanyDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.phoneNumber, entity.phoneNumber) &&
                Objects.equals(this.website, entity.website) &&
                Objects.equals(this.field, entity.field) &&
                Objects.equals(this.size, entity.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, website, field, size);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "phoneNumber = " + phoneNumber + ", " +
                "website = " + website + ", " +
                "field = " + field + ", " +
                "size = " + size + ")";
    }
}