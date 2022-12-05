package org.hbrs.se2.project.softwaree.test;
import org.hbrs.se2.project.softwaree.util.Globals;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class GlobalsTest {
  @Test
  public void testCurrentUser() {
    Assertions.assertEquals(Globals.CURRENT_USER, "current_User");
  }

  @Test
  public void testRouting() {
    Assertions.assertEquals(Globals.Pages.LOGIN_VIEW, "login");
    Assertions.assertEquals(Globals.Pages.JOB_OFFER, "jobOffer");
    Assertions.assertEquals(Globals.Pages.SHOW_JOB_DETAILS, "job");
    Assertions.assertEquals(Globals.Pages.SHOW_JOBS, "jobs");
    Assertions.assertEquals(Globals.Pages.EDIT_PROFILE, "editProfile");
    Assertions.assertEquals(Globals.Pages.REGISTER, "register");
    Assertions.assertEquals(Globals.Pages.REGISTER_STUDENT, "register/student");
    Assertions.assertEquals(Globals.Pages.REGISTER_COMPANY, "register/company");
  }

}
