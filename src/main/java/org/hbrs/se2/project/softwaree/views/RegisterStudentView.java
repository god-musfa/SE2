package org.hbrs.se2.project.softwaree.views;
//import com.vaadin.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "register/student")
public class RegisterStudentView {
  //Anrede - TextfeldS
  Select anr = new Select<>();
  public void initializeANR() {
    anr.setLabel("Anrede");
    anr.setItems("Herr","Frau");
  }
  //Vorname - Textfeld
  TextField vn = new TextField("Vorname");
  public void initializeVN() {
    vn.setMaxLength(20);
    vn.setRequired(true);
  }
  // Nachname - Textfeld
  TextField nn = new TextField("Nachname");
  public void initializeNN() {
    nn.setMaxLength(20);
    nn.setRequired(true);
  }
  // Straße, Hausnummer - Textfeld
  TextField sthn = new TextField("Straße, Hausnummer");
  public void initializeSTHN() {
    sthn.setMaxLength(20);
    sthn.setRequired(true);
    sthn.setPattern("([a-zA-Z]|[ß]|[ ])+, [0-9]+\n");
  }
  // PLZ - Textfeld
  TextField plz = new TextField("PLZ");
  public void initializePLZ() {
    plz.setMaxLength(20);
    plz.setRequired(true);
    plz.setPattern("[0-9][0-9][0-9][0-9][0-9]");
  }
  // Abschluss - Textfeld
  Select abs = new Select<>();
  public void initializeABS() {
    abs.setLabel("Abschluss");
    abs.setItems("Keinen","Bachelor","Master","Doktor");
  }
  // Universität - Textfeld
  TextField uni = new TextField("Universität");
  public void initializeUNI() {
    uni.setMaxLength(20);
    uni.setRequired(true);
  }
  //Registrieren - Button
  Button registerButton = new Button("Registrieren");
  public void initializeButton() {
    registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
  }
}

