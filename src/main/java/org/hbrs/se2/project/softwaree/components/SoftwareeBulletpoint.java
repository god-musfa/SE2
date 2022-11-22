package org.hbrs.se2.project.softwaree.components;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**@author dheil2s
 *
 * This component is part of the SoftwareeJobCard component.
 * Bulletpoint component which has an icon on the left-hand side, followed by a key-value pair.
 * This component is styled by the SoftwareeJobCard.css file.
 */
@CssImport("./styles/components/SoftwareeJobCard.css")
public class SoftwareeBulletpoint extends HorizontalLayout {
    Icon icon = new Icon(VaadinIcon.CIRCLE);
    Label key = new Label("Veröffentlicht");
    Label value = new Label("-");


    public SoftwareeBulletpoint(VaadinIcon icon, String key, String value) {

        // Set values for the properties:
        this.icon = icon.create();
        this.key.setText(key);
        this.value.setText(value);

        // Set all CSS-relevant attributes:
        this.key.addClassName("jobcard-key");
        this.value.addClassName("jobcard-value");
        this.icon.addClassName("jobcard-icon");
        addClassName("jobcard-bulletpoint");
        setSizeFull();

        // Add all subcomponents to this bulletpoint:
        add(this.icon);
        add(this.key);
        add(this.value);
    }


    // Setters:

    public void setIcon(VaadinIcon icon) {
        this.icon = new Icon(icon);
    }

    public void setKey(String key) {
        this.key.setText(key);
    }

    public void setValue(String value) {
        this.value.setText(value);
    }
}
