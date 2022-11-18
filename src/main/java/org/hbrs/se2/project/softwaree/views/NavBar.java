package org.hbrs.se2.project.softwaree.views;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

@CssImport("./styles/views/main/main-view.css")
@Route("main2")
@PWA(name = "HelloCar", shortName = "HelloCar", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
// tag::snippet[]
    public class NavBar extends AppLayout implements BeforeEnterObserver {

    private Tabs menu;

        public NavBar() {
            if (getCurrentUser() == null) {
                System.out.println("LOG: In Constructor of App View - No User given!");
            } else {
                setUpUI();
            }
        }

        public void setUpUI() {
            H1 title = new H1("Collab@HBRS");
            title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                    .set("margin", "var(--lumo-space-m) var(--lumo-space-l)");

            menu = getTabs();

            H2 viewTitle = new H2("View title");
            Paragraph viewContent = new Paragraph("View content");

            Div content = new Div();
            content.add(viewTitle, viewContent);

            addToNavbar(title);
            addToNavbar(true, menu);

            Tabs secondaryNavbar = getSecondaryNavigation();

            HorizontalLayout wrapper = new HorizontalLayout(secondaryNavbar);
            setContent(wrapper);
           // setContent(content);
            //addToNavbar(true, wrapper);

        }
        private boolean checkIfUserIsLoggedIn() {
            // Falls der Benutzer nicht eingeloggt ist, dann wird er auf die Startseite gelenkt
            UserDTO userDTO = this.getCurrentUser();
            if (userDTO == null) {
                UI.getCurrent().navigate(Globals.Pages.LOGIN_VIEW);
                return false;
            }
            return true;
        }

        private Tabs getTabs() {
            Tabs tabs = new Tabs();
            tabs.add(createTab(VaadinIcon.LIST_UL, JobView.class),
                    createTab(VaadinIcon.LOCATION_ARROW, ShowAddressView.class),
                    createTab(VaadinIcon.USER, EditProfileView.class),
                    createTab(VaadinIcon.SIGN_IN, RegisterStudentView.class));
            tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL,
                    TabsVariant.LUMO_EQUAL_WIDTH_TABS);
            return tabs;
        }

        private Tab createTab(VaadinIcon viewIcon, Class<? extends Component> navigationTarget) {
            Icon icon = viewIcon.create();
            icon.setSize("var(--lumo-icon-size-s)");
            icon.getStyle().set("margin", "auto");

            RouterLink link = new RouterLink();
            link.add(icon);
            // Demo has no routes
            link.setRoute(navigationTarget);
            link.setTabIndex(-1);

            return new Tab(link);
        }

        private Tabs getSecondaryNavigation() {
            Tabs tabs = new Tabs();
            tabs.add(new Tab("All"), new Tab("Open"), new Tab("Completed"),
                    new Tab("Cancelled"));
            tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL,
                    TabsVariant.LUMO_EQUAL_WIDTH_TABS);
            return tabs;
        }

    private void logoutUser() {
        UI ui = this.getUI().get();
        ui.getSession().close();
        ui.getPage().setLocation("/");
    }
    @Override
    protected void afterNavigation() {
        super.afterNavigation();

        // Falls der Benutzer nicht eingeloggt ist, dann wird er auf die Startseite gelenkt
        if ( !checkIfUserIsLoggedIn() ) return;

/*        // Der aktuell-selektierte Tab wird gehighlighted.
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);

        // Setzen des aktuellen Names des Tabs
        viewTitle.setText(getCurrentPageTitle());

        // Setzen des Vornamens von dem aktuell eingeloggten Benutzer
        helloUser.setText("Hello my dear old friend!! "  + this.getCurrentNameOfUser() );*/
    }
    private String getCurrentNameOfUser() {
        return String.valueOf(getCurrentUser().getEmail());
    }

    private UserDTO getCurrentUser() {
        return (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
    }

    @Override
    /**
     * Methode wird vor der eigentlichen Darstellung der UI-Components aufgerufen.
     * Hier kann man die finale Darstellung noch abbrechen, wenn z.B. der Nutzer nicht eingeloggt ist
     * Dann erfolgt hier ein ReDirect auf die Login-Seite. Eine Navigation (Methode navigate)
     * ist hier nicht möglich, da die finale Navigation noch nicht stattgefunden hat.
     * Diese Methode in der AppLayout sichert auch den un-authorisierten Zugriff auf die innerliegenden
     * Views (hier: ShowCarsView und EnterCarView) ab.
     *
     */
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (getCurrentUser() == null){
            beforeEnterEvent.rerouteTo(Globals.Pages.LOGIN_VIEW);
        }

    }
    }
// end::snippet[]


