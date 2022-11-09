package org.hbrs.se2.project.hellocar.repository;

import org.hbrs.se2.project.hellocar.entities.*;
import org.hbrs.se2.project.hellocar.dtos.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
/**
 * JPA-Repository für die Abfrage von registrierten User. Die Bezeichnung einer Methode
 * bestimmt dabei die Selektionsbedingung (den WHERE-Teil). Der Rückgabewert einer
 * Methode bestimmt den Projectionsbedingung (den SELECT-Teil).
 * Mehr Information über die Entwicklung von Queries in JPA:
 * https://www.baeldung.com/spring-data-jpa-projections
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {



    // SELECT firstname, lastname, id
    // FROM User p
    // WHERE p.userid = [StringValueOf( userid )] AND p.password = [StringValueOf( password )]
    UserDTO findUserByIdAndPassword(String userid , String password);

    @Query("  SELECT new org.hbrs.se2.project.hellocar.dtos.UserDTO(u.email, u.password)  FROM User u WHERE u.email = ?1 AND u.password = ?2")
    UserDTO findUserByEmailAndPassword(String email, String password);
}