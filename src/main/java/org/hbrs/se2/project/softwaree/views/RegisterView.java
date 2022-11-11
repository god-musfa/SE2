package org.hbrs.se2.project.softwaree.views;
//import com.vaadin.*;
//import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
//import javax.validation.constraints.Email;

@Route(value = "register")
public class RegisterView extends VerticalLayout {
  //Benutzername - Textfeld
  TextField un = new TextField();
  public void initializeUN() {
    un.setMaxLength(20);
    un.setRequired(true);
  }

  //E-Mail - Textfeld
  EmailField em = new EmailField("E-Mail");
  public void initializeEM() {
    em.setMaxLength(32);
    //em.setRequired(true);
  }

  //Passwort, mind. 12 Zeichen - Passwort-Textfeld
  PasswordField pw = new PasswordField("Passwort");
  public void initializePW() {
    pw.setRevealButtonVisible(false);
    pw.setMaxLength(32);
    pw.setMinLength(12);
    pw.setHelperText("Das Passwort muss mindestens 12 Zeichen haben.");
    pw.setErrorMessage("Kein valides Passwort");
    pw.setRequired(true);
  }

  //Passwort wiederholen - Passwort Textfeld
  PasswordField pw2 = new PasswordField("Passwort wiederholen");
  public void initializePW2() {
    pw2.setRevealButtonVisible(false);
    pw2.setMaxLength(32);
    pw2.setMinLength(12);
    pw2.setRequired(true);
  }

  //Ich stimme der AGB und der Datenschutzbestimmung zu: - Checkmark
  Checkbox agb = new Checkbox("Ich stimme der AGB und der Datenschutzbestimmung zu.");


  //Registrieren - Button
  Button registerButton = new Button("Registrieren");
  public void initializeButton() {
    registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
  }

  public RegisterView() {
    addClassName("RegisterView");

    setId("registerView");
    initializeEM();
    initializeUN();
    initializePW();
    initializePW2();
    initializeButton();

    VerticalLayout layout = new VerticalLayout();
    layout.add(em,un,pw,pw2,agb,registerButton);
    add(layout);

  }


}
