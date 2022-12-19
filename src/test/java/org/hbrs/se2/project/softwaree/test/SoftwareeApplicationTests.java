package org.hbrs.se2.project.softwaree.test;

import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.entities.User;
import org.hbrs.se2.project.softwaree.repository.CompanyRepository;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.hbrs.se2.project.softwaree.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@SpringBootTest
class SoftwareeApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobRepository jobRepository ;

   /* @Autowired
    private CarRepository carRepository;
*/
    @Test
    void testUserDTOByAttribute() {
        UserDTO personDTO = userRepository.findUserByID(188);
        System.out.println(personDTO.getEmail());
        assertEquals("sascha", personDTO.getEmail());
        assertEquals(188 , personDTO.getId());
    }


  /*  @Test
    void testCompanyAndItsJobs() {
        CompanyDTO companyDTO = companyRepository.findCompany(3);
        assertEquals("asdasd" , companyDTO.getName());
        Set<Job> list;
        list = companyDTO.getOwJobList();
        System.out.println(list.size());
        assertEquals(1 , list.size());
    } */

    @Test
    void testPersonLoad() {
        Optional<User> wrapper = userRepository.findById(188);
        if ( wrapper.isPresent() ) {
            User user = wrapper.get();
            assertEquals("sascha" , user.getEmail());
        }
    }
/*


    @Test
    void testFindCarsAndTheirUsers() {
        List<Object[]> list = this.carRepository.findAllCarsAndTheirUsers();
        for (Object[] item: list) {
            System.out.println("Brand: " + item[0] );
            System.out.println("Model: " + item[1] );
            System.out.println("Price: " + item[2] );
            System.out.println("First Name: " + item[3] );
            System.out.println("Last Name: " + item[4] );
        }
        // Todo: Definition von passenden Assertions
    }



    @Test
    void testFindCarsWithMostImportantValues() {
        List<CarDTO> list = this.carRepository.findCarsByDateIsNotNull();
        for (CarDTO item: list) {
            System.out.println("Brand: " + item.getBrand() );
            System.out.println("Model: " + item.getModel() );
            System.out.println("Price: " + item.getPrice() );
            System.out.println("Phone: " + item.getPhone() );
        }
        // Todo: Definition von passenden Assertions
    }

*/
}
