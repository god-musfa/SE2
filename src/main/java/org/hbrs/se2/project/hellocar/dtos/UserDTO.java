package org.hbrs.se2.project.hellocar.dtos;

import org.hbrs.se2.project.hellocar.entities.User;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
public class UserDTO implements Serializable {

    private String email;
    private String password;

    private int id;

    public int getId(){
        return id;
    }


    public UserDTO() {
    }

    public UserDTO( int id ,String email, String password) {
        this.email = email;
        this.password = password;
        this.id = id;
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


}