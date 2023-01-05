package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.components.CardComponent;
import org.hbrs.se2.project.softwaree.control.MessageControl;
import org.hbrs.se2.project.softwaree.dtos.ApplicationDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

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
        VerticalLayout listLayout = new VerticalLayout();
        MessageList messageList = new MessageList();

        if(user.getUserType().equals("student")){
        Set<ApplicationDTO> jobs = msg.getJobsForStudent(user.getId());
        if(jobs.isEmpty()){
            layout.add(new H1("Keine Nachrichten vorhanden"));
            return layout;
        }
        else{
        for(ApplicationDTO job: jobs){
            CardComponent c = new CardComponent(job,user.getUserType());
            CardComponent finalC1 = c;
            listLayout.add(finalC1);
            c.addClickListener(event -> {
                messageList.setItems(msg.getMessagesForStudentAndJob(user.getId(), finalC1.getJobID()));
                layout.add(messageList);});
        }
        layout.add(listLayout);
        return layout;}
    } else if (user.getUserType().equals("company")) {
            Set<ApplicationDTO> jobs = msg.getJobsForCompany(user.getId());
            if(jobs.isEmpty()){
                layout.add(new H1("Keine Nachrichten vorhanden"));
                return layout;
            }
            else {
                for (ApplicationDTO job : jobs) {
                    CardComponent c = new CardComponent(job, user.getUserType());


                    CardComponent finalC1 = c;
                    listLayout.add(finalC1);
                    c.addClickListener(event -> {
                        messageList.setItems(msg.getMessagesForCompanyAndJob(user.getId(), finalC1.getJobID()));
                        layout.add(messageList);
                    });
                }
                layout.add(listLayout);
                return layout;
            }
        }
        else {
            Notification notification = new Notification("User ist kein Unternehmen oder Student!");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            layout.add(notification);
            notification.open();
            return layout;
        }
    }
}
