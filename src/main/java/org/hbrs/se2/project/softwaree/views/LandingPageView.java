package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.control.RegistrationControl;

import java.awt.*;
@Route(value = "landingpage")
public class LandingPageView extends HorizontalLayout {

    private H3 text = new H3("Jobsuche einfach gemacht!");
    private Paragraph t =new Paragraph("Jetzt Registrieren und so schnell wie noch nie einen Job oder Mitarbieter finden");
    //private TextArea t = new TextArea("Loremipsum");

    private Image i = new Image("images/handshake.png","bild");
    private Button Login = new Button("Login");
    private Button Registrieren = new Button("Registrieren");

    /*public void setupTopBar(){
        HorizontalLayout a = new HorizontalLayout();
        a.add(Login,Registrieren);
    }*/



    public void setup(){

        i.setMaxHeight("50%");
        i.setMaxWidth("50%");
        text.setMaxHeight("50%");
        text.setMaxWidth("50%");
        text.getStyle().set("fontsize","100");


        VerticalLayout vl1 = new VerticalLayout();

        VerticalLayout vl2 = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        vl1.add( text, t);
        vl2.add(i);
        hl.add(vl1,vl2);
        hl.setAlignItems(Alignment.CENTER);
        hl.getStyle().set("background-color", "Yellow");
        hl.setHeight("100%");
        add(hl);
    }

    RegistrationControl registrationControl;
    public LandingPageView(RegistrationControl registrationControl){

        this.registrationControl = registrationControl;
        addClassName("LandingPageView");

        setId("landingPageView");
        setup();
    }
}
