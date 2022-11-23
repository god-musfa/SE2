package org.hbrs.se2.project.softwaree.test;

import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDate;

public class JobTest {
  Job job;
  Company company;

  @Before
  public void setUp() {
    job = new Job();

    company = new Company();
    company.setName("Coca Cola");
    company.setField("Informatik");
    company.setPhoneNumber("123456789");
    company.setWebsite("https://www.coca-cola-deutschland.de/");


  }

  @Test
  public void getSetTitle_success() {
    job.setTitle("Job1");
    Assert.assertEquals(job.getTitle(), "Job1");
  }

  @Test
  public void getSetDescription_success() {
    job.setDescription("Best Job");
    Assert.assertEquals(job.getDescription(), "Best Job");
  }

  @Test
  public void getSetCreation_date_success() {
    LocalDate now = LocalDate.now();
    job.setCreation_date(now);
    Assert.assertEquals(job.getCreationDate(), now);
  }

  @Test
  public void getSetDeadline_date_success() {
    LocalDate now = LocalDate.now();
    job.setDeadline(now);
    Assert.assertEquals(job.getDeadline(), now);
  }

  @Test
  public void getSetLast_edit_success() {
    LocalDate now = LocalDate.now();
    job.setLast_edit(now);
    Assert.assertEquals(job.getLastEdit(), now);
  }

  @Test
  public void getSetCompany_success() {
    job.setCompany(company);
    Assert.assertEquals(job.getCompany(), company);
  }


}
