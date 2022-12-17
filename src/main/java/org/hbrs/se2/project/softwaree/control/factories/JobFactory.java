package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.hbrs.se2.project.softwaree.entities.Skill;

import java.util.Set;

public class JobFactory {

    public static JobDTO createDTO(Job job, Set<Benefit> ben, Set<Skill> ski, Set<Requirement> req) {
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                job.getCreationDate(),
                job.getLastEdit(),
                job.getDeadline(),
                job.getDescription(),
                job.getLocation(),
                job.getViews(),
                job.getCompany(),
                ben,
                req,
                ski
        );
    }

}
