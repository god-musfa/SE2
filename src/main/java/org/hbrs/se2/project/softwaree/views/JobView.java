package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.hbrs.se2.project.softwaree.control.ManageJobsControl;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Darstellung einer Tabelle (bei Vaadin: ein Grid) zur Anzeige von Autos.
 * Hier wurden nur grundlegende Elemente verarbeitet. Weitere Optionen (z.B.
 * weitere Filter-Möglichkeiten) kann man hier entnehmen:
 * https://vaadin.com/components/vaadin-grid/java-examples/header-and-footer
 *
 */
@Route(value = Globals.Pages.SHOW_JOBS, layout = NavBar.class)
@PageTitle("show jobs")
@CssImport("./styles/views/showcars/show-cars-view.css")
public class JobView extends Div {

    private List<Job> jobList;

    public JobView(ManageJobsControl jobsControl) {
        addClassName("jobs");

        // Auslesen alle abgespeicherten Autos aus der DB (über das Control)
        jobList = jobsControl.readAllJobs();


       /* imageList.add(new Image("icons/Deutsche_Telekom_2022.svg.png"));
        imageList.add(new Image("icons/Deutsche_Telekom_2022.svg.png"));
        imageList.add(new Image("icons/Deutsche_Telekom_2022.svg.png"));*/
        // Titel überhalb der Tabelle
        add(this.createTitle());

        //Card List View Darstellung mit Carten

        add(createCardList());

        // Einfache Darstellung mit Grid Tabelle
        //add(createGridTable());
    }

    private Component createGridTable() {
        Grid<Job> grid = new Grid<>();


        ListDataProvider<Job> dataProvider = new ListDataProvider<>(
                jobList);
        grid.setDataProvider(dataProvider);

        Grid.Column<Job> streetColumn = grid
                .addColumn(Job::getTitle).setHeader("Jobtitle");
        Grid.Column<Job> numberColumn = grid.addColumn(Job::getCompanyName)
               .setHeader("Company Names");

        Grid.Column<Job> beschreibung = grid
                .addColumn(Job::getDescription)
                .setHeader("Beschreibung");

        Grid.Column<Job> requirements = grid
                .addColumn(Job::getRequirementDescription)
                .setHeader("Requirements");

        Grid.Column<Job> creation_date = grid
                .addColumn(Job::getCreationDate)
                .setHeader("Creation Date");

        Grid.Column<Job> location = grid
                .addColumn(Job::getLocation)
                .setHeader("Location");

        Grid.Column<Job> benefit = grid
                .addColumn(Job::getBenefitsAsString)
                .setHeader("Vorteile");

        HeaderRow filterRow = grid.appendHeaderRow();



        // Filter nach dem Ort des Jobs
        TextField locationFilter = new TextField();
        locationFilter.addValueChangeListener(event -> dataProvider.addFilter(
                Job -> StringUtils.containsIgnoreCase(Job.getLocation(),
                        locationFilter.getValue())));

        locationFilter.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(location).setComponent(locationFilter);
        locationFilter.setSizeFull();
        locationFilter.setPlaceholder("Ort...");

        // Filter nach der Beschreibung des Jobs
        TextField descriptionFilter = new TextField();
        descriptionFilter.addValueChangeListener(event -> dataProvider
                .addFilter(Job -> StringUtils.containsIgnoreCase(
                        String.valueOf(Job.getDescription()), descriptionFilter.getValue())));

        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(beschreibung).setComponent(descriptionFilter);
        descriptionFilter.setSizeFull();
        descriptionFilter.setPlaceholder("Beschreibung...");


        grid.addSelectionListener(selection -> {
            Optional<Job> optionalJob = selection.getFirstSelectedItem();
            if (optionalJob.isPresent()) {
                UI.getCurrent().navigate(Globals.Pages.SHOW_JOB_DETAILS+"/"+optionalJob.get().getId());

                /*System.out.printf("Selected person: %s%n",
                optionalJob.get());*/
            }
        });

        return grid;
    }

    private Component createTitle() {
        return new H3("Stellenanzeigen");
    }

    private Component createCardList() {
        Grid<Job> grid = new Grid<>();
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.setItems(jobList);
        grid.addComponentColumn(job -> createCard(job));
        grid.addSelectionListener(selection -> {
            Optional<Job> optionalJob = selection.getFirstSelectedItem();
            if (optionalJob.isPresent()) {
                UI.getCurrent().navigate(Globals.Pages.SHOW_JOB_DETAILS+"/"+optionalJob.get().getTitle());

                /*System.out.printf("Selected person: %s%n",
                optionalJob.get());*/
            }
        });
        add(grid);
        return grid;
    }

    private HorizontalLayout createCard(Job job) {

        // [horizontal ] Card Wrapper
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");
        card.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);

        // Company Logo
        Image image = new Image();
        image.setSrc("icons/icon.png");
        image.setHeight("50px");

        // [vertical] Content Wrapper Component
        VerticalLayout contentWrapper = new VerticalLayout();
        contentWrapper.addClassName("description");
        contentWrapper.setSpacing(false);
        contentWrapper.setPadding(false);
        contentWrapper.setAlignItems(FlexComponent.Alignment.START);

        // [Horizontal] Header of the Card
        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");
        header.setWidthFull();

        // 1. Paragraph Job Title and Creation Date
        H2 title = new H2(job.getTitle());
        title.addClassName("job_title");
        String dateString = ( job.getCreationDate()!= null ?  job.getCreationDate().toString() : "NaN");
        Span date = new Span("vom " + (dateString)) ;
        date.addClassName("creation_date");
        date.getStyle().set("font-style" , "italic");
        date.getStyle().set("margin-left", "auto");

        header.add(title, date);

        // 2. Job Description and
        Paragraph job_description = new Paragraph(job.getDescription());
        job_description.addClassName("job_description");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        Icon likeIcon = VaadinIcon.HEART.create();
        likeIcon.addClassName("icon");

        String viewsString = ( job.getViews() != null ?  job.getViews().toString() : "NaN");
        Span likes = new Span(viewsString);
        likes.addClassName("likes");
        /*Icon commentIcon = VaadinIcon.COMMENT.create();
        commentIcon.addClassName("icon");
        Span comments = new Span(job.getComments());
        comments.addClassName("comments");
        Icon shareIcon = VaadinIcon.CONNECT.create();
        shareIcon.addClassName("icon");
        Span shares = new Span(job.getShares());
        shares.addClassName("shares");*/

        actions.add(likeIcon, likes);//, commentIcon, comments, shareIcon, shares);

        contentWrapper.add(header, job_description, actions);
        card.add(image, contentWrapper);
        card.addClickListener(event -> {

        });
        return card;
    }

    //Create Local Navigation for the Profile Page including Editing Profile, Settings, and Logout Buttons
    private Component createLocalNavigation() {
        Button logout = createButton("logout");
        logout.addClickListener(event -> {
            UI ui = this.getUI().get();
            ui.getSession().close();
            ui.getPage().setLocation("/");
        });

        super.setSizeFull();
        HorizontalLayout localNavigation = new HorizontalLayout(createButton("Edit"),createButton("Settings"), logout);
        localNavigation.setSpacing(false); // Space around the Components = False
        localNavigation.setWidthFull();
        localNavigation.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        logout.getStyle().set("margin-left", "auto").set("padding-right", "3em"); // Specify the lignment of one component -> logout to the  Left

        return localNavigation;
    }

    private Button createButton(String name) {
        Button button = new Button(name);
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        return button;
    }

    /*
        // Anordung der Componenten
        localNavigation.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        // Nur bei Vertical Layout : Component ans Ende schieben
        localNavigation.setAlignSelf(FlexComponent.Alignment.END, logout);

        // Component nach ganz links anordnen
        logout.getStyle().set("margin-left", "auto");

        // Ausbreitung der Componenten
        localNavigation.setAlignItems(FlexComponent.Alignment.STRETCH);*/

}
