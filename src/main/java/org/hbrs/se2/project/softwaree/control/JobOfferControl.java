package org.hbrs.se2.project.softwaree.control;


import org.hbrs.se2.project.softwaree.control.factories.BenefitFactory;
import org.hbrs.se2.project.softwaree.control.factories.JobOfferFactory;
import org.hbrs.se2.project.softwaree.control.factories.RequirementFactory;
import org.hbrs.se2.project.softwaree.control.factories.SkillFactory;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.*;
import org.hbrs.se2.project.softwaree.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
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

  public void createJobOffer(JobDTO jobDTO, Integer id){
    Company company = repoC.findById(id).get();
    Job jobEntity = JobOfferFactory.createJobOffer(jobDTO,company);
    repoJ.save(jobEntity);
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

  public List<BenefitDTO> getAvailableBenefits() {
    return beneRepo.findAll().stream()
            .map(BenefitFactory::getDTO)
            .collect(Collectors.toList());
  }

  public List<RequirementDTO> getAvailableRequirements() {
    return requRepo.findAll().stream()
            .map(RequirementFactory::getDTO)
            .collect(Collectors.toList());
  }

  public List<BenefitDTO> getJobBenefits(Integer job) {
    Optional<Job> targetJob = jobRepo.getFullJobInfo(job);
    if (targetJob.isPresent()) {
      return targetJob.get().getBenefits().stream()
              .map(BenefitFactory::getDTO)
              .collect(Collectors.toList());
    } else {
      System.out.println(String.format("Cannot get benefits for user with id %d", job));
      return new ArrayList<>();       // Return empty list, if user cannot be found.
    }
  }


  public List<RequirementDTO> getJobRequirements(Integer job) {
    Optional<Job> targetJob = jobRepo.getFullJobInfo(job);
    if (targetJob.isPresent()) {
      return targetJob.get().getRequirements().stream()
              .map(RequirementFactory::getDTO)
              .collect(Collectors.toList());
    } else {
      System.out.println(String.format("Cannot get requirements for user with id %d", job));
      return new ArrayList<>();       // Return empty list, if user cannot be found.
    }
  }

  public List<SkillDTO> getJobSkills(Integer job) {
    Optional<Job> targetJob = jobRepo.getFullJobInfo(job);
    if (targetJob.isPresent()) {
      return targetJob.get().getSkills().stream()
              .map(SkillFactory::getDTO)
              .collect(Collectors.toList());
    } else {
      System.out.println(String.format("Cannot get requirements for user with id %d", job));
      return new ArrayList<>();       // Return empty list, if user cannot be found.
    }
  }

  public Set<Requirement> createRequirementSet(Set<String> requirementNames) {
    Set<Requirement> returnSet = new HashSet<>();

    for (String requirementName : requirementNames) {
      Optional<Requirement> requirementFromDB = requRepo.findByDescription(requirementName);
      if (requirementFromDB.isPresent()) {
        // Take Skill from DB and add to list:
        returnSet.add(requirementFromDB.get());
      } else {
        // Create empty skill and add description from combobox:
        Requirement newRequirement = new Requirement();
        newRequirement.setDescription(requirementName);
        returnSet.add(newRequirement);
      }
    }

    return returnSet;
  }

  public Set<Benefit> createBenefitSet(Set<String> benefitNames) {
    Set<Benefit> returnSet = new HashSet<>();

    for (String benefitName : benefitNames) {
      Optional<Benefit> benefitFromDB = beneRepo.findByDescription(benefitName);
      if (benefitFromDB.isPresent()) {
        // Take Skill from DB and add to list:
        returnSet.add(benefitFromDB.get());
      } else {
        // Create empty skill and add description from combobox:
        Benefit newBenefit = new Benefit();
        newBenefit.setDescription(benefitName);
        returnSet.add(newBenefit);
      }
    }

    return returnSet;
  }

  public Set<Skill> createSkillSet(Set<String> skillNames) {
    Set<Skill> returnSet = new HashSet<>();

    for (String skillName : skillNames) {
      Optional<Skill> skillFromDB = skillRepo.findByDescription(skillName);
      if (skillFromDB.isPresent()) {
        // Take Skill from DB and add to list:
        returnSet.add(skillFromDB.get());
      } else {
        // Create empty skill and add description from combobox:
        Skill newSkill = new Skill();
        newSkill.setDescription(skillName);
        returnSet.add(newSkill);
      }
    }

    return returnSet;
  }

  public void saveSkill(Skill skill) {
    skillRepo.save(skill);
  }
  public void saveBenefit(Benefit benefit) {
    beneRepo.save(benefit);
  }
  public void saveRequirement(Requirement requirement) {
    requRepo.save(requirement);
  }


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
