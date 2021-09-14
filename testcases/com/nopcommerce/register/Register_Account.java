package com.nopcommerce.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_Login;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;

public class Register_Account extends BaseTest {
	WebDriver driver;
	String firstName, lastName, confirmPassword;

	public static String email, password, homePageUrl;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		log.info("Pre-Condition - Open browser '" + browserName + "' with url: '" + urlNopCommerce + "'");
		driver = getBrowser(browserName, urlNopCommerce);
		email = "cr7_" + generateEmail();
		password = "123456";		
		confirmPassword = password;
		firstName = "Cristiano";
		lastName = "Ronaldo";
		
	}
	@Test
	public void Register_00_All_Info_Correct() {
		log.info(" Register_00 - Step 1: Verify Home Page Slider is displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		
		log.info(" Register_00 - Step 2: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info(" Register_00 - Step 3: Check to Gender Male radio button");
		registerPage.clickToGenderMaleRadioButton();
		
		log.info(" Register_00 - Step 4: Enter to First Name textbox with data '" + firstName + "'");
		registerPage.enterToFirstnameTextbox(firstName);
		
		log.info(" Register_00 - Step 5: Enter to Last Name textbox with data '" + lastName + "'");
		registerPage.enterToLastnameTextbox(lastName);
		
		log.info(" Register_00 - Step 6: Enter to Email textbox with data '" + email + "'");
		registerPage.enterToEmailTextbox(email);
		
		log.info(" Register_00 - Step 7: Enter to Password textbox with data '" + password + "'");
		registerPage.enterToPasswordTextbox(password);
		
		log.info(" Register_00 - Step 8: Enter to Confirm Password textbox with data '" + confirmPassword + "'");
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		
		log.info(" Register_00 - Step 9: Click To Register Button");
		registerPage.clickToRegisterButton();
		
		log.info(" Register_00 - Step 10: Verify Success Message is displayed with content 'Your registration completed'");
		verifyTrue(registerPage.isSuccessMessageDisplayed());
		
		log.info(" Register_00 - Step 11: Click To Logout Link");
		homePage =  registerPage.clickToLogoutLink();
	}

	@Test
	public void Register_01_Empty_Data() {
		log.info(" Register_01 - Step 1: Verify Home Page Slider is displayed");
		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		
		log.info(" Register_01 - Step 2: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info(" Register_01 - Step 3: Check to Gender Male radio button");
		registerPage.clickToGenderMaleRadioButton();
		
		log.info(" Register_01 - Step 4: Enter to First Name textbox with blank data");
		registerPage.enterToFirstnameTextbox("");
		
		log.info(" Register_01 - Step 5: Enter to Last Name textbox with blank data");
		registerPage.enterToLastnameTextbox("");
		
		log.info(" Register_01 - Step 6: Enter to Email textbox with blank data");
		registerPage.enterToEmailTextbox("");
		
		log.info(" Register_01 - Step 6: Enter to Password textbox with blank data");
		registerPage.enterToPasswordTextbox("");
		
		log.info(" Register_01 - Step 7: Enter to Confirm Password textbox with blank data");
		registerPage.enterToConfirmPasswordTextbox("");
		
		log.info(" Register_01 - Step 8: Click To Register Button");
		registerPage.clickToRegisterButton();
	
		
		log.info(" Register_01 - Step 9: Verify error messages displayed on 5 texboxes");
		verifyEquals(registerPage.getEmptyFirstNameErrorMsg(), "First name is required.");
		verifyEquals(registerPage.getEmptyLastNameErrorMsg(), "Last name is required.");
		verifyEquals(registerPage.getEmptyEmailErrorMsg(), "Email is required.");
		verifyEquals(registerPage.getEmptyPasswordErrorMsg(), "Password is required.");
		verifyEquals(registerPage.getEmptyConfirmPasswordErrorMsg(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		log.info(" Register_02 - Step 1: Check to Gender Male radio button");
		registerPage.clickToGenderMaleRadioButton();
		
		log.info(" Register_02 - Step 2: Enter to First Name textbox with data '" + firstName + "'");
		registerPage.enterToFirstnameTextbox(firstName);
		
		log.info(" Register_02 - Step 3: Enter to Last Name textbox with data '" + lastName + "'");
		registerPage.enterToLastnameTextbox(lastName);
		
		log.info(" Register_02 - Step 4: Enter to Email textbox with data '12@nn'");
		registerPage.enterToEmailTextbox("12@nn");
		
		log.info(" Register_02 - Step 5: Enter to Password textbox with data '" + password + "'");
		registerPage.enterToPasswordTextbox(password);
		
		log.info(" Register_02 - Step 6: Enter to Confirm Password textbox with data '" + confirmPassword + "'");
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		
		log.info(" Register_02 - Step 7: Click To Register Button");
		registerPage.clickToRegisterButton();
		
		
		log.info(" Register_02 - Step 8: Verify error message is displayed with content 'Wrong email'");
		verifyEquals(registerPage.getInvalidEmailErrorMsg(), "Wrong email");

	}

	@Test
	public void Register_03_Email_Exist() {
		log.info(" Register_03 - Step 1: Check to Gender Male radio button");
		registerPage.clickToGenderMaleRadioButton();
		
		log.info(" Register_03 - Step 2: Enter to First Name textbox with data '" + firstName + "'");
		registerPage.enterToFirstnameTextbox(firstName);
		
		log.info(" Register_03 - Step 3: Enter to Last Name textbox with data '" + lastName + "'");
		registerPage.enterToLastnameTextbox(lastName);
		
		log.info(" Register_03 - Step 4: Enter to Email textbox with data '" + Common_Login.email + "'");
		registerPage.enterToEmailTextbox(Common_Login.email);
		
		log.info(" Register_03 - Step 5: Enter to Password textbox with data '" + password + "'");
		registerPage.enterToPasswordTextbox(password);
		
		log.info(" Register_03 - Step 6: Enter to Password textbox with data '" + confirmPassword + "'");
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		
		log.info(" Register_03 - Step 7: Click To Register Button");
		registerPage.clickToRegisterButton();
	
		
		log.info(" Register_03 - Step 8: Verify error message is displayed with content 'The specified email already exists'");
		verifyEquals(registerPage.getEmailExistErrorMsg(), "The specified email already exists");
	}

	@Test
	public void Register_04_Password_Less_Than_Six_Characters() {
		log.info(" Register_04 - Step 1: Check to Gender Male radio button");
		registerPage.clickToGenderMaleRadioButton();
		
		log.info(" Register_04 - Step 2: Enter to First Name textbox with data '" + firstName + "'");
		registerPage.enterToFirstnameTextbox(firstName);
		
		log.info(" Register_04 - Step 3: Enter to Last Name textbox with data '" + lastName + "'");
		registerPage.enterToLastnameTextbox(lastName);
		
		log.info(" Register_04 - Step 4: Enter to Email textbox with data 'hp95@mail.com'");
		registerPage.enterToEmailTextbox("hp95@mail.com");
		
		log.info(" Register_04 - Step 5: Enter to Password textbox with data '123'");
		registerPage.enterToPasswordTextbox("123");
		
		log.info(" Register_04 - Step 6: Enter to Confirm Password textbox with data '123'");
		registerPage.enterToConfirmPasswordTextbox("123");
		
		log.info(" Register_04 - Step 7: Click To Register Button");
		registerPage.clickToRegisterButton();
		
		
		log.info(" Register_04 - Step 8: Verify error message is displayed with content 'Password must meet the following rules: must have at least 6 characters'");
		verifyTrue(registerPage.isPasswordLessThanSixErrorMsgDisplayed());
	}

	@Test
	public void Register_05_Password_Not_Match_ConfimPassword() {
		log.info(" Register_05 - Step 1: Check to Gender Male radio button");
		registerPage.clickToGenderMaleRadioButton();
		
		log.info(" Register_05 - Step 2: Enter to First Name textbox with data '" + firstName + "'");
		registerPage.enterToFirstnameTextbox(firstName);
		
		log.info(" Register_05 - Step 3: Enter to Last Name textbox with data '" + lastName + "'");
		registerPage.enterToLastnameTextbox(lastName);
		
		log.info(" Register_05 - Step 4: Enter to Email textbox with data 'hp95@mail.com'");
		registerPage.enterToEmailTextbox("hp95@mail.com");
		
		log.info(" Register_05 - Step 5: Enter to Password textbox with data '123457'");
		registerPage.enterToPasswordTextbox("123456");
		
		log.info(" Register_05 - Step 6: Enter to Confirm Password textbox with data '123457'");
		registerPage.enterToConfirmPasswordTextbox("123457");
		
		log.info(" Register_05 - Step 7: Click To Register Button");
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);
		
		log.info(" Register_05 - Step 8: Verify error message is displayed with content 'The password and confirmation password do not match.'");
		verifyEquals(registerPage.getPassNotMatchConfirmPassErrorMsg(), "The password and confirmation password do not match.");
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void closeBrowser(String browserName) {
		log.info("Post-Condition - Close browser '" + browserName + "'");
		closeBrowserAndDriver();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;

}
