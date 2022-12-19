package org.hbrs.se2.project.softwaree.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.hbrs.se2.project.softwaree.components.*;
import org.hbrs.se2.project.softwaree.control.ViewProfileControl;
import org.hbrs.se2.project.softwaree.control.factories.CompanyFactory;
import org.hbrs.se2.project.softwaree.control.factories.UserFactory;
import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.User;
import org.hbrs.se2.project.softwaree.util.Globals;

@Route(value = Globals.Pages.VIEW_PROFILE, layout = NavBar.class)
@CssImport("./styles/views/profile/profile-view.css")
public class ProfileView extends VerticalLayout implements HasDynamicTitle, HasUrlParameter<String> {

    //Logic elements
    private ViewProfileControl viewProfileControl;
    private int currentProfileID = -1;
    private boolean ratingEnabled = false;

    private UserDTO profileUser;

    private String profileTitle = "";


    /** Components for page **/

    private Label skillsHeader = new Label("Fähigkeiten");

    private SoftwareeProfileCard profileCard;

    private SkillsComponent profileSkills;



    /** Logic **/

    public ProfileView(ViewProfileControl controller) {
        this.viewProfileControl = controller;
    }


    /** Styling **/

    private void build_student() {
        // Styling:
        skillsHeader.addClassName("skills_header");
        setAlignItems(Alignment.CENTER);
        setPadding(true);

        // Fetch student:
        StudentDTO currentStudent = viewProfileControl.getStudentFromUser(profileUser);

        // Build page infos
        profileTitle = String.format(
                "%s %s",
                currentStudent.getFirstName(),
                currentStudent.getLastName()
        );

        // Get the CompanyDTO from current visitor:
        AddressDTO userAddress = viewProfileControl.getAddressOfUser(profileUser);

        UserDTO currentVisitorUserDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);


        if (viewProfileControl.getUserType(currentVisitorUserDTO) == ViewProfileControl.UserType.COMPANY) {
            // Enable rating on ProfileCard:
            ratingEnabled = true;
        }

        profileCard = new SoftwareeProfileCard(
                profileTitle,
                profileUser.getProfilePic(),
                String.format("%s %s", userAddress.getPostalCode(), userAddress.getCity()),
                currentStudent.getFirstName(),
                currentStudent.getLastName(),
                currentStudent.getBirthday().toString(),
                currentStudent.getSubject(),
                currentStudent.getDegree(),
                currentStudent.getSemester().toString(),
                currentStudent.getUniversity(),
                viewProfileControl,
                currentProfileID,
                currentVisitorUserDTO.getId(),
                ratingEnabled
        );

        profileSkills = new SkillsComponent(null, SkillsComponent.ComponentType.SKILL_VIEWER);
        currentStudent.getSkills().stream()
                .forEach(skill -> {profileSkills.addSkill(skill.getDescription());});

        add(profileCard);
        add(skillsHeader);
        add(profileSkills);
    }





    // Header
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        /*
         * This view uses the following baseline for url formatting:
         * URL format:     http://localhost:8080/profile/{profileID}
         */

        // Try to fetch profile id from url query:
        try {
            currentProfileID = Integer.parseInt(parameter);
        } catch (NumberFormatException ex) {
            currentProfileID = -1;
        }

        // Validate profile id from query:
        if (viewProfileControl.validateProfileID(currentProfileID)) {
            profileUser = viewProfileControl.getUserByID(currentProfileID);

            switch (viewProfileControl.getUserType(profileUser)) {
                case STUDENT:
                    build_student();
                    break;
                default:
                    UI.getCurrent().navigate("");
            }

        } else {
            // Route to main page if invalid profile id has been supplied:
            UI.getCurrent().navigate("");
        }

    }


    @Override
    public String getPageTitle() {
        return profileTitle;
    }




}
