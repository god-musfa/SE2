package org.hbrs.se2.project.softwaree.components;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import org.hbrs.se2.project.softwaree.control.DataExtractionControl;
import org.hbrs.se2.project.softwaree.control.RatingFeedbackControl;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.io.File;
import java.io.IOException;


/**@author dheil2s
 *
 * SoftwareeJobCard component displays job details in a card using several attributes.
 * Each attribute is represented in a SoftwareeBulletpoint component.
 * Styling is done by SoftwareeJobCard.css.
 */
@CssImport("./styles/components/SoftwareeProfileCard.css")
public class SoftwareeProfileCard extends Div {

    // Used inner layouts:
    private final VerticalLayout innerLayout = new VerticalLayout();
    private final HorizontalLayout titleLayout = new HorizontalLayout();
    private final VerticalLayout labelLayout = new VerticalLayout();
    private final HorizontalLayout locationLayout = new HorizontalLayout();
    private final HorizontalLayout buttonLayout = new HorizontalLayout();

    // Properties for title layout:
    private final Image profileImage = new Image();
    private final Header profileTitle = new Header();

    private final Label locationLabel = new Label();

    private final Icon locationIcon = VaadinIcon.MAP_MARKER.create();


    // Properties list (using bulletpoint components):
    private final SoftwareeBulletpoint profileFirstName = new SoftwareeBulletpoint(VaadinIcon.ANGLE_RIGHT, "Vorname", "");
    private final SoftwareeBulletpoint profileLastName = new SoftwareeBulletpoint(VaadinIcon.ANGLE_RIGHT, "Nachname", "");
    private final SoftwareeBulletpoint profileBirthday = new SoftwareeBulletpoint(VaadinIcon.ASTERISK, "Geburtsdatum", "");
    private final SoftwareeBulletpoint profileStudySubject = new SoftwareeBulletpoint(VaadinIcon.BOOK, "Studiengang", "");
    private final SoftwareeBulletpoint profileStudyDegree = new SoftwareeBulletpoint(VaadinIcon.ACADEMY_CAP, "Akademischer Grad", "");
    private final SoftwareeBulletpoint profileStudyUniversity = new SoftwareeBulletpoint(VaadinIcon.BUILDING, "Universität", "");
    private final SoftwareeBulletpoint profileStudySemester = new SoftwareeBulletpoint(VaadinIcon.PROGRESSBAR, "Semester", "");



    // Properties list (using bulletpoint components) - COMPANY SPECIFIC:
    private final SoftwareeBulletpoint profileCompanyName = new SoftwareeBulletpoint(VaadinIcon.BRIEFCASE, "Firma", "");
    private final SoftwareeBulletpoint profileCompanyWebsite = new SoftwareeBulletpoint(VaadinIcon.GLOBE, "Webseite", "");
    private final SoftwareeBulletpoint profileCompanyPhone = new SoftwareeBulletpoint(VaadinIcon.PHONE, "Telefon", "");
    private final SoftwareeBulletpoint profileCompanyContact = new SoftwareeBulletpoint(VaadinIcon.CHAT, "Ansprechpartner", "");
    private final SoftwareeBulletpoint profileCompanyField = new SoftwareeBulletpoint(VaadinIcon.FACTORY, "Branche", "");
    private final SoftwareeBulletpoint profileCompanySize = new SoftwareeBulletpoint(VaadinIcon.USERS, "Unternehmensgröße", "");


    private RatingComponent ratingComponent;

    private Button contactButton;

    private DataExtractionControl dataExtractionControl;
    private int studentId;
    public SoftwareeProfileCard(RatingFeedbackControl ratingFeedbackControl,
                                DataExtractionControl dataExtractionControl,
                                int studentId, int company_id, boolean ratingEnabled, boolean isCompany) {
        this.dataExtractionControl = dataExtractionControl;
        this.studentId = studentId;
        // Styling settings (CSS):
        addClassName("profilecard");
        this.setMaxWidth("25rem");
        this.setMinWidth("20rem");

        // Title section (CSS):
        profileImage.setHeight("5em");
        profileImage.setWidth("5em");
        profileTitle.addClassName("profiletitle");
        locationLabel.addClassName("jobcard-views-label");
        locationIcon.addClassName("jobcard-views-icon");

        // Build up layouts:
        locationLayout.add(locationIcon);
        locationLayout.add(locationLabel);
        locationLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        locationLayout.setSpacing(false);

        labelLayout.add(profileTitle);
        labelLayout.add(locationLayout);
        labelLayout.setSpacing(false);

        titleLayout.add(profileImage);
        titleLayout.add(labelLayout);
        titleLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        innerLayout.add(titleLayout);

        // Initialize bulletpoints:

        if (isCompany) {
            innerLayout.add(profileCompanyName);
            innerLayout.add(profileCompanyWebsite);
            innerLayout.add(profileCompanyPhone);
            innerLayout.add(profileCompanyContact);
            innerLayout.add(profileCompanyField);
            innerLayout.add(profileCompanySize);
        } else {
            innerLayout.add(profileFirstName);
            innerLayout.add(profileLastName);
            innerLayout.add(profileBirthday);
            innerLayout.add(profileStudySubject);
            innerLayout.add(profileStudyDegree);
            innerLayout.add(profileStudySemester);
            innerLayout.add(profileStudyUniversity);
        }

        // If enabled, add rating component:
        if (ratingEnabled) {
            ratingComponent = new RatingComponent(
                    ratingFeedbackControl.getRating(studentId, company_id, isCompany),
                    ratingFeedbackControl,
                    studentId,
                    company_id,
                    isCompany
            );
            innerLayout.add(ratingComponent);
        }

        add(innerLayout);


        // Build contact button if (company -> user OR user -> company)
        if (((UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER)).getUserType().equals("company") && !isCompany) {
            addContactButton();
            addPdfButton();
            buttonLayout.setPadding(true);
            add(buttonLayout);
        }
    }


    // STUDENT CONSTRUCTOR:
    public SoftwareeProfileCard(String profileTitle, String profileImage, String location, String firstName, String lastName, String birthday,
                                String subject, String degree, String semester, String university, RatingFeedbackControl ratingFeedbackControl,
                                int studentId, int company_id, boolean ratingEnabled, DataExtractionControl dataExtractionControl) {

        this(ratingFeedbackControl,dataExtractionControl, studentId, company_id, ratingEnabled, false);
        this.setLocation((location == null)?("-"):location);
        this.setTitle((profileTitle == null)?("-"):profileTitle);
        this.setFirstname((firstName == null)?("-"):firstName);
        this.setLastName((lastName == null)?("-"):lastName);
        this.setBirthday((birthday == null)?("-"):birthday);
        this.setStudySubject((subject == null)?("-"):subject);
        this.setStudyDegree((degree == null)?("-"):degree);
        this.setStudySemester((semester == null)?("-"):semester);
        this.setStudyUniversity((university == null)?("-"):university);
        this.setProfileImage((profileImage == null)?(Globals.DEFAULT_PROFILE_PICTURE):profileImage);
    }

    // COMPANY CONSTRUCTOR:
    public SoftwareeProfileCard(String profileTitle, String companyName, String profileImage, String location, String phoneNumber,
                                String contactPerson, String website, String field, String size,
                                RatingFeedbackControl ratingFeedbackControl, DataExtractionControl dataExtractionControl,
                                int studentId, int company_id, boolean ratingEnabled) {

        this(ratingFeedbackControl,dataExtractionControl, studentId, company_id, ratingEnabled, true);

        this.setLocation((location == null)?("-"):location);
        this.setTitle((profileTitle == null)?("-"):profileTitle);
        this.setCompanyName(companyName);
        this.setCompanyWebsite(website);
        this.setCompanyPhone(phoneNumber);
        this.setCompanyContact(contactPerson);
        this.setCompanyField(field);
        this.setCompanySize(size);
        this.setProfileImage((profileImage == null)?(Globals.DEFAULT_PROFILE_PICTURE):profileImage);
    }

    private void addContactButton() {
        contactButton = new Button("Kontaktieren", VaadinIcon.COMMENTS.create());
        contactButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_PRIMARY);
        contactButton.addClickListener(clickEvent -> {
            // TODO!
        });
        buttonLayout.add(contactButton);
    }
    private void addPdfButton() {

            File file;
            try {
                file = dataExtractionControl.createPDFFromStudent(studentId);
                buttonLayout.add(addLinkToFile(file));
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }



    }

    public void setTitle(String title) {
        profileTitle.setText(title);
    }
    public void setProfileImage(String imageLocation){
        profileImage.setSrc(imageLocation);
        profileImage.addClassName("profilecard-image");
    }

    public void setLocation(String location) {
        locationLabel.setText(location);
    }

    public void setFirstname(String firstName) {
        profileFirstName.setValue(firstName);
    }

    public void setLastName(String lastName) {
        profileLastName.setValue(lastName);
    }

    public void setBirthday(String birthday) {
        profileBirthday.setValue(birthday);
    }

    public void setStudySubject(String subject) {
        profileStudySubject.setValue(subject);
    }

    public void setStudyDegree(String degree) {
        profileStudyDegree.setValue(degree);
    }

    public void setStudySemester(String semester) {
        profileStudySemester.setValue(semester);
    }

    public void setStudyUniversity(String university) {
        profileStudyUniversity.setValue(university);
    }

    private Component addLinkToFile(File file) {
        StreamResource streamResource = new StreamResource(file.getName(), () -> dataExtractionControl.getStream(file));
        Anchor link = new Anchor(streamResource, String.format("%s (%d KB)", file.getName(),
                (int) file.length() / 1024));
        link.getElement().setAttribute("download", true);
        link.removeAll();
        Button button = new Button("Pdf erstellen");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_PRIMARY);
        link.add(button);

        return link;
    }
    public void setCompanyName(String name) {
        profileCompanyName.setValue(name);
    }

    public void setCompanyWebsite(String website) {
        profileCompanyWebsite.setValue(website);
    }

    public void setCompanyPhone(String phone) {
        profileCompanyPhone.setValue(phone);
    }

    public void setCompanyContact(String contactName) {
        profileCompanyContact.setValue(contactName);
    }

    public void setCompanyField(String field) {
        profileCompanyField.setValue(field);
    }

    public void setCompanySize(String size) {
        profileCompanySize.setValue(size);
    }

}
