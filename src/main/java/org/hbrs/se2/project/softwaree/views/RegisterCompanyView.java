package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.control.RegistrationControl;
import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Stream;

@Route(value = "register/company")

public class RegisterCompanyView extends VerticalLayout {
    private H3 title = new H3("Jetzt registrieren – gratis und in nur 2 Minuten!");
    private TextField name = new TextField("Unternehmensname");
    private TextField phone_number = new TextField("Telefonnummer");
    private TextField website = new TextField("Webseite");
    private ComboBox<String> field = new ComboBox<String>("Branche");
    private ComboBox<String> size = new ComboBox<String>("Firmengröße");
    private TextField contactPerson = new TextField("Kontaktperson");
    private TextField street = new TextField("Straße");
    private TextField number = new TextField("Hausnummer");
    private TextField postalCode = new TextField("Postleitzahl");
    TextField city = new TextField(("Stadt"));

    private Span errorMessageField = new Span();

    Button registerButton = new Button("Registrieren");
    private final Image i = new Image("images/Softwaree_Logo.png", "Logo");

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
    public Span getErrorMessageField() {
        return errorMessageField; }

    private Binder<UserDTO> userDTOBinder = new Binder<>(UserDTO.class);
    private Binder<CompanyDTO> companyDTOBinder = new Binder<>(CompanyDTO.class);
    private Binder<AddressDTO> addressDTOBinder = new Binder<>(AddressDTO.class);
    public void setupComponents() {

        FormLayout lt = new FormLayout();
        lt.add(i,title);
        add(lt);
        setRequiredIndicatorVisible(name, phone_number, website, field, size, contactPerson,
                street, number, postalCode, city);

        field.setItems("Informatik", "Chemie", "Biologie");
        size.setItems(
                "kleines Unternehmen (weniger als 49 Mitarbeiter)",
                "mittelständisches Unternehmen (weniger als 249 Mitarbeiter)",
                "Großunternehmen (ab 250 Mitarbeiter)"
        );

        FormLayout layout = new FormLayout();
        layout.setSizeFull();
        layout.add(name, phone_number, website, field, size, contactPerson,
            street, number, city, postalCode);

        add(layout);
        add(registerButton);
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        userDTOBinder.setBean((UserDTO) UI.getCurrent().getSession().getAttribute( Globals.CURRENT_USER));

        companyDTOBinder.setBean(new CompanyDTO());
        companyDTOBinder.bindInstanceFields(this);

        addressDTOBinder.setBean(new AddressDTO());
        addressDTOBinder.bindInstanceFields(this);

        registerButton.addClickListener(e -> {

            registrationControl.saveC(userDTOBinder.getBean(), companyDTOBinder.getBean(), addressDTOBinder.getBean());
            UI ui = this.getUI().get();
            ui.getSession().close();
            ui.getPage().setLocation("login");
        });


    }
    public void validation() {
        String webregex = "^(https?|ftp|file|www)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        String phoneregex = "(\\(?([\\d \\-\\)\\–\\+\\/\\(]+){6,}\\)?([ .\\-–\\/]?)([\\d]+))" ;
        String plzregex = "^[0-9]{5}$";
        String numberregex = "^\\d{1,3}[a-zA-Z]?$";

        //Phonenumbervalidation
        companyDTOBinder.forField(phone_number)
                .withValidator( new RegexpValidator("Bitte geben Sie eine valide Nummer ein", phoneregex))
                .asRequired("Feld darf nicht leer sein")
                .bind(CompanyDTO::getPhoneNumber, CompanyDTO::setPhoneNumber);

        //Unternehmensname
        companyDTOBinder.forField(name)
                .withValidator(name -> name.length() >= 3,"Name muss mehr als 3 Zeichen haben")
                // Shorthand for requiring the field to be non-empty
                .asRequired("Feld darf nicht leer sein")
                .bind(CompanyDTO::getName, CompanyDTO::setName);

        //website
        companyDTOBinder.forField(website)
                .withValidator( new RegexpValidator("Die Website muss mit eine gültige Domain haben", webregex))
                .asRequired("Feld darf nicht leer sein")
                .bind(CompanyDTO::getWebsite, CompanyDTO::setWebsite);

        //PLZ
        addressDTOBinder.forField(postalCode)
                .withValidator( new RegexpValidator("Bitte gültige Postleitzahl angeben", plzregex))
                .asRequired("Feld darf nicht leer sein")
                .bind(AddressDTO::getPostalCode, AddressDTO::setPostalCode);

        //Hausnummer
        addressDTOBinder.forField(number)
                .withValidator( new RegexpValidator("Bitte geben Sie eine valide Hausnummer an", numberregex))
                .asRequired("Feld darf nicht leer sein")
                .bind(AddressDTO::getNumber, AddressDTO::setNumber);

        addressDTOBinder.forField(city)
                .asRequired("Feld darf nicht leer sein")
                .bind(AddressDTO::getCity, AddressDTO::setCity);

    }



    RegistrationControl registrationControl;

    public RegisterCompanyView(RegistrationControl registrationControl){
        validation();
            //add(i);
            this.registrationControl = registrationControl;
            addClassName("RegisterCompanyView");
            setId("registerCompanyView");
            setupComponents();
    }
}

