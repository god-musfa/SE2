package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface JobRepository extends JpaRepository<Job, Integer> {


    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO(id, title, description, location) FROM Job  WHERE id = ?1")
    JobDTO findJobWithTitleDescriptionLocation(int jobID);

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO(id, title, creation_date, deadline, description, location) FROM Job  WHERE id = ?1")
    JobDTO findJobWithTitleCreationDateDeadlineDescriptionLocation(int jobID);

    @Query("select j from Job j where j.id = ?1")
    Optional<Job> getFullJobInfo(Integer id);

    @Query("select j from Job j where j.id = ?1")
    JobDTO getFullJob(Integer id);

    @Query("select (count(j) > 0) from Job j where j.id = ?1")
    boolean checkJobExists(Integer id);

    @Query(value = "SELECT j FROM Job j WHERE j.company.id NOT IN :ids")
    List<Job> findAllNotBlockedCompanys(@Param("ids") List<Integer> idList);

    @Query(value = "SELECT j FROM Job j WHERE j.company.id = ?1")
    List<Job> readJobsFromCompany(Integer id);

}
