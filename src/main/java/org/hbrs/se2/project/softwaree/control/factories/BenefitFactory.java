package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.BenefitDTO;
import org.hbrs.se2.project.softwaree.entities.Benefit;

public class BenefitFactory {

    public static BenefitDTO getDTO(Benefit benefit) {
        return new BenefitDTO(
                benefit.getId(),
                benefit.getDescription()
        );
    }

}
