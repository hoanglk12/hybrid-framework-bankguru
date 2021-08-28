package com.bankguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Login_01_Register_And_Login_By_Inheritance extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String loginPageUrl, userID, password, name, dob, address, city, state, pin, phone, customerID;
	
	@BeforeClass
	public void initBrowser() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
		
	}

	@Test
	public void Login_01_Register_Account() {
		loginPageUrl = driver.getCurrentUrl();
		
		clickToElement(driver, "//a[(text()='here')]");
		sendkeyToElement(driver, "//input[@name='emailid']", generateEmail());
		clickToElement(driver, "//input[@name='btnLogin']");
	

		// Retrieve value of userID and password
		
		userID = getTextElement(driver, "//td[text()='User ID :']/following-sibling::td");
		password = getTextElement(driver, "//td[text()='Password :']/following-sibling::td");
	}
	@Test
	public void Login_02_Login_To_System() {
		driver.get(loginPageUrl);

		// Login with userID and password
		sendkeyToElement(driver, "//input[@name='uid']", userID);
		sendkeyToElement(driver, "//input[@name='password']", password);
		clickToElement(driver, "//input[@name='btnLogin']");
		
		
		// Assert
		Assert.assertEquals(getTextElement(driver, "//marquee[@class='heading3']"),"Welcome To Manager's Page of Guru99 Bank");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return "john_wick" + rand.nextInt(9999) + "@mail.com";
	}
}
