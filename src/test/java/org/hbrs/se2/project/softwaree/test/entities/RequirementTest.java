package org.hbrs.se2.project.softwaree.test.entities;
import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class RequirementTest {
  Requirement requirement;

  @Before
  public void setUp() {
    requirement = new Requirement();
  }

  @Test
  public void getSetId() {
    requirement.setId(1234);
    Assert.assertEquals((int)requirement.getId(), 1234);
  }

  @Test
  public void getSetDescription() {
    requirement.setDescription("Best req");
    Assert.assertEquals(requirement.getDescription(), "Best req");
  }
}
