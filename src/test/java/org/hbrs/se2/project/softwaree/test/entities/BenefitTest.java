package org.hbrs.se2.project.softwaree.test.entities;

import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class BenefitTest {

  Benefit benefit;
  Job job ;
  Company comp ;

  @Before
  public void setUp() {
    benefit = new Benefit();
    job = new Job();
    comp = new Company();
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

  @Test
  public void getSetJobs(){

    job.setId(123);
    job.setDescription("Good Job!");
    comp.setName("SE2");
    job.setCompany(comp);
    job.setLocation("Bonn");
    Assert.assertEquals("Good Job!" , job.getDescription());
    Assert.assertEquals("Bonn" , job.getLocation());
    Assert.assertEquals("SE2" , job.getCompany().getName());





  }
}
