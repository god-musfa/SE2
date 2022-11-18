package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.hbrs.se2.project.softwaree.entities.Job;
@Route(value = "job", layout = NavBar.class)
@PageTitle("Stellenangebot Detail View")
public class JobDetailView extends Div implements HasUrlParameter<String> {


    @Override
    public void setParameter(BeforeEvent event,  @OptionalParameter String parameter) {

            if (parameter == null) {
                setText("Kein Stellenangebot ausgewählt.");
            } else {
                setText(String.format("Ausgewähltes Angebot %s.", parameter));
            }
    }
}
