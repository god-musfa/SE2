package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
    private TextField field = new TextField("Branche");
    private TextField size = new TextField("Firmengröße");
    private TextField contactPerson = new TextField("Kontaktperson");
    private TextField street = new TextField("Straße");
    private TextField number = new TextField("Hausnummer");
    private TextField postalCode = new TextField("Postleitzahl");
    private Span errorMessageField = new Span();
    private Button submitButton;
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
        /*setRequiredIndicatorVisible(name, phone_number, website, field, size, contactPerson,
                street, number, postalCode);
*/
        FormLayout layout = new FormLayout();
        layout.setSizeFull();
        layout.add(name, phone_number, website, field, size, contactPerson,
            street, number, postalCode);

        add(layout);
        add(registerButton);
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

     /*   setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

        // These components always take full width
        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(errorMessageField, 2);
        //setColspan(submitButton, 2);
*/
        userDTOBinder.setBean((UserDTO) UI.getCurrent().getSession().getAttribute( Globals.CURRENT_USER));

        companyDTOBinder.setBean(new CompanyDTO());
        companyDTOBinder.bindInstanceFields(this);

        addressDTOBinder.setBean(new AddressDTO());
        addressDTOBinder.bindInstanceFields(this);
        registerButton.addClickListener(e -> {

            registrationControl.saveC(userDTOBinder.getBean(), companyDTOBinder.getBean(), addressDTOBinder.getBean());
            UI ui = this.getUI().get();
            ui.getSession().close();
            ui.getPage().setLocation("/");
        });


    }

    public void setupFields(){
        String pattern  = "/\\(?\\+\\(?49\\)?[ ()]?([- ()]?\\d[- ()]?){10}/g";
        phone_number.setHelperText("Format: +49");
        phone_number.setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
        phone_number.setMinLength(12);
        phone_number.setErrorMessage("asldkn");
        phone_number.setPreventInvalidInput(true);

        name.setPattern("^[a-zA-z\\s]+");
        name.setPreventInvalidInput(true);

    }

    RegistrationControl registrationControl;

    public RegisterCompanyView(RegistrationControl registrationControl){
        setupFields();
            //add(i);
            this.registrationControl = registrationControl;
            addClassName("RegisterCompanyView");

            setId("registerCompanyView");
            setupComponents();




    }
}

