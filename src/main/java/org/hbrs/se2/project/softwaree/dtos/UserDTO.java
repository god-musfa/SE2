package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Address;
import org.hbrs.se2.project.softwaree.entities.User;

import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
public class UserDTO implements Serializable {
    private Integer id;
    private Address address;
    private String email;
    private String password;
    private String profilePic;
    private String userType;

    public UserDTO( int id ,String email, String password,String userType) {
        this.email = email;
        this.password = password;
        this.id = id;
        this.userType = userType;
    }

    public UserDTO(Integer id, Address address, String email, String password, String profilePic, String userType) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.userType = userType;
    }

    public UserDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}