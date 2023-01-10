package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.control.exception.DatabaseUserException;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.repository.UserRepository;
import org.hbrs.se2.project.softwaree.security.SecurityHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LoginControl {

    @Autowired
    private UserRepository repository;

    private UserDTO userDTO = null;

    public boolean authentificate(String email, String password ) throws DatabaseUserException {
        // Standard: User wird mit Spring JPA ausgelesen (Was sind die Vorteile?)
        UserDTO tmpUser = this.getUserWithJPA(email);

        // Alternative: Auslesen des Users mit JDBC (Was sind die Vorteile bzw. Nachteile?)
        // UserDTO tmpUser = this.getUserWithJDBC( username , password );

        // Check password:
        if ( tmpUser == null || !SecurityHandler.checkPassword(password, tmpUser.getPassword())) {
            // ggf. hier ein Loggin einfügen
            return false;
        }
        this.userDTO = tmpUser;
        return true;
    }

    public UserDTO getCurrentUser(){
        return this.userDTO;

    }

    private UserDTO getUserWithJPA( String email) throws DatabaseUserException {
        UserDTO userTmp;
        try {
            userTmp = repository.findUserByEmail(email);
        } catch ( org.springframework.dao.DataAccessResourceFailureException e ) {
            // Analyse und Umwandlung der technischen Errors in 'lesbaren' Darstellungen (ToDo!)
           throw new DatabaseUserException("A failure occured while trying to connect to database with JPA");
        }
        return userTmp;
    }

}
