package org.hbrs.se2.project.softwaree.control;


import org.hbrs.se2.project.softwaree.dtos.ApplicationDTO;
import org.hbrs.se2.project.softwaree.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManageApplicationControl {
    @Autowired
    private ApplicationRepository repository;


    public List<ApplicationDTO> readAllApplications(int id) {
        return repository.findAllApplicationByCompanyId(id);
    }


/*    public  List<JobDTO> readAllJobsNative() {
        return repository.findAllJobsNative();
    }*/
}
