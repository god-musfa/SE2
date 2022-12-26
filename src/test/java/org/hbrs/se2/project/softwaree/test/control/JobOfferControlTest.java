package org.hbrs.se2.project.softwaree.test.control;

import org.hbrs.se2.project.softwaree.control.JobOfferControl;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.*;
import org.hbrs.se2.project.softwaree.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobOfferControlTest {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    RequirementRepository requirementRepository;
    @Autowired
    BenefitRepository benefitRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    JobOfferControl joc;

   /* @Test
    void createJobOffer() {

        int size = companyRepository.findCompany(1).getOwJobList().size();

        JobDTO jobDTO = new JobDTO();
        jobDTO.setTitle("TestCreateJobOffer");
        jobDTO.setCompany(companyRepository.getOne(1));
        joc.createJobOffer(jobDTO,1);
        assertEquals(companyRepository.findCompany(1).getOwJobList().size() , size+1);



    }*/

    @Test
    void getAvailableSkills() {
        Optional<Skill> testSkillOpt = skillRepository.findByDescription("Python");
        Skill testSkill  = testSkillOpt.get();
        SkillDTO testSkillDTO = new SkillDTO(testSkill.getId() , testSkill.getDescription() );
        List<SkillDTO> list = joc.getAvailableSkills();
        assertTrue(list.contains(testSkillDTO));
        assertFalse(list.isEmpty());
        assertEquals(list.size() , skillRepository.findAll().size());
    }

    @Test
    void getAvailableBenefits() {

        List <BenefitDTO> list = joc.getAvailableBenefits();
        assertFalse(list.isEmpty());
        assertEquals(list.size() , benefitRepository.findAll().size());


    }

    @Test
    void getAvailableRequirements() {

        List <RequirementDTO> list = joc.getAvailableRequirements();
        assertFalse(list.isEmpty());
        assertEquals(list.size() , requirementRepository.findAll().size());

    }
/*
    @Test
    void getJobBenefits() {


    }

    @Test
    void getJobRequirements() {
    }

    @Test
    void getJobSkills() {
    }
*/
    @Test
    void createRequirementSet() {

        Set <String> strings = new HashSet<>();
        strings.add("test1");
        strings.add("test2");
        strings.add("test3");
        Set <Requirement> requirements = joc.createRequirementSet(strings);
        assertFalse(requirements.isEmpty());
        assertEquals(3 , requirements.size());



    }

    @Test
    void createBenefitSet() {
        Set <String> strings = new HashSet<>();
        strings.add("test1");
        strings.add("test2");
        strings.add("test3");
        Set <Benefit> benefits = joc.createBenefitSet(strings);
        assertFalse(benefits.isEmpty());
        assertEquals(3 , benefits.size());
    }

    @Test
    void createSkillSet() {
        Set <String> strings = new HashSet<>();
        strings.add("test1");
        strings.add("test2");
        strings.add("test3");
        Set <Skill> skills = joc.createSkillSet(strings);
        assertFalse(skills.isEmpty());
        assertEquals(3 , skills.size());
    }


    @Test
    void getJobFromJobID() {
        Job testjob = jobRepository.getFullJob(3);
        assertEquals(testjob.getId() , joc.getJobFromJobID(testjob.getId()).getId());
        assertEquals(testjob.getDescription() , joc.getJobFromJobID(testjob.getId()).getDescription());
        assertEquals(testjob.getTitle() , joc.getJobFromJobID(testjob.getId()).getTitle());
    }

    @Test
    void getRequirementFromID() {
        RequirementDTO requirementDTO = requirementRepository.getDescription(51);
        assertEquals(requirementDTO.getDescription(), joc.getRequirementFromID(51).getDescription());
        assertEquals(requirementDTO.getId(), joc.getRequirementFromID(51).getId());
    }

    @Test
    void getBenefitFromID() {
        BenefitDTO benefitDTO = benefitRepository.getDescription(-35);
        assertEquals(benefitDTO.getDescription() , joc.getBenefitFromID(-35).getDescription());
        assertEquals(benefitDTO.getDescription() , joc.getBenefitFromID(-35).getDescription());
    }
}