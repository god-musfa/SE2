package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Job;

public class JobFactory {

    public static JobDTO createDTO(Job job) {
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getCreation_date(),
                job.getLast_edit(),
                job.getDeadline(),
                job.getDescription(),
                job.getLocation(),
                job.getViews(),
                job.getCompany(),
                job.getBenefits(),
                job.getRequirements()
        );
    }

}
