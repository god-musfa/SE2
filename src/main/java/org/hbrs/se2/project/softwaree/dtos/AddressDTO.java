package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Address;

/**
 * A DTO for the {@link Address} entity
 */
public class AddressDTO {
    private final Integer id;
    private final String street;
    private final String number;
    private final String city;
    private final String postalCode;

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

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }


}