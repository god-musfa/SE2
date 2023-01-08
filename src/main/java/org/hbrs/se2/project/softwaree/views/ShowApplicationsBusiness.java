package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.hbrs.se2.project.softwaree.control.ManageApplicationControl;
import org.hbrs.se2.project.softwaree.dtos.ApplicationDTO;
import org.hbrs.se2.project.softwaree.dtos.MessageDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Darstellung einer Tabelle (bei Vaadin: ein Grid) zur Anzeige von Autos.
 * Hier wurden nur grundlegende Elemente verarbeitet. Weitere Optionen (z.B.
 * weitere Filter-Möglichkeiten) kann man hier entnehmen:
 * https://vaadin.com/components/vaadin-grid/java-examples/header-and-footer
 *
 */
@Route(value = "showApplicationsBusiness", layout = NavBar.class)
@PageTitle("show jobs")
@CssImport("./styles/views/showcars/show-cars-view.css")
public class ShowApplicationsBusiness extends Div  {

    private List<ApplicationDTO> applicationList;
    Button button = new Button("Alle Bewerber kontaktieren");
    UserDTO user;
    public ShowApplicationsBusiness(ManageApplicationControl applicationControl) {
        addClassName("jobs");
        user = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
        // Auslesen alle abgespeicherten Autos aus der DB (über das Control)
        applicationList = applicationControl.readAllApplications(user.getId());


        // Titel überhalb der Tabelle
        add(this.createTitle());

        // Hinzufügen der Tabelle (bei Vaadin: ein Grid)
        add(createGridTable());
    }

    private Component createGridTable() {
        Grid<org.hbrs.se2.project.softwaree.dtos.ApplicationDTO> grid = new Grid<>();


        ListDataProvider<org.hbrs.se2.project.softwaree.dtos.ApplicationDTO> dataProvider = new ListDataProvider<>(
                applicationList);
        grid.setDataProvider(dataProvider);

        Grid.Column<ApplicationDTO> title = grid
                .addColumn(org.hbrs.se2.project.softwaree.dtos.ApplicationDTO::getTitle).setHeader("Jobtitel");
        Grid.Column<ApplicationDTO> deadline = grid.addColumn(ApplicationDTO::getDeadline)
                .setHeader("Deadline");

        Grid.Column<ApplicationDTO> firstName = grid
                .addColumn(ApplicationDTO::getFirstName)
                .setHeader("Vorname");

        Grid.Column<ApplicationDTO> lastName = grid
                .addColumn(ApplicationDTO::getLastName)
                .setHeader("Nachname");

        Grid.Column<ApplicationDTO> semester = grid
                .addColumn(ApplicationDTO::getSemester)
                .setHeader("Semester");

        Grid.Column<ApplicationDTO> subject = grid
                .addColumn(ApplicationDTO::getSubject)
                .setHeader("Studienfach");

        Grid.Column<ApplicationDTO> university = grid
                .addColumn(ApplicationDTO::getUniversity)
                .setHeader("Universität");

        Grid.Column<ApplicationDTO> degree = grid
                .addColumn(ApplicationDTO::getDegree)
                .setHeader("Abschluss");

        HeaderRow filterRow = grid.appendHeaderRow();



        // Filter nach dem Abschluss des Jobs
        TextField degreeFilter = new TextField();
        degreeFilter.addValueChangeListener(event -> dataProvider.addFilter(
                ApplicationDTO -> StringUtils.containsIgnoreCase(ApplicationDTO.getDegree(),
                        degreeFilter.getValue())));

        degreeFilter.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(degree).setComponent(degreeFilter);
        degreeFilter.setSizeFull();
        degreeFilter.setPlaceholder("Abschluss...");

        // Filter nach der Hochschule
        TextField universityFilter = new TextField();
        universityFilter.addValueChangeListener(event -> dataProvider
                .addFilter(ApplicationDTO -> StringUtils.containsIgnoreCase(
                        String.valueOf(ApplicationDTO.getUniversity()), universityFilter.getValue())));

        universityFilter.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(university).setComponent(universityFilter);
        universityFilter.setSizeFull();
        universityFilter.setPlaceholder("Universität...");


        grid.addSelectionListener(selection -> {
            Optional<ApplicationDTO> optionalApplicationDTO = selection.getFirstSelectedItem();
            if (optionalApplicationDTO.isPresent()) {
                UI.getCurrent().navigate("profile/"+optionalApplicationDTO.get().getStudent_id());

                /*System.out.printf("Selected person: %s%n",
                optionalJob.get());*/
            }
        });

        return grid;
    }


    private Component createTitle() {
        VerticalLayout layout = new VerticalLayout();
        layout.add(new H3("Bewerbungen"));
        if (!applicationList.isEmpty()){
            layout.add(button);
            List<MessageDTO> mlist = new ArrayList<>();
            for(ApplicationDTO a:applicationList){
                MessageDTO m = new MessageDTO(null, null,null,a.getStudent_id(),user.getId(),a.getJobId());
                mlist.add(m);
            };
            button.addClickListener(event-> {
                UI.getCurrent().getSession().setAttribute("userList", mlist);
                UI.getCurrent().navigate("kontakt");
            });}

        return layout;
    }



}
