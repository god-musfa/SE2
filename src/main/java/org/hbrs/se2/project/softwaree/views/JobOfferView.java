package org.hbrs.se2.project.softwaree.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.control.JobOfferControl;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.util.Globals;
import java.time.LocalDate;
import java.util.Locale;

@Route(value = Globals.Pages.JOB_OFFER, layout = NavBar.class)
@PageTitle("Stellenanzeige erstellen")
@CssImport("./styles/views/main/main-view.css")
public class JobOfferView extends Div {
    // Accordion for splitting profile information settings into different topics
    private final Accordion profileSettingsAccordion = new Accordion();

    // Base components for both user types
    private TextField title = new TextField("Titel");
    private final DatePicker creationDate = new DatePicker("Creation Date");
    private final DatePicker lastEdit = new DatePicker("Last Edit");
    private final DatePicker deadline = new DatePicker("Deadline");
    private TextField description = new TextField("Beschreibung");
    final TextField contactPerson = new TextField("Kontaktperson");
    private TextField location = new TextField("Location");
    private final Locale germanLocale = new Locale("de", "DE");
    private Button saveButton = new Button( "Save");
    private Button cancelButton = new Button( "Cancel");


    // Form Layouts for splitting user settings into two parts (public information, personal information)
    private final FormLayout publicInfoForm = new FormLayout();


    // Components for companies:
    // To DO  Get Company Details

    UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    JobOfferControl jc;

    private Binder<JobDTO> binder2 = new Binder<>(JobDTO.class);



    // Methods to setup components:

    /**
     * Sets up all components needed by student user and adds them to the main Accordion.
     */
    private void setupJobOfferComponents() {



        // Responsive layout specification:
        publicInfoForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.SMARTPHONE_LANDSCAPE, 4),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.WORKSTATION, 8 )
        );

        // Patterns and rules for input validation:
        deadline.setMin(LocalDate.now());

        /*
        // E-Mail field settings:
        email.setLabel("E-Mail Adresse");
        email.setPlaceholder("username@example.com");
        email.setErrorMessage("Bitte geben Sie eine gültige E-Mail Adresse ein!");
        email.setClearButtonVisible(true);

         */

        // Locale settings (for birthday picker):
        deadline.setLocale(germanLocale);


        // Placeholders for arrangement of form fields:

        Div titlePlaceholder = new Div();
        Div creationDatePlaceholder = new Div();
        Div lastEditPlaceholder = new Div();
        Div deadlinePlaceholder = new Div();
        Div descriptionPlaceholder = new Div();
        Div locationPlaceholder = new Div();
        Div viewsPlaceholder = new Div();
        Div companyPlaceholder = new Div();



        // Add all components to publicInfoForm container:

        publicInfoForm.add(title);
        publicInfoForm.add(titlePlaceholder);
        publicInfoForm.add(creationDate);
        publicInfoForm.add(creationDatePlaceholder);
        publicInfoForm.add(lastEdit);
        publicInfoForm.add(lastEditPlaceholder);
        publicInfoForm.add(deadline);
        publicInfoForm.add(deadlinePlaceholder);
        publicInfoForm.add(description);
        publicInfoForm.add(descriptionPlaceholder);
        publicInfoForm.add(location);
        publicInfoForm.add(locationPlaceholder);



        // Configure colspan of components inside of container:
        publicInfoForm.setColspan(title, 2);
        publicInfoForm.setColspan(titlePlaceholder, 4);

        publicInfoForm.setColspan(creationDate, 2);
        publicInfoForm.setColspan(creationDatePlaceholder, 4);

        publicInfoForm.setColspan(lastEdit, 2);
        publicInfoForm.setColspan(lastEditPlaceholder, 4);

        publicInfoForm.setColspan(deadline, 2);
        publicInfoForm.setColspan(deadlinePlaceholder, 4);

        publicInfoForm.setColspan(description, 2);
        publicInfoForm.setColspan(descriptionPlaceholder, 4);

        publicInfoForm.setColspan(location, 2);
        publicInfoForm.setColspan(locationPlaceholder, 4);




        // Second outter container to implement padding - (padding is sexy!):
        VerticalLayout publicjobOfferPaddingContainer = new VerticalLayout();
        publicjobOfferPaddingContainer.add(publicInfoForm);
        publicjobOfferPaddingContainer.setPadding(true);



        // Button design
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton,
                cancelButton);

        profileSettingsAccordion.add("Stellenanzeige", publicjobOfferPaddingContainer);
        publicjobOfferPaddingContainer.add(buttonLayout);

        binder2.setBean(new JobDTO());
        binder2.bindInstanceFields(this);


        saveButton.addClickListener(e -> {
            UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
            System.out.println(binder2.getBean().getDescription());
            jc.createJobOffer(binder2.getBean(), userDTO.getId());

            Notification notification = Notification
                    .show("Daten gespeichert!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        } );






    }




/*
    private void setupCompanyComponents() {

        // E-Mail field:
        email.setLabel("E-Mail Adresse");
        email.setPlaceholder("username@example.com");
        email.setErrorMessage("Bitte geben Sie eine gültige E-Mail Adresse ein!");
        email.setClearButtonVisible(true);

        // Public information form layout:
        publicInfoForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("20em", 2)
        );



        // Container to implement padding
        VerticalLayout publicProfilePaddingContainer = new VerticalLayout();
        publicProfilePaddingContainer.add(publicInfoForm);
        publicProfilePaddingContainer.setPadding(true);

        // Button design
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton,
                cancelButton);


        // Add the two type components to accordion:
        profileSettingsAccordion.add("Profilangaben", publicProfilePaddingContainer);
        publicProfilePaddingContainer.add(buttonLayout);

        Binder<CompanyDTO> binder = new Binder<>(CompanyDTO.class);
        binder.bindInstanceFields(this);
        binder.setBean(jc.getCompanyFromUser(userDTO));

        Binder<AddressDTO> binderAdress = new Binder<>(AddressDTO.class);
        binderAdress.bindInstanceFields(this);
        binderAdress.setBean(jc.getAdressFromUser(userDTO));

        Binder<UserDTO> binderEmail = new Binder<>(UserDTO.class);
        binderEmail.bindInstanceFields(this);
        binderEmail.setBean(userDTO);

        saveButton.addClickListener(e -> {
            UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);

            jc.createCompany(binder.getBean());
            jc.createAddress(binderAdress.getBean(), userDTO);

            Notification notification = Notification
                    .show("Daten gespeichert!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        } );



    }


 */

    public JobOfferView(JobOfferControl jc) {
        this.jc = jc;
        addClassName("job-offer-view");
        UserDTO userdto = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
        String userType = userDTO.getUserType();

        // Check account type to show corresponding content:
        switch (userType) {
            case "company": {
                setupJobOfferComponents();
                add(profileSettingsAccordion);
                break;
            }

        }
    }


}
