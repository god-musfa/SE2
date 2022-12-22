package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import org.hbrs.se2.project.softwaree.control.StudentListControl;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route(value = "studentlist")
@CssImport("./styles/views/studentlist/student-list-view.css")
public class StudentListView extends VerticalLayout {

    private final StudentListControl studentListControl;
    private final Grid<StudentDTO> grid = new Grid<>(StudentDTO.class);
    private final TextField filterText = new TextField();
    private final Button backbutton = new Button("Zurück");

    public StudentListView(StudentListControl studentListControl) {
        this.studentListControl = studentListControl;

        switch (studentListControl.getUserType((UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER))) {
            case COMPANY:
                buildCompany();
                break;
            case STUDENT:
                navigateJobs();
                break;
            default:
                navigateMain();
                break;
        }
    }

    private void buildCompany() {
        addClassName("student-list-view");
        setSizeFull();
        configureGrid();
        configureFilter();
        configureButton();

        HorizontalLayout horizontalLayout = new HorizontalLayout(filterText, backbutton);
        horizontalLayout.addClassName("horizontal-layout");
        add(horizontalLayout, grid);
        updateList();
    }

    private void navigateJobs() {
        UI.getCurrent().navigate(Globals.Pages.SHOW_JOBS);
        UI.getCurrent().getPage().reload(); //the url is correct, but somehow it doesn't load, so this does it explicit
    }

    private void navigateMain() {
        UI.getCurrent().navigate(Globals.Pages.MAIN_VIEW);
        UI.getCurrent().getPage().reload(); //the url is correct, but somehow it doesn't load, so this does it explicit
    }

    private void configureButton() {
        backbutton.addClassName("back-button");
        backbutton.addClickListener(buttonClickEvent -> UI.getCurrent().getPage().getHistory().back());


    }

    private void configureFilter() {
        filterText.addClassName("filter");
        filterText.setPlaceholder("Suche");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void configureGrid() {
        grid.addClassName("student-grid");
        grid.setSizeFull();
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        if (isMobileDevice()) { //drop-down menu for mobile devices
            grid.setItemDetailsRenderer(createStudentDTODetailsRenderer());
        }

        initColumns();
    }

    private void initColumns() {
        grid.removeAllColumns();
        grid.addColumn(studentDTO -> studentDTO.getFirstName() + " " + studentDTO.getLastName()).setHeader("Name");
        grid.addColumn(StudentDTO::getSubject).setHeader("Studiengang");

        if (!isMobileDevice()) {    //instead of drop-down menu a more detailes grid
            grid.addColumn(studentDTO -> studentDTO.getSkills().stream().map(Skill::getDescription).collect(Collectors.joining(", "))).setHeader("Skills");
            grid.addColumn(StudentDTO::getDegree).setHeader("Abschluss");
            grid.addColumn(StudentDTO::getUniversity).setHeader("Universität");

            grid.addItemDoubleClickListener(studentDTOItemDoubleClickEvent -> {
                //navigation to detailview of this student
                int id = studentDTOItemDoubleClickEvent.getItem().getId();
                UI.getCurrent().navigate(Globals.Pages.VIEW_PROFILE + "/" + id);
            });
        }

        if (!isMobileDevice()) {    //autowidth for desktop
            grid.getColumns().forEach(col -> col.setAutoWidth(true));
        }

        //for every device sortable
        grid.getColumns().forEach(col -> col.setSortable(true));
    }

    private boolean isMobileDevice() {
        WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
        return webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone();
    }


    private void updateList() {
        grid.setItems(studentListControl.findAllDTO(filterText.getValue()));
    }

    private static ComponentRenderer<StudentDTODetailsFormLayout, StudentDTO> createStudentDTODetailsRenderer() {
        //renderer for drop-down menu
        return new ComponentRenderer<>(StudentDTODetailsFormLayout::new,
                StudentDTODetailsFormLayout::setStudentDTO);
    }
}

class StudentDTODetailsFormLayout extends FormLayout {
    private final TextField subjectField = new TextField("Studiengang");
    private final TextField skillField = new TextField("Skills");
    private final TextField degreeField = new TextField("Abschluss");
    private final TextField universityField = new TextField("Universität");
    private final Button button = new Button("Details");


    public StudentDTODetailsFormLayout() {
        addClassName("Formlayout");

        Stream.of(subjectField, degreeField, skillField,
                universityField).forEach(field -> {
            field.setReadOnly(true);
            add(field);
        });

        subjectField.addClassName("drop-down-text");
        degreeField.addClassName("drop-down-text");
        skillField.addClassName("drop-down-text");
        universityField.addClassName("drop-down-text");
        button.addClassName("drop-down-button");

        add(button);
        setResponsiveSteps(new ResponsiveStep("0", 4));

        setColspan(button, 2);
        setColspan(subjectField, 2);
        setColspan(degreeField, 2);
        setColspan(skillField, 4);
        setColspan(universityField, 4);
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        subjectField.setValue(studentDTO.getSubject() == null ? "" : studentDTO.getSubject());
        degreeField.setValue(studentDTO.getDegree() == null ? "" : studentDTO.getDegree());
        skillField.setValue(studentDTO.getSkills().stream().map(Skill::getDescription).collect(Collectors.joining(", ")));
        universityField.setValue(studentDTO.getUniversity() == null ? "" : studentDTO.getUniversity());

        button.addClickListener(buttonClickEvent -> {
            int id = studentDTO.getId();
            UI.getCurrent().navigate(Globals.Pages.VIEW_PROFILE + "/" + id);
        });
    }
}