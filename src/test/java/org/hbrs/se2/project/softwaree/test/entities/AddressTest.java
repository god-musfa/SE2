package org.hbrs.se2.project.softwaree.test.entities;

import org.hbrs.se2.project.softwaree.entities.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class AddressTest {
  Address address;
  @Before
  public void setUp() {
    address = new Address();
  }

  @Test
  public void getSetStreet() {
    address.setStreet("Grantham-Allee");
    Assert.assertEquals(address.getStreet(), "Grantham-Allee");
  }

  @Test
  public void getSetCity() {
    address.setCity("Sankt Augustin");
    Assert.assertEquals(address.getCity(), "Sankt Augustin");
  }

  @Test
  public void getSetNumber() {
    address.setNumber("20");
    Assert.assertEquals(address.getNumber(), "20");
  }
  @Test
  public void getSetPostalCode() {
    address.setPostalCode("53757");
    Assert.assertEquals(address.getPostalCode(), "53757");
  }

  @Test
  public void getSetId() {
    address.setId(1234);
    Assert.assertEquals((int)address.getId(), 1234);
  }

}
