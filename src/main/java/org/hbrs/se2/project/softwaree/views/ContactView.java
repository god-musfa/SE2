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

@PageTitle("Kontakt")
@Route(value = "kontakt", layout = NavBar.class)
@CssImport("./styles/views/contact/contact-view.css")
public class ContactView extends VerticalLayout  {
    private final int limit = 10000; //Zeichenlimit im Eingabefeld
    UserDTO user = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    private int jobID = -1;
    private int companyID;
    ContactControl cc;
    Button cancel = new Button("Feld leeren");
    Button save = new Button("Speichern");

    private final Binder<MessageDTO> binderContact = new Binder<>(MessageDTO.class);

    public ContactView(ContactControl cc) {
        this.cc = cc;

        UI.getCurrent().getSession().setAttribute( "companyID", 3 ); //todo entferne sobald links zur seite korrekt implementiert wurden
        if(UI.getCurrent().getSession().getAttribute("companyID") != null) {
            companyID =  (Integer) UI.getCurrent().getSession().getAttribute("companyID");


            UI.getCurrent().getSession().setAttribute( "jobID", 1 ); //todo entferne sobald die links zur seite korrekt implementiert wurden
            if(UI.getCurrent().getSession().getAttribute( "jobID" ) != null) {
                jobID = (Integer) UI.getCurrent().getSession().getAttribute( "jobID" );
            }

            addClassName("contact-view");

            add(createTitle());
            add(createContentbox());
            add(createButton());
            setSpacing(false);

            cancel.addClickListener(event -> clearForm());

            save.addClickListener(e -> {
                cc.createContact(user.getId(), companyID, jobID, binderContact.getBean());

                Notification.show("Nachricht übermittelt!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                clearForm();
            });
        }
        else {
            add(createTitle());
            add(new Paragraph("Fehler. Bitte das Formular über eine Firma oder ein Jobangebot aufrufen."));
            Notification.show("Fehler! Es konnte keine Firma mit dieser Nachricht verknüpft werden")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    private Component createTitle() {
        return new H3("Kontaktformular");
    }

    private Component createContentbox() {
        //Create Components
        FormLayout layout = new FormLayout();
        Component companybox = createCompanybox();
        Component studentenbox = createStudentbox();
        Component jobBox = createJobbox();
        Component message = createMessage();

        //Build Layout
        layout.add(
                companybox,
                studentenbox
        );
        if(jobID != -1) {
            layout.add(jobBox);
            layout.setColspan(jobBox, 2);
        }
        layout.add(message);
        layout.setColspan(message, 2);

        layout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.SMARTPHONE_LANDSCAPE, 2)
        );
        return layout;
    }


    private Component createCompanybox() {
        Span name = new Span();
        Span address = new Span();
        Span email = new Span();
        Span phone = new Span();
        Span website = new Span();
        Span field = new Span();
        Span size = new Span();

        Binder<CompanyDTO> binder =
                new Binder<>(CompanyDTO.class);
        binder.forField(new ReadOnlyHasValue<>(name::setText)).bind(CompanyDTO::getName, null);
        binder.forField(new ReadOnlyHasValue<>(phone::setText)).bind(CompanyDTO::getPhoneNumber, null);
        binder.forField(new ReadOnlyHasValue<>(website::setText)).bind(CompanyDTO::getWebsite, null);
        binder.forField(new ReadOnlyHasValue<>(field::setText)).bind(CompanyDTO::getField, null);
        binder.forField(new ReadOnlyHasValue<>(size::setText)).bind(CompanyDTO::getSize, null);
        binder.readBean(cc.getCompanyFromCompanyID(companyID));
        UserDTO cUser = cc.getUserFromCompanyID(companyID); // UserDTO for the next Binders

        Binder<UserDTO> binderMail = new Binder<>(UserDTO.class);
        ReadOnlyHasValue<String> mail = new ReadOnlyHasValue<>(email::setText);
        binderMail.forField(mail).bind(UserDTO::getEmail, null);
        binderMail.readBean(cUser);

        Binder<AddressDTO> binderAddress =
                new Binder<>(AddressDTO.class);
        ReadOnlyHasValue<String> adr = new ReadOnlyHasValue<>(address::setText);
        binderAddress.forField(adr).bind(ContactControl::getFullAddress, null); //Adresse
        binderAddress.readBean(cc.getAddressFromUser(cUser));


        VerticalLayout content = new VerticalLayout(name, address, email, phone, website, field, size);
        content.setSpacing(false);
        content.setPadding(false);
        content.setId("company");

        Details details = new Details("Unternehmensdaten", content);
        details.setOpened(true);

        return details;
    }

    private Component createStudentbox() {
        Span name = new Span();
        Span address = new Span();
        Span email = new Span();
        Span birthday = new Span();
        Span universitySubject = new Span();
        Span semester = new Span();
        Span degree = new Span();

        Binder<StudentDTO> binder =
                new Binder<>(StudentDTO.class);
        binder.forField(new ReadOnlyHasValue<>(name::setText)).bind(ContactControl::getFullName, null);
        binder.forField(new ReadOnlyHasValue<>(birthday::setText)).bind(ContactControl::getBirthdayString, null); //Geburtstag
        binder.forField(new ReadOnlyHasValue<>(universitySubject::setText)).bind(ContactControl::getUniSubString, null);
        binder.forField(new ReadOnlyHasValue<>(semester::setText)).bind(ContactControl::getSemesterString, null); //Semester
        binder.forField(new ReadOnlyHasValue<>(degree::setText)).bind(ContactControl::getDegreeString, null);
        binder.readBean(cc.getStudentFromUser(user)); //vom user zum student objekt

        Binder<UserDTO> binderMail =
                new Binder<>(UserDTO.class);
        binderMail.forField(new ReadOnlyHasValue<>(email::setText)).bind(UserDTO::getEmail, null);
        binderMail.readBean(user);

        Binder<AddressDTO> binderAddress =
                new Binder<>(AddressDTO.class);
        binderAddress.forField(new ReadOnlyHasValue<>(address::setText)).bind(ContactControl::getFullAddress, null); //Adresse
        binderAddress.readBean(cc.getAddressFromUser(user));


        VerticalLayout content = new VerticalLayout(name, address, email, birthday, universitySubject, semester, degree);
        content.setSpacing(false);
        content.setPadding(false);
        content.setId("student");

        Details details = new Details("Meine Daten", content);
        details.setOpened(true);

        return details;
    }

    private Component createJobbox() {
        Span title = new Span("job.title()");
        title.setId("jobTitle");
        Span description = new Span("job.description()");
        description.setId("jobBeschreibung");
        Span location = new Span();

        Binder<JobDTO> binder =
                new Binder<>(JobDTO.class);
        binder.forField(new ReadOnlyHasValue<>(title::setText)).bind(JobDTO::getTitle, null);
        binder.forField(new ReadOnlyHasValue<>(description::setText)).bind(JobDTO::getDescription, null);
        binder.forField(new ReadOnlyHasValue<>(location::setText)).bind(ContactControl::getLocationString, null);
        binder.readBean(cc.getJobByID(jobID));

        VerticalLayout jobContent = new VerticalLayout(title, description, location);
        jobContent.setSpacing(false);
        jobContent.setPadding(false);
        jobContent.setId("jobBox");

        Details jobDetails = new Details("Job Übersicht", jobContent);
        jobDetails.setOpened(true);

        return jobDetails;
    }

    private Component createMessage() {
        TextArea message = new TextArea();
        message.setWidthFull();
        message.setLabel("Nachricht");
        message.setMaxLength(limit);
        message.setMinHeight("15em");
        message.setMaxHeight("30%");
        message.setValueChangeMode(ValueChangeMode.EAGER);
        message.addValueChangeListener(e -> e.getSource()
                .setHelperText(e.getValue().length() + "/" + limit));
        message.setId("nachrichtenbox");
        message.setPlaceholder("Ihre Nachricht an das Unternehmen");
        binderContact.forField(message).bind(MessageDTO::getMessage, MessageDTO::setMessage);
        binderContact.setBean(new MessageDTO());
        return message;
    }

    private Component createButton() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-area");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

    private void clearForm() {
        Page page = UI.getCurrent().getPage();
        page.executeJs("document.getElementById('nachrichtenbox').value = ''");
    }
}
