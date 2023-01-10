package org.hbrs.se2.project.softwaree.test.views;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTest {
  WebDriver driver;
  // Launching the Chrome browser
  @BeforeEach
  void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("http://sepp-test.inf.h-brs.de:8080/Softwaree-0.0.1-SNAPSHOT/");
  }
  @Test
  @Order(1)
  public void register() throws InterruptedException {
    Thread.sleep(2000);
    // Registerbutton
    driver.findElement(By.cssSelector("#landingPageView > vaadin-vertical-layout > vaadin-horizontal-layout:nth-child(3) > vaadin-button:nth-child(2)")).click();
    Thread.sleep(2000);
    // E-Mail
    driver.findElement(By.cssSelector("#input-vaadin-email-field-14")).sendKeys("softwaree.test@se2.de");
    // User name
    driver.findElement(By.cssSelector("#input-vaadin-password-field-15")).sendKeys("abc123456");
    driver.findElement(By.cssSelector("#input-vaadin-password-field-16")).sendKeys("abc123456");
    driver.findElement(By.cssSelector("#input-vaadin-checkbox-17")).click();
    driver.findElement(By.cssSelector("#registerView > vaadin-vertical-layout > vaadin-select > vaadin-select-value-button")).click();
    driver.findElement(By.cssSelector("body > vaadin-select-overlay > vaadin-select-list-box > vaadin-select-item:nth-child(1)")).click();
    driver.findElement(By.cssSelector("#registerView > vaadin-vertical-layout > vaadin-button")).click();
    Thread.sleep(2000);
    // register/student
    driver.findElement(By.cssSelector("#registerStudentView > vaadin-vertical-layout > vaadin-select:nth-child(1) > vaadin-select-value-button")).click();
    //
    driver.findElement(By.cssSelector("body > vaadin-select-overlay > vaadin-select-list-box > vaadin-select-item:nth-child(1)")).click();
    // First Name
    driver.findElement(By.cssSelector("#input-vaadin-text-field-50")).sendKeys("Softwaree");
    // Last Name
    driver.findElement(By.cssSelector("#input-vaadin-text-field-51")).sendKeys("Tester");
    //Birthdate
    driver.findElement(By.cssSelector("#input-vaadin-date-picker-52")).click();
    driver.findElement(By.cssSelector("#input-vaadin-date-picker-52")).sendKeys("6/1/1995");
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("#input-vaadin-date-picker-52")).sendKeys(Keys.ENTER);

    //Street
    driver.findElement(By.cssSelector("#input-vaadin-text-field-53")).sendKeys("Grantham-Allee");
    driver.findElement(By.cssSelector("#input-vaadin-text-field-54")).sendKeys("20");
    driver.findElement(By.cssSelector("#input-vaadin-text-field-55")).sendKeys("Sankt Augustin");
    driver.findElement(By.cssSelector("#input-vaadin-text-field-56")).sendKeys("53757");
    driver.findElement(By.cssSelector("#registerStudentView > vaadin-vertical-layout > vaadin-select:nth-child(9) > vaadin-select-value-button")).click();
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("body > vaadin-select-overlay > vaadin-select-list-box > vaadin-select-item:nth-child(1)")).click();

    driver.findElement(By.cssSelector("#input-vaadin-text-field-57")).sendKeys("Hochschule Bonn-Rhein-Sieg");
    Thread.sleep(5000);

    // Register Button
    driver.findElement(By.cssSelector("#registerStudentView > vaadin-vertical-layout > vaadin-button")).click();
    Thread.sleep(5000);
    Assertions.assertEquals(driver.getCurrentUrl(), "http://sepp-test.inf.h-brs.de:8080/Softwaree-0.0.1-SNAPSHOT/login");
  }

  // Login Selenium Training and clicking on it

  @Test
  @Order(2)
  public void login() throws InterruptedException {
    Thread.sleep(5000);
    driver.findElement(By.cssSelector("#landingPageView > vaadin-vertical-layout > vaadin-horizontal-layout:nth-child(3) > vaadin-button:nth-child(1)")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("#input-vaadin-text-field-6")).sendKeys("softwaree.test@se2.de");
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("#input-vaadin-password-field-7")).sendKeys("abc123456");
    driver.findElement(By.cssSelector("#Softwaree001SNAPSHOT-491737271 > vaadin-vertical-layout > vaadin-login-form > vaadin-login-form-wrapper > form > vaadin-button")).click();
    Thread.sleep(5000);
    Assertions.assertEquals(driver.getCurrentUrl(), "http://sepp-test.inf.h-brs.de:8080/Softwaree-0.0.1-SNAPSHOT/jobs");
  }

  @Test
  @Order(3)
  public void logout() throws InterruptedException {
    Thread.sleep(5000);
    driver.findElement(By.cssSelector("#landingPageView > vaadin-vertical-layout > vaadin-horizontal-layout:nth-child(3) > vaadin-button:nth-child(1)")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("#input-vaadin-text-field-6")).sendKeys("softwaree.test@se2.de");
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("#input-vaadin-password-field-7")).sendKeys("abc123456");
    driver.findElement(By.cssSelector("#Softwaree001SNAPSHOT-491737271 > vaadin-vertical-layout > vaadin-login-form > vaadin-login-form-wrapper > form > vaadin-button")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("#Softwaree001SNAPSHOT-491737271 > vaadin-app-layout > vaadin-tabs > vaadin-tab:nth-child(4) > a > vaadin-vertical-layout > vaadin-icon")).click();
    Thread.sleep(3000);
    Assertions.assertEquals(driver.getCurrentUrl(), "http://sepp-test.inf.h-brs.de:8080/Softwaree-0.0.1-SNAPSHOT/landingpage");
  }

  @Test
  @Order(4)
  public void delete() throws InterruptedException {
    Thread.sleep(5000);
    driver.findElement(By.cssSelector("#landingPageView > vaadin-vertical-layout > vaadin-horizontal-layout:nth-child(3) > vaadin-button:nth-child(1)")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("#input-vaadin-text-field-6")).sendKeys("softwaree.test@se2.de");
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("#input-vaadin-password-field-7")).sendKeys("abc123456");
    driver.findElement(By.cssSelector("#Softwaree001SNAPSHOT-491737271 > vaadin-vertical-layout > vaadin-login-form > vaadin-login-form-wrapper > form > vaadin-button")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("#Softwaree001SNAPSHOT-491737271 > vaadin-app-layout > vaadin-tabs > vaadin-tab:nth-child(2) > a > vaadin-vertical-layout")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("#Softwaree001SNAPSHOT-491737271 > vaadin-app-layout > div.edit-profile-view > vaadin-horizontal-layout > vaadin-button")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("#overlay > flow-component-renderer > div > vaadin-horizontal-layout > vaadin-button:nth-child(2)")).click();
    Thread.sleep(3000);
    Assertions.assertEquals(driver.getCurrentUrl(), "http://sepp-test.inf.h-brs.de:8080/Softwaree-0.0.1-SNAPSHOT/landingpage");
  }

  @AfterEach
  void tearDown() {
    driver.quit();
  }

}
