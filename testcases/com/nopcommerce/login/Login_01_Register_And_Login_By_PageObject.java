package com.nopcommerce.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

public class Login_01_Register_And_Login_By_PageObject{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email, firstName, lastName, password, confirmPassword;
	
	@BeforeClass
	public void initBrowser() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		email = generateEmail(); 
		firstName = "Cristiano"; 
		lastName = "Ronaldo"; 
		password = "123456"; 
		confirmPassword = password;
	}

	@Test
	public void Login_01_Register_Account() {
		// Step 1: HomePage -> click to Register link
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		homePage.clickToRegisterLink();
		
		//Step 2: RegisterPage
		registerPage = new RegisterPageObject(driver);
		
		//Step 3: Click to Gender Male radio button
		registerPage.clickToGenderMaleRadioButton();
		
		//Step 4: Enter to FirstnameTextbox
		registerPage.enterToFirstnameTextbox(firstName);
				
		//Step 5: Enter to LastnameTextbox
		registerPage.enterToLastnameTextbox(lastName);
		
		//Step 6: Enter to EmailTextbox
		registerPage.enterToEmailTextbox(email);
		
		//Step 7: Enter to PasswordTextbox
		registerPage.enterToPasswordTextbox(password);
		
		//Step 8: Enter to ConfirmPasswordTextbox
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		
		//Step 9: Click to Register button
		registerPage.clickToRegisterButton();
		
		//Step 10: Verify success message displayed
		Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
		
		//Step 11: Click to Register button
		registerPage.clickToLogoutLink();
	}
	@Test
	public void Login_02_Login_To_System() {
		//Step 1: HomePage -> Click to Login link
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		homePage.clickToLoginLink();
		
		//Step 2: LoginPage
		loginPage = new LoginPageObject(driver);
		
		//Step 3: Enter to emailtextbox
		loginPage.enterToEmailTextbox(email);
		
		//Step 4: Enter to passwordtextbox
		loginPage.enterToPasswordTextbox(password);
		
		//Step 5: Click to Login button
		loginPage.clickToLoginButton();
		
		//Step 1: HomePage -> Verify slider is displayed
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	public String generateEmail() {
		Random rand = new Random();
		return "cr7_" + rand.nextInt(99999) + "@mail.com";
	}
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
}
