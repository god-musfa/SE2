package org.hbrs.se2.project.softwaree.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.hbrs.se2.project.softwaree.components.SoftwareeJobCard;
import org.hbrs.se2.project.softwaree.control.JobDetailControl;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.util.Globals;
import org.hbrs.se2.project.softwaree.util.Softwareeicons;

import java.util.Set;


@Route(value = "job", layout = NavBar.class)
@PageTitle("Stellenangebot Details")
@CssImport("./styles/views/job/job.css")
@JsModule("./icons/softwareeicons.js")
public class JobDetailView extends Div implements HasUrlParameter<String> {

    // Controller and currentJobID temp variable:
    JobDetailControl jobDetailControl;
    int currentJobID;           // Used to temporary save current job ID (fetched from URL query)


    // Main view components arrangement:
    FormLayout pageContainer = new FormLayout();
    VerticalLayout jobContainer = new VerticalLayout();
    VerticalLayout cardContainer = new VerticalLayout();


    // Base job information:
    H1 jobTitle = new H1("Stellentitel");
    Label jobDescription = new Label("Keine Beschreibung vorhanden");


    // Skills and sharing options:
    HorizontalLayout skillContainer = new HorizontalLayout();
    HorizontalLayout shareContainer = new HorizontalLayout();


    // Social media links:
    Button facebookButton = new Button(VaadinIcon.FACEBOOK.create());
    Button twitterButton = new Button(VaadinIcon.TWITTER.create());
    Button instagramButton = new Button(Softwareeicons.INSTAGRAM.create());
    Button linkedinButton = new Button(Softwareeicons.LINKEDIN.create());


    // Subtopics for this job:
    VerticalLayout qualificationsLayout = new VerticalLayout();
    VerticalLayout benefitsLayout = new VerticalLayout();
    HorizontalLayout skillsLayout = new HorizontalLayout();


    // Jobcard:
    SoftwareeJobCard jobcard = new SoftwareeJobCard();

    @Override
    public void setParameter(BeforeEvent event,  @OptionalParameter String parameter) {
        /*
        * This view uses the following baseline for url formatting:
        * URL format:     http://localhost:8080/job/{jobID}
         */

        // Try to fetch job id from url query:
        try {
            currentJobID = Integer.parseInt(parameter);
        } catch (NumberFormatException ex) {
            currentJobID = -1;
        }

        // Validate job id from query:
        if (jobDetailControl.validateJobID(currentJobID)) {
            //increment views for this job
            jobDetailControl.incrementViews(currentJobID, 1);

            build();
        } else {
            // Route to main page if invalid job id has been supplied:
            UI.getCurrent().navigate("jobs");
        }
    }

    public JobDetailView(JobDetailControl controller) {
        this.jobDetailControl = controller;
    }


    public void build() {



        // Get data from DB and fill out information:
        fillJobData();

        // Styling (CSS):
        skillContainer.addClassName("skillcontainer");
        shareContainer.addClassName("sharecontainer");

        // Responsive layout specification:
        pageContainer.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep(Globals.ScreenSizes.WORKSTATION, 2 )
        );

        // Build up Job container:
        jobContainer.add(jobTitle);
        jobContainer.add(jobDescription);

        // Add subtopics for job overview:
        jobContainer.add(new H2("Qualifikationen"));
        jobContainer.add(qualificationsLayout);
        jobContainer.add(new H2("Wir bieten"));
        jobContainer.add(benefitsLayout);

        // Add skills:
        skillContainer.add(new Label("Benötigte Fähigkeiten"));
        skillContainer.add(skillsLayout);

        // Add sharing options:
        shareContainer.add(new Label("Teilen"));
        shareContainer.add(twitterButton);
        shareContainer.add(facebookButton);
        shareContainer.add(instagramButton);
        shareContainer.add(linkedinButton);
        shareContainer.setAlignItems(FlexComponent.Alignment.CENTER);

        // Build up containers:
        jobContainer.add(skillContainer);
        jobContainer.add(shareContainer);
        cardContainer.add(jobcard);
        pageContainer.add(jobContainer, 1);
        pageContainer.add(cardContainer, 1);


        add(pageContainer);
    }



    void fillJobData() {

        // Read into new JobDTO:
        JobDTO thisJob = jobDetailControl.getJob(currentJobID);
        Set<Benefit> benefits = thisJob.getBenefits();
        Set<Requirement> requirements = thisJob.getRequirements();
        Set<Skill> skills = thisJob.getSkills();

        // Fill UI with Job data:
        jobTitle.setText(thisJob.getTitle());
        jobDescription.setText(thisJob.getDescription());

        // Job requirements list:
        for (Requirement req : requirements) {
            qualificationsLayout.add(
                    new Label("- " + req.getDescription())
            );
        }

        // Job benefits list:
        for (Benefit benefit : benefits) {
            benefitsLayout.add(
                    new Label("- " + benefit.getDescription())
            );
        }

        // Job skills list:
        for (Skill skill : skills) {
            Span skill1 = new Span(skill.getDescription());
            skill1.getElement().getThemeList().add("badge");
            skillsLayout.add(
                    skill1
            );
        }

        // Fill jobcard information:
        jobcard.setTitle(thisJob.getTitle());
        jobcard.setCompany(thisJob.getCompany().getName());
        jobcard.setViews(jobDetailControl.validateJobViews(thisJob.getViews()));
        jobcard.setCreationDate(thisJob.getCreationDateAsString());
        jobcard.setDeadline(thisJob.getDeadlineAsString());
        jobcard.setLocationInfo(thisJob.getLocation());
        jobcard.setButtonEvents(thisJob.getCompany().getId(), jobDetailControl);
        jobcard.setIcon("https://cdn-icons-png.flaticon.com/512/149/149071.png");
        // ToDo: jobcard.setCareerInfo(thisJob.getCareerInfo());
        // ToDo: jobcard.setQualification(thisJob.getQualification());
        // ToDo: jobcard.setSalary(thisJob.getSalary());


    }
}
