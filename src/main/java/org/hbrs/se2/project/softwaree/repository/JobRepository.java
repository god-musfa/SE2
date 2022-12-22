package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
public interface JobRepository extends JpaRepository<Job, Integer> {


    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO(id, title, description, location) FROM Job  WHERE id = ?1")
    JobDTO findJobWithTitleDescriptionLocation(int jobID);

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO(id, title, creation_date, deadline, description, location) FROM Job  WHERE id = ?1")
    JobDTO findJobWithTitleCreationDateDeadlineDescriptionLocation(int jobID);

    @Query("select j from Job j LEFT JOIN FETCH j.skills LEFT JOIN FETCH j.benefits LEFT JOIN FETCH j.requirements JOIN FETCH j.company where j.id = ?1")
    Job getFullJob(Integer id);

    @Query("select (count(j) > 0) from Job j where j.id = ?1")
    boolean checkJobExists(Integer id);

    @Query(value = "SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO(j.id,j.title,j.creation_date,j.description,j.views) FROM Job j WHERE j.company.id NOT IN :ids")
    List<JobDTO> findAllNotBlockedCompanys(@Param("ids") List<Integer> idList);

    @Query(value = "SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO(j.id,j.title,j.creation_date,j.last_edit,j.deadline,j.description,j.location,j.views,j.company)  FROM Job j WHERE j.company.id = ?1")
    List<JobDTO> readJobsFromCompany(Integer id);

    @Query(value = "SELECT new org.hbrs.se2.project.softwaree.dtos.JobDTO(j.id,j.title,j.creation_date,j.last_edit,j.deadline,j.description,j.location,j.views,j.company) FROM Job j")
    List<JobDTO> getMostImportantDetailsAsList();

    @Query(value = "SELECT j.requirements FROM Job j WHERE j.id=?1")
    Set<Requirement> getRequirements(Integer id);
    @Query(value = "SELECT j.skills FROM Job j WHERE j.id=?1")
    Set<Skill> getSkills(Integer id);
    @Query(value = "SELECT j.benefits FROM Job j WHERE j.id=?1")
    Set<Benefit> getBenefits(Integer id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Job j SET j.views = j.views + ?2 WHERE j.id=?1")
    void incrementViews(Integer id, Integer increment);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Job j SET j.views=1 WHERE j.id=?1")
    void setViewsToOne(Integer id);
}
