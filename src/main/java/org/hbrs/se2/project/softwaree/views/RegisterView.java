package org.hbrs.se2.project.softwaree.views;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;


@Route(value = "register")
public class RegisterView extends VerticalLayout {
  //Benutzername - Textfeld
  private final Image i = new Image("images/Softwaree_Logo.png", "Logo");


  //E-Mail - Textfeld
  EmailField email = new EmailField("E-Mail");
  public void initializeEM() {
    email.setMaxLength(32);
    email.setErrorMessage("Keine valide E-Mail-Addresse");
    //em.setRequired(true);
  }

  //Passwort, mind. 12 Zeichen - Passwort-Textfeld
  PasswordField password = new PasswordField("Passwort");
  PasswordField pw2 = new PasswordField("Passwort wiederholen");

  Select<String> userType = new Select<>();
  public void initializeKAT() {
    userType.setLabel("Benutzerkategorie");
    userType.setItems("student","company");
    userType.setEmptySelectionAllowed(false);
  }

  //Ich stimme der AGB und der Datenschutzbestimmung zu: - Checkmark
  Checkbox agb = new Checkbox("Ich stimme der AGB und der Datenschutzbestimmung zu.");


  //Registrieren - Button
  Button registerButton = new Button("Registrieren");
  public void initializeButton() {
    registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
  }

  private Binder<UserDTO> userDTOBinder = new Binder<>(UserDTO.class);
  public void setupComponents(){
    password.setHelperText("Mindestens 8 Zeichen lang\n" +
            "Mindestens ein Buchstabe\n" +
            "Mindestens eine Zahl");
    initializeEM();

    //initializePW();
    //initializePW2();
    initializeKAT();
    initializeButton();

    VerticalLayout layout = new VerticalLayout();
    layout.setWidth("250px");
    setHorizontalComponentAlignment(Alignment.CENTER, layout);
    layout.add(i);

    layout.add(email,password,pw2,agb,userType,registerButton);
    add(layout);

    userDTOBinder.setBean(new UserDTO());
    userDTOBinder.bindInstanceFields(this);


    registerButton.addClickListener(e -> {
      if(!email.getValue().equals("") && !password.getValue().equals("") && !pw2.getValue().equals("")) {
        UI.getCurrent().getSession().setAttribute(Globals.CURRENT_USER, userDTOBinder.getBean());

        if (userType.getValue().equals("student")) {
          UI.getCurrent().navigate(Globals.Pages.REGISTER_STUDENT);
        } else if (userType.getValue().equals("company")) {
          UI.getCurrent().navigate(Globals.Pages.REGISTER_COMPANY);
        }
      }
      else {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        Div text = new Div(new Text("Bitte alle Felder ausf??llen!"));
        notification.add(text);
        notification.setDuration(5000);
        notification.open();

      }

    });
  }

  public void validation(){
    String passwordreg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    //email
    userDTOBinder.forField(email)
            .asRequired("Feld darf nicht leer sein")
            .withValidator(new EmailValidator("Keine valide E-Mail Adresse"))
            .bind(UserDTO::getEmail,UserDTO::setEmail);
    userDTOBinder.bindInstanceFields(this);

   /* //passwort
    userDTOBinder.forField(password)
            .asRequired("Feld darf nicht leer sein")
            .withValidator(new RegexpValidator("Kein valides Passwort",passwordreg))
            .bind(UserDTO::getPassword,UserDTO::setPassword);
    userDTOBinder.bindInstanceFields(this);

    userDTOBinder.forField(pw2)
            .bind(UserDTO::getPassword,UserDTO::setPassword);

    userDTOBinder.withValidator(
            (userDTO,) ->pw2.equals(userDTO.getPassword()));
*/
  }

  public RegisterView() {
    validation();
    setSizeFull();

    addClassName("RegisterView");

    setId("registerView");
    setupComponents();

  }
}
