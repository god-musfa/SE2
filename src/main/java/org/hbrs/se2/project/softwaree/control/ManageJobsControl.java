package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.dtos.JobDTO;
import org.hbrs.se2.project.softwaree.dtos.JobListDTO;
import org.hbrs.se2.project.softwaree.repository.*;
import org.hbrs.se2.project.softwaree.repository.BlacklistRepository;
import org.hbrs.se2.project.softwaree.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ManageJobsControl {

    @Autowired
    private JobRepository repository;
    @Autowired
    private BlacklistRepository blacklistRepo;
    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private CompanyRatingRepository ratingRepository;
    @Autowired
    private SkillRepository skillRepo;

    /**
     * Gibt eine Liste mit allen Jobs zurück, die nicht von Unternehmen stammen, die vom Nutzer blockiert worden sind.
     * @param userid NutzerID des aktuell eingeloggten Nutzers
     * @return Liste vom Typ Job
     */
    public List<JobDTO> readAllJobs(Integer userid) {
        List<Integer> blockedCompanys = new ArrayList<>();
        blacklistRepo.findBlockedCompanys(userid).forEach( dto -> blockedCompanys.add(dto.getCompanyID()) ); //Liste der blockierten Unternehmen
        return blockedCompanys.isEmpty() ? repository.getMostImportantDetailsAsList() : repository.findAllNotBlockedCompanys(blockedCompanys);
    }

    public List<JobDTO> readJobsFromCompany(Integer id) {
        return repository.readJobsFromCompany(id);
    }

    @Autowired
    JPAJobListRepository custJPARepo;
    @Transactional
    public List<JobListDTO> getJobList_with_JPA_CUSTOM_ResSetMapping(String searchTerm, Integer studentID, Set<String> skillSet, Integer avgRating, LocalDate timeLimit) {
        return custJPARepo.getJobList(searchTerm,studentID,skillSet, avgRating,timeLimit);
    }

    public List<JobListDTO> getJobListDTObyCompanyID(Integer id) {
        return custJPARepo.getJobByCompanyID(id);
    }
    @Transactional
    public void deleteJobById(int id) {
        repository.deleteJob(id);
    }
}