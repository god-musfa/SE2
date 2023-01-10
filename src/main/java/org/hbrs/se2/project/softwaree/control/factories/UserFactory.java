package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.Address;
import org.hbrs.se2.project.softwaree.entities.User;
import org.hbrs.se2.project.softwaree.repository.AddressRepository;
import org.hbrs.se2.project.softwaree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserFactory {

    public static User createUser(UserDTO userDTO, Address address){
        User user = new User();
        user.setUserType(userDTO.getUserType());
        user.setAddress(address);
        user.setPassword(userDTO.getPassword());
        user.setProfilePic(userDTO.getProfilePic());
        user.setEmail(userDTO.getEmail());

        return user;
    }

    public static User createUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        // SET ADDRESS!!
        user.setUserType(userDTO.getUserType());
        user.setPassword(userDTO.getPassword());
        user.setProfilePic(userDTO.getProfilePic());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
