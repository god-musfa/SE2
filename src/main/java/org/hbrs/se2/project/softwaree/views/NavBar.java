package org.hbrs.se2.project.softwaree.views;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.PWA;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;
import java.util.Optional;

@CssImport("./styles/views/main/main-view.css")
@Route("main2")
@PWA(name = "HelloCar", shortName = "HelloCar", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
// tag::snippet[]
    public class NavBar extends AppLayout implements BeforeEnterObserver {

    private Tabs menu;
    private Span viewTitle;
    Icon logo = new Icon("Softwaree_Logo, logo");
    private final Image i = new Image("images/Softwaree_klein.png", "Logo");



        public NavBar() {
            if (getCurrentUser() == null) {
                System.out.println("LOG: In Constructor of App View - No User given!");
            } else {
                menu = getTabs();
                addToNavbar(createTopHeader());
                addToNavbar(true, menu);
            }
        }

        private Component createTopHeader() {
            viewTitle = new Span("View Title_x");












            VerticalLayout TopHeader = new VerticalLayout();
            // TopHeader.setAlignItems(FlexComponent.Alignment.CENTER);
            TopHeader.setWidth("100px"); TopHeader.setHeight("50px");


            Span home = new Span (i);
            i.setMaxHeight(TopHeader.getHeight());
            i.setMaxWidth(TopHeader.getWidth());
            //home.setMaxWidth("50%");

            home.setTitle("Welcome to Colab@HBRS");
            Icon icon = VaadinIcon.HOME.create();
            icon.setSize("var(--lumo-icon-size-s)");

            TopHeader.add(home);


            return TopHeader;
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
            tabs.add(
                    createTab(VaadinIcon.LIST_UL, JobView.class),
                    createTab(VaadinIcon.LOCATION_ARROW, ShowAddressView.class),
                    createTab(VaadinIcon.USER, EditProfileView.class),
                    createTab(VaadinIcon.MAILBOX, ContactView.class));

            tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL,
                    TabsVariant.LUMO_EQUAL_WIDTH_TABS);
            tabs.setSizeFull();

            return tabs;
        }


        private Tab createTab(VaadinIcon viewIcon, Class<? extends Component> navigationTarget) {
            // Setting up the Icon for the Tab
            Icon icon = viewIcon.create();
            icon.setSize("var(--lumo-icon-size-s)");
            //icon.getStyle().set("margin", "auto");

            // Setting up the Route for the Tab
            Span tabTitle = new Span(navigationTarget.getSimpleName());
            tabTitle.getStyle().set("font-size", "10px");
            VerticalLayout tabLayout = new VerticalLayout();
            tabLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            tabLayout.add(icon, tabTitle);
            //tabLayout.
            RouterLink link = new RouterLink();

            link.add(tabLayout);
            link.setRoute(navigationTarget);

            // Creating new Tab Instance
            Tab tab = new Tab(link);
            tab.getStyle().set("margin", "0");
            ComponentUtil.setData(tab, Class.class, navigationTarget);
            return tab;
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

        //Der aktuell-selektierte Tab wird gehighlighted.
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);

        //Setzen des aktuellen Names des Tabs
        viewTitle.setText(getCurrentPageTitle());

        // Setzen des Vornamens von dem aktuell eingeloggten Benutzer
        //helloUser.setText("Hello my dear old friend!! "  + this.getCurrentNameOfUser() );
    }
    private String getCurrentNameOfUser() {
        return String.valueOf(getCurrentUser().getEmail());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }
    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
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



