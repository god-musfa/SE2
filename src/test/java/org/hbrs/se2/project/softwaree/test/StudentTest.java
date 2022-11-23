package org.hbrs.se2.project.softwaree.test;

import org.hbrs.se2.project.softwaree.entities.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.time.LocalDate;
import java.util.Optional;

public class StudentTest {
  Student student;
  @Before
  public void setUp() {
    student = new Student();
  }

  @Test
  public void getSetFirstName_success() {
    student.setFirstName("Michael");
    Assert.assertEquals(student.getFirstName(), "Michael");
  }

  @Test
  public void getSetLastName_success() {
    student.setLastName("Meier");
    Assert.assertEquals(student.getLastName(), "Meier");
  }
/*
  @Test
  public void getSetSemester_success() {
    student.setSemester();
    Assert.assertEquals(student.getSemester(), 3);
  }

 */

  @Test
  public void getSetDegree_success() {
    student.setDegree("Abitur");
    Assert.assertEquals(student.getDegree(), "Abitur");
  }

  @Test
  public void getSetBirthday_success() {
    LocalDate now = LocalDate.now();
    student.setBirthday(now);
    Assert.assertEquals(student.getBirthday(), now);
  }
  @Test
  public void getSetUniversity_success() {
    student.setUniversity("Hochschule Bonn-Rhein-Sieg");
    Assert.assertEquals(student.getUniversity(), "Hochschule Bonn-Rhein-Sieg");
  }

}
