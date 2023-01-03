package org.hbrs.se2.project.softwaree.components;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.hbrs.se2.project.softwaree.control.RatingFeedbackControl;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Student;

@CssImport("./styles/components/ratingComponent.css")
public class RatingComponent extends Div {

    // StudentRating value defaults:
    private int maxScore = 5;
    private int rating = 0;

    // FeedbackController (used for handling "rating" event):
    private RatingFeedbackControl feedbackController;

    // References to DB:
    private int student_id;
    private int company_id;

    private boolean studentRatesCompany;

    // UI components:
    private HorizontalLayout starsLayout = new HorizontalLayout();
    private VerticalLayout mainLayout = new VerticalLayout();

    private Icon[] starIcons = new Icon[10];
    private Label ratingLabel = new Label("0/5");


    public RatingComponent(int ratingScore, int maxScore, RatingFeedbackControl ratingFeedbackControl, int student_id, int company_id, boolean studentRatesCompany) {
        // Setup logics:
        this.feedbackController = ratingFeedbackControl;
        this.student_id = student_id;
        this.company_id = company_id;
        this.studentRatesCompany = studentRatesCompany;

        // Check maxScore value not being negative:
        if (maxScore > 0) {
            this.maxScore = maxScore;
        } else {
            this.maxScore = 5;
        }

        // Build stars:
        buildStars();

        // Check ratingScore being in interval [0,maxScore]:
        if (ratingScore > 0) {
            setRating(ratingScore % maxScore);
        } else {
            setRating(0);
        }

        // Visual:
        mainLayout.add(starsLayout);
        mainLayout.add(ratingLabel);

        addClassName("rating-container");
        add(mainLayout);
    }

    public RatingComponent(int ratingScore, RatingFeedbackControl ratingFeedbackControl, int student_id, int company_id, boolean studentRatesCompany) {
        this(ratingScore, 5, ratingFeedbackControl, student_id, company_id, studentRatesCompany);
    }


    public void setRating(int ratingScore) {
        // Check if rating is invalid (-> not rated):
        if (ratingScore == 0) {
            ratingLabel.setText("noch nicht bewertet");
        } else {
            this.rating = ratingScore;
        }

        // Use feedback controller to handle rating event:
        feedbackController.setRating(ratingScore, this.student_id, this.company_id, false);

        // Render new rating:
        renderStars(ratingScore);
    }

    public int getRating() {
        return this.rating;
    }


    /** Build stars UI and add event listener **/
    private void buildStars() {
        starsLayout.removeAll();

        for (int i=0; i<maxScore; i++) {
            starIcons[i] = new Icon(VaadinIcon.STAR);
            final int currentRating = i;
            starIcons[i].addClickListener(
                    clickEvent -> {
                        setRating(currentRating + 1);
                    }
            );
            starsLayout.add(starIcons[i]);
        }
    }


    private void renderStars(int ratingScore) {
        // Go through all star objects and assign the corresponding state (checked, unchecked):
        for (int i=0; i<maxScore; i++) {

            // Remove the beforehand states to reset the state to new value:
            starIcons[i].removeClassName("rating-checked");
            starIcons[i].removeClassName("rating-unchecked");

            // Set corresponding state:
            if (ratingScore > i) {
                starIcons[i].addClassName("rating-checked");
            } else {
                starIcons[i].addClassName("rating-unchecked");
            }
            starsLayout.add(starIcons[i]);
        }

        ratingLabel.setText(String.format("%d/%d", ratingScore, maxScore));
    }

}
