package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.SkillDTO;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    @Query("select new org.hbrs.se2.project.softwaree.dtos.SkillDTO(id, description) from Skill")
    public List<SkillDTO> getAvailable();

}