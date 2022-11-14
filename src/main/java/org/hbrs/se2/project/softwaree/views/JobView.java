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
import org.hbrs.se2.project.softwaree.control.ManageAddressControl;
import org.hbrs.se2.project.softwaree.control.ManageJobsControl;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.util.List;

/**
 * Darstellung einer Tabelle (bei Vaadin: ein Grid) zur Anzeige von Autos.
 * Hier wurden nur grundlegende Elemente verarbeitet. Weitere Optionen (z.B.
 * weitere Filter-Möglichkeiten) kann man hier entnehmen:
 * https://vaadin.com/components/vaadin-grid/java-examples/header-and-footer
 *
 */
@Route(value = "jobs", layout = AppView.class)
@PageTitle("show jobs")
@CssImport("./styles/views/showcars/show-cars-view.css")
public class JobView extends Div  {

    private List<JobDTO> personList;

    public JobView(ManageJobsControl jobsControl) {
        addClassName("show-address-view");

        // Auslesen alle abgespeicherten Autos aus der DB (über das Control)
        personList = jobsControl.readAllJobs();

        // Titel überhalb der Tabelle
        add(this.createTitle());

        // Hinzufügen der Tabelle (bei Vaadin: ein Grid)
        add(this.createGridTable());
    }

    private Component createGridTable() {
        Grid<JobDTO> grid = new Grid<>();

        // Befüllen der Tabelle mit den zuvor ausgelesenen Autos
        ListDataProvider<JobDTO> dataProvider = new ListDataProvider<>(
                personList);
        grid.setDataProvider(dataProvider);

        Grid.Column<JobDTO> streetColumn = grid
                .addColumn(JobDTO::getTitle).setHeader("Street");
        Grid.Column<JobDTO> numberColumn = grid.addColumn(JobDTO::getViews)
                .setHeader("Views");
        Grid.Column<JobDTO> postalColumn = grid
                .addColumn(JobDTO::getCreation_date)
                .setHeader("Creation Date");
        Grid.Column<JobDTO> cityColumn = grid
                .addColumn(JobDTO::getLocation)
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
        return new H3("Search for Addresses");
    }


};
