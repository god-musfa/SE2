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

        JobDTO jobDTO = new JobDTO(498, "SAP Developer (m/w/d)", "Du verstärkst unser Produktteam Forderungsmanagement als SAP Developer. Nach einer intensiven und zielgerichteten Einarbeitung durch erfahrene Kollegen übernimmst du sukzessive eigenverantwortlich Themenbereiche in crossfunktional arbeitenden Teams und unterstützt so unsere Kollegen bei der Konzeption und Entwicklung sowohl im SAP R/3 als auch in S/4HANA und der Business Transaction Platform (BTP). Dabei arbeiten wir als SCRUM Team eng mit unseren Stakeholdern zusammen und pflegen sowohl beim Support als auch bei der Weiterentwicklung einen offenen Austausch auf Augenhöhe." ,"Kiel");
        assertEquals(jobDTO.getId() , jobDetailControl.getJob(498).getId());
        assertEquals(jobDTO.getDescription() , jobDetailControl.getJob(498).getDescription());
        assertEquals(jobDTO.getLocation() , jobDetailControl.getJob(498).getLocation());
        assertEquals(jobDTO.getTitle() , jobDetailControl.getJob(498).getTitle());


    }

    @Test
    void validateJobID() {
        assertTrue(jobDetailControl.validateJobID(498));
        assertFalse(jobDetailControl.validateJobID(9999));
    }

    @Test
    void validateJobViews() {

        assertEquals(2, jobDetailControl.validateJobViews(2));
        assertEquals(0, jobDetailControl.validateJobViews(null));
    }


}