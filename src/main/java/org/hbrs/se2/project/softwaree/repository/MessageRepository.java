package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.MessageDTO;
import org.hbrs.se2.project.softwaree.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.MessageDTO(id,time_sent,message, student_id, company_id, job_id) FROM Message  WHERE id = ?1")
    MessageDTO findMessage(int id);
}
