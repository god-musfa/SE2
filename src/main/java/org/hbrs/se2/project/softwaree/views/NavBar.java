package org.hbrs.se2.project.softwaree.views;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CssImport("./styles/views/main/navbar.css")
@Route("main2")
//@PWA(name = "HelloCar", shortName = "HelloCar")
@JsModule("./styles/shared-styles.js")
public class NavBar extends AppLayout implements BeforeEnterObserver {
    Map<Class<? extends Component>, String> titleMap;
    private Tabs navigation;
    private Div controlContainer;
    private Image i = new Image("images/Softwaree_klein.png", "Logo");
    private Tab homeTab;

    public NavBar() {
        titleMap = createTitleMap();
        i.addClassName("SoftwareeLogo");
        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
        });

        if (getCurrentUser() == null || getCurrentUser().getUserType() == null) {
            System.out.print("Not sufficient Roles!");
        } else {
            this.navigation = createTabs(getCurrentUser().getUserType());
            navigation.addClassName("navigationTabs");

            this.controlContainer = new Div();
            getHomeTab("");
            addToNavbar(this.controlContainer);
            addToNavbar(true, navigation);
        }
    }

    private void logoEvent() {
        UI.getCurrent().navigate("jobs/");
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

    private Map<Class<? extends Component>, String> createTitleMap() {
        Map<Class<? extends Component>, String> titleMap = new HashMap<>();
        titleMap.put(JobListView.class, "Stellenanzeigen");
        titleMap.put(ShowApplicationsBusiness.class, "Bewerbungen");
        titleMap.put(EditProfileView.class, "Profil");
        titleMap.put(JobOfferView.class, "");
        titleMap.put(Logout.class, "");
        titleMap.put(MessageView.class, "Nachrichten");
        titleMap.put(StudentListView.class, "Studenten suchen");

        return titleMap;
    }

    private Tabs createTabs(String type) {
        Tabs tabs = new Tabs();
        Tab logout = createTab(VaadinIcon.SIGN_OUT.create(), Logout.class, "Logout");
        logout.getStyle().set("flex-grow", "0.5");

        if (type.equals("student")) {
            tabs.add(
                    createTab(VaadinIcon.LIST_UL.create(), JobListView.class, "Stellenanzeigen"),
                    createTab(VaadinIcon.USER.create(), EditProfileView.class, "Mein Profil"),
                    createTab(VaadinIcon.CHAT.create(), MessageView.class, "Nachrichten"),
                    logout
            );
        } else if (type.equals("company")) {
            tabs.add(
                    createTab(LumoIcon.ORDERED_LIST.create(), StudentListView.class, "Studenten finden"),
                    createTab(VaadinIcon.ACADEMY_CAP.create(), JobListView.class, "Meine Stellenanzeigen"),
                    createTab(VaadinIcon.CHAT.create(), MessageView.class, "Nachrichten"),
                    createTab(VaadinIcon.TEXT_INPUT.create(), ShowApplicationsBusiness.class, "Bewerbungen"),
                    createTab(VaadinIcon.USER.create(), EditProfileView.class, "Mein Profil"),
                    logout
            );
        } else {
            System.out.println("LOG: In Constructor of App View - No sufficient Role given! \n " + type);
        }
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL,
                TabsVariant.LUMO_EQUAL_WIDTH_TABS);
        tabs.setSizeFull();
        return tabs;
    }

    private Tab createTab(Component navigationIcon, Class<? extends Component> navigationTarget, String tabTitleName) {
        // Setting up the Route for the Tab

        VerticalLayout tabLayout = new VerticalLayout();
        tabLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        if (tabTitleName.equals("")) {
            tabLayout.add(navigationIcon);
        } else {
            Span tabTitle = new Span(tabTitleName);
            tabLayout.add(navigationIcon, tabTitle);
        }

        // Routing
        RouterLink link = new RouterLink();
        link.add(tabLayout);
        link.setRoute(navigationTarget);
        link.addClassName("RouterLinks");

        // Creating new Tab Instance
        Tab tab = new Tab(link);
        tab.getStyle().set("margin", "0");

        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }


    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        if (!checkIfUserIsLoggedIn()) return;
        getTabForComponent(getContent()).ifPresent(navigation::setSelectedTab);
        getHomeTab(getCurrentPageTitle());
    }

    private void getHomeTab(String currentPageTitle) {
        this.controlContainer.removeAll();
        switch (currentPageTitle) {
            case (Globals.PageTitles.PAGETITLE_STELLENANZEIGE_DETAILS):
                this.controlContainer.add(createTab(VaadinIcon.ARROW_BACKWARD.create(), JobListView.class, "zurück"));
                break;
            case (Globals.PageTitles.JOB_CONTACT):
                this.controlContainer.add(createReturnTab(VaadinIcon.ARROW_BACKWARD.create(), JobDetailView.class));
                break;
            default:
                this.controlContainer.add(createTab(i, JobListView.class, ""));
        }
    }

    private Tab createReturnTab(Icon icon, Class<? extends Component> navigationTarget) {
        VerticalLayout tabLayout = new VerticalLayout();
        tabLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        int jobID = (UI.getCurrent().getSession().getAttribute("jobID") != null) ? (Integer) UI.getCurrent().getSession().getAttribute("jobID") : -1;
        tabLayout.add(icon);

        // Routing
        RouterLink link = new RouterLink();
        link.add(tabLayout);
        link.setRoute(JobDetailView.class,String.valueOf(jobID));
        link.addClassName("RouterLinks");

        // Creating new Tab Instance
        Tab tab = new Tab(link);
        tab.getStyle().set("margin", "0");
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    private String getCurrentNameOfUser() {
        return String.valueOf(getCurrentUser().getEmail());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return navigation.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
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
        if (getCurrentUser() == null) {
            beforeEnterEvent.rerouteTo(Globals.Pages.LOGIN_VIEW);
        }
    }

    // Maps the View Classes to the titles that are actually displayed

}



