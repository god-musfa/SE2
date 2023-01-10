package org.hbrs.se2.project.softwaree.components;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.hbrs.se2.project.softwaree.control.JobDetailControl;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;


/**@author dheil2s
 *
 * SoftwareeJobCard component displays job details in a card using several attributes.
 * Each attribute is represented in a SoftwareeBulletpoint component.
 * Styling is done by SoftwareeJobCard.css.
 */
@CssImport("./styles/components/SoftwareeJobCard.css")
public class SoftwareeJobCard extends Div implements SoftwareeJobCardIf{

    // Used inner layouts:
    private VerticalLayout innerLayout = new VerticalLayout();
    private HorizontalLayout titleLayout = new HorizontalLayout();
    private VerticalLayout labelLayout = new VerticalLayout();
    private HorizontalLayout viewsLayout = new HorizontalLayout();


    // Properties for title layout:
    private Image companyIcon = new Image();
    private Header jobTitle = new Header();
    private Label companyLabel = new Label("Company");
    private Label viewsLabel = new Label("0");

    private Icon viewsIcon = VaadinIcon.EYE.create();


    // Properties list (using bulletpoint components):
    private SoftwareeBulletpoint createBullet = new SoftwareeBulletpoint(
            VaadinIcon.CALENDAR, "Veröffentlicht", "-");
    private SoftwareeBulletpoint deadlineBullet = new SoftwareeBulletpoint(
            VaadinIcon.CLOCK, "Bewerbungsfrist", "-");
    private SoftwareeBulletpoint locationBullet = new SoftwareeBulletpoint(
            VaadinIcon.LOCATION_ARROW, "Standort", "-");
    private SoftwareeBulletpoint careerBullet = new SoftwareeBulletpoint(
            VaadinIcon.CHART_LINE, "Karrierestufe", "-");
    private SoftwareeBulletpoint experienceBullet = new SoftwareeBulletpoint(
            VaadinIcon.LIGHTBULB, "Berufserfahrung", "-");
    private SoftwareeBulletpoint qualificationBullet = new SoftwareeBulletpoint(
            VaadinIcon.SUITCASE, "Qualifikation", "-");
    private SoftwareeBulletpoint salaryBullet = new SoftwareeBulletpoint(
            VaadinIcon.MONEY, "Gehalt", "- €");

    private Button applyJobButton;
    private Button blacklistButton;


    public SoftwareeJobCard() {

        // Styling settings (CSS):
        addClassName("jobcard");
        this.setMaxWidth("25rem");
        this.setMinWidth("20rem");

        // Title section (CSS):
        companyIcon.setHeight("5em");
        companyIcon.setWidth("5em");
        jobTitle.addClassName("jobtitle");
        companyLabel.addClassName("companytitle");
        viewsLabel.addClassName("jobcard-views-label");
        viewsIcon.addClassName("jobcard-views-icon");

        // Build up layouts:
        viewsLayout.add(viewsIcon);
        viewsLayout.add(viewsLabel);
        viewsLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        viewsLayout.setSpacing(false);

        labelLayout.add(jobTitle);
        labelLayout.add(companyLabel);
        labelLayout.add(viewsLayout);
        labelLayout.setSpacing(false);

        titleLayout.add(companyIcon);
        titleLayout.add(labelLayout);
        titleLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        innerLayout.add(titleLayout);

        // Initialize bulletpoints:
        innerLayout.add(createBullet);
        innerLayout.add(deadlineBullet);
        innerLayout.add(locationBullet);
        innerLayout.add(careerBullet);
        innerLayout.add(experienceBullet);
        innerLayout.add(qualificationBullet);
        innerLayout.add(salaryBullet);

        add(innerLayout);

        // Build button:
        switch (((UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER)).getUserType()) {
            case "student":
                buildStudentButton();
                break;
            case "company":
                buildCompanyButton();
                break;
        }
    }

    private void buildStudentButton() {
        applyJobButton = setButtonAttributes("Jetzt bewerben", new Icon(VaadinIcon.PAPERPLANE), false);
        add(applyJobButton);
        blacklistButton = setButtonAttributes("Firma blockieren", new Icon(VaadinIcon.BAN), true);
        add(blacklistButton);
    }

    private void buildCompanyButton() {
        applyJobButton = setButtonAttributes("Jetzt bearbeiten", new Icon(VaadinIcon.PAPERPLANE), false);
        add(applyJobButton);
    }

    private Button setButtonAttributes(String buttonText, Icon icon, boolean block) {
        Button b = new Button(buttonText);
        b.setIcon(icon);
        b.setIconAfterText(true);
        if (block) {
            b.addThemeVariants(ButtonVariant.LUMO_ERROR);
        } else {
            b.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_PRIMARY);
        }
        return b;
    }

    public SoftwareeJobCard(String jobTitle, String companyName, String creationDate, String editDate,
                            String deadline, String locationInfo, String salary, int views) {

        this();
        this.setTitle(jobTitle);
        this.setCompany(companyName);
        this.setCreationDate(creationDate);
        this.setEditDate(editDate);
        this.setDeadline(deadline);
        this.setLocationInfo(locationInfo);
        this.setViews(views);
        this.setSalary(salary);

    }

    @Override
    public void setTitle(String title) {
        jobTitle.setText(title);
    }

    @Override
    public void setCompany(String company) {
        companyLabel.setText(company);
    }

    @Override
    public void setIcon(String iconPath) {
        companyIcon.setSrc(iconPath);
    }

    @Override
    public void setCreationDate(String creationDate) {
        createBullet.value.setText(creationDate);
    }

    @Override
    public void setEditDate(String editDate) {
        deadlineBullet.value.setText(editDate);
    }

    @Override
    public void setDeadline(String deadline) {
        deadlineBullet.value.setText(deadline);
    }

    @Override
    public void setLocationInfo(String locationInfo) {
        locationBullet.value.setText(locationInfo);
    }

    @Override
    public void setJobExperienceInfo(String experienceInfo) {
        experienceBullet.value.setText(experienceInfo);
    }

    @Override
    public void setQualification(String qualification) {
        qualificationBullet.value.setText(qualification);
    }

    @Override
    public void setViews(int views) {
        this.viewsLabel.setText(String.valueOf(
                (views>=0) ?views:0
        ));
    }

    @Override
    public void setCareerInfo(String info) {
        careerBullet.value.setText(info);
    }

    @Override
    public void setSalary(String salary) {
        salaryBullet.value.setText(salary);
    }

    @Override
    public void setButtonEvents(int id, JobDetailControl jc) {
        applyJobButton.addClickListener(e -> {
            if(((UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER)).getUserType().equals("company")) {
                UI.getCurrent().navigate("jobOffer");
            } else {
                UI.getCurrent().getSession().setAttribute( "companyID", id );
                UI.getCurrent().navigate("kontakt");
            }
        });
        if(blacklistButton != null) {
            blacklistButton.addClickListener(e -> {
                jc.addBlacklist(((UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER)).getId(), id);
                Notification.show("Unternehmen blockiert.").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("jobs");
            });
        }
    }
}
