package org.hbrs.se2.project.softwaree.test.entities;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import org.hbrs.se2.project.softwaree.entities.Blacklist;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class BlacklistTest {
  Blacklist blacklist;

  @Before
  public void setUp() {
    blacklist = new Blacklist();
  }

  @Test
  public void getSetCompanyId() {
    blacklist.setCompanyID(1234);
    Assert.assertEquals((int)blacklist.getCompanyID(), 1234);
  }

  @Test
  public void getSetStudentId() {
    blacklist.setStudentID(1234);
    Assert.assertEquals((int)blacklist.getStudentID(), 1234);
  }


}
