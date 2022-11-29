package org.hbrs.se2.project.softwaree.dtos;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.hbrs.se2.project.softwaree.entities.Skill} entity
 */
public class SkillDTO implements Serializable {
    private final Integer id;
    private final String description;

    public SkillDTO(Integer id, String description) {
        this.id = id;
        this.description = description;
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
        SkillDTO entity = (SkillDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "description = " + description + ")";
    }
}