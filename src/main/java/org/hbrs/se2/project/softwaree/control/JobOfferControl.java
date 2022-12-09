package org.hbrs.se2.project.softwaree.control;


import org.hbrs.se2.project.softwaree.control.factories.JobOfferFactory;
import org.hbrs.se2.project.softwaree.control.factories.SkillFactory;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.*;
import org.hbrs.se2.project.softwaree.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class JobOfferControl {
  @Autowired
  JobRepository repoJ;
  @Autowired
  CompanyRepository repoC;

  @Autowired
  JobRepository jobRepo;

  @Autowired
  SkillRepository skillRepo;
  @Autowired
  RequirementRepository requRepo;
  @Autowired
  BenefitRepository beneRepo;
  private final org.hbrs.se2.project.softwaree.repository.JobRepository jobRepository;
  public JobOfferControl(JobRepository JobRepository) {
    this.jobRepository = JobRepository;
  }
  public void createJobOffer(JobDTO jobDTO, RequirementDTO requirementDTO, BenefitDTO benefitDTO, Integer id){
    Company company = repoC.findById(id).get();
    Job jobEntity = JobOfferFactory.createJobOffer(jobDTO,company);
    repoJ.save(jobEntity);
    /* todo
    Requirement requirementEntity = JobOfferFactory.createRequirementOffer(requirementDTO);
    requRepo.save(requirementEntity);
    Benefit benefitEntity = JobOfferFactory.createBenefitOffer(benefitDTO);
    beneRepo.save(benefitEntity);

     */
  }

  public List<SkillDTO> getAvailableSkills() {
    return skillRepo.findAll().stream()
            .map(SkillFactory::getDTO)
            .collect(Collectors.toList());
  }

  /*
  public List<SkillDTO> getJobSkills(JobDTO jobDTO) {
    Optional<Job> targetJob = jobRepo.getFullJobInfo(jobDTO.getId());
    if (targetJob.isPresent()) {
      return targetJob.get().getSkills().stream()
              .map(SkillFactory::getDTO)
              .collect(Collectors.toList());
    } else {
      System.out.println(String.format("Cannot get skills for user with id %d", jobDTO.getId()));
      return new ArrayList<>();       // Return empty list, if user cannot be found.
    }
  }

   */

  public JobDTO getJobFromJobID(int jobID) {
    return jobRepo.findJobWithTitleCreationDateDeadlineDescriptionLocation(jobID);
  }

  public RequirementDTO getRequirementFromID(int jobID) {
    return requRepo.getDescription(jobID);
  }

  public BenefitDTO getBenefitFromID(int jobID) {
    return beneRepo.getDescription(jobID);
  }
}
