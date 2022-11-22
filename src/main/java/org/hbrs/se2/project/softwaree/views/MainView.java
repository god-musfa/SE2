package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import org.hbrs.se2.project.softwaree.control.LoginControl;
import org.hbrs.se2.project.softwaree.control.exception.DatabaseUserException;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * View zur Darstellung der Startseite. Diese zeigt dem Benutzer ein Login-Formular an.
 * ToDo: Integration einer Seite zur Registrierung von Benutzern
 */
@Route(value = "")
@RouteAlias(value = "login")
public class MainView extends VerticalLayout {
    @Autowired
    private LoginControl loginControl;

    public MainView() {
        setSizeFull();
        LoginForm component = new LoginForm();

        component.addLoginListener(e -> {

            boolean isAuthenticated = false;
            try {
                isAuthenticated = loginControl.authentificate( e.getUsername() , e.getPassword() );

            } catch (DatabaseUserException databaseException) {
                Dialog dialog = new Dialog();
                dialog.add( new Text( databaseException.getReason()) );
                dialog.setWidth("400px");
                dialog.setHeight("150px");
                dialog.open();
            }
            if (isAuthenticated) {
                grabAndSetUserIntoSession();
                navigateToMainPage();

            } else {
                // Kann noch optimiert werden
                component.setError(true);
            }
        });

        RouterLink toRegister = new RouterLink("Registrieren", RegisterView.class);
        //Styling Register same as forgot password phrase
        toRegister.getStyle().set("margin", "0");
        toRegister.getStyle().set("font-family", "var(--lumo-font-family)");
        toRegister.getStyle().set("font-size", "var(--lumo-font-size,var(--lumo-font-size-m))");
        toRegister.getStyle().set("line-height", "var(--lumo-line-height-m)");
        toRegister.getStyle().set("font-weight", "600");
        add(component, toRegister);
        setAlignItems(Alignment.CENTER);

    }

    private void grabAndSetUserIntoSession() {
        UserDTO userDTO = loginControl.getCurrentUser();
        UI.getCurrent().getSession().setAttribute( Globals.CURRENT_USER, userDTO );
    }


    private void navigateToMainPage() {
        // Navigation zur Startseite, hier auf die Teil-Komponente Show-Cars.
        // Die anzuzeigende Teil-Komponente kann man noch individualisieren, je nach Rolle,
        // die ein Benutzer besitzt
        UI.getCurrent().navigate(Globals.Pages.SHOW_JOBS);

    }
}
