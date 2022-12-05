package org.hbrs.se2.project.softwaree.test.entities;

import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.time.LocalDate;

public class StudentTest {
  Student student;
  User user;
  @Before
  public void setUp() {
    student = new Student();
    user = new User();
  }

  @Test
  public void getSetFirstName() {
    student.setFirstName("Michael");
    Assert.assertEquals(student.getFirstName(), "Michael");
  }

  @Test
  public void getSetLastName() {
    student.setLastName("Meier");
    Assert.assertEquals(student.getLastName(), "Meier");
  }

  @Test
  public void getSetSemester() {
    student.setSemester(3);
    Assert.assertEquals((int)student.getSemester(), 3);
  }

  @Test
  public void getSetSubject() {
    student.setSubject("Mathe");
    Assert.assertEquals(student.getSubject(), "Mathe");
  }

  @Test
  public void getSetDegree() {
    student.setDegree("Abitur");
    Assert.assertEquals(student.getDegree(), "Abitur");
  }

  @Test
  public void getSetBirthday() {
    LocalDate now = LocalDate.now();
    student.setBirthday(now);
    Assert.assertEquals(student.getBirthday(), now);
  }
  @Test
  public void getSetUniversity() {
    student.setUniversity("Hochschule Bonn-Rhein-Sieg");
    Assert.assertEquals(student.getUniversity(), "Hochschule Bonn-Rhein-Sieg");
  }

  @Test
  public void getSetId() {
    student.setId(1234);
    Assert.assertEquals((int)student.getId(), 1234);
  }

  @Test
  public void getSetUser() {
    student.setUser(user);
    Assert.assertEquals(student.getUser(), user);
  }

  // ToDo: getSetSkills

}
