package com.nopcommerce.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;

public class Register_Account extends BaseTest {
	WebDriver driver;
	String firstName, lastName, confirmPassword, browserRunning;

	public String email = generateEmail();
	public String password = "123456";

	@Parameters({ "browser", "url" })
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
		homePage = PageGeneratorManager.getHomePage(driver);
		//homePage = new HomePageObject(driver);
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

		Assert.assertEquals(registerPage.getEmptyFirstNameErrorMsg(), "First name is required.");
		Assert.assertEquals(registerPage.getEmptyLastNameErrorMsg(), "Last name is required.");
		Assert.assertEquals(registerPage.getEmptyEmailErrorMsg(), "Email is required.");
		Assert.assertEquals(registerPage.getEmptyPasswordErrorMsg(), "Password is required.");
		Assert.assertEquals(registerPage.getEmptyConfirmPasswordErrorMsg(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		//registerPage.refreshCurrentPage(driver);

		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox("12@nn");
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);

		Assert.assertEquals(registerPage.getInvalidEmailErrorMsg(), "Wrong email");

	}

	@Test
	public void Register_03_Email_Exist() {
		//registerPage.refreshCurrentPage(driver);
		
		//registerPage.handleUnexpectedAlert(driver);
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox("hy@mail.com");
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);

		Assert.assertEquals(registerPage.getEmailExistErrorMsg(), "The specified email already exists");
	}

	@Test
	public void Register_04_Password_Less_Than_Six_Characters() {

		//registerPage.refreshCurrentPage(driver);
		//registerPage.handleUnexpectedAlert(driver);
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox("hp95@mail.com");
		registerPage.enterToPasswordTextbox("123");
		registerPage.enterToConfirmPasswordTextbox("123");
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);

		Assert.assertTrue(registerPage.isPasswordLessThanSixErrorMsgDisplayed());
	}

	@Test
	public void Register_05_Password_Not_Match_ConfimPassword() {

		//registerPage.refreshCurrentPage(driver);
		registerPage.handleUnexpectedAlert(driver);
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox("hp95@mail.com");
		registerPage.enterToPasswordTextbox("123456");
		registerPage.enterToConfirmPasswordTextbox("123457");
		registerPage.clickToRegisterButton();
		registerPage.sleepInSecond(1);

		Assert.assertEquals(registerPage.getPassNotMatchConfirmPassErrorMsg(), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_06_All_Info_Correct() {
		//registerPage.refreshCurrentPage(driver);
		//registerPage.handleUnexpectedAlert(driver);
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox(email);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();

		Assert.assertTrue(registerPage.isSuccessMessageDisplayed());

	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;

}
