package org.hbrs.se2.project.softwaree.test.control;

import org.hbrs.se2.project.softwaree.control.JobDetailControl;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.repository.BlacklistRepository;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobDetailControlTest {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    BlacklistRepository blacklistRepository;


    @Autowired
    JobDetailControl jobDetailControl ;


    @Test
    void getJob() {

        JobDTO jobDTO = new JobDTO(2, "Test", "test" ,"test");
        assertEquals(jobDTO.getId() , jobDetailControl.getJob(2).getId());
        assertEquals(jobDTO.getDescription() , jobDetailControl.getJob(2).getDescription());
        assertEquals(jobDTO.getLocation() , jobDetailControl.getJob(2).getLocation());
        assertEquals(jobDTO.getTitle() , jobDetailControl.getJob(2).getTitle());


    }

    @Test
    void validateJobID() {
        assertTrue(jobDetailControl.validateJobID(2));
        assertFalse(jobDetailControl.validateJobID(9999));
    }

    @Test
    void validateJobViews() {

        assertEquals(2, jobDetailControl.validateJobViews(2));
        assertEquals(0, jobDetailControl.validateJobViews(null));
    }


}