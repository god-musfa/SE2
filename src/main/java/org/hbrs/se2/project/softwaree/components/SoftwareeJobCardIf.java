package org.hbrs.se2.project.softwaree.components;

import java.time.LocalDate;

public interface SoftwareeJobCardIf {
    public void setTitle(String title);
    public void setCompany(String company);
    public void setIcon(String iconPath);
    public void setCreationDate(String creationDate);
    public void setEditDate(String editDate);
    public void setDeadline(String deadline);

    public void setLocationInfo(String locationInfo);
    public void setJobExperienceInfo(String experienceInfo);
    public void setQualification(String qualification);
    public void setViews(int views);
    public void setSalary(String salary);
    public void setCareerInfo(String info);
    public void setButtonCompanyID(int id);
}
