package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.RequirementDTO;
import org.hbrs.se2.project.softwaree.entities.Requirement;

public class RequirementFactory {
    private RequirementFactory() {
        //RequirementFactory is a utility class
    }
    public static RequirementDTO getDTO(Requirement requirement) {
        return new RequirementDTO(
                requirement.getId(),
                requirement.getDescription()
        );
    }

}
