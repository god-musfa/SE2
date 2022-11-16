package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.control.factories.AddressFactory;
import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ManageJobsControl {
    @Autowired
    private JobRepository repository;


    public List<JobDTO> readAllJobs() {
        return repository.findAllJobs();
    }
    public  List<JobDTO> readAllJobsNative() {
        return repository.findAllJobsNative();
    }
}