package com.bankguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_01_Register_And_Login {
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
		loginPageUrl = driver.getCurrentUrl();
	}

	@Test
	public void Login_01_Register_Account() {
		// Click Here -> Input email and click Submit button
		driver.findElement(By.xpath("//a[(text()='here')]")).click();
		driver.findElement(By.name("emailid")).sendKeys(generateEmail());
		driver.findElement(By.name("btnLogin")).click();

		// Retrieve value of userID and password
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}
	@Test
	public void Login_02_Login_To_System() {
		driver.get(loginPageUrl);

		// Login with userID and password
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		// Assert
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");
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
