package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.entities.*;
import org.hbrs.se2.project.softwaree.dtos.*;
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

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.UserDTO(u.id,u.address.id, u.email, u.password,u.profilePic,u.userType)  FROM User u WHERE u.email = ?1 AND u.password = ?2")
    UserDTO findUserByEmailAndPassword(String email, String password);

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.UserDTO(u.id,u.address.id, u.email, u.password,u.profilePic,u.userType)  FROM User u WHERE u.id = ?1")
    UserDTO findUserByID(int id);

    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean checkEmailExists(String email);
}