package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.entities.Blacklist;

public class BlacklistFactory {
    public static Blacklist createMessage(Integer studentID, Integer companyID) {
        Blacklist blacklist = new Blacklist();
        blacklist.setStudentID(studentID);
        blacklist.setCompanyID(companyID);
        return blacklist;
    }
}
