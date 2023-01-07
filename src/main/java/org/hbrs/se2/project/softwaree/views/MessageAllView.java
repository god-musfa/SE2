package org.hbrs.se2.project.softwaree.views;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import org.hbrs.se2.project.softwaree.control.ContactControl;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.util.Date;
import java.util.List;

@PageTitle("Kontakt")
@Route(value = "messageAllStudents", layout = NavBar.class)
@CssImport("./styles/views/contact/contact-view.css")
public class MessageAllView extends VerticalLayout  {
    private static final int LIMIT = 10000; //Zeichenlimit im Eingabefeld
    private UserDTO user = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    private int jobID = -1;
    private int companyID = -1;
    private ContactControl cc;
    private Button clear = new Button("Feld leeren");
    private Button save = new Button("Speichern");

    private final Binder<MessageDTO> binderContact = new Binder<>(MessageDTO.class);
    private List<MessageDTO> userList;
    public MessageAllView(ContactControl cc) {
        this.cc = cc;
        userList = (UI.getCurrent().getSession().getAttribute("userList") != null) ? (List<MessageDTO>) UI.getCurrent().getSession().getAttribute("userList") : null;

        //UI.getCurrent().getSession().setAttribute( "companyID", 3 ); //Beispielwert fuer Debugging
        companyID = (UI.getCurrent().getSession().getAttribute("companyID") != null) ? (Integer) UI.getCurrent().getSession().getAttribute("companyID") : -1;
        if(user.getUserType().equals("company")) {
            setJobID();
            addClassName("contact-view");

            add(createTitle());
            add(createContentbox());
            add(createButton());
            setSpacing(false);

            //Button Events
            clear.addClickListener(event -> clearForm());

            save.addClickListener(e -> {
                Date date = new Date();
                for(MessageDTO m: userList){
                    m.setMessage(binderContact.getBean().getMessage());
                    m.setTimeSent(date);
                    System.out.println(m.getMessage()+ " "+ m.getTimeSent()+" "+ " "+m.getStudentID()+ " " + m.getCompanyID()+" "+ m.getJobID());
                    cc.createMessageWithCompanyAsSender(m);
                }
                Notification.show("Nachricht übermittelt!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                clearForm();
            });


        }
        else {
            //Meldungen für unterschiedliche Fehlerfälle
            if(user.getUserType().equals("student")) {
                createErrorMessage("Fehler. Als Student können sie keine anderen Studenten kontaktieren.",
                        "Fehler. Als Student können sie keine anderen Studenten kontaktieren.");
            }
            else {
                createErrorMessage("Unbekannter Fehlertyp.",
                        "Unbekannter Fehlertyp.");
            }
        }
    }

    private void setJobID() {
        //UI.getCurrent().getSession().setAttribute( "jobID", 1 ); //Beispielwert fuer Debugging
        if(UI.getCurrent().getSession().getAttribute( "jobID" ) != null) {
            jobID = (Integer) UI.getCurrent().getSession().getAttribute( "jobID" );
        }
    }

    private Component createTitle() {
        return new H3("Kontaktformular");
    }

    private Component createContentbox() {
        //Create Components
        FormLayout layout = new FormLayout();
        Component message = createMessage();

        //Build Layout


        layout.add(message);
        layout.setColspan(message, 2);

        layout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.SMARTPHONE_LANDSCAPE, 2)
        );
        return layout;
    }





    private Component createMessage() {
        TextArea message = new TextArea();
        message.setWidthFull();
        message.setLabel("Nachricht");
        message.setMaxLength(LIMIT);
        message.setMinHeight("15em");
        message.setMaxHeight("30%");
        message.setValueChangeMode(ValueChangeMode.EAGER);
        message.addValueChangeListener(e -> e.getSource()
                .setHelperText(e.getValue().length() + "/" + LIMIT));
        message.setId("nachrichtenbox");
        message.setPlaceholder("Ihre Nachricht an die Studenten");
        binderContact.forField(message).bind(MessageDTO::getMessage, MessageDTO::setMessage);
        binderContact.setBean(new MessageDTO());
        return message;
    }

    private Component createButton() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-area");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(clear);

        return buttonLayout;
    }

    private void clearForm() {
        Page page = UI.getCurrent().getPage();
        page.executeJs("document.getElementById('nachrichtenbox').value = ''");
    }

    private void backButtonEvent() {
        if ((jobID != -1)) {
            UI.getCurrent().navigate("job/" + jobID);
        } else if(companyID != -1) {
            UI.getCurrent().getPage().executeJs("window.history.back()"); //Firmenseite
        } else {
            UI.getCurrent().navigate("jobs/"); //Fehlerfall (unklar was die letzte Seite war)
        }
    }

    private void createErrorMessage(String websiteMessage, String notificationMessage) {
        addClassName("message-all-view");
        add(createTitle());
        add(new Paragraph(websiteMessage));
        Notification.show(notificationMessage)
                .addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}
