package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.Optional;

@Component
public interface JobRepository extends JpaRepository<Job, Integer> {
    //@Query(value="SELECT * FROM coll.job_listing",nativeQuery = true)
    @Query("SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO( id, title, creation_date, last_edit, deadline, description, location, views, company)" +
            " FROM Job")
    List<JobDTO> findAllJobs();

/*    @Query(nativeQuery = true)
    List<JobDTO> findAllJobsNative();*/

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO(id, title, description, location) FROM Job  WHERE id = ?1")
    JobDTO findJobWithTitleDescriptionLocation(int jobID);

    @Query("select j from Job j where j.id = ?1")
    Optional<Job> getFullJobInfo(Integer id);

    @Query("select (count(j) > 0) from Job j where j.id = ?1")
    boolean checkJobExists(Integer id);



}
