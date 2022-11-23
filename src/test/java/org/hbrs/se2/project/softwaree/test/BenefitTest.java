package org.hbrs.se2.project.softwaree.test;

import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class BenefitTest {
  Benefit benefit;

  @Before
  public void setUp() {
    benefit = new Benefit();
  }

  @Test
  public void getSetDescription_success() {
    benefit.setDescription("Best Benefit");
    Assert.assertEquals(benefit.getDescription(), "Best Benefit");
  }
}
