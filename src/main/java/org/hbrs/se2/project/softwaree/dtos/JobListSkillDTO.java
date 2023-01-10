package org.hbrs.se2.project.softwaree.dtos;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * A DTO for the {@link org.hbrs.se2.project.softwaree.entities.Skill} entity
 */
public class JobListSkillDTO implements Serializable {
    private Integer id;
    private String description;
    public static final String ID_ALIAS = "s_id";
    public static final String DESCRIPTION_ALIAS = "s_description";

    public JobListSkillDTO(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public JobListSkillDTO(
            Object[] tuples,
            Map<String, Integer> aliasToIndexMap) {
        this.id = (Integer)(tuples[aliasToIndexMap.get(ID_ALIAS)]);
        this.description = (String) (tuples[aliasToIndexMap.get(DESCRIPTION_ALIAS)]);
    }


    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobListSkillDTO entity = (JobListSkillDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}