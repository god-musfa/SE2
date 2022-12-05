package org.hbrs.se2.project.softwaree.test.entities;
import org.hbrs.se2.project.softwaree.entities.Address;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class UserTest {
  User user;
  Student student;
  Company company;
  Address address;

  @Before
  public void setUp() {
    user = new User();
    student = new Student();
    company = new Company();
    address = new Address();
  }

  @Test
  public void getSetPassword() {
    user.setPassword("test123");
    Assert.assertEquals(user.getPassword(), "test123");
  }

  @Test
  public void getSetCompany() {
    user.setCompany(company);
    Assert.assertEquals(user.getCompany(), company);
  }

  @Test
  public void getSetStudent() {
    user.setStudent(student);
    Assert.assertEquals(user.getStudent(), student);
  }


  @Test
  public void getSetUserType() {
    user.setUserType("student");
    Assert.assertEquals(user.getUserType(), "student");
  }

  @Test
  public void getSetAddress() {
    user.setAddress(address);
    Assert.assertEquals(user.getAddress(), address);
  }

  @Test
  public void getSetEmail() {
    user.setEmail("test.test@test.de");
    Assert.assertEquals(user.getEmail(), "test.test@test.de");
  }

  @Test
  public void getSetId() {
    user.setId(1234);
    Assert.assertEquals((int)user.getId(), 1234);
  }

}
