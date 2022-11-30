package org.hbrs.se2.project.softwaree.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.hbrs.se2.project.softwaree.dtos.SkillDTO;
import org.hbrs.se2.project.softwaree.util.Globals;


import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CssImport("./styles/components/SkillsComponent.css")
public class SkillsComponent extends VerticalLayout {

    FlexLayout addSkillLayout = new FlexLayout();
    Button addSkillButton = new Button("Hinzufügen");
    ComboBox newSkillField = new ComboBox();
    UnorderedList skillsLayout = new UnorderedList();
    Label infoLabel = new Label("Fähigkeiten aus der Liste hinzufügen oder durch Klicken entfernen");

    private HashMap<String, ListItem> skills = new HashMap<>();


    /** Component UI **/

    public SkillsComponent(List<SkillDTO> autoCompleteSet) {
        // For outer component styling:
        addClassName("skill-component");

        // Styling inner components:
        newSkillField.setPlaceholder("Neue Fähigkeit");
        newSkillField.setAllowCustomValue(false);               // Note: can be set to true, to accept custom skills.

        skillsLayout.addClassName("skills-layout");
        addSkillButton.addClassName("add-button");
        infoLabel.addClassName("info-label");

        // Header part:
        addSkillLayout.add(newSkillField, addSkillButton);

        // Add both parts to main component:
        add(infoLabel, addSkillLayout, skillsLayout);

        // Set autocomplete set to combobox autocomplete:
        this.newSkillField.setItems(
                autoCompleteSet.stream()
                        .map(SkillDTO::getDescription)
                        .collect(Collectors.toList())
        );

        // Set button behavior:
        addSkillButton.addClickListener(clickEvent -> {
            addSkill(newSkillField.getValue().toString());
        });
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
            ListItem newItem = new ListItem(skillName);
            newItem.addClassName("skill-pill");
            newItem.addClickListener(clickEvent -> {
                removeSkill(skillName);
            });
            skillsLayout.add(newItem);
            skills.put(skillName, newItem);
            skillsLayout.add(newItem);
        }
    }

    public Set<String> getSkillNames() {
        return skills.keySet();
    }







}
