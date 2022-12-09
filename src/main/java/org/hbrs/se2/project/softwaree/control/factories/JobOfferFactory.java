package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.BenefitDTO;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.dtos.RequirementDTO;
import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.hbrs.se2.project.softwaree.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class JobOfferFactory {
  @Autowired
  CompanyRepository repoC;
  public static Job createJobOffer(JobDTO jobDTO, Company company){
    Job job = new Job();

    job.setTitle(jobDTO.getTitle());
    job.setDescription(jobDTO.getDescription());
    job.setLocation(jobDTO.getLocation());
    job.setCompany(company);
    job.setCreation_date(jobDTO.getCreation_date());
    job.setDeadline(jobDTO.getDeadline());
    job.setLast_edit(jobDTO.getLast_edit());

    return job;
  }
  public static Requirement createRequirementOffer(RequirementDTO requirementDTO){
    Requirement requirement = new Requirement();
    requirement.setDescription(requirementDTO.getDescription());
    return requirement;
  }
  public static Benefit createBenefitOffer(BenefitDTO benefitdto){
    Benefit benefit = new Benefit();
    benefit.setDescription(benefitdto.getDescription());
    return benefit;
  }
}

