package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    //@Query(value="SELECT * FROM coll.job_listing",nativeQuery = true)
    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO(j.id, j.title, j.creation_date, j.last_edit, j.deadline, j.description, j.location, j.views)" +
            " FROM Job j")
    List<JobDTO> findAllJobs();
}