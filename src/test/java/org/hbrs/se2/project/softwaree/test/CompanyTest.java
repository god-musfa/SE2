package org.hbrs.se2.project.softwaree.test;

import org.hbrs.se2.project.softwaree.entities.Company;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class CompanyTest {
  Company company;
  @Before
  public void setUp() {
    company = new Company();
  }
  @Test
  public void getSetName_success() {
    company.setName("Coca Cola");
    Assert.assertEquals(company.getName(), "Coca Cola");
  }
  @Test
  public void getSetField_success() {
    company.setField("Informatik");
    Assert.assertEquals(company.getField(), "Informatik");
  }

  @Test
  public void getSetPhoneNumber_success() {
    company.setPhoneNumber("123456789");
    Assert.assertEquals(company.getPhoneNumber(), "123456789");
  }

  @Test
  public void getSetWebsite_success() {
    company.setWebsite("https://www.coca-cola-deutschland.de/");
    Assert.assertEquals(company.getWebsite(), "https://www.coca-cola-deutschland.de/");
  }

  @Test
  public void getSetSize_success() {
    company.setSize("small");
    Assert.assertEquals(company.getSize(), "small");
  }

  @Test
  public void getSetContactPerson_success() {
    company.setContactPerson("Herr Müller");
    Assert.assertEquals(company.getContactPerson(), "Herr Müller");
  }
}
