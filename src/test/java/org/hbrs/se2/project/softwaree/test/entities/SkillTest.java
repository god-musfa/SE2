package org.hbrs.se2.project.softwaree.test.entities;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class SkillTest {
  Skill skill;

  @Before
  public void setUp() {
    skill = new Skill();
  }

  @Test
  public void getSetDescription() {
    skill.setDescription("Java");
    Assert.assertEquals(skill.getDescription(), "Java");
  }

  @Test
  public void getSetId() {
    skill.setId(1234);
    Assert.assertEquals((int)skill.getId(), 1234);
  }
}
