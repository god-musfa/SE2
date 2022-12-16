package org.hbrs.se2.project.softwaree.dtos;

public class BenefitDTO implements LabelDTO {
    private int id;
    private String description;

    public BenefitDTO(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public BenefitDTO(String description) {
        this.description = description;
    }

    public BenefitDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
