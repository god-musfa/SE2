package org.hbrs.se2.project.hellocar.dtos;

import org.hbrs.se2.project.hellocar.entities.Address;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Address} entity
 */
public class AddressDTO implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO entity = (AddressDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.street, entity.street) &&
                Objects.equals(this.number, entity.number) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.postalCode, entity.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, city, postalCode);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "street = " + street + ", " +
                "number = " + number + ", " +
                "city = " + city + ", " +
                "postalCode = " + postalCode + ")";
    }
}