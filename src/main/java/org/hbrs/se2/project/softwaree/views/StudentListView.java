package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.softwaree.control.StudentListControl;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.entities.Skill;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route(value = "studentlist")
public class StudentListView extends VerticalLayout {

    private final StudentListControl studentListControl;
    Grid<StudentDTO> grid = new Grid<>(StudentDTO.class);
    TextField filterText = new TextField();

    public StudentListView(StudentListControl studentListControl){
        this.studentListControl = studentListControl;

        addClassName("student-list-view");
        setSizeFull();
        configureGrid();
        configureFilter();

        add(filterText, grid);
        updateList();
    }

    private void configureFilter() {
        filterText.setPlaceholder("Suche");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void configureGrid() {
        grid.addClassName("student-grid");
        grid.setSizeFull();
        grid.setItemDetailsRenderer(createStudentDTODetailsRenderer());

        initColumns();
    }

    private void initColumns() {
        grid.removeAllColumns();
        grid.addColumn(studentDTO -> studentDTO.getFirstName() + " " + studentDTO.getLastName()).setHeader("Name");
        grid.addColumn(StudentDTO::getSubject).setHeader("Studiengang");
        grid.addColumn(studentDTO -> studentDTO.getSkills().stream().map(Skill::getDescription).collect(Collectors.joining(", "))).setHeader("Skills");
        grid.addColumn(StudentDTO::getDegree).setHeader("Abschluss");
        grid.addColumn(StudentDTO::getUniversity).setHeader("Universität");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }


    private void updateList() {
        grid.setItems(studentListControl.findAllDTO(filterText.getValue()));
    }

    private static ComponentRenderer<StudentDTODetailsFormLayout, StudentDTO> createStudentDTODetailsRenderer() {
        return new ComponentRenderer<>(StudentDTODetailsFormLayout::new,
                StudentDTODetailsFormLayout::setStudentDTO);
    }
}

class StudentDTODetailsFormLayout extends FormLayout {
    private final TextField subjectField = new TextField("Studiengang");
    private final TextField skillField = new TextField("Skills");
    private final TextField degreeField = new TextField("Abschluss");
    private final TextField universityField = new TextField("Universität");


    public StudentDTODetailsFormLayout() {
        Stream.of(subjectField, degreeField, skillField,
                universityField).forEach(field -> {
            field.setReadOnly(true);
            add(field);
        });

        setResponsiveSteps(new ResponsiveStep("0", 3));

        setColspan(skillField, 3);
        setColspan(universityField, 3);
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        subjectField.setValue(studentDTO.getSubject() == null ? "" : studentDTO.getSubject());
        degreeField.setValue(studentDTO.getDegree() == null ? "" : studentDTO.getDegree());
        skillField.setValue(studentDTO.getSkills().stream().map(Skill::getDescription).collect(Collectors.joining(", ")));
        universityField.setValue(studentDTO.getUniversity() == null ? "" : studentDTO.getUniversity());
    }
}
