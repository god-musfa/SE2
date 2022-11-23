package org.hbrs.se2.project.softwaree.test;

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
  public void getSetSteet_success() {
    address.setStreet("Grantham-Allee");
    Assert.assertEquals(address.getStreet(), "Grantham-Allee");
  }

  @Test
  public void getSetCity_success() {
    address.setCity("Sankt Augustin");
    Assert.assertEquals(address.getCity(), "Sankt Augustin");
  }

  @Test
  public void getSetNumber_success() {
    address.setNumber("20");
    Assert.assertEquals(address.getNumber(), "20");
  }
  @Test
  public void getSetPostalCode_success() {
    address.setPostalCode("53757");
    Assert.assertEquals(address.getPostalCode(), "53757");
  }


}
