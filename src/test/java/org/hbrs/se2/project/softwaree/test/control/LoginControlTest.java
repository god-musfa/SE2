package org.hbrs.se2.project.softwaree.test.control;

import org.hbrs.se2.project.softwaree.control.LoginControl;
import org.hbrs.se2.project.softwaree.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoginControlTest {

    @Autowired
    LoginControl lc ;

    @Autowired
    UserRepository repository;

    @Test
    void authentificate() throws Exception{

        assertTrue(lc.authentificate("sascha" , "abc"));
    }

}