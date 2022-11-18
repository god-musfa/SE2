package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.shared.Registration;
import org.apache.commons.lang3.StringUtils;
import org.hbrs.se2.project.softwaree.control.ManageJobsControl;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.util.Globals;
import org.springframework.util.ObjectUtils;

import java.util.Iterator;
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
public class JobView extends Div  {

    private List<Job> personList;
    public JobView(ManageJobsControl jobsControl) {
        addClassName("jobs");

        // Auslesen alle abgespeicherten Autos aus der DB (über das Control)
        personList = jobsControl.readAllJobs();

        // Titel überhalb der Tabelle
        add(this.createTitle());

        // Hinzufügen der Tabelle (bei Vaadin: ein Grid)
        add(createGridTable());
    }

    private Component createGridTable() {
        Grid<Job> grid = new Grid<>();


        ListDataProvider<Job> dataProvider = new ListDataProvider<>(
                personList);
        grid.setDataProvider(dataProvider);

        Grid.Column<Job> streetColumn = grid
                .addColumn(Job::getTitle).setHeader("Jobtitle");
        Grid.Column<Job> numberColumn = grid.addColumn(Job::getAllCompanyNames)
               .setHeader("Company Names");

        Grid.Column<Job> beschreibung = grid
                .addColumn(Job::getDescription)
                .setHeader("Beschreibung");

        Grid.Column<Job> requirements = grid
                .addColumn(Job::getRequirementDescription)
                .setHeader("Requirements");

        Grid.Column<Job> creation_date = grid
                .addColumn(Job::getCreation_date)
                .setHeader("Creation Date");

        Grid.Column<Job> location = grid
                .addColumn(Job::getLocation)
                .setHeader("Location");

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
                UI.getCurrent().navigate(Globals.Pages.SHOW_JOB_DETAILS+"/"+optionalJob.get().getTitle());

                /*System.out.printf("Selected person: %s%n",
                optionalJob.get());*/
            }
        });

        return grid;
    }


    private Component createTitle() {
        return new H3("Stellenanzeigen");
    }



}
