package org.hbrs.se2.project.softwaree.test.control;

import org.hbrs.se2.project.softwaree.control.JobOfferControl;
import org.hbrs.se2.project.softwaree.control.factories.SkillFactory;
import org.hbrs.se2.project.softwaree.dtos.BenefitDTO;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.dtos.RequirementDTO;
import org.hbrs.se2.project.softwaree.dtos.SkillDTO;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

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

    @Test
    void createJobOffer() {

    /*    JobDTO jobDTO = new JobDTO(2222, "Test", "test" ,"test");
        Company company = companyRepository.getOne(3);
        jobDTO.setCompany(company);
        joc.createJobOffer(jobDTO, 2222);
        assertEquals(jobDTO.getTitle() , jobRepository.findJobWithTitleDescriptionLocation(2).getTitle());
        jobRepository.deleteById(2222);
*/

    }

    @Test
    void getAvailableSkills() {
        Optional<Skill> testSkillOpt = skillRepository.findByDescription("Python");
        Skill testSkill  = testSkillOpt.get();
        SkillDTO testSkillDTO = new SkillDTO(testSkill.getId() , testSkill.getDescription() );
        List<SkillDTO> list = joc.getAvailableSkills();
        assertTrue(list.contains(testSkillDTO));

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