package org.hbrs.se2.project.hellocar.views;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;

@PageTitle("Kontakt")
@Route(value = "kontakt", layout = AppView.class)
@CssImport("./styles/views/contact/contact-view.css")
public class ContactView extends VerticalLayout  {

    private final int limit = 10000;
    private int jobID = 1;
    //private companieTDO companie;
    //private studentTDO student;
    //private jobTDO job;

    //private Binder<CarDTOImpl> binder = new Binder(CarDTOImpl.class);

    Button cancel = new Button("Abbrechen");
    Button save = new Button("Speichern");

    Details jobDetails = null;

    public ContactView() throws InterruptedException {
        addClassName("contact-view");

        add(createTitle());
        add(createContentbox());
        add(createButton());
        setSpacing(false);

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
                new FormLayout.ResponsiveStep("440px", 2)); //todo an allgemeine groessen anpassen

        return layout;
    }


    private Component createCompanybox() {
        Span name = new Span("companie.getName()");
        Span adress = new Span("companie.getStreet() + ' ' + companie.getNumber() + ', ' + companie.getPostalCode() + ' ' + companie.getCity()");
        Span email = new Span("companie.getMail()");
        Span phone = new Span("companie.getPhone()");
        Span website = new Span("companie.getWebsite()");
        Span field = new Span("companie.getField()");
        Span size = new Span("companie.getSize()");

        VerticalLayout content = new VerticalLayout(name, adress, email, phone, website, field, size);
        content.setSpacing(false);
        content.setPadding(false);
        content.setId("company");

        Details details = new Details("Unternehmensdaten", content);
        details.setOpened(true);

        return details;
    }

    private Component createStudentbox() {
        Span name = new Span("student.getFirstName() + ' ' + student.getLastName()");
        Span adress = new Span("student.getStreet() + ' ' + student.getNumber() + ', ' + student.getPostalCode() + ' ' + student.getCity()");
        Span email = new Span("student.getMail()");
        Span birthday = new Span("student.getBirthday()");
        Span semester = new Span("student.getSemester()");

        VerticalLayout content = new VerticalLayout(name, adress, email, birthday, semester);
        content.setSpacing(false);
        content.setPadding(false);
        content.setId("student");

        Details details = new Details("Meine Daten" + " pp", content);
        details.setOpened(true);

        return details;
    }

    private Component createJobbox() {
        Span title = new Span("job.title()");
        title.setId("jobTitle");
        Span beschreibung = new Span("job.description()");
        beschreibung.setId("jobBeschreibung");

        VerticalLayout jobContent = new VerticalLayout(title, beschreibung);
        jobContent.setSpacing(false);
        jobContent.setPadding(false);
        jobContent.setId("jobBox");

        jobDetails = new Details("Job Übersicht", jobContent);
        jobDetails.setOpened(true);

        return jobDetails;
    }

    private Component createMessage() {
        TextArea nachricht = new TextArea();
        nachricht.setWidthFull();
        nachricht.setLabel("Nachricht");
        nachricht.setMinLength(20);
        nachricht.setMaxLength(limit);
        nachricht.setMinHeight("15em");
        nachricht.setMaxHeight("30%");
        nachricht.setValueChangeMode(ValueChangeMode.EAGER);
        nachricht.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + limit);
        });
        nachricht.setId("nachrichtenbox");
        nachricht.setPlaceholder("Ihre Nachricht an das Unternehmen");
        return nachricht;
    }

    private Component createButton() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-area");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

}
