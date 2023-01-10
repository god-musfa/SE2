package org.hbrs.se2.project.softwaree.dtos;

import java.io.Serializable;

/**
 * A DTO for the {@link org.hbrs.se2.project.softwaree.entities.Address} entity
 */
public class AddressDTO implements Serializable {
    private Integer id;
    private String street;
    private String number;
    private String city;
    private String postalCode;

    public AddressDTO() {
    }

    public AddressDTO(Integer id, String street, String number, String city, String postalCode) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}