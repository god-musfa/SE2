package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.springframework.beans.factory.annotation.Autowired;

public class JobOfferFactory {
  public static Job createJobOffer(JobDTO jobDTO){
    Job job = new Job();

    job.setTitle(jobDTO.getTitle());
    job.setDescription(jobDTO.getDescription());
    job.setLocation(jobDTO.getLocation());
    job.setId(jobDTO.getId());

    return job;
  }
}

