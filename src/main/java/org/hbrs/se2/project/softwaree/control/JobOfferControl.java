package org.hbrs.se2.project.softwaree.control;

import com.vaadin.flow.component.UI;
import org.hbrs.se2.project.softwaree.control.factories.JobOfferFactory;
import org.hbrs.se2.project.softwaree.control.factories.StudentFactory;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
public class JobOfferControl {
  @Autowired
  JobRepository repoJ;
  private final org.hbrs.se2.project.softwaree.repository.JobRepository JobRepository;
  public JobOfferControl(JobRepository JobRepository) {
    this.JobRepository = JobRepository;
  }
  public void createJobOffer(JobDTO JobDTO){
    Job jobEntity = JobOfferFactory.createJobOffer(JobDTO);
    repoJ.save(jobEntity);
  }

}
