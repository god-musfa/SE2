package org.hbrs.se2.project.softwaree.control;

import com.vaadin.flow.component.messages.MessageListItem;
import org.hbrs.se2.project.softwaree.control.factories.MessageListItemFactory;
import org.hbrs.se2.project.softwaree.dtos.ApplicationDTO;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
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
    public Set<ApplicationDTO> getJobsForUser(Integer id) {
        System.out.println("Here");
        System.out.println(mess.getJobsForStudent(id).isEmpty());
        return mess.getJobsForStudent(id);
    }

    public Collection<MessageListItem> getMessagesForUserAndJob(Integer id, Integer jobID) {
        List<MessageDTO> studentMessages = mess.getStudentMessagesByUserIdAndJobId(id,jobID);
        List<MessageDTO> companyMessages = mess.getCompanyMessagesByUserIdAndJobId(id,jobID);
        studentMessages.addAll(companyMessages);
        return MessageListItemFactory.createMessageListItemList(studentMessages);
    }
}
