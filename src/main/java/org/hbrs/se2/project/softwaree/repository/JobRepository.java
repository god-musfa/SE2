package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.persistence.NamedNativeQuery;
import java.util.List;
@Component
public interface JobRepository extends JpaRepository<Job, Integer> {
    //@Query(value="SELECT * FROM coll.job_listing",nativeQuery = true)
    @Query("SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO( id, title, creation_date, last_edit, deadline, description, location, views, company)" +
            " FROM Job")
    List<JobDTO> findAllJobs();
    
/*    @Query(nativeQuery = true)
    List<JobDTO> findAllJobsNative();*/
}