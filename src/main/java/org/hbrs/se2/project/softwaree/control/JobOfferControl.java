package org.hbrs.se2.project.softwaree.control;


import org.hbrs.se2.project.softwaree.control.factories.JobOfferFactory;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.repository.CompanyRepository;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JobOfferControl {
  @Autowired
  JobRepository repoJ;
  @Autowired
  CompanyRepository repoC;
  private final org.hbrs.se2.project.softwaree.repository.JobRepository JobRepository;
  public JobOfferControl(JobRepository JobRepository) {
    this.JobRepository = JobRepository;
  }
  public void createJobOffer(JobDTO JobDTO, Integer Id){
    Company company = repoC.findById(Id).get();
    Job jobEntity = JobOfferFactory.createJobOffer(JobDTO,company);
    repoJ.save(jobEntity);
  }

}
