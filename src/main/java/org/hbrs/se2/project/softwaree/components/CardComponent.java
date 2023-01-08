package org.hbrs.se2.project.softwaree.components;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.hbrs.se2.project.softwaree.dtos.ApplicationDTO;

public class CardComponent extends HorizontalLayout{
    ApplicationDTO application;
    public CardComponent(ApplicationDTO application,String usertype) {
        this.application = application;
        // [horizontal ] Card Wrapper
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");
        card.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);

        // Company Logo
        Image image = new Image();
        image.setSrc("icons/icon.png");
        image.setHeight("50px");
        image.getStyle().set("margin","30px 0px 12px 8px");

        // [vertical] Content Wrapper Component
        VerticalLayout contentWrapper = new VerticalLayout();
        contentWrapper.addClassName("description");
        contentWrapper.setSpacing(false);
        contentWrapper.setPadding(false);
        contentWrapper.setAlignItems(FlexComponent.Alignment.START);

        // [Horizontal] Header of the Card
        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");
        header.setWidthFull();

        // 1. Paragraph Job Title and Creation Date
        H2 title = new H2(application.getTitle());
        title.addClassName("job_title");
        title.getStyle().set("font-size", "1.5em");
        String dateString = ( application.getDeadline().toString());
        Span date = new Span(dateString) ;
        date.addClassName("creation_date");
        date.getStyle().set("font-style" , "italic");
        date.getStyle().set("margin-left", "auto");

        header.add(title, date);
        Paragraph applicationDescription;
        // 2. Job Description and
        if(usertype.equals("student")) {
            applicationDescription = new Paragraph(application.getCompanyName());
            applicationDescription.addClassName("job_description");
        }
        else {
            applicationDescription = new Paragraph(application.getFirstName() + " " + application.getLastName());
            applicationDescription.addClassName("job_description");
        }
        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        Icon likeIcon = VaadinIcon.EYE.create();
        likeIcon.addClassName("icon");





        contentWrapper.add(header, applicationDescription);
        card.add(image, contentWrapper);
        card.addClickListener(event -> {

        });
        add(card);

    }
    public Integer getJobID(){
        return application.getJobId();
    }
}
