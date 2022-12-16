package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.BenefitDTO;
import org.hbrs.se2.project.softwaree.dtos.RequirementDTO;
import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.hbrs.se2.project.softwaree.entities.Requirement;

public class RequirementFactory {

    public static RequirementDTO getDTO(Requirement requirement) {
        return new RequirementDTO(
                requirement.getId(),
                requirement.getDescription()
        );
    }

}
