package org.hbrs.se2.project.softwaree.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumTest {
  WebDriver driver;
  // Launching the Chrome browser
  @BeforeEach
  void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("http://sepp-test.inf.h-brs.de:8080/Softwaree-0.0.1-SNAPSHOT/");
  }

  // Login Selenium Training and clicking on it
  @Test
  public void login() throws InterruptedException {
    driver.findElement(By.id("vaadinLoginUsername")).sendKeys("sascha");
    Thread.sleep(3000);
    driver.findElement(By.id("vaadinLoginPassword")).sendKeys("abc");
    Thread.sleep(3000);
    driver.findElement(By.xpath("/html/body/vaadin-vertical-layout/vaadin-login-form/vaadin-login-form-wrapper/form/vaadin-button")).click();
    Thread.sleep(5000);
    Assertions.assertEquals(driver.getCurrentUrl(), "http://sepp-test.inf.h-brs.de:8080/Softwaree-0.0.1-SNAPSHOT/address");
  }
  //Logout
  @Test
  public void logout() throws InterruptedException {
    driver.findElement(By.id("vaadinLoginUsername")).sendKeys("sascha");
    Thread.sleep(3000);
    driver.findElement(By.id("vaadinLoginPassword")).sendKeys("abc");
    Thread.sleep(3000);
    driver.findElement(By.xpath("/html/body/vaadin-vertical-layout/vaadin-login-form/vaadin-login-form-wrapper/form/vaadin-button")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//*[@id=\"header\"]/vaadin-horizontal-layout/vaadin-menu-bar//div/vaadin-menu-bar-button[1]/vaadin-context-menu-item")).click();
    Assertions.assertEquals(driver.getCurrentUrl(), "http://sepp-test.inf.h-brs.de:8080/Softwaree-0.0.1-SNAPSHOT/");
  }

  @AfterEach
  void tearDown() {
    driver.quit();
  }
/*
  public static void main(String[] args) throws InterruptedException {
    SeleniumLoginTest obj = new SeleniumLoginTest();
    obj.launchBrowser();
    obj.login();

  }

 */
}
