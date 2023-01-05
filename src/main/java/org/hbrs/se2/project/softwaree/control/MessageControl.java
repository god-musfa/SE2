package org.hbrs.se2.project.softwaree.control;

import com.vaadin.flow.component.messages.MessageListItem;
import org.hbrs.se2.project.softwaree.control.factories.MessageListItemFactory;
import org.hbrs.se2.project.softwaree.dtos.ApplicationDTO;
import org.hbrs.se2.project.softwaree.dtos.MessageDTO;
import org.hbrs.se2.project.softwaree.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class MessageControl {
    @Autowired
    MessageRepository mess;
    public Set<ApplicationDTO> getJobsForStudent(Integer id) {
        System.out.println("Here");
        System.out.println(mess.getJobsForStudent(id).isEmpty());
        return mess.getJobsForStudent(id);
    }
    public Set<ApplicationDTO> getJobsForCompany(Integer id) {
        return mess.getJobsForCompany(id);
    }

    public Collection<MessageListItem> getMessagesForStudentAndJob(Integer id, Integer jobID) {
        List<MessageDTO> studentMessages = mess.getMessagesSentByStudent(id,jobID);
        List<MessageDTO> companyMessages = mess.getMessagesReceivedByStudent(id,jobID);
        studentMessages.addAll(companyMessages);
        return MessageListItemFactory.createMessageListItemList(studentMessages);
    }

    public Collection<MessageListItem> getMessagesForCompanyAndJob(Integer id, Integer jobID) {
        List<MessageDTO> studentMessages = mess.getMessagesSentByCompany(id,jobID);
        List<MessageDTO> companyMessages = mess.getMessagesReceivedByCompany(id,jobID);
        studentMessages.addAll(companyMessages);
        return MessageListItemFactory.createMessageListItemList(studentMessages);
    }
}
