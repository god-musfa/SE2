package org.hbrs.se2.project.hellocar.entities;

import javax.persistence.*;

@Entity
@Table(name = "address", schema = "coll")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "street")
    private String street;

    @Lob
    @Column(name = "number")
    private String number;

    @Lob
    @Column(name = "city")
    private String city;

    @Lob
    @Column(name = "postal_code")
    private String postalCode;

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