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
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;

public class Login_To_Page extends BaseTest {
	WebDriver driver;
	String email, password, firstName, lastName, confirmPassword;
	String homePageUrl;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		driver = getBrowser(browserName, urlNopCommerce);
		firstName = "Cristiano";
		lastName = "Ronaldo";
		email = "cr7_" + generateEmail();
		password = "123456";
		confirmPassword = password;
	}

	@Test
	public void Login_01_Empty_Data() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePageUrl = homePage.getCurrentPageUrl(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		
		loginPage = homePage.clickToLoginLink();
		loginPage.enterToEmailTextbox("");
		loginPage.enterToPasswordTextbox("");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage.enterToEmailTextbox("123@");
		loginPage.enterToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Wrong email");

	}

	@Test
	public void Login_03_Unregisted_Email() {
		loginPage.enterToEmailTextbox("lucifer@hgmail.com");
		loginPage.enterToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		Assert.assertTrue(loginPage.isUnregEmailEmptyWrongPassErrorMsgDisplayed());

	}

	@Test
	public void Login_04_Registed_Email_Empty_Password() {
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox("");
		loginPage.clickToLoginButton();

		Assert.assertTrue(loginPage.isUnregEmailEmptyWrongPassErrorMsgDisplayed());
	}

	@Test
	public void Login_05_Registed_Email_Wrong_Password() {
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox("123457");
		loginPage.clickToLoginButton();

		Assert.assertTrue(loginPage.isUnregEmailEmptyWrongPassErrorMsgDisplayed());
	}

	@Test
	public void Login_05_Register_With_All_Info_Correct() {

		loginPage.openPageUrl(driver, homePageUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox(email);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void Login_06_Correct_Email_Password() {
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		loginPage = homePage.clickToLoginLink();
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		loginPage.sleepInSecond(1);
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
