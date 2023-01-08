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
import org.hbrs.se2.project.softwaree.control.DataExtractionControl;
import org.hbrs.se2.project.softwaree.control.ViewProfileControl;
import org.hbrs.se2.project.softwaree.control.factories.CompanyFactory;
import org.hbrs.se2.project.softwaree.control.factories.UserFactory;
import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.User;
import org.hbrs.se2.project.softwaree.util.Globals;

@Route(value = Globals.Pages.VIEW_PROFILE, layout = NavBar.class)
@CssImport("./styles/views/profile/profile-view.css")
public class ProfileView extends VerticalLayout implements HasDynamicTitle, HasUrlParameter<String> {

    //Logic elements
    private ViewProfileControl viewProfileControl;
    private DataExtractionControl dataExtractionControl;
    private int currentProfileID = -1;
    private boolean ratingEnabled = false;

    private UserDTO profileUser;

    private String profileTitle = "";


    /** Components for page **/

    private Label skillsHeader = new Label("Fähigkeiten");

    private SoftwareeProfileCard profileCard;

    private SkillsComponent profileSkills;



    /** Logic **/

    public ProfileView(ViewProfileControl controller,DataExtractionControl dataExtractionControl) {
        this.viewProfileControl = controller;
        this.dataExtractionControl = dataExtractionControl;
    }


    /** Styling **/

    private void build_student() {
        // Styling:
        skillsHeader.addClassName("skills_header");
        setAlignItems(Alignment.CENTER);
        setPadding(true);

        // Fetch student for profile:
        StudentDTO currentStudent = viewProfileControl.getStudentFromUser(profileUser);

        // Set page info to current profile name
        profileTitle = String.format(
                "%s %s",
                currentStudent.getFirstName(),
                currentStudent.getLastName()
        );

        // Get the profile user information:
        AddressDTO userAddress = viewProfileControl.getAddressOfUser(profileUser);

        /* Determine whether the rating should be enabled or disabled:
        *
        *   enabled: company visits student's profile
        *   disabled: student visit's student profile
        *   (rating is disabled by default)
         */
        UserDTO currentVisitorUserDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);

        if (viewProfileControl.getUserType(currentVisitorUserDTO) == ViewProfileControl.UserType.COMPANY) {
            // Enable rating on ProfileCard:
            ratingEnabled = true;
        }

        // Build up new profile card component with profile information:

        profileCard = new SoftwareeProfileCard(
                profileTitle,
                profileUser.getProfilePic(),
                String.format("%s %s", userAddress.getPostalCode(), userAddress.getCity()),
                currentStudent.getFirstName(),
                currentStudent.getLastName(),
                (currentStudent.getBirthday() == null)?("-"):(currentStudent.getBirthday().toString()),
                currentStudent.getSubject(),
                currentStudent.getDegree(),
                (currentStudent.getSemester() == null)?("-"):(currentStudent.getSemester().toString()),
                currentStudent.getUniversity(),
                viewProfileControl,
                currentProfileID,
                currentVisitorUserDTO.getId(),
                ratingEnabled,
                dataExtractionControl
        );

        // Get profile user's skills:
        profileSkills = new SkillsComponent(null, SkillsComponent.ComponentType.SKILL_VIEWER);
        currentStudent.getSkills().stream()
                .forEach(skill -> {profileSkills.addSkill(skill.getDescription());});

        // Add all components to page:
        add(profileCard);
        add(skillsHeader);
        add(profileSkills);
    }



    private void build_company() {
        // Styling:
        setAlignItems(Alignment.CENTER);
        setPadding(true);

        // Fetch company for profile:
        CompanyDTO currentCompany = viewProfileControl.getCompanyFromUser(profileUser);


        // Set page info to current profile name
        profileTitle = String.format(
                "%s",
                currentCompany.getName()
        );

        // Get the profile user information:
        AddressDTO userAddress = viewProfileControl.getAddressOfUser(profileUser);

        /* Determine whether the rating should be enabled or disabled:
         *
         *   enabled: company visits student's profile / student visits company's profile
         *   disabled: student visit's student profile
         *   (rating is disabled by default)
         */
        UserDTO currentVisitorUserDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);

        // Already a company profile, just check the visitor type:
        if (viewProfileControl.getUserType(currentVisitorUserDTO) == ViewProfileControl.UserType.STUDENT) {
            // Enable rating on ProfileCard:
            ratingEnabled = true;
        }

        // Build up new profile card component with profile information:
        profileCard = new SoftwareeProfileCard(
                profileTitle,
                currentCompany.getName(),
                profileUser.getProfilePic(),
                String.format("%s %s", userAddress.getPostalCode(), userAddress.getCity()),
                currentCompany.getPhoneNumber(),
                currentCompany.getContactPerson(),
                currentCompany.getWebsite(),
                currentCompany.getField(),
                currentCompany.getSize(),
                viewProfileControl,
                currentVisitorUserDTO.getId(),
                currentProfileID,
                ratingEnabled
        );

        // Add all components to page:
        add(profileCard);
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

            // Differentiate between profile user type:
            switch (viewProfileControl.getUserType(profileUser)) {
                case STUDENT:
                    build_student();
                    break;
                case COMPANY:
                    build_company();
                    break;
                default:
                    UI.getCurrent().navigate("");
                    break;
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
