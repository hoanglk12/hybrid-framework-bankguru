package com.nopcommerce.login;

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

public class Login_To_Page extends BaseTest {
	WebDriver driver;
	String email, password, firstName, lastName, confirmPassword;
	String homePageUrl;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		driver = getBrowser(browserName, urlNopCommerce);
		email = generateEmail();
		firstName = "Cristiano";
		lastName = "Ronaldo";
		email = generateEmail();
		password = "123456";
		confirmPassword = password;
	}

	@Test
	public void Login_01_Empty_Data() {
		homePage = new HomePageObject(driver);
		homePageUrl = homePage.getCurrentPageUrl(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		homePage.clickToLoginLink();

		loginPage = new LoginPageObject(driver);
		loginPage.enterToEmailTextbox("");
		loginPage.enterToPasswordTextbox("");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {

		loginPage.refreshCurrentPage(driver);

		loginPage = new LoginPageObject(driver);
		loginPage.enterToEmailTextbox("123@");
		loginPage.enterToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Wrong email");

	}

	@Test
	public void Login_03_Unregisted_Email() {

		loginPage.refreshCurrentPage(driver);

		loginPage = new LoginPageObject(driver);
		loginPage.enterToEmailTextbox("lucifer@hgmail.com");
		loginPage.enterToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		Assert.assertTrue(loginPage.isUnregEmailEmptyWrongPassErrorMsgDisplayed());

	}

	@Test
	public void Login_04_Registed_Email_Empty_Password() {

		loginPage.refreshCurrentPage(driver);

		loginPage.handleUnexpectedAlert(driver);
		loginPage = new LoginPageObject(driver);
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox("");
		loginPage.clickToLoginButton();

		Assert.assertTrue(loginPage.isUnregEmailEmptyWrongPassErrorMsgDisplayed());
	}

	@Test
	public void Login_05_Registed_Email_Wrong_Password() {

		loginPage.refreshCurrentPage(driver);
		loginPage.handleUnexpectedAlert(driver);
		loginPage.handleUnexpectedAlert(driver);
		loginPage = new LoginPageObject(driver);
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox("123457");
		loginPage.clickToLoginButton();

		Assert.assertTrue(loginPage.isUnregEmailEmptyWrongPassErrorMsgDisplayed());
	}

	@Test
	public void Login_05_Register_With_All_Info_Correct() {

		loginPage.openPageUrl(driver, homePageUrl);
		loginPage.handleUnexpectedAlert(driver);
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox(email);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
		registerPage.clickToLogoutLink();
	}

	@Test
	public void Login_06_Correct_Email_Password() {

		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		homePage.clickToLoginLink();

		loginPage = new LoginPageObject(driver);
		loginPage.handleUnexpectedAlert(driver);
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		loginPage.sleepInSecond(2);

		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;

}
