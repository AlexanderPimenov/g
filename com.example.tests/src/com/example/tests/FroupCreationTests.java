package com.example.tests;


import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;


public class FroupCreationTests {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://alinea-test.opensoftdev.ru/client/user/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testValidGroupCanBeCreated() throws Exception {
    driver.get(baseUrl);
 
  loginAdmin();
  openUsersPage();
  fillFields(new GroupObject("lastName", "FirstName", "SecondName", "Login", "pswd"));
  }

private void loginAdmin() {
	driver.findElement(By.xpath("html/body/div[1]/div/div/form/div[2]/div/input[1]")).sendKeys("admin");
    driver.findElement(By.xpath("html/body/div[1]/div/div/form/div[2]/div/input[2]")).sendKeys("gfhjkm");
    driver.findElement(By.xpath("html/body/div[1]/div/div/form/div[2]/div/button")).click();
}
private void fillFields(GroupObject groupObject) {
	
	type(".//*[@id='gazprom_alinea_user_lastName']", groupObject.lastName);
    type(".//*[@id='gazprom_alinea_user_firstName']", groupObject.firstName);
	type(".//*[@id='gazprom_alinea_user_secondName']", groupObject.secondName);
	
    Select select = new Select(driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_role']")));
	select.selectByValue("ROLE_DISPATCHER");

	type(".//*[@id='gazprom_alinea_user_login']", groupObject.login);
	type(".//*[@id='gazprom_alinea_user_password_password']", groupObject.pwd);
	type(".//*[@id='gazprom_alinea_user_password_confirm']", groupObject.pwd);
/*	
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_firstName']")).clear();
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_firstName']")).sendKeys(firstName);
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_secondName']")).clear();
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_secondName']")).sendKeys(secondName);
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_login']")).clear();
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_login']")).sendKeys(login);
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_password_password']")).clear();
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_password_password']")).sendKeys(pwd);
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_password_confirm']")).clear();
	driver.findElement(By.xpath(".//*[@id='gazprom_alinea_user_password_confirm']")).sendKeys(pwd);
	*/
}

private void type(String locator, String lastName) {
	driver.findElement(By.xpath(locator)).clear();
	driver.findElement(By.xpath(locator)).sendKeys(lastName);
}


private void openUsersPage() {
	driver.findElement(By.xpath("html/body/div[1]/div/div[2]/div/div[2]/div/div/div/a[2]")).click();
}

  @After
  public void tearDown() throws Exception {
//    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
