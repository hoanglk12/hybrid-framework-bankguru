package com.nopcommerce.register;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

public class Register_Account extends BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, confirmPassword;
	public String email = generateEmail();
	public String password = "123456";
	@Parameters({"browser","url"})
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		driver = getBrowser(browserName, urlNopCommerce);
		email = generateEmail(); 
		firstName = "Cristiano"; 
		lastName = "Ronaldo"; 
		confirmPassword = password;
	}

	@Test
	public void Register_06_All_Info_Correct() {
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
	
	//@Test
	public void Register_06_All_Info_Correct(WebDriver driver, String firstName, String lastName, String email, String password, String confirmPassword) {
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
