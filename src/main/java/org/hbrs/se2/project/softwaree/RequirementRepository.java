package org.hbrs.se2.project.softwaree;

import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

}