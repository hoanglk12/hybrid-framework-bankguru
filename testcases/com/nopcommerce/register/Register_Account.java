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
	public void Register_01_Empty_Data() {
		// Step 1: HomePage -> click to Register link
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		homePage.clickToRegisterLink();
		
		//Step 2: RegisterPage
		registerPage = new RegisterPageObject(driver);
		
		//Step 3: Click to Gender Male radio button
		registerPage.clickToGenderMaleRadioButton();
	
		//Step 4: Enter to FirstnameTextbox
		registerPage.enterToFirstnameTextbox("");
	
		
		//Step 5: Enter to LastnameTextbox
		registerPage.enterToLastnameTextbox("");
		
		
		//Step 6: Enter to EmailTextbox
		registerPage.enterToEmailTextbox("");
		
		
		//Step 7: Enter to PasswordTextbox
		registerPage.enterToPasswordTextbox("");
		
		
		//Step 8: Enter to ConfirmPasswordTextbox
		registerPage.enterToConfirmPasswordTextbox("");
		
		
		//Step 9: Click to Register button
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getEmptyFirstNameErrorMsg(), "First name is required.");
		Assert.assertEquals(registerPage.getEmptyLastNameErrorMsg(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmptyEmailErrorMsg(), "Email is required.");
		Assert.assertEquals(registerPage.getEmptyPasswordErrorMsg(), "Password is required.");
		Assert.assertEquals(registerPage.getEmptyConfirmPasswordErrorMsg(), "Password is required.");
	
	}
	
	@Test
	public void Register_02_Invalid_Email() {
		// Step 1: HomePage -> click to Register link
		registerPage.refreshCurrentPage(driver);
		
		//Step 3: Click to Gender Male radio button
		registerPage.clickToGenderMaleRadioButton();
		
		//Step 4: Enter to FirstnameTextbox
		registerPage.enterToFirstnameTextbox(firstName);
		
		//Step 5: Enter to LastnameTextbox
		registerPage.enterToLastnameTextbox(lastName);
		
		//Step 6: Enter to EmailTextbox
		registerPage.enterToEmailTextbox("12@nn");
		
		//Step 7: Enter to PasswordTextbox
		registerPage.enterToPasswordTextbox(password);
		
		//Step 8: Enter to ConfirmPasswordTextbox
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		
		//Step 9: Click to Register button
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getInvalidEmailErrorMsg(), "Wrong email");
		
		
	}
	
	@Test
	public void Register_03_Email_Exist() {
		// Step 1: HomePage -> click to Register link
		registerPage.refreshCurrentPage(driver);
		
		//Step 2: RegisterPage
		registerPage = new RegisterPageObject(driver);
		
		//Step 3: Click to Gender Male radio button
		registerPage.clickToGenderMaleRadioButton();
		
		//Step 4: Enter to FirstnameTextbox
		registerPage.enterToFirstnameTextbox(firstName);
		
		//Step 5: Enter to LastnameTextbox
		registerPage.enterToLastnameTextbox(lastName);
		
		//Step 6: Enter to EmailTextbox
		registerPage.enterToEmailTextbox("hp95@mail.com");

		//Step 7: Enter to PasswordTextbox
		registerPage.enterToPasswordTextbox(password);
		
		//Step 8: Enter to ConfirmPasswordTextbox
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		
		//Step 9: Click to Register button
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getEmailExistErrorMsg(), "The specified email already exists");
		
		
		
	}
	
	@Test
	public void Register_04_Password_Less_Than_Six_Characters() {
		// Step 1: HomePage -> click to Register link
		registerPage.refreshCurrentPage(driver);

		// Step 2: RegisterPage
		registerPage = new RegisterPageObject(driver);

		// Step 3: Click to Gender Male radio button
		registerPage.clickToGenderMaleRadioButton();

		// Step 4: Enter to FirstnameTextbox
		registerPage.enterToFirstnameTextbox(firstName);

		// Step 5: Enter to LastnameTextbox
		registerPage.enterToLastnameTextbox(lastName);

		// Step 6: Enter to EmailTextbox
		registerPage.enterToEmailTextbox("hp95@mail.com");
		

		// Step 7: Enter to PasswordTextbox
		registerPage.enterToPasswordTextbox("123");
		

		// Step 8: Enter to ConfirmPasswordTextbox
		registerPage.enterToConfirmPasswordTextbox("123");

		// Step 9: Click to Register button
		registerPage.clickToRegisterButton();
		
		Assert.assertTrue(registerPage.isPasswordLessThanSixErrorMsgDisplayed());
		//span[@class='field-validation-error' and contains(.,'Password must meet the following rules: must have at least 6 characters')]
	}
	
	
	@Test
	public void Register_05_Password_Not_Match_ConfimPassword() {
		// Step 1: HomePage -> click to Register link
		registerPage.refreshCurrentPage(driver);

		// Step 2: RegisterPage
		registerPage = new RegisterPageObject(driver);

		// Step 3: Click to Gender Male radio button
		registerPage.clickToGenderMaleRadioButton();

		// Step 4: Enter to FirstnameTextbox
		registerPage.enterToFirstnameTextbox(firstName);

		// Step 5: Enter to LastnameTextbox
		registerPage.enterToLastnameTextbox(lastName);

		// Step 6: Enter to EmailTextbox
		registerPage.enterToEmailTextbox("hp95@mail.com");

		// Step 7: Enter to PasswordTextbox
		registerPage.enterToPasswordTextbox("123456");

		// Step 8: Enter to ConfirmPasswordTextbox
		registerPage.enterToConfirmPasswordTextbox("123457");

		// Step 9: Click to Register button
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getPassNotMatchConfirmPassErrorMsg(), "The specified email already exists");
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
