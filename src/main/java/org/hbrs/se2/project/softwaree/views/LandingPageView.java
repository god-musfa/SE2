package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import org.hbrs.se2.project.softwaree.control.RegistrationControl;

@Route(value = "")
@RouteAlias(value = "landingpage")
@CssImport("./styles/views/landingpage/landing.css")
public class LandingPageView extends VerticalLayout {

    public void setup() {

        // Get user agent:
        WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
        boolean isMobile = webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone();

        setWidthFull();
        setHeightFull();
        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        VerticalLayout mobileVL = new VerticalLayout();
        VerticalLayout txxt = new VerticalLayout();




        Image logo = new Image("images/Softwaree_Logo.png", "Vaadin logo");
        logo.setMaxWidth("100%");

        Image i = new Image("images/handshake.png","logo");
        i.setWidth("50%");
        i.setWidth("50%");
        i.addClassName("handshake_logo");




        H3 text = new H3("Jetzt Registrieren und so schnell wie noch nie \n einen Job oder Mitarbieter finden");
        text.getStyle().set("font-size", "40px");
        text.getStyle().set("color", "#333333");
        text.getStyle().set("text-align", "left");
        text.setMaxWidth("700px");

        Paragraph label = new Paragraph("Willkommen auf unserer Plattform für Studenten und Unternehmen! Wir bieten Unternehmen die Möglichkeit, talentierte Studenten zu finden und einzustellen, \n während Studenten auf unserer Seite nach Arbeitsmöglichkeiten suchen können.");
        label.getStyle().set("font-size", "20px");
        label.getStyle().set("color", "#333333");
        label.getStyle().set("text-align", "left");
        label.setMaxWidth("500px");


        HorizontalLayout buttons = new HorizontalLayout();

        com.vaadin.flow.component.button.Button loginButton = new com.vaadin.flow.component.button.Button("Log in");
        loginButton.getStyle().set("background-color", "green");
        loginButton.getStyle().set("color", "white");

        loginButton.getStyle().set("border-radius", "4px");
        loginButton.addClickListener(event -> {
            // Navigate to login page
            UI.getCurrent().navigate("login");
        });
        buttons.add(loginButton);

        com.vaadin.flow.component.button.Button registerButton = new Button("Jetzt kostenlos registrieren!");
        registerButton.getStyle().set("background-color", "white");
        registerButton.getStyle().set("color", "green");
        registerButton.getStyle().set("border-radius", "4px");
        registerButton.getStyle().set("border", "3px solid green");
        registerButton.addClickListener(event -> {
            // Navigate to registration page
            UI.getCurrent().navigate("register");
        });
        txxt.add(text,label);
        buttons.add(registerButton);

        if (isMobile) {
            mobileVL.add(i, txxt);
            vl.add(logo, mobileVL, buttons);
        } else {
            hl.add(txxt, i);
            vl.add(logo,hl,buttons);
        }
        add(vl);

    }

    RegistrationControl registrationControl;
    public LandingPageView(RegistrationControl registrationControl){

        this.registrationControl = registrationControl;
        addClassName("LandingPageView");

        setId("landingPageView");
        setup();
    }
}
