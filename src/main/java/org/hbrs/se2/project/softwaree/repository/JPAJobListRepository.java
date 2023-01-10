package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAJobListRepository
        extends JpaRepository<Job, Integer>, JobListRepository {

}