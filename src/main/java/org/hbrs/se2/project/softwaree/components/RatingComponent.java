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
    private int maxScore = 5;
    private int rating = 0;

    private RatingFeedbackControl feedbackController;

    private HorizontalLayout starsLayout = new HorizontalLayout();
    private VerticalLayout mainLayout = new VerticalLayout();

    Icon[] starIcons = new Icon[10];
    private Label ratingLabel = new Label("0/5");

    // References to DB:
    private int student_id;
    private int company_id;


    public RatingComponent(int ratingScore, int maxScore, RatingFeedbackControl ratingFeedbackControl, int student_id, int company_id) {
        // Setup logics:
        setFeedbackController(ratingFeedbackControl);
        this.student_id = student_id;
        this.company_id = company_id;

        if (maxScore > 0) {
            this.maxScore = maxScore;
        } else {
            this.maxScore = 5;
        }

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

    public RatingComponent(int ratingScore, RatingFeedbackControl ratingFeedbackControl, int student_id, int company_id) {
        this(ratingScore, 5, ratingFeedbackControl, student_id, company_id);
    }


    public void setRating(int ratingScore) {
        if (ratingScore == 0) {
            ratingLabel.setText("noch nicht bewertet");
        } else {
            this.rating = ratingScore;
        }
        feedbackController.setRating(ratingScore, this.student_id, this.company_id);
        renderStars(ratingScore);
    }

    public int getRating() {
        return this.rating;
    }

    public void setFeedbackController(RatingFeedbackControl controller) {
        feedbackController = controller;
    }

    // Logics:

    private void renderStars(int ratingScore) {
        starsLayout.removeAll();

        for (int i=0; i<maxScore; i++) {
            starIcons[i] = new Icon(VaadinIcon.STAR);
            starIcons[i].removeClassName("rating-checked");
            starIcons[i].removeClassName("rating-unchecked");

            final int currentRating = i;
            starIcons[i].addClickListener(
                    clickEvent -> {setRating(currentRating+1);}
            );

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
