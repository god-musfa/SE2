package org.hbrs.se2.project.softwaree.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Company")
@Table(name = "company", schema = "coll")
public class Company {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "website")
    private String website;

    @Column(name = "field")
    private String field;

    @Column(name = "size")
    private String size;

    @Column(name = "\"contactPerson\"")
    private String contactPerson;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;


    // StudentRating (ManyToMany)


    @OneToMany(mappedBy = "company", orphanRemoval = true)
    private Set<StudentRating> studentRatings = new LinkedHashSet<>();

    public Set<StudentRating> getRatings() {
        return studentRatings;
    }

    public void setRatings(Set<StudentRating> studentRatings) {
        this.studentRatings = studentRatings;
    }


    @OneToMany(mappedBy = "company", orphanRemoval = true)
    private Set<CompanyRating> companyRatings = new LinkedHashSet<>();

    public Set<CompanyRating> getCompanyRatings() {
        return companyRatings;
    }

    public void setCompanyRatings(Set<CompanyRating> companyRatings) {
        this.companyRatings = companyRatings;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
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

}