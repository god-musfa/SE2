package org.hbrs.se2.project.softwaree.views;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.control.RegistrationControl;
import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

@Route(value = "register/student") // hotfix: removed layout to prevent error when entering the editprofilview while still registering
public class RegisterStudentView extends VerticalLayout {
  //Anrede - TextfeldS
  Select anr = new Select<>();
  private final Image i = new Image("images/Softwaree_Logo.png", "Logo");

  public void initializeANR() {
    anr.setLabel("Anrede");
    anr.setItems("Herr","Frau");
  }
  //Vorname - Textfeld
  TextField firstName = new TextField("Vorname");
  public void initializeVN() {
    firstName.setMaxLength(20);
    firstName.setRequired(true);
  }
  // Nachname - Textfeld
  TextField lastName = new TextField("Nachname");
  public void initializeNN() {
    lastName.setMaxLength(20);
    lastName.setRequired(true);
  }
  // Straße, Hausnummer - Textfeld
  TextField street = new TextField("Straße");
  public void initializeStreet() {
    street.setMaxLength(100);
    street.setRequired(true);
    street.setPattern("([a-zA-Z]|[ß]|[ ])+, [0-9]+\n");
  }

  TextField number = new TextField("Hausnummer");
  public void initializeNumber() {
    number.setMaxLength(20);
    number.setRequired(true);
    number.setPattern("([a-zA-Z]|[ß]|[ ])+, [0-9]+\n");
  }

  TextField city = new TextField(("Stadt"));
  public void initCity(){
    city.setRequired(true);
  }
  // PLZ - Textfeld
  TextField postalCode = new TextField("PLZ");
  public void initializePLZ() {
    postalCode.setMaxLength(20);
    postalCode.setRequired(true);
    postalCode.setPattern("[0-9][0-9][0-9][0-9][0-9]");
  }
  // Abschluss - Textfeld
  Select<String> degree = new Select<>();
  public void initializeABS() {
    degree.setLabel("Abschluss");
    degree.setItems("Keinen","Bachelor","Master","Doktor");
  }
  // Universität - Textfeld
  TextField university = new TextField("Universität");
  public void initializeUNI() {
    university.setMaxLength(100);
    university.setRequired(true);
  }
  private final DatePicker birthday = new DatePicker("Geburtsdatum");
  public void initializeBirthday(){
    birthday.setRequired(true);
  }
  //Registrieren - Button
  Button registerButton = new Button("Registrieren");
  public void initializeButton() {
    registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
  }

  private Binder<UserDTO> userDTOBinder = new Binder<>(UserDTO.class);
  private Binder<StudentDTO> studentDTOBinder = new Binder<>(StudentDTO.class);
  private Binder<AddressDTO> addressDTOBinder = new Binder<>(AddressDTO.class);
  public void setupComponents(){
    initializeANR();
    initializeVN();
    initializeNN();
    initializeStreet();
    initializeNumber();
    initializePLZ();
    initializeABS();
    initializeUNI();
    initializeBirthday();
    initializeButton();
    initCity();

    VerticalLayout layout = new VerticalLayout();
    layout.setWidth("250px");
    setHorizontalComponentAlignment(Alignment.CENTER, layout);
    layout.add(anr,firstName,lastName,birthday,street,number,city,postalCode,degree,university,registerButton);
    add(layout);

    userDTOBinder.setBean((UserDTO) UI.getCurrent().getSession().getAttribute( Globals.CURRENT_USER));

    studentDTOBinder.setBean(new StudentDTO());
    studentDTOBinder.bindInstanceFields(this);

    addressDTOBinder.setBean(new AddressDTO());
    addressDTOBinder.bindInstanceFields(this);

    registerButton.addClickListener(e -> {

      registrationControl.save(userDTOBinder.getBean(), studentDTOBinder.getBean(), addressDTOBinder.getBean());
      registrationControl.setDefaultProfilePicture(userDTOBinder.getBean());
      UI ui = this.getUI().get();
      ui.getSession().close();
      ui.getPage().setLocation("login");
    });
  }

  RegistrationControl registrationControl;
  public RegisterStudentView(RegistrationControl registrationControl) {
    add(i);
    this.registrationControl = registrationControl;
    addClassName("RegisterStudentView");

    setId("registerStudentView");
    setupComponents();

  }
  }

