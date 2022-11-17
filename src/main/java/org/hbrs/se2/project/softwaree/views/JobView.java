package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.Component;
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
import org.apache.commons.lang3.StringUtils;
import org.hbrs.se2.project.softwaree.control.ManageJobsControl;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.util.Iterator;
import java.util.List;
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
        add(this.createGridTable());
    }

    private Component createGridTable() {
        Grid<Job> grid = new Grid<>();

        // Befüllen der Tabelle mit den zuvor ausgelesenen Autos
        ListDataProvider<Job> dataProvider = new ListDataProvider<>(
                personList);
        grid.setDataProvider(dataProvider);

        Grid.Column<Job> streetColumn = grid
                .addColumn(Job::getTitle).setHeader("Jobtitle");
        Grid.Column<Job> numberColumn = grid.addColumn(Job::getAllCompanyNames)
                .setHeader("Company Names");
        Grid.Column<Job> postalColumn = grid
                .addColumn(Job::getCreation_date)
                .setHeader("Creation Date");
        Grid.Column<Job> cityColumn = grid
                .addColumn(Job::getLocation)
                .setHeader("City");

        HeaderRow filterRow = grid.appendHeaderRow();

        // First filter
        TextField streetField = new TextField();
        streetField.addValueChangeListener(event -> dataProvider.addFilter(
                addressDTO -> StringUtils.containsIgnoreCase(addressDTO.getLocation(),
                        streetField.getValue())));

        streetField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(streetColumn).setComponent(streetField);
        streetField.setSizeFull();
        streetField.setPlaceholder("Filter");

        // Second filter
        TextField cityField = new TextField();
        cityField.addValueChangeListener(event -> dataProvider
                .addFilter(JobDTO -> StringUtils.containsIgnoreCase(
                        String.valueOf(JobDTO.getLocation()), cityField.getValue())));

        cityField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(cityColumn).setComponent(cityField);
        cityField.setSizeFull();
        cityField.setPlaceholder("Filter");

        return grid;
    }

    private Component createTitle() {
        return new H3("Search for Jobs");
    }


}
