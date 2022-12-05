package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.repository.BlacklistRepository;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManageJobsControl {
    @Autowired
    private JobRepository repository;

    @Autowired
    private BlacklistRepository blacklistRepo;

    /**
     * Gibt eine Liste mit allen Jobs zurück, die nicht von Unternehmen stammen, die vom Nutzer blockiert worden sind.
     * @param userid NutzerID des aktuell eingeloggten Nutzers
     * @return Liste vom Typ Job
     */
    public List<Job> readAllJobs(Integer userid) {
        List<Integer> blockedCompanys = new ArrayList<>();
        blacklistRepo.findBlockedCompanys(userid).forEach( dto -> blockedCompanys.add(dto.getCompanyID()) ); //Liste der blockierten Unternehmen
        return blockedCompanys.isEmpty() ? repository.findAll() : repository.findAllNotBlockedCompanys(blockedCompanys);
    }


/*    public  List<JobDTO> readAllJobsNative() {
        return repository.findAllJobsNative();
    }*/
}