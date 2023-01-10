package org.hbrs.se2.project.softwaree.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.hbrs.se2.project.softwaree.components.JobListFilter;
import org.hbrs.se2.project.softwaree.control.JobOfferControl;
import org.hbrs.se2.project.softwaree.control.ManageJobsControl;
import org.hbrs.se2.project.softwaree.dtos.JobListDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.util.Globals;

import java.time.LocalDate;
import java.util.*;

/**
 * Darstellung einer Tabelle (bei Vaadin: ein Grid) zur Anzeige von Autos.
 * Hier wurden nur grundlegende Elemente verarbeitet. Weitere Optionen (z.B.
 * weitere Filter-Möglichkeiten) kann man hier entnehmen:
 * https://vaadin.com/components/vaadin-grid/java-examples/header-and-footer
 */
@Route(value = Globals.Pages.SHOW_JOBS, layout = NavBar.class)
@PageTitle(Globals.PageTitles.PAGETITLE_STELLENANZEIGE)
@CssImport("./styles/views/job/jobListView.css")
public class JobListView extends VerticalLayout {
    String userType;
    private final Div filterUI;
    private JobListFilter filter;
    private Grid<JobListDTO> jobGrid;
    private ManageJobsControl jobControl;
    private List<JobListDTO> jobList;

    public JobListView(ManageJobsControl jobsControl, JobOfferControl jobOfferControl) {
        addClassName("jobs");
        super.setSizeFull();
        UserDTO user = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
        userType = user.getUserType();
        this.jobControl = jobsControl;

        if(userType.equals("company")) {
            filterUI = new Div();
            jobList = jobControl.getJobListDTObyCompanyID(user.getId());
            createGrid(Comparator.comparing(JobListDTO::getCreation_date, Comparator.nullsFirst(Comparator.naturalOrder())));
            add(this.createJobButton(), this.jobGrid);
        }
        else {
            this.filter = new JobListFilter(jobsControl, jobOfferControl);
            this.filterUI = createFilterUI();
            this.filter.studentID = user.getId();

            this.jobList = filter.fetchJobsFromBackend();
            createGrid(Comparator.comparing(JobListDTO::getCreation_date, Comparator.nullsFirst(Comparator.naturalOrder())));
            add(this.filterUI, this.jobGrid);
        }

    }

    private void createGrid(Comparator<JobListDTO> sortCriteriaComperator) {
        if (jobGrid != null) remove(this.jobGrid);
        Grid<JobListDTO> grid = new Grid<>();
        Grid.Column<JobListDTO> column = grid.addComponentColumn(job -> createJobCard(job))
                .setSortable(true)
                .setComparator(sortCriteriaComperator);
        GridSortOrder<JobListDTO> sortOrder = new GridSortOrder<>(column, SortDirection.DESCENDING);
        grid.setItems(this.jobList);
        grid.sort(Arrays.asList(sortOrder));

        grid.addSelectionListener(selection -> {
            Optional<JobListDTO> optionalJob = selection.getFirstSelectedItem();
            if (optionalJob.isPresent()) {
                UI.getCurrent().navigate(Globals.Pages.SHOW_JOB_DETAILS + "/" + optionalJob.get().getId());
                UI.getCurrent().getSession().setAttribute("jobID", optionalJob.get().getId());
                UI.getCurrent().getSession().setAttribute("companyID", optionalJob.get().getCompanyId());
            }
        });

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_WRAP_CELL_CONTENT);
        setSizeFull();
        this.jobGrid = grid;
    }

    private HorizontalLayout createJobCard(JobListDTO job) {
        /*      Displayed Job Information        */
        String jobTitle = job.getTitle();
        String jobDescription = job.getDescription();
        String companyName = job.getCompanyName();
        String location = job.getLocation();
        Double companyRating = job.get_avgcompanyrating();
        String companyWebsite = job.getCompanyWebsite();

        String jobViews = job.getViewsAsString();
        LocalDate jobCreationDate = job.getCreation_date();//"2022-24-12";

        // [horizontal ] Card Wrapper
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");
        card.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);

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

        /*          Company Logo            */
        Image image = new Image();
        image.setSrc("icons/icon.png");
        image.setHeight("50px");
        image.getStyle().set("margin", "30px 0px 12px 8px");

        /*            Job Title           */
        H2 title = new H2(job.getTitle());
        title.addClassName("job_title");
        title.getStyle().set("font-size", "1.5em");

        /*          Creation Date           */
        Div creationDate = new Div();
        creationDate.add(new Span(LumoIcon.CLOCK.create(), new Text(jobCreationDate.toString())));
        creationDate.add(new Span(VaadinIcon.LOCATION_ARROW.create(), new Text(location)));
        creationDate.addClassName("date_location_wrapper");
        header.add(title, creationDate);

        /*          Company Name            */
        Button companyButton = new Button(companyName);
        //companyButton.addClickListener(e-> UI.getCurrent().getPage().open(companyWebsite , "_blank"));
        companyButton.addClickListener(e-> UI.getCurrent().navigate("/profile/" + job.getCompanyId()));

        /*      Rating          */
        HorizontalLayout ratingWrapper = new HorizontalLayout(createStarRating(companyRating), new Text(companyRating.toString()));
        ratingWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        ratingWrapper.addClassName("ratingWrapper");

        /*          Job Description         */
        Paragraph job_description = new Paragraph(jobDescription + "...");
        job_description.addClassName("job_description");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        /*      Job Views       */
        Icon likeIcon = VaadinIcon.EYE.create();
        likeIcon.addClassName("icon");
        Span likes = new Span(jobViews);
        likes.addClassName("likes");
        actions.add(likeIcon, likes);

        contentWrapper.add(header, ratingWrapper, companyButton, job_description, actions);
        card.add(image, contentWrapper);
        return card;
    }

    private Div createFilterUI() {
        /*      Create Filter Elements      */
        TextField seachTermTextfield = this.createSearchFilter();
        MultiSelectComboBox<String> skillFilter = this.createSkillFilter();
        Select<List<VaadinIcon>> ratingFilter = this.createRatingFilter();
        Select<String> timeLimitFilter = this.createCreationDateFilter();
        Select<String> sortCriteriaSelect = this.createSortCriteriaSelection();

        // Add all the elements to the wrapper

        HorizontalLayout searchBar = new HorizontalLayout();
        searchBar.addClassName("searchBar");
        searchBar.add(seachTermTextfield);

        Div filterWrapper = new Div();
        filterWrapper.addClassName("filterWrapper");
        filterWrapper.add(ratingFilter, skillFilter, timeLimitFilter, sortCriteriaSelect);

        // Dropdown additional Filters
        Icon filterIcon = VaadinIcon.FILTER.create();
        HorizontalLayout detailsSummary = new HorizontalLayout();
        detailsSummary.add(filterIcon);

        Details details = new Details(filterIcon, filterWrapper);
        details.setOpened(false);

        Div wrapper = new Div();
        wrapper.add(searchBar, details);
        wrapper.addClassName("wrapper");
        return wrapper;
    }

    private Button createJobButton() {
        Button button = new Button("Stellenanzeige erstellen");
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        button.addClickListener(e -> {
            UI.getCurrent().getSession().setAttribute("jobID", -1);
            UI.getCurrent().navigate(Globals.Pages.JOB_OFFER);
        });
        return button;
    }

    private Select<String> createSortCriteriaSelection() {
        List<String> sortCriteriaList = new ArrayList<>();
        sortCriteriaList.add("Bewertungen");
        sortCriteriaList.add("Datum der Veröffentlichung");
        sortCriteriaList.add("Aufrufe");
        sortCriteriaList.add("Alphabetisch nach Titel");

        Select<String> sortCriteriaSelect = new Select<>();
        sortCriteriaSelect.setLabel("Sortieren nach ...");

        sortCriteriaSelect.setItems(sortCriteriaList);
        sortCriteriaSelect.addValueChangeListener(sortCriteria -> setSortCriteria(sortCriteria.getValue()));

        return sortCriteriaSelect;
    }

    private void setSortCriteria(String sortCriteria) {
        Comparator<JobListDTO> sortCriteriaComperator;
        switch (sortCriteria) {
            case ("Aufrufe"):
                sortCriteriaComperator = Comparator.comparing(JobListDTO::getViews, Comparator.nullsFirst(Comparator.naturalOrder()));
                break;
            case ("Bewertungen"):
                sortCriteriaComperator = Comparator.comparing(JobListDTO::get_avgcompanyrating, Comparator.nullsFirst(Comparator.naturalOrder()));
                break;
            case ("Alphabetisch nach Titel"):
                sortCriteriaComperator = Comparator.comparing(JobListDTO::getTitle, Comparator.nullsFirst(Comparator.naturalOrder()));
                break;
            default:
                sortCriteriaComperator = Comparator.comparing(JobListDTO::getCreation_date, Comparator.nullsFirst(Comparator.naturalOrder()));
                break;
        }
        createGrid(sortCriteriaComperator);
        add(this.jobGrid);
    }

    private TextField createSearchFilter() {
        TextField seachTermTextfield = new TextField();
        seachTermTextfield.setValueChangeMode(ValueChangeMode.EAGER);
        seachTermTextfield.addClassName("Jobfilter");
        seachTermTextfield.setPlaceholder("Software; Python; HTML; ...");
        seachTermTextfield.setPrefixComponent(VaadinIcon.SEARCH.create());
        seachTermTextfield.addValueChangeListener(event -> {
            this.filter.searchTerm = event.getValue();
            this.jobList = filter.fetchJobsFromBackend();
            this.jobGrid.setItems(this.jobList);
        });
        return seachTermTextfield;
    }

    public MultiSelectComboBox<String> createSkillFilter() {
        MultiSelectComboBox<String> comboBox = this.createComboBox(filter.skillSet);
        comboBox.setLabel("Fähigkeiten ...");
        comboBox.addSelectionListener(multiSelectionEvent -> {
            filter.skillSet = multiSelectionEvent.getValue();
            this.jobList = filter.fetchJobsFromBackend();
            this.jobGrid.setItems(this.jobList);
        });
        comboBox.setItems(this.filter.fillSkillSet());
        return comboBox;
    }

    private Span createStarRating(Double ratingValue) {
        Span ratingWrapper = new Span();
        double i = 0;
        while(i <= 4) {
            if(ratingValue>=(i+1)) {
                ratingWrapper.add(VaadinIcon.STAR.create());
                i++;
            }
            else if (ratingValue>=(i+0.5)) {
                ratingWrapper.add(VaadinIcon.STAR_HALF_LEFT_O.create());
                i += 0.5;
            } else {
                ratingWrapper.add(VaadinIcon.STAR_O.create());
                i += 1;
            }
        }
        return ratingWrapper;
    }

    public Select<List<VaadinIcon>> createRatingFilter() {
        List<List<VaadinIcon>> selectItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<VaadinIcon> starRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                starRow.add(VaadinIcon.STAR);
            }
            selectItems.add(starRow);
        }

        Select<List<VaadinIcon>> select = new Select<>();
        select.setLabel("Rating");
        select.setRenderer(new ComponentRenderer<HorizontalLayout, List<VaadinIcon>>(rating -> {
            HorizontalLayout ratingWrapper = new HorizontalLayout();
            rating.stream().forEach(star -> {
                Icon newStar = star.create();
                newStar.setSize("16px");
                ratingWrapper.add(newStar);
            });
            return ratingWrapper;
        }));
        select.setItems(selectItems);
        select.setLabel("Rating");
        select.addValueChangeListener(rating -> {
            filter.avgRating = rating.getValue().size();
            this.jobList = filter.fetchJobsFromBackend();
            this.jobGrid.setItems(this.jobList);
        });
        return select;
    }

    private Select<String> createCreationDateFilter() {
        List<String> timeLimits = new ArrayList<>();
        Map<String, LocalDate> timeLimitMap = new HashMap<>();

        timeLimits.add("< 24 Stunden");
        timeLimits.add("< 7 Tagen");
        timeLimits.add("< 1 Monaten");
        timeLimits.add("< 3 Monaten");
        timeLimits.add("< 6 Monaten");
        timeLimits.add("< 12 Monaten");
        timeLimits.add("12 Monaten oder länger...");

        timeLimitMap.put(timeLimits.get(0), LocalDate.now().minusDays(1));
        timeLimitMap.put(timeLimits.get(1), LocalDate.now().minusWeeks(1));
        timeLimitMap.put(timeLimits.get(2), LocalDate.now().minusMonths(1));
        timeLimitMap.put(timeLimits.get(3), LocalDate.now().minusMonths(3));
        timeLimitMap.put(timeLimits.get(4), LocalDate.now().minusMonths(6));
        timeLimitMap.put(timeLimits.get(5), LocalDate.now().minusMonths(12));
        timeLimitMap.put(timeLimits.get(6), LocalDate.EPOCH);

        Select<String> timelimitselection = new Select<>();
        timelimitselection.setItems(timeLimits);

        timelimitselection.addValueChangeListener(limit -> {
            System.out.print(timeLimitMap.get(limit.getValue()));
            this.filter.timeLimit = timeLimitMap.get(limit.getValue());
            this.jobList = filter.fetchJobsFromBackend();
            this.jobGrid.setItems(this.jobList);
        });
        timelimitselection.setLabel("Veröffentlicht vor ...");
        return timelimitselection;
    }

    private MultiSelectComboBox createComboBox(Set<String> filterItemSet) {
        MultiSelectComboBox<String> comboBox = new MultiSelectComboBox<>();
        comboBox.setItems(filterItemSet);
        return comboBox;
    }
}