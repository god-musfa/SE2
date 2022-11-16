package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Company;

/**
 * A DTO for the {@link Company} entity
 */
public class CompanyDTO {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String website;
    private String field;
    private String size;
    private String profilePic;

    private String contactPerson;




    public CompanyDTO(int id, String name, String phoneNumber, String website, String field, String size, String contactPerson) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.field = field;
        this.size = size;
        this.profilePic = profilePic;
        this.id = id;
        this.contactPerson = contactPerson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

}