package org.hbrs.se2.project.softwaree.views;
//import com.vaadin.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "register/unternehmer")
public class RegisterUnternehmerView extends VerticalLayout {

  //Name - Textfeld
  TextField name = new TextField("Name");
  public void initializeNAME() {
    name.setMaxLength(20);
    name.setRequired(true);
  }

  // Webseite - Textfeld
  TextField web = new TextField("Webseite");
  public void initializeWEB() {
    web.setMaxLength(30);
    web.setRequired(true);
  }
  // Feld - Textfeld
  TextField feld = new TextField("Straße, Hausnummer");
  public void initializeFELD() {
    feld.setMaxLength(20);
    feld.setRequired(true);
  }
  // Größe - Textfeld
  TextField gr = new TextField("Größe");
  public void initializeGR() {
    gr.setMaxLength(20);
    gr.setRequired(true);
  }

  // Kontaktperson - Textfeld
  TextField kon = new TextField("Kontaktperson");
  public void initializeKON() {
    kon.setMaxLength(20);
    kon.setRequired(true);
  }
  //Telefonnummer - Nummernfeld
  NumberField phn = new NumberField("Telefonnummer");
  public void initializePHN() {
  }

  //Registrieren - Button
  Button registerButton = new Button("Registrieren");
  public void initializeButton() {
    registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
  }

  public RegisterUnternehmerView() {
    addClassName("RegisterUnternehmerView");

    setId("registerUnternehmerView");
    initializeNAME();
    initializeWEB();
    initializeFELD();
    initializeGR();
    initializeKON();
    initializePHN();
    initializeButton();

    VerticalLayout layout = new VerticalLayout();
    layout.add(name,web,feld,gr,kon,phn,registerButton);
    add(layout);
  }
}

