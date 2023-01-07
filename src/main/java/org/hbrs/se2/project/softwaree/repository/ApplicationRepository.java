package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.ApplicationDTO;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Application;
import org.hbrs.se2.project.softwaree.entities.ApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {
    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.ApplicationDTO(a.student.id,a.student.firstName,a.student.lastName," +
            "a.student.semester,a.student.degree,a.student.university,a.student.subject,a.job.id,a.job.title,a.job.deadline)" +
            " FROM Application a WHERE a.job.company.id = ?1 ")
    List<ApplicationDTO> findAllApplicationByCompanyId(int id);
}