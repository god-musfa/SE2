package org.hbrs.se2.project.softwaree.views;


import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import org.apache.catalina.webresources.FileResource;
import org.hbrs.se2.project.softwaree.components.BlacklistComponent;
import org.hbrs.se2.project.softwaree.components.SkillsComponent;
import org.hbrs.se2.project.softwaree.components.SoftwareeAvatar;
import org.hbrs.se2.project.softwaree.control.EditProfileControl;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.util.Globals;
import com.vaadin.flow.component.notification.Notification;
import org.hbrs.se2.project.softwaree.util.ProfilePictureService;
import org.springframework.context.annotation.Profile;


/**
 * @author dheil2s, myild22s
 * AppView to view and change profile information of the current user.
 * This AppView handles both, students and universities.
 **/

@Route(value = Globals.Pages.EDIT_PROFILE, layout = NavBar.class)
@PageTitle("Profil bearbeiten")
@CssImport("./styles/views/main/main-view.css")
public class EditProfileView extends Div {
    // Accordion for splitting profile information settings into different topics
    private final Accordion profileSettingsAccordion = new Accordion();

    // Base components for both user types
    private TextField street = new TextField("Straße");
    private TextField number = new TextField("Hausnummer");
    private TextField postalCode = new TextField("Postleitzahl");
    private TextField city = new TextField("Ort");
    private EmailField email = new EmailField("E-Mail");


    private Button saveButton = new Button( "Save");
    private Button cancelButton = new Button( "Cancel");



    // Profile picture layout
    private final SoftwareeAvatar profileAvatar = new SoftwareeAvatar(true);
    private final SoftwareeAvatar companyAvatar = new SoftwareeAvatar(true);



    // Form Layouts for splitting user settings into two parts (public information, personal information)
    private final FormLayout publicInfoForm = new FormLayout();


    // Components for students:
    private final TextField firstName = new TextField("Vorname");
    private final TextField lastName = new TextField("Nachname");
    private final Locale germanLocale = new Locale("de", "DE");
    private final DatePicker birthday = new DatePicker("Geburtsdatum");
    private final IntegerField semester = new IntegerField();
    private final ComboBox<String> degree = new ComboBox<String>("Abschluss");
    private final TextField university = new TextField("Hochschule");
    private final TextField subject = new TextField("Studiengang");


    // Components for companies:
    final TextField contactPerson = new TextField("Kontaktperson");

    final TextField companyName = new TextField("Unternehmensname");

    final TextField phoneNumber = new TextField("Telefonnummer");
    final ComboBox<String> field = new ComboBox<>("Branche");
    final RadioButtonGroup<String> size = new RadioButtonGroup<>();
    final TextField website = new TextField("Webseite");

    UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    EditProfileControl pc;

    private Dialog profilePictureFeedbackDialog = new Dialog();

    // Methods to setup components:

    /**
     * Sets up all components needed by student user and adds them to the main Accordion.
     */
    private void setupStudentComponents() {

        // Responsive layout specification:
        publicInfoForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.SMARTPHONE_LANDSCAPE, 4),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.WORKSTATION, 8 )
        );

        // Patterns and rules for input validation:
        //v
        number.setPattern("^[0-9]*[A-Za-z]{0,1}$");
        number.setMaxLength(3);
        number.setErrorMessage("Diese Hausnummer ist ungültig!");
        postalCode.setRequired(true);

        postalCode.setMaxLength(5);
        postalCode.setPattern("^[0-9]{5}$");
        postalCode.setErrorMessage("Diese Postleitzahl ist ungültig!");
        birthday.setMax(LocalDate.now());
        degree.setAllowCustomValue(false);

        // E-Mail field settings:
        email.setLabel("E-Mail Adresse");
        email.setPlaceholder("username@example.com");
        email.setErrorMessage("Bitte geben Sie eine gültige E-Mail Adresse ein!");
        email.setClearButtonVisible(true);


        // Locale settings (for birthday picker):
        birthday.setLocale(germanLocale);
        birthday.setReadOnly(true);

        // Set Settings for semester value:
        semester.setLabel("Semester");
        semester.setHelperText("Ihr aktuelles Semester");
        semester.setMin(1);
        semester.setMax(50);
        semester.setHasControls(true);

        // Profile picture section:
        profileAvatar.setSize("6rem");
        profileAvatar.setUploadController(pc, userDTO);
        profileAvatar.setImage(userDTO.getProfilePic());

        // Graduation dropdown settings:
        String[] studyGraduations = {"B. Sc.", "M. Sc.", "Diplom", "Magister", "Dr.", "Langzeitstudent"};
        degree.setItems(studyGraduations);

        // Placeholders for arrangement of form fields:
        Div namePlaceholder = new Div();
        Div streetPlaceholder = new Div();
        Div cityPlaceholder = new Div();
        Div mailBirthdayPlaceholder = new Div();
        Div studyInfoPlaceholder = new Div();
        Div universityInfoPlaceholder = new Div();
        Div profilePicturePlaceholder = new Div();

        // Prefill
        Binder<StudentDTO> binder = new Binder(StudentDTO.class);
        binder.bindInstanceFields(this);
        binder.setBean(pc.getStudentFromUser(userDTO));

        Binder<AddressDTO> binderAdress = new Binder<>(AddressDTO.class);
        binderAdress.bindInstanceFields(this);
        binderAdress.setBean(pc.getAdressFromUser(userDTO));

        Binder<UserDTO> binderEmail = new Binder<>(UserDTO.class);
        binderEmail.bindInstanceFields(this);
        binderEmail.readBean(userDTO);
        binderEmail.setReadOnly(true);

        // Add all components to publicInfoForm container:
        publicInfoForm.add(profileAvatar);
        publicInfoForm.add(profilePicturePlaceholder);
        publicInfoForm.add(firstName);
        publicInfoForm.add(lastName);
        publicInfoForm.add(namePlaceholder);

        publicInfoForm.add(street);
        publicInfoForm.add(number);
        publicInfoForm.add(streetPlaceholder);

        publicInfoForm.add(postalCode);
        publicInfoForm.add(city);
        publicInfoForm.add(cityPlaceholder);

        publicInfoForm.add(email);
        publicInfoForm.add(birthday);
        publicInfoForm.add(mailBirthdayPlaceholder);

        publicInfoForm.add(subject);
        publicInfoForm.add(semester);
        publicInfoForm.add(studyInfoPlaceholder);

        publicInfoForm.add(university);
        publicInfoForm.add(degree);
        publicInfoForm.add(universityInfoPlaceholder);


        // Configure colspan of components inside of container:
        publicInfoForm.setColspan(profileAvatar, 2);
        publicInfoForm.setColspan(profilePicturePlaceholder, 7);

        publicInfoForm.setColspan(firstName, 2);
        publicInfoForm.setColspan(lastName, 2);
        publicInfoForm.setColspan(namePlaceholder, 4);

        publicInfoForm.setColspan(street, 2);
        publicInfoForm.setColspan(number, 1);
        publicInfoForm.setColspan(streetPlaceholder, 5);

        publicInfoForm.setColspan(city, 3);
        publicInfoForm.setColspan(postalCode, 1);
        publicInfoForm.setColspan(cityPlaceholder, 4);

        publicInfoForm.setColspan(email, 2);
        publicInfoForm.setColspan(birthday, 2);
        publicInfoForm.setColspan(mailBirthdayPlaceholder, 4);

        publicInfoForm.setColspan(semester, 1);
        publicInfoForm.setColspan(subject, 2);
        publicInfoForm.setColspan(studyInfoPlaceholder, 5);

        publicInfoForm.setColspan(degree, 2);
        publicInfoForm.setColspan(university, 2);
        publicInfoForm.setColspan(universityInfoPlaceholder, 4);

        // Skills:
        SkillsComponent skills = new SkillsComponent(pc.getAvailableSkills());
        for (SkillDTO skillDTO : pc.getStudentSkills(userDTO)){
            skills.addSkill(skillDTO.getDescription());
        }
        publicInfoForm.add(skills, 4);

        // Remove Blacklist:
        BlacklistComponent blacklist = new BlacklistComponent(pc.getBlockedCompanys(userDTO.getId()),"Blockierte Unternehmen. Klicke zum freigeben.");
        if(blacklist.isFilled()) {
            publicInfoForm.add(blacklist, 4);
        }

        // Second outter container to implement padding - (padding is sexy!):
        VerticalLayout publicProfilePaddingContainer = new VerticalLayout();
        publicProfilePaddingContainer.add(publicInfoForm);
        publicProfilePaddingContainer.setPadding(true);


        // Button design
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton,
                cancelButton);

        profileSettingsAccordion.add("Profilangaben", publicProfilePaddingContainer);
        publicProfilePaddingContainer.add(buttonLayout);


        // Logics:

        cancelButton.addClickListener(event -> UI.getCurrent().getPage().reload());

        saveButton.addClickListener(e -> {
            UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);

            // Load skills into studentDTO:
            StudentDTO currentStudent = binder.getBean();
            Set<Skill> currentSkillSet = pc.createSkillSet(skills.getSkillNames());

            for (Skill s : currentSkillSet) {
                pc.saveSkill(s);
            }
            currentStudent.setSkills(currentSkillSet);

            //Blacklist Elements
            Set<Company> currentCompanySet = pc.createCompanySet(blacklist.getBlacklistNames());
            List<Integer> oldCompanyList = pc.getBlockedCompanyIDs(userDTO.getId());
            for(Company c : currentCompanySet) {
                oldCompanyList.remove(c.getId()); //Entfernt alle weiterhin aktuellen Blacklist Elemente aus der Liste
            }
            for(Integer companyID : oldCompanyList) {
                pc.removeCompFromBlacklist(userDTO.getId(), companyID);
            }

            pc.createStudent(currentStudent);
            pc.createAddress(binderAdress.getBean(), userDTO);

            Notification notification = Notification
                    .show("Daten gespeichert!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        } );

    }


    private void setupCompanyComponents() {

        // E-Mail field:
        email.setRequiredIndicatorVisible(true);
        email.setLabel("E-Mail Adresse");
        email.setPlaceholder("username@example.com");
        email.setErrorMessage("Bitte geben Sie eine gültige E-Mail Adresse ein!");
        email.setClearButtonVisible(true);
        email.setInvalid(true);
        email.setPreventInvalidInput(false);

        // Public information form layout:
        publicInfoForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.SMARTPHONE_LANDSCAPE, 4),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.WORKSTATION, 8 )
        );

        // Add input components to form for public info:
        publicInfoForm.add(companyAvatar);
        publicInfoForm.add(companyName);
        publicInfoForm.add(street);
        publicInfoForm.add(number);
        publicInfoForm.add(city);
        publicInfoForm.add(postalCode);
        publicInfoForm.add(email);

        //Patterns
        contactPerson.setRequired(true);
        contactPerson.setPattern("^[a-zA-Z\\s]+");
        website.setPattern("^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$");
        phoneNumber.setHelperText("Format: +4915776534993");
        phoneNumber.setLabel("Telefonnummer");
        phoneNumber.setPattern("\\(?\\+\\(?49\\)?[ ()]?([- ()]?\\d[- ()]?){10}");
        contactPerson.setLabel("Kontaktperson");
        companyName.setLabel("Unternehmensname");
        companyName.setReadOnly(true);
        companyAvatar.setSize("6rem");
        number.setMaxLength(3);
        number.setPattern("^[0-9]{2}$");
        postalCode.setMaxLength(5);
        postalCode.setPattern("^(?!01000|99999)(0[1-9]\\d{3}|[1-9]\\d{4})$");


        field.setAllowCustomValue(true);

        String[] brancheCollection = {"Informatik", "Chemie", "Biologie"};
        ComboBox.ItemFilter<String> brancheFilter = (String branche, String filterString) -> branche.startsWith(filterString);
        field.setItems(brancheFilter, brancheCollection);

        size.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        size.setLabel("Firmengröße");
        size.setItems(
                "kleines Unternehmen (weniger als 49 Mitarbeiter)",
                "mittelständisches Unternehmen (weniger als 249 Mitarbeiter)",
                "Großunternehmen (ab 250 Mitarbeiter)"
        );

        publicInfoForm.add(contactPerson);
        publicInfoForm.add(field);
        publicInfoForm.add(size);
        publicInfoForm.add(website);
        publicInfoForm.add(phoneNumber);

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
        binder.setBean(pc.getCompanyFromUser(userDTO));

        Binder<AddressDTO> binderAdress = new Binder<>(AddressDTO.class);
        binderAdress.bindInstanceFields(this);
        binderAdress.setBean(pc.getAdressFromUser(userDTO));

        Binder<UserDTO> binderEmail = new Binder<>(UserDTO.class);
        binderEmail.bindInstanceFields(this);
        binderEmail.readBean(userDTO);
        binderEmail.setReadOnly(true);

        //button function
        cancelButton.addClickListener(event -> UI.getCurrent().getPage().reload());

        saveButton.addClickListener(e -> {
            UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);

            pc.createCompany(binder.getBean());
            pc.createAddress(binderAdress.getBean(), userDTO);

            Notification notification = Notification
                    .show("Daten gespeichert!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        } );
    }


    public EditProfileView(EditProfileControl pc) {
        this.pc = pc;
        addClassName("edit-profile-view");
        String userType = userDTO.getUserType();

        // Check account type to show corresponding content:
        switch (userType) {
            case "student": {
                add(createLocalNavigation());
                setupStudentComponents();
                add(profileSettingsAccordion);
                break;
            }
            case "company": {
                add(createLocalNavigation());
                setupCompanyComponents();
                add(profileSettingsAccordion);
                break;
            }

        }
    }
    //Create Local Navigation for the Profile Page including Editing Profile, Settings, and Logout Buttons
    private Component createLocalNavigation() {
        Button logout = createButton("Logout");
        logout.addClickListener(event -> {
            UI ui = this.getUI().get();
            ui.getSession().close();
            ui.getPage().setLocation("/");
        });
        Dialog d = createConfirmDialog();

        Button delete = createButton("Konto löschen");
        delete.addClickListener(event -> {
            d.open();
            /*pc.deleteAccount(userDTO);
            UI ui = this.getUI().get();
            ui.getSession().close();
            ui.getPage().setLocation("/");*/
        });

        super.setSizeFull();
        HorizontalLayout localNavigation = new HorizontalLayout(createButton("Edit"),createButton("Settings"),delete, logout);
        localNavigation.setSpacing(false); // Space around the Components = False
        localNavigation.setWidthFull();
        localNavigation.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        logout.getStyle().set("margin-left", "auto").set("padding-right", "3em"); // Specify the lignment of one component -> logout to the  Left

        return localNavigation;
    }

    private Button createButton(String name) {
        Button button = new Button(name);
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        return button;
    }
    private Dialog createConfirmDialog(){
        Span s = new Span("Möchten Sie Ihren Account wirklich löschen?");
        Dialog d = new Dialog(s);
        HorizontalLayout h = new HorizontalLayout();
        Button deleteDialogButton = new Button();
        deleteDialogButton.setText("Account löschen");
        deleteDialogButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancel = new Button();
        cancel.setText("Abbrechen");
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        h.add(cancel,deleteDialogButton);
        d.add(h);
        deleteDialogButton.addClickListener(event ->{
                pc.deleteAccount(userDTO);
                UI ui = this.getUI().get();
                ui.getSession().close();
                ui.getPage().setLocation("/");});
        cancel.addClickListener(event -> d.close());
        return d;

    }
}
