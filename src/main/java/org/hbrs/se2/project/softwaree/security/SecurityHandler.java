package org.hbrs.se2.project.softwaree.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityHandler {

    // Bcrypt hashing component:
    private static BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();



    public static String hashPassword(String plainPassword) {
        return bcryptEncoder.encode(plainPassword);
    }

    public static boolean checkPassword(String plainPassword, String passwordHash) {
        return bcryptEncoder.matches(plainPassword, passwordHash);
    }

}
