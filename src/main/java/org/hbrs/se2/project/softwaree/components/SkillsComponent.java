package org.hbrs.se2.project.softwaree.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.Query;
import org.hbrs.se2.project.softwaree.dtos.SkillDTO;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CssImport("./styles/components/SkillsComponent.css")
public class SkillsComponent extends VerticalLayout {

    /** Main Components in the SkillComponent **/
    FlexLayout addSkillLayout = new FlexLayout();
    Button addSkillButton = new Button("Hinzufügen");
    ComboBox<String> newSkillField = new ComboBox();
    UnorderedList skillsLayout = new UnorderedList();
    Label infoLabel = new Label("Fähigkeiten aus der Liste hinzufügen oder durch Klicken entfernen");
    Label skillAmountLabel = new Label("(0/20)");
    private HashMap<String, ListItem> skills = new HashMap<>();


    /** Component UI **/
    public SkillsComponent(List<SkillDTO> autoCompleteSet) {
        // For outer component styling:
        addClassName("skill-component");

        // Styling inner components:
        newSkillField.setPlaceholder("Neue Fähigkeit");
        newSkillField.setAllowCustomValue(true);               // Accept custom skills.

        skillsLayout.addClassName("skills-layout");
        addSkillButton.addClassName("add-button");
        infoLabel.addClassName("info-label");
        skillAmountLabel.addClassName("amount-label");

        // Header part:
        addSkillLayout.add(newSkillField, addSkillButton, skillAmountLabel);
        addSkillLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        addSkillLayout.setAlignContent(FlexLayout.ContentAlignment.CENTER);

        // Add both parts to main component:
        add(infoLabel, addSkillLayout, skillsLayout);

        // Set autocomplete set to combobox autocomplete:
        this.newSkillField.setItems(
                autoCompleteSet.stream()
                        .map(SkillDTO::getDescription)
                        .collect(Collectors.toList())
        );

        // Capabitlity to set own values:
        newSkillField.addCustomValueSetListener(event -> {
            List<String> items = newSkillField.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());
            items.add(event.getDetail());
            newSkillField.setItems(items);
            newSkillField.setValue(event.getDetail());
        });


        // Set button behavior:
        addSkillButton.addClickListener(clickEvent -> {
            Optional<String> skillValue = Optional.of(newSkillField.getValue().toString());
            if (skillValue.isPresent()) {
                addSkill(newSkillField.getValue().toString());
            }
        });
    }


    /** Component logic **/
    public void removeSkill(String skillName) {
        if (skills.containsKey(skillName)) {
            skillsLayout.remove(skills.get(skillName));
            skills.remove(skillName);
            skillAmountLabel.setText("(" + skills.size() + "/20)");
            skillAmountLabel.removeClassName("skills-full");
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
            skillAmountLabel.setText("(" + skills.size() + "/20)");
            if (skills.size() == 20) {
                skillAmountLabel.addClassName("skills-full");
            }
        }
    }

    public Set<String> getSkillNames() {
        return skills.keySet();
    }







}
