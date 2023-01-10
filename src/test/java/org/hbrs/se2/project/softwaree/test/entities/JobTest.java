package org.hbrs.se2.project.softwaree.test.entities;

import org.hbrs.se2.project.softwaree.entities.Benefit;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Job;
import org.hbrs.se2.project.softwaree.entities.Requirement;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class JobTest {
  Job job;
  Job jobnull;
  Job jobprefilled;
  Company company;
  Requirement requirement1;
  Requirement requirement2;
  Benefit benefit1;
  Benefit benefit2;
  Set<Benefit> benefitSet;
  Set <Requirement> requirementSet;

  @Before
  public void setUp() {
    job = new Job();
    jobnull = new Job();
    jobprefilled = new Job();
    jobprefilled.setLocation("Sankt Augustin");
    jobprefilled.setCreation_date(LocalDate.now());
    jobprefilled.setDeadline(LocalDate.now());
    jobprefilled.setLast_edit(LocalDate.now());
    jobprefilled.setTitle("Job2");
    jobprefilled.setDescription("Best Job 2");
    jobprefilled.setCompany(company);
    jobprefilled.setId(12345);
    jobprefilled.setViews(12345);


    company = new Company();
    company.setName("Coca Cola");
    company.setField("Informatik");
    company.setPhoneNumber("123456789");
    company.setWebsite("https://www.coca-cola-deutschland.de/");


    benefit1 = new Benefit();
    benefit1.setDescription("WLAN");
    benefit1.setId(555);
    benefit2 = new Benefit();
    benefit2.setDescription("Coffee");
    benefit1.setId(444);


    requirement1 = new Requirement();
    requirement1.setDescription("HTML");
    requirement1.setId(333);
    requirement2 = new Requirement();
    requirement2.setDescription("Java");
    requirement2.setId(222);

    benefitSet = new HashSet<>();
    benefitSet.add(benefit1);
    benefitSet.add(benefit2);

    requirementSet = new HashSet<>();
    requirementSet.add(requirement1);
    requirementSet.add(requirement2);
  }

  @Test
  public void getSetTitle() {
    job.setTitle("Job1");
    Assert.assertEquals(job.getTitle(), "Job1");
  }

  @Test
  public void getSetDescription() {
    job.setDescription("Best Job");
    Assert.assertEquals(job.getDescription(), "Best Job");
  }

  @Test
  public void getSetCreationDate() {
    LocalDate now = LocalDate.now();
    job.setCreation_date(now);
    Assert.assertEquals(job.getCreationDate(), now);
  }

  @Test
  public void getSetDeadlineDate() {
    LocalDate now = LocalDate.now();
    job.setDeadline(now);
    Assert.assertEquals(job.getDeadline(), now);
  }

  @Test
  public void getSetLastEdit() {
    LocalDate now = LocalDate.now();
    job.setLast_edit(now);
    Assert.assertEquals(job.getLastEdit(), now);
  }

  @Test
  public void getSetCompany() {
    job.setCompany(company);
    Assert.assertEquals(job.getCompany(), company);
  }

  @Test
  public void getSetId() {
    job.setId(1234);
    Assert.assertEquals((int)job.getId(), 1234);
  }

  @Test
  public void getSetLocation() {
    job.setLocation("Sankt Augustin");
    Assert.assertEquals(job.getLocation(), "Sankt Augustin");
  }

  @Test
  public void getSetViews() {
    job.setViews(1234);
    Assert.assertEquals((int)job.getViews(), 1234);
  }

  @Test
  public void getSetCompanyName() {
    jobprefilled.setCompany(company);
    Assert.assertEquals(jobprefilled.getCompanyName(), "Coca Cola");

  }

  @Test
  public void notNull() {
    Assert.assertNotNull(jobprefilled.getLastEditAsString());
    Assert.assertNotNull(jobprefilled.getCreationDateAsString());
    Assert.assertNotNull(jobprefilled.getDeadlineAsString());
    Assert.assertNotNull(jobprefilled.getViewsAsString());
  }

  @Test
  public void isNull() {
    Assert.assertEquals(jobnull.getLastEditAsString(), "-");
    Assert.assertEquals(jobnull.getCreationDateAsString(), "-");
    Assert.assertEquals(jobnull.getDeadlineAsString(), "-");
    Assert.assertEquals(jobnull.getViewsAsString(), "-");
  }



  @Test
  public void getSetRequirements(){
    jobprefilled.setBenefits(benefitSet);
    Set <Benefit> getSet = jobprefilled.getBenefits();
    Assert.assertTrue(getSet.contains(benefit1));
    Assert.assertTrue(getSet.contains(benefit2));


  }

  @Test
  public void getSetBenefits(){

    jobprefilled.setRequirements(requirementSet);
    Set <Requirement> getSet = jobprefilled.getRequirements();
    Assert.assertTrue(getSet.contains(requirement1));
    Assert.assertTrue(getSet.contains(requirement1));


  }

}
