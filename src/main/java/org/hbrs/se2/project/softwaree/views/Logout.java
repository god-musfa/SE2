package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

@PageTitle("Logout")
@Route(value = "logout", layout = NavBar.class)
public class Logout extends VerticalLayout {

    public Logout()  {
        setSizeFull();
        Span message = new Span("Goodbye " + getCurrentNameOfUser());
        add(message);

        UI.getCurrent().getSession().close();
        UI.getCurrent().getPage().setLocation("landingpage");
    }

    private String getCurrentNameOfUser() {
        return String.valueOf(getCurrentUser().getEmail());
    }
    private UserDTO getCurrentUser() {
        return (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    }
}
