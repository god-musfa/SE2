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
import org.hbrs.se2.project.softwaree.control.RatingFeedbackControl;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.entities.User;
import org.hbrs.se2.project.softwaree.util.Globals;


/**@author dheil2s
 *
 * SoftwareeJobCard component displays job details in a card using several attributes.
 * Each attribute is represented in a SoftwareeBulletpoint component.
 * Styling is done by SoftwareeJobCard.css.
 */
@CssImport("./styles/components/SoftwareeProfileCard.css")
public class SoftwareeProfileCard extends Div {

    // Used inner layouts:
    private VerticalLayout innerLayout = new VerticalLayout();
    private HorizontalLayout titleLayout = new HorizontalLayout();
    private VerticalLayout labelLayout = new VerticalLayout();
    private HorizontalLayout locationLayout = new HorizontalLayout();


    // Properties for title layout:
    private Image profileImage = new Image();
    private Header profileTitle = new Header();

    private Label locationLabel = new Label();

    private Icon locationIcon = VaadinIcon.MAP_MARKER.create();


    // Properties list (using bulletpoint components):
    private SoftwareeBulletpoint profileFirstName = new SoftwareeBulletpoint(VaadinIcon.ANGLE_RIGHT, "Vorname", "");
    private SoftwareeBulletpoint profileLastName = new SoftwareeBulletpoint(VaadinIcon.ANGLE_RIGHT, "Nachname", "");
    private SoftwareeBulletpoint profileBirthday = new SoftwareeBulletpoint(VaadinIcon.ASTERISK, "Geburtsdatum", "");
    private SoftwareeBulletpoint profileStudySubject = new SoftwareeBulletpoint(VaadinIcon.BOOK, "Studiengang", "");
    private SoftwareeBulletpoint profileStudyDegree = new SoftwareeBulletpoint(VaadinIcon.ACADEMY_CAP, "Akademischer Grad", "");
    private SoftwareeBulletpoint profileStudyUniversity = new SoftwareeBulletpoint(VaadinIcon.BUILDING, "Universität", "");
    private SoftwareeBulletpoint profileStudySemester = new SoftwareeBulletpoint(VaadinIcon.PROGRESSBAR, "Semester", "");



    // Properties list (using bulletpoint components) - COMPANY SPECIFIC:
    private SoftwareeBulletpoint profileCompanyName = new SoftwareeBulletpoint(VaadinIcon.BRIEFCASE, "Firma", "");
    private SoftwareeBulletpoint profileCompanyWebsite = new SoftwareeBulletpoint(VaadinIcon.GLOBE, "Webseite", "");
    private SoftwareeBulletpoint profileCompanyPhone = new SoftwareeBulletpoint(VaadinIcon.PHONE, "Telefon", "");
    private SoftwareeBulletpoint profileCompanyContact = new SoftwareeBulletpoint(VaadinIcon.CHAT, "Ansprechpartner", "");
    private SoftwareeBulletpoint profileCompanyField = new SoftwareeBulletpoint(VaadinIcon.FACTORY, "Branche", "");
    private SoftwareeBulletpoint profileCompanySize = new SoftwareeBulletpoint(VaadinIcon.USERS, "Unternehmensgröße", "");


    private RatingComponent ratingComponent;

    private Button contactButton;
    private Button blacklistButton;


    public SoftwareeProfileCard(RatingFeedbackControl ratingFeedbackControl, int student_id, int company_id, boolean ratingEnabled, boolean isCompany) {

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
                    ratingFeedbackControl.getRating(student_id, company_id, false ),
                    ratingFeedbackControl,
                    student_id,
                    company_id,
                    isCompany
            );
            innerLayout.add(ratingComponent);
        }

        add(innerLayout);


        // Build contact button if (company -> user OR user -> company)
        if (((UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER)).getUserType().equals("company") && !isCompany) {
            addContactButton();
        }

        if (((UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER)).getUserType().equals("student") && isCompany) {
            addContactButton();
        }
    }


    // STUDENT CONSTRUCTOR:
    public SoftwareeProfileCard(String profileTitle, String profileImage, String location, String firstName, String lastName, String birthday,
                                String subject, String degree, String semester, String university, RatingFeedbackControl ratingFeedbackControl,
                                int student_id, int company_id, boolean ratingEnabled) {

        this(ratingFeedbackControl, student_id, company_id, ratingEnabled, false);
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
                                RatingFeedbackControl ratingFeedbackControl,
                                int student_id, int company_id, boolean ratingEnabled) {

        this(ratingFeedbackControl, student_id, company_id, ratingEnabled, true);

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
        add(contactButton);
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
