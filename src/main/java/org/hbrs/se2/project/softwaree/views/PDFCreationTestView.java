package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.hbrs.se2.project.softwaree.control.DataExtractionControl;

import java.io.File;
import java.io.IOException;

@Route(value = "pdf", layout = NavBar.class)
public class PDFCreationTestView extends HorizontalLayout {
    private DataExtractionControl dc;

    public PDFCreationTestView(DataExtractionControl dc) throws IOException {
        this.dc = dc;
        addLinkToFile(dc.createPDFFromStudent(236));

    }
    private void addLinkToFile(File file) {
        StreamResource streamResource = new StreamResource(file.getName(), () -> dc.getStream(file));
        Anchor link = new Anchor(streamResource, String.format("%s (%d KB)", file.getName(),
                (int) file.length() / 1024));
        link.getElement().setAttribute("download", true);
        link.removeAll();
        link.add(new Button("Pdf erstellen"));

        add(link);
    }
}
