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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.components.LabelsComponent;
import org.hbrs.se2.project.softwaree.control.JobOfferControl;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.util.Globals;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

@Route(value = Globals.Pages.JOB_OFFER, layout = NavBar.class)
@PageTitle("Stellenanzeige erstellen")
@CssImport("./styles/views/main/main-view.css")
public class JobOfferView extends Div {
    // Accordion for splitting profile information settings into different topics
    private final Accordion profileSettingsAccordion = new Accordion();

    // Base components for both user types
    private final TextField title = new TextField("Titel");
    private final DatePicker creationDate = new DatePicker("Erstellungsdatum");
    private final DatePicker lastEdit = new DatePicker("Bearbeitungsdatum");
    private final DatePicker deadline = new DatePicker("Frist");
    private final TextField description = new TextField("Beschreibung");
    final TextField contactPerson = new TextField("Kontaktperson");
    private final TextArea requirement = new TextArea("Qualifikation");
    private final TextArea benefit = new TextArea("Wir bieten");
    private final TextField location = new TextField("Standort");
    private final Locale germanLocale = new Locale("de", "DE");
    private final Button saveButton = new Button( "Speichern");

    private final Button backButton = new Button("Zurück");


    // Form Layouts for splitting user settings into two parts (public information, personal information)
    private final FormLayout publicInfoForm = new FormLayout();


    // Components for companies:
    // To DO  Get Company Details

    UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    JobOfferControl jc;

    private final Binder<JobDTO> binder2 = new Binder<>(JobDTO.class);



    // Methods to setup components:

    /**
     * Sets up all components needed by student user and adds them to the main Accordion.
     */
    private void setupJobOfferComponents() {

        int jobID = ((UI.getCurrent().getSession().getAttribute( "jobID" ) != null) ? ((Integer) UI.getCurrent().getSession().getAttribute( "jobID" )) : -1);

        // Responsive layout specification:
        publicInfoForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.SMARTPHONE_LANDSCAPE, 4),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.WORKSTATION, 8 )
        );

        // Patterns and rules for input validation:
        deadline.setMin(LocalDate.now());

        // Locale settings (for birthday picker):
        deadline.setLocale(germanLocale);


        // Placeholders for arrangement of form fields:

        Div titlePlaceholder = new Div();
        Div creationDatePlaceholder = new Div();
        Div lastEditPlaceholder = new Div();
        Div deadlinePlaceholder = new Div();
        Div descriptionPlaceholder = new Div();
        Div locationPlaceholder = new Div();
        Div requirementPlaceholder = new Div();
        Div benefitPlaceholder = new Div();
        Div skillPlaceholder = new Div();

        creationDate.setReadOnly(true);

        // Add all components to publicInfoForm container:

        publicInfoForm.add(title);
        publicInfoForm.add(titlePlaceholder);
        publicInfoForm.add(creationDate);
        publicInfoForm.add(deadline);
        publicInfoForm.add(deadlinePlaceholder);
        publicInfoForm.add(description);
        publicInfoForm.add(descriptionPlaceholder);
        publicInfoForm.add(location);
        publicInfoForm.add(locationPlaceholder);

        LabelsComponent requirement = new LabelsComponent(jc.getAvailableRequirements(), "requirements", "Qualifikation");
        for (RequirementDTO requirementDTO : jc.getJobRequirements(jobID)){
            requirement.addLabel(requirementDTO.getDescription());
        }
        publicInfoForm.add(requirement, 4);
        publicInfoForm.add(requirementPlaceholder);

        LabelsComponent benefit = new LabelsComponent(jc.getAvailableBenefits(), "benefits", "Leistung");
        for (BenefitDTO benefitDTO : jc.getJobBenefits(jobID)){
            benefit.addLabel(benefitDTO.getDescription());
        }
        publicInfoForm.add(benefit, 4);
        publicInfoForm.add(benefitPlaceholder);

        LabelsComponent skill = new LabelsComponent(jc.getAvailableSkills(), "Skills", "Fähigkeit");
        for (SkillDTO skillDTO : jc.getJobSkills(jobID)){
            skill.addLabel(skillDTO.getDescription());
        }
        publicInfoForm.add(skill, 4);
        publicInfoForm.add(skillPlaceholder);




        // Configure colspan of components inside of container:
        publicInfoForm.setColspan(title, 2);
        publicInfoForm.setColspan(titlePlaceholder, 6);

        publicInfoForm.setColspan(creationDate, 1);
        publicInfoForm.setColspan(deadline, 1);
        publicInfoForm.setColspan(deadlinePlaceholder, 6);

        publicInfoForm.setColspan(description, 4);
        publicInfoForm.setColspan(descriptionPlaceholder, 4);

        publicInfoForm.setColspan(location, 2);
        publicInfoForm.setColspan(locationPlaceholder, 6);

        publicInfoForm.setColspan(requirementPlaceholder, 4);

        publicInfoForm.setColspan(benefitPlaceholder, 4);




        // Second outter container to implement padding - (padding is sexy!):
        VerticalLayout publicjobOfferPaddingContainer = new VerticalLayout();
        publicjobOfferPaddingContainer.add(publicInfoForm);
        publicjobOfferPaddingContainer.setPadding(true);



        // Button design
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, backButton);

        profileSettingsAccordion.add("Stellenanzeige", publicjobOfferPaddingContainer);
        publicjobOfferPaddingContainer.add(buttonLayout);

        binder2.bindInstanceFields(this);

        if(jobID != -1) {
            binder2.setBean(jc.getJobFromJobID(jobID));
        }
        else {
            binder2.setBean(new JobDTO());
        }

        saveButton.addClickListener(e -> {
            UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
            System.out.println(binder2.getBean().getDescription());

            JobDTO currentJob = binder2.getBean();

            Set<Benefit> currentBenefitSet = jc.createBenefitSet(benefit.getNames());
            for (Benefit b : currentBenefitSet) {
                jc.saveBenefit(b);
            }
            currentJob.setBenefits(currentBenefitSet);

            Set<Requirement> currentRequirementSet = jc.createRequirementSet(requirement.getNames());
            for (Requirement r : currentRequirementSet) {
                System.out.println(r.getId());
                jc.saveRequirement(r);
            }
            currentJob.setRequirement(currentRequirementSet);

            Set<Skill> currentSkillSet = jc.createSkillSet(skill.getNames());
            for (Skill s : currentSkillSet) {
                jc.saveSkill(s);
            }
            currentJob.setSkills(currentSkillSet);

            currentJob.setLast_edit(LocalDate.now());

            jc.createJobOffer(currentJob, userDTO.getId());

            Notification notification = Notification
                    .show("Daten gespeichert!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            UI.getCurrent().navigate("jobs/");
        } );

        backButton.addClickListener(e -> {
            UI.getCurrent().getPage().executeJs("window.history.back()");
        } );
    }

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
