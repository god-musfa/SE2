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
import org.hbrs.se2.project.softwaree.dtos.LabelDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CssImport("./styles/components/SkillsComponent.css")
public class LabelsComponent<T extends LabelDTO> extends VerticalLayout {

    /** Main Components **/
    FlexLayout layout = new FlexLayout();
    Button addButton = new Button("Hinzufügen");
    ComboBox<String> newField = new ComboBox();
    UnorderedList labelsLayout = new UnorderedList();
    Label infoLabel;
    Label skillAmountLabel = new Label("(0/20)");
    private HashMap<String, ListItem> labels = new HashMap<>();

    /** Component types for this component **/
    public enum ComponentType {
        EDITOR, EDITOR_NO_CUSTOM, VIEWER;
    }
    private boolean readOnly = false;


    /** Component UI **/
    public LabelsComponent(List<T> autoCompleteSet, String type, String placeholder) {
        infoLabel = new Label(placeholder + "en");
        // For outer component styling:
        addClassName("skill-component");

        // Styling inner components:
        newField.setPlaceholder("Neue " + placeholder);
        newField.setAllowCustomValue(true);               // Accept custom skills.

        labelsLayout.addClassName(type + "-layout");
        addButton.addClassName("add-button");
        infoLabel.addClassName("info-label");
        skillAmountLabel.addClassName("amount-label");

        // Header part:
        layout.add(newField, addButton, skillAmountLabel);
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        layout.setAlignContent(FlexLayout.ContentAlignment.CENTER);

        // Add both parts to main component:
        add(infoLabel, layout, labelsLayout);

        // Set autocomplete set to combobox autocomplete:
        this.newField.setItems(
                autoCompleteSet.stream()
                        .map(T::getDescription)
                        .collect(Collectors.toList())
        );

        // Capabitlity to set own values:
        newField.addCustomValueSetListener(event -> {
            List<String> items = newField.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());
            items.add(event.getDetail());
            newField.setItems(items);
            newField.setValue(event.getDetail());
        });


        // Set button behavior:
        addButton.addClickListener(clickEvent -> {
            Optional<String> skillValue = Optional.of(newField.getValue().toString());
            if (skillValue.isPresent()) {
                addLabel(newField.getValue().toString());
            }
        });
    }


    /** Component logic **/
    public void removeLabel(String labelName) {
        if (labels.containsKey(labelName) && !readOnly) {
            labelsLayout.remove(labels.get(labelName));
            labels.remove(labelName);
            skillAmountLabel.setText("(" + labels.size() + "/20)");
            skillAmountLabel.removeClassName("skills-full");
        }
    }

    public void addLabel(String labelName) {
        if (!labels.containsKey(labelName) && labels.size() < 20) {
            ListItem newItem = new ListItem(labelName);
            newItem.addClassName("skill-pill");
            newItem.addClickListener(clickEvent -> removeLabel(labelName));
            labelsLayout.add(newItem);
            labels.put(labelName, newItem);
            labelsLayout.add(newItem);
            skillAmountLabel.setText("(" + labels.size() + "/20)");
            if (labels.size() == 20) {
                skillAmountLabel.addClassName("skills-full");
            }
        }
    }

    public Set<String> getNames() {
        return labels.keySet();
    }


    /** Component state can be switched between only viewing,
     * editing and editing with restriction to predefined values **/
    public void setSkillComponentType(ComponentType componentType) {
        switch (componentType) {

            case EDITOR: {
                removeAll();
                add(infoLabel, layout, labelsLayout);
                newField.setAllowCustomValue(true);
                readOnly = false;
                break;
            }

            case EDITOR_NO_CUSTOM: {
                removeAll();
                add(infoLabel, layout, labelsLayout);
                newField.setAllowCustomValue(false);
                readOnly = false;
                break;
            }

            case VIEWER: {
                removeAll();
                add(labelsLayout);
                readOnly = true;
                break;
            }
        }
    }
}
