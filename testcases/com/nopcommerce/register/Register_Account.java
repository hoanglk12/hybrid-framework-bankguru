package com.nopcommerce.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
		driver = getBrowser(browserName, urlNopCommerce);
		email = "cr7_" + generateEmail();
		password = "123456";		
		confirmPassword = password;
		firstName = "Cristiano";
		lastName = "Ronaldo";
		
	}
	@Test
	public void Register_00_All_Info_Correct() {
		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		homePageUrl = homePage.getCurrentPageUrl(driver);
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox(email);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();

		verifyTrue(registerPage.isSuccessMessageDisplayed());
		homePage =  registerPage.clickToLogoutLink();
	}

	@Test
	public void Register_01_Empty_Data() {
		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox("");
		registerPage.enterToLastnameTextbox("");
		registerPage.enterToEmailTextbox("");
		registerPage.enterToPasswordTextbox("");
		registerPage.enterToConfirmPasswordTextbox("");
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);

		verifyEquals(registerPage.getEmptyFirstNameErrorMsg(), "First name is required.");
		verifyEquals(registerPage.getEmptyLastNameErrorMsg(), "Last name is required.");
		verifyEquals(registerPage.getEmptyEmailErrorMsg(), "Email is required.");
		verifyEquals(registerPage.getEmptyPasswordErrorMsg(), "Password is required.");
		verifyEquals(registerPage.getEmptyConfirmPasswordErrorMsg(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox("12@nn");
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);

		verifyEquals(registerPage.getInvalidEmailErrorMsg(), "Wrong email");

	}

	@Test
	public void Register_03_Email_Exist() {
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox(email);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);

		verifyEquals(registerPage.getEmailExistErrorMsg(), "The specified email already exists");
	}

	@Test
	public void Register_04_Password_Less_Than_Six_Characters() {
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox("hp95@mail.com");
		registerPage.enterToPasswordTextbox("123");
		registerPage.enterToConfirmPasswordTextbox("123");
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);

		verifyTrue(registerPage.isPasswordLessThanSixErrorMsgDisplayed());
	}

	@Test
	public void Register_05_Password_Not_Match_ConfimPassword() {
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox("hp95@mail.com");
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123457");
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);

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
