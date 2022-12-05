package org.hbrs.se2.project.softwaree.test.entities;

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
  public void getSetDescription() {
    benefit.setDescription("Best Benefit");
    Assert.assertEquals(benefit.getDescription(), "Best Benefit");
  }

  @Test
  public void getSetId() {
    benefit.setId(1234);
    Assert.assertEquals((int)benefit.getId(), 1234);
  }

  // ToDo: getSetJobs
}
