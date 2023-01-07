package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.ApplicationDTO;
import org.hbrs.se2.project.softwaree.dtos.MessageDTO;
import org.hbrs.se2.project.softwaree.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.MessageDTO(id,timeSent,message, senderID, recipientID, jobID) FROM Message  WHERE id = ?1")
    MessageDTO findMessage(int id);
    @Transactional
    @Modifying
    @Query(value = "SELECT distinct new org.hbrs.se2.project.softwaree.dtos.ApplicationDTO(j.title,j.company.name,j.deadline,j.id,s.id,j.company.id)  FROM Message m JOIN Student s on s.id =m.senderID JOIN Job j ON m.jobID = j.id WHERE m.senderID = ?1")
    Set<ApplicationDTO> getJobsForStudent(int id);

    @Query(value = "SELECT distinct new org.hbrs.se2.project.softwaree.dtos.ApplicationDTO(s.firstName,s.lastName,j.title,j.deadline,j.id,s.id) FROM Job j " +
            "JOIN Message m ON j.id = m.jobID JOIN Application a ON j.id = a.job.id AND (a.student.id = m.recipientID OR a.student.id = m.senderID) JOIN Student s ON a.student.id = s.id WHERE m.recipientID =?1" )
    Set<ApplicationDTO> getJobsForCompany(int id);
    @Query(value="Select new org.hbrs.se2.project.softwaree.dtos.MessageDTO(m.timeSent,m.message,s.firstName,s.lastName) FROM Message m JOIN Student s on m.senderID = s.id WHERE m.senderID=?1 AND m.jobID =?2 AND m.recipientID =?3")
    List<MessageDTO> getMessagesSentByStudent(Integer id, Integer jobID, Integer companyId);

    @Query(value="Select new org.hbrs.se2.project.softwaree.dtos.MessageDTO(m.timeSent,m.message,c.name) FROM Message m JOIN Company c on m.senderID = c.id WHERE m.recipientID=?1 AND m.jobID =?2 AND m.senderID =?3")
    List<MessageDTO> getMessagesReceivedByStudent(Integer id, Integer jobID, Integer companyId);

    @Query(value="Select new org.hbrs.se2.project.softwaree.dtos.MessageDTO(m.timeSent,m.message,s.firstName,s.lastName) FROM Message m JOIN Student s on m.senderID = s.id WHERE m.recipientID=?1 AND m.jobID =?2 AND m.senderID=?3")
    List<MessageDTO> getMessagesReceivedByCompany(Integer id, Integer jobID, Integer studentID);

    @Query(value="Select new org.hbrs.se2.project.softwaree.dtos.MessageDTO(m.timeSent,m.message,c.name) FROM Message m  JOIN Company c on m.senderID = c.id WHERE m.senderID=?1 AND m.jobID =?2 AND m.recipientID =?3")
    List<MessageDTO> getMessagesSentByCompany(Integer id, Integer jobID, Integer studentID);



}
