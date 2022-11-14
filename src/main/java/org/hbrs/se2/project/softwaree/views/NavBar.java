package org.hbrs.se2.project.softwaree.views;
import com.vaadin.flow.component.applayout.AppLayout;
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
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;




    @Route("main2")
// tag::snippet[]
    public class NavBar extends AppLayout {

        public NavBar() {
            H1 title = new H1("MyApp");
            title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                    .set("margin", "var(--lumo-space-m) var(--lumo-space-l)");

            Tabs tabs = getTabs();

            H2 viewTitle = new H2("View title");
            Paragraph viewContent = new Paragraph("View content");

            Div content = new Div();
            content.add(viewTitle, viewContent);

            addToNavbar(title);
            addToNavbar(true, tabs);

            Tabs secondaryNavbar = getSecondaryNavigation();

            HorizontalLayout wrapper = new HorizontalLayout(secondaryNavbar);
            setContent(wrapper);
           // setContent(content);
            //addToNavbar(true, wrapper);

        }
        // end::snippet[]

        private Tabs getTabs() {
            Tabs tabs = new Tabs();
            tabs.add(createTab(VaadinIcon.DASHBOARD, "Dashboard"),
                    createTab(VaadinIcon.CART, "Orders"),
                    createTab(VaadinIcon.USER_HEART, "Customers"),
                    createTab(VaadinIcon.PACKAGE, "Products"));
            tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL,
                    TabsVariant.LUMO_EQUAL_WIDTH_TABS);
            return tabs;
        }

        private Tab createTab(VaadinIcon viewIcon, String viewName) {
            Icon icon = viewIcon.create();
            icon.setSize("var(--lumo-icon-size-s)");
            icon.getStyle().set("margin", "auto");

            RouterLink link = new RouterLink();
            link.add(icon);
            // Demo has no routes
            // link.setRoute(viewClass.java);
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
        // tag::snippet[]
    }
// end::snippet[]


