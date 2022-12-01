package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.SkillDTO;
import org.hbrs.se2.project.softwaree.entities.Skill;

public class SkillFactory {

    public static SkillDTO getDTO(Skill skill) {
        return new SkillDTO(
                skill.getId(),
                skill.getDescription()
        );
    }
}
