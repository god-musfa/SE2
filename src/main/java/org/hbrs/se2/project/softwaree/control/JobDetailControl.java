package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.control.factories.BlacklistFactory;
import org.hbrs.se2.project.softwaree.control.factories.JobFactory;
import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.entities.Blacklist;
import org.hbrs.se2.project.softwaree.repository.BlacklistRepository;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JobDetailControl {
    @Autowired
    JobRepository repo;

    @Autowired
    BlacklistRepository blacklistRepo;

    public JobDTO getJob(int id) {
        return JobFactory.createDTO(repo.getFullJobInfo(id).get());
    }

    public boolean validateJobID(int id) {
        System.out.println(repo.getFullJobInfo(id).isPresent()+" "+repo.getFullJobInfo(id).isPresent());
        return repo.getFullJobInfo(id).isPresent() && repo.checkJobExists(id);
    }

    public int validateJobViews(Integer viewsFromDTO) {
        if (viewsFromDTO == null) {
            return 0;
        } else {
            return viewsFromDTO;
        }
    }

    public void addBlacklist(Integer studentID, Integer companyID) {
        Blacklist entityBlacklist = BlacklistFactory.createMessage(studentID, companyID);
        blacklistRepo.save(entityBlacklist);
    }
}
