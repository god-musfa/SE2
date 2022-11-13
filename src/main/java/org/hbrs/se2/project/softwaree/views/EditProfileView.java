package org.hbrs.se2.project.softwaree.views;


import java.time.LocalDate;
import java.util.Locale;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
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
import org.hbrs.se2.project.softwaree.components.SoftwareeAvatar;
import org.hbrs.se2.project.softwaree.control.EditProfileControl;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;


/**
 * @author dheil2s, myild22s
 * AppView to view and change profile information of the current user.
 * This AppView handles both, students and universities.
 **/

@Route(value = Globals.Pages.EDIT_PROFILE, layout = AppView.class)
@PageTitle("Profil bearbeiten")
@CssImport("./styles/views/main/main-view.css")
public class EditProfileView extends Div {
    // Accordion for splitting profile information settings into different topics
    private final Accordion profileSettingsAccordion = new Accordion();

    // Base components for both user types
    private TextField addressStreetField = new TextField("Straße");
    private TextField addressHouseField = new TextField("Hausnummer");
    private TextField addressPostalCodeField = new TextField("Postleitzahl");
    private TextField addressCityField = new TextField("Ort");
    private EmailField emailField = new EmailField("E-Mail");

    private Button saveButton = new Button( "Save");
    private Button cancelButton = new Button( "Cancel");



    // Profile picture layout
    private final SoftwareeAvatar profileAvatar = new SoftwareeAvatar(true);


    // Form Layouts for splitting user settings into two parts (public information, personal information)
    private final FormLayout publicInfoForm = new FormLayout();


    // Components for students:
    private final TextField firstName = new TextField("Vorname");
    private final TextField lastName = new TextField("Nachname");
    private final Locale germanLocale = new Locale("de", "DE");
    private final DatePicker birthdayPicker = new DatePicker("Geburtsdatum");
    private final IntegerField semesterField = new IntegerField();
    private final ComboBox<String> studyGraduationField = new ComboBox<String>("Abschluss");
    private final TextField universityField = new TextField("Hochschule");
    private final TextField studyDegreeField = new TextField("Studiengang");


    // Components for companies:
    final TextField contactPersonField = new TextField("Kontaktperson");
    final TextField phoneNumberField = new TextField("Telefonnummer");
    final ComboBox<String> brancheField = new ComboBox<>("Branche");
    final RadioButtonGroup<String> companySizeRadioGroup = new RadioButtonGroup<>();
    final TextField companyWebsiteField = new TextField("Webseite");
    UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    EditProfileControl pc;

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
        addressHouseField.setPattern("^[0-9]*[A-Za-z]{0,1}$");
        addressHouseField.setErrorMessage("Diese Hausnummer ist ungültig!");
        addressPostalCodeField.setPattern("^[0-9]{5}$");
        addressPostalCodeField.setErrorMessage("Diese Postleitzahl ist ungültig!");
        birthdayPicker.setMax(LocalDate.now());
        studyGraduationField.setAllowCustomValue(false);

        // E-Mail field settings:
        emailField.setLabel("E-Mail Adresse");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Bitte geben Sie eine gültige E-Mail Adresse ein!");
        emailField.setClearButtonVisible(true);

        // Locale settings (for birthday picker):
        birthdayPicker.setLocale(germanLocale);

        // Set Settings for semester value:
        semesterField.setLabel("Semester");
        semesterField.setHelperText("Ihr aktuelles Semester");
        semesterField.setMin(1);
        semesterField.setMax(50);
        semesterField.setHasControls(true);

        // Profile picture section:
        profileAvatar.setSize("6rem");

        // Graduation dropdown settings:
        String[] studyGraduations = {"B. Sc.", "M. Sc.", "Diplom", "Magister", "Dr.", "Langzeitstudent"};
        studyGraduationField.setItems(studyGraduations);

        // Placeholders for arrangement of form fields:
        Div namePlaceholder = new Div();
        Div streetPlaceholder = new Div();
        Div cityPlaceholder = new Div();
        Div mailBirthdayPlaceholder = new Div();
        Div studyInfoPlaceholder = new Div();
        Div universityInfoPlaceholder = new Div();
        Div profilePicturePlaceholder = new Div();


        // Add all components to publicInfoForm container:
        publicInfoForm.add(profileAvatar);
        publicInfoForm.add(profilePicturePlaceholder);
        publicInfoForm.add(firstName);
        publicInfoForm.add(lastName);
        publicInfoForm.add(namePlaceholder);

        publicInfoForm.add(addressStreetField);
        publicInfoForm.add(addressHouseField);
        publicInfoForm.add(streetPlaceholder);

        publicInfoForm.add(addressPostalCodeField);
        publicInfoForm.add(addressCityField);
        publicInfoForm.add(cityPlaceholder);

        publicInfoForm.add(emailField);
        publicInfoForm.add(birthdayPicker);
        publicInfoForm.add(mailBirthdayPlaceholder);

        publicInfoForm.add(studyDegreeField);
        publicInfoForm.add(semesterField);
        publicInfoForm.add(studyInfoPlaceholder);

        publicInfoForm.add(universityField);
        publicInfoForm.add(studyGraduationField);
        publicInfoForm.add(universityInfoPlaceholder);


        // Configure colspan of components inside of container:
        publicInfoForm.setColspan(profileAvatar, 2);
        publicInfoForm.setColspan(profilePicturePlaceholder, 7);

        publicInfoForm.setColspan(firstName, 2);
        publicInfoForm.setColspan(lastName, 2);
        publicInfoForm.setColspan(namePlaceholder, 4);

        publicInfoForm.setColspan(addressStreetField, 2);
        publicInfoForm.setColspan(addressHouseField, 1);
        publicInfoForm.setColspan(streetPlaceholder, 5);

        publicInfoForm.setColspan(addressCityField, 3);
        publicInfoForm.setColspan(addressPostalCodeField, 1);
        publicInfoForm.setColspan(cityPlaceholder, 4);

        publicInfoForm.setColspan(emailField, 2);
        publicInfoForm.setColspan(birthdayPicker, 2);
        publicInfoForm.setColspan(mailBirthdayPlaceholder, 4);

        publicInfoForm.setColspan(semesterField, 1);
        publicInfoForm.setColspan(studyDegreeField, 2);
        publicInfoForm.setColspan(studyInfoPlaceholder, 5);

        publicInfoForm.setColspan(studyGraduationField, 2);
        publicInfoForm.setColspan(universityField, 2);
        publicInfoForm.setColspan(universityInfoPlaceholder, 4);


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

        Binder<StudentDTO> binder = new Binder<>(StudentDTO.class);
        binder.bindInstanceFields(this);

        binder.readBean(pc.getStudentFromUser(userDTO));


    }


    private void setupCompanyComponents() {

        // E-Mail field:
        emailField.setLabel("E-Mail Adresse");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Bitte geben Sie eine gültige E-Mail Adresse ein!");
        emailField.setClearButtonVisible(true);

        // Public information form layout:
        publicInfoForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("20em", 2)
        );

        // Add input components to form for public info:
        publicInfoForm.add(addressStreetField);
        publicInfoForm.add(addressHouseField);
        publicInfoForm.add(addressCityField);
        publicInfoForm.add(addressPostalCodeField);
        publicInfoForm.add(emailField);


        companyWebsiteField.setPattern("^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$");
        phoneNumberField.setHelperText("Format: +4915776534993");
        phoneNumberField.setLabel("Telefonnummer");
        phoneNumberField.setPattern("\\(?\\+\\(?49\\)?[ ()]?([- ()]?\\d[- ()]?){10}");
        contactPersonField.setLabel("Kontaktperson");

        brancheField.setAllowCustomValue(true);

        String[] brancheCollection = {"Informatik", "Chemie", "Biologie"};
        ComboBox.ItemFilter<String> brancheFilter = (String branche, String filterString) -> branche.startsWith(filterString);
        brancheField.setItems(brancheFilter, brancheCollection);

        companySizeRadioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        companySizeRadioGroup.setLabel("Firmengröße");
        companySizeRadioGroup.setItems(
                "kleines Unternehmen (weniger als 49 Mitarbeiter)",
                "mittelständisches Unternehmen (weniger als 249 Mitarbeiter)",
                "Großunternehmen (ab 250 Mitarbeiter)"
        );

        publicInfoForm.add(contactPersonField);
        publicInfoForm.add(brancheField);
        publicInfoForm.add(companySizeRadioGroup);
        publicInfoForm.add(companyWebsiteField);
        publicInfoForm.add(phoneNumberField);

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

    }


    public EditProfileView(EditProfileControl pc) {
        this.pc = pc;
        addClassName("edit-profile-view");
        UserDTO userdto = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
        String userType = userDTO.getUserType();

        // Check account type to show corresponding content:
        switch (userType) {
            case "student": {
                setupStudentComponents();
                add(profileSettingsAccordion);
                break;
            }
            case "company": {
                setupCompanyComponents();
                add(profileSettingsAccordion);
                break;
            }

        }
    }


}
