package org.hbrs.se2.project.softwaree.views;

import antlr.debug.MessageListener;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.components.CardComponent;
import org.hbrs.se2.project.softwaree.control.MessageControl;
import org.hbrs.se2.project.softwaree.dtos.ApplicationDTO;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.hbrs.se2.project.softwaree.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@PageTitle("Nachrichten")
@Route(value = "messages", layout = NavBar.class)
public class MessageView extends HorizontalLayout{
    MessageControl msg;

    private UserDTO user = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    public MessageView(MessageControl msg) {
        this.msg = msg;
        add(createJobList());
        this.setPadding(true);
    }



    private Component createJobList(){
       HorizontalLayout layout = new HorizontalLayout();
        Set<ApplicationDTO> jobs = msg.getJobsForUser(user.getId());
        CardComponent c = new CardComponent((ApplicationDTO) jobs.toArray()[0],user.getUserType());
        layout.add(c);
        MessageList messageList = new MessageList();
        c.addClickListener(event -> {
            messageList.setItems(msg.getMessagesForUserAndJob(user.getId(),c.getJobID()));
            System.out.println(messageList.getItems().get(0).getText());
            layout.add(messageList);});

        return layout;
    }
}
