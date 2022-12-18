package org.hbrs.se2.project.softwaree.components;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@CssImport("./styles/components/SkillsComponent.css")
public class BlacklistComponent extends VerticalLayout {

    @Autowired
    CompanyRepository compRepo;

    /** Main Components **/
    FlexLayout layout = new FlexLayout();
    UnorderedList labelsLayout = new UnorderedList();
    Label infoLabel;
    private HashMap<CompanyDTO, ListItem> labels = new HashMap<>();

    /** Component UI **/
    public BlacklistComponent(List<CompanyDTO> companyDTO, String placeholder) {
        infoLabel = new Label(placeholder);
        // For outer component styling:
        addClassName("skill-component");

        // Styling inner components:

        labelsLayout.addClassName("layout");
        infoLabel.addClassName("info-label");

        // Header part:
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        layout.setAlignContent(FlexLayout.ContentAlignment.CENTER);

        // Add both parts to main component:
        add(infoLabel, layout, labelsLayout);

        for(CompanyDTO company : companyDTO) {
            addLabel(company);
        }
    }


    /** Component logic **/
    public void removeLabel(CompanyDTO company) {
        labelsLayout.remove(labels.get(company));
        labels.remove(company);
    }

    public void addLabel(CompanyDTO company) {
        ListItem newItem = new ListItem(company.getName());
        newItem.addClassName("skill-pill");
        newItem.addClickListener(clickEvent -> removeLabel(company));
        labelsLayout.add(newItem);
        labels.put(company, newItem);
        labelsLayout.add(newItem);
    }

    public Set<CompanyDTO> getBlacklistNames() {
        return labels.keySet();
    }
    public boolean isFilled() {
        return !labels.isEmpty();
    }
}
