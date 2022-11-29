package org.hbrs.se2.project.softwaree.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.hbrs.se2.project.softwaree.dtos.SkillDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class SkillsComponent extends VerticalLayout {

    HorizontalLayout addSkillLayout = new HorizontalLayout();
    Button addSkillButton = new Button("Hinzufügen");
    ComboBox newSkillField = new ComboBox();
    FormLayout skillsLayout = new FormLayout();


    private HashMap<String, SkillPill> skills = new HashMap<>();


    /** Component UI **/

    public SkillsComponent(List<SkillDTO> autoCompleteSet) {

        // Styling inner components:
        newSkillField.setPlaceholder("Neue Fähigkeit");
        newSkillField.setAllowCustomValue(false);               // Note: can be set to true, to accept custom skills.
        skillsLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.SMARTPHONE_PORTRAIT, 1),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.SMARTPHONE_LANDSCAPE, 2),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.TABLET_PORTRAIT, 4)
        );
        setMaxHeight("30%");

        // Header part:
        addSkillLayout.add(newSkillField, addSkillButton);

        // Add both parts to main component:
        add(addSkillLayout, skillsLayout);

        // Set autocomplete set to combobox autocomplete:
        this.newSkillField.setItems(
                autoCompleteSet.stream()
                        .map(SkillDTO::getDescription)
                        .collect(Collectors.toList())
        );
    }

    class SkillPill extends HorizontalLayout {
        private String skillName = "-";
        private Label skillLabel;
        private Icon removeIcon;

        public SkillPill(String skillName) {
            this.skillName = skillName;
            this.skillLabel = new Label(skillName);
            this.removeIcon = VaadinIcon.CLOSE.create();

            addClassName("skill-pill");
            this.removeIcon.setSize("1rem");
            setMaxWidth("5rem");
            this.skillLabel.setMaxWidth("4rem");

            this.removeIcon.addClickListener(clickEvent -> {
                removeSkill(this.skillName);
            });

        }

    }



    /** Component logic **/
    public void removeSkill(String skillName) {
        if (skills.containsKey(skillName)) {
            skillsLayout.remove(skills.get(skillName));
            skills.remove(skillName);
        }
    }

    public void addSkill(String skillName) {
        if (!skills.containsKey(skillName) && skills.size() < 20) {
            SkillPill newPill = new SkillPill(skillName);
            skills.put(skillName, newPill);
            skillsLayout.add(newPill);
        }
    }

    public Set<String> getSkillNames() {
        return skills.keySet();
    }







}
