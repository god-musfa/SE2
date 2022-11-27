package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.BlacklistDTO;
import org.hbrs.se2.project.softwaree.entities.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlacklistRepository  extends JpaRepository<Blacklist, Integer> {

    @Query("SELECT new org.hbrs.se2.project.softwaree.dtos.BlacklistDTO(student_id, company_id) FROM Blacklist WHERE student_id = ?1")
    List<BlacklistDTO> findBlockedCompanys(Integer student_id);

}
