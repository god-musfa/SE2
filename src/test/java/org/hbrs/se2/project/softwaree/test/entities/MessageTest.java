package org.hbrs.se2.project.softwaree.test.entities;
import org.hbrs.se2.project.softwaree.entities.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.Date;

public class MessageTest {
  Message message;
  Date date;

  @Before
  public void setUp() {
    message = new Message();
    date = new Date(2022, 12, 5);
  }

  @Test
  public void getSetId() {
    message.setId(1234);
    Assert.assertEquals((int)message.getId(), 1234);
  }

  @Test
  public void getSetMessage() {
    message.setMessage("Hallo");
    Assert.assertEquals(message.getMessage(), "Hallo");
  }

  @Test
  public void getSetStudentId() {
    message.setStudentID(1234);
    Assert.assertEquals((int)message.getStudentID(), 1234);
  }

  @Test
  public void getSetCompanyId() {
    message.setCompanyID(1234);
    Assert.assertEquals((int)message.getCompanyID(), 1234);
  }

  @Test
  public void getSetJobId() {
    message.setJobID(1234);
    Assert.assertEquals((int)message.getJobID(), 1234);
  }

  @Test
  public void getSetTimeSent() {
    message.setTimeSent(date);
    Assert.assertEquals(message.getTimeSent(), date);
  }

}
