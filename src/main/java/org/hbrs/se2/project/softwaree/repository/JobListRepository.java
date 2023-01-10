package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.JobListDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface JobListRepository {
    List<JobListDTO> getJobList(String searchTerm, Integer studentID, Set<String> skillSet, Integer avgRating, LocalDate timeLimit);

    @Transactional
    List<JobListDTO> getJobByCompanyID(Integer companyID);
}