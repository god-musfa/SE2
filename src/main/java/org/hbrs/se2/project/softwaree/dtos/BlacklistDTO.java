package org.hbrs.se2.project.softwaree.dtos;

import org.hbrs.se2.project.softwaree.entities.Blacklist;

/**
 * A DTO for the {@link Blacklist} entity
 */
public class BlacklistDTO {

    private Integer student_id;
    private Integer company_id;

    public BlacklistDTO(Integer student_id, Integer company_id) {
        this.student_id = student_id;
        this.company_id = company_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

}
