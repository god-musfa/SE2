package org.hbrs.se2.project.softwaree.components;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import org.hbrs.se2.project.softwaree.control.JobOfferControl;
import org.hbrs.se2.project.softwaree.control.ManageJobsControl;
import org.hbrs.se2.project.softwaree.dtos.JobListDTO;
import org.hbrs.se2.project.softwaree.dtos.SkillDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class JobListFilter {
    private final Grid<JobListDTO> grid;
    private final JobOfferControl jobOfferControl;
    private  ManageJobsControl jobsControl;
    private List<JobListDTO> jobList;
    GridListDataView<JobListDTO> gridListDataView;

    public Set<String> skillSet;
    public int studentID = 0;
    public String searchTerm = "";
    public LocalDate timeLimit = LocalDate.EPOCH;
    public Integer avgRating=0;

    public JobListFilter(ManageJobsControl jobsControl, JobOfferControl jobOfferControl) {
        this.skillSet = new HashSet<>();
        this.jobsControl = jobsControl;
        this.jobOfferControl = jobOfferControl;
        this.jobList = jobsControl.getJobList_with_JPA_CUSTOM_ResSetMapping(searchTerm, studentID, skillSet, this.avgRating, this.timeLimit);
        this.grid = new Grid<>();
        this.gridListDataView = this.grid.setItems(jobList);
    }

    public Grid<JobListDTO> getGrid() {
        return this.grid;
    }

    public List<JobListDTO> fetchJobsFromBackend() {
        this.jobList = jobsControl.getJobList_with_JPA_CUSTOM_ResSetMapping(this.searchTerm,this.studentID,this.skillSet, this.avgRating, this.timeLimit);

        return this.jobList;
    }

    // ToDo: fetch from: jobControl.getUniqueSkills()
    public Set<String> fillSkillSet() {
        List<SkillDTO> skillDTOList = new ArrayList<>();
        skillDTOList = jobOfferControl.getAvailableSkills();

        Set<String> skillDescription = new HashSet<>();
        skillDTOList.forEach(skillDTO -> skillDescription.add(skillDTO.getDescription()));

        return skillDescription;
    }
}
