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
import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.util.List;

/**
 * Darstellung einer Tabelle (bei Vaadin: ein Grid) zur Anzeige von Autos.
 * Hier wurden nur grundlegende Elemente verarbeitet. Weitere Optionen (z.B.
 * weitere Filter-Möglichkeiten) kann man hier entnehmen:
 * https://vaadin.com/components/vaadin-grid/java-examples/header-and-footer
 *
 */
@Route(value = Globals.Pages.SHOW_ADDRESS, layout = NavBar.class)
@PageTitle("Show Addresses")
@CssImport("./styles/views/showcars/show-cars-view.css")
public class ShowAddressView extends Div  {

    private List<AddressDTO> personList;
    private List<CompanyDTO> companyList;

    public ShowAddressView(ManageAddressControl addressControl) {
            addClassName("show-address-view");

            // Auslesen alle abgespeicherten Autos aus der DB (über das Control)
            personList = addressControl.readAllAddress();


            // Titel überhalb der Tabelle
            add(this.createTitle());

            // Hinzufügen der Tabelle (bei Vaadin: ein Grid)
            add(this.createGridTable());
    }

    private Component createGridTable() {
        Grid<AddressDTO> grid = new Grid<>();

        // Befüllen der Tabelle mit den zuvor ausgelesenen Autos
        ListDataProvider<AddressDTO> dataProvider = new ListDataProvider<>(
                personList);
        grid.setDataProvider(dataProvider);

        Grid.Column<AddressDTO> streetColumn = grid
                .addColumn(AddressDTO::getStreet).setHeader("Street");
        Grid.Column<AddressDTO> numberColumn = grid.addColumn(AddressDTO::getNumber)
                .setHeader("Number");
        Grid.Column<AddressDTO> postalColumn = grid
                .addColumn(AddressDTO::getPostalCode)
                .setHeader("Postal Code");
        Grid.Column<AddressDTO> cityColumn = grid
                .addColumn(AddressDTO::getCity)
                .setHeader("City");

        HeaderRow filterRow = grid.appendHeaderRow();

       // First filter
        TextField streetField = new TextField();
        streetField.addValueChangeListener(event -> dataProvider.addFilter(
                addressDTO -> StringUtils.containsIgnoreCase(addressDTO.getStreet(),
                        streetField.getValue())));

        streetField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(streetColumn).setComponent(streetField);
        streetField.setSizeFull();
        streetField.setPlaceholder("Filter");

        // Second filter
        TextField cityField = new TextField();
        cityField.addValueChangeListener(event -> dataProvider
                .addFilter(addressDTO -> StringUtils.containsIgnoreCase(
                        String.valueOf(addressDTO.getCity()), cityField.getValue())));

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
