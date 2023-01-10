package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.BlacklistDTO;
import org.hbrs.se2.project.softwaree.entities.Blacklist;
import org.hbrs.se2.project.softwaree.repository.BlacklistRepository;

public class BlacklistFactory {

    private BlacklistFactory() {
        //BlacklistFactory is a utility class
    }
    public static Blacklist createMessage(Integer studentID, Integer companyID) {
        Blacklist blacklist = new Blacklist();
        blacklist.setStudentID(studentID);
        blacklist.setCompanyID(companyID);
        return blacklist;
    }

    public static Blacklist getBlacklistEntity(BlacklistRepository blacklistRepo, Integer userID, Integer companyID) {
        BlacklistDTO bDTO = blacklistRepo.findBlacklistEl(userID,companyID);
        Blacklist b = new Blacklist();
        b.setCompanyID(bDTO.getCompanyID());
        b.setStudentID((bDTO.getStudentID()));
        return b;
    }
}
