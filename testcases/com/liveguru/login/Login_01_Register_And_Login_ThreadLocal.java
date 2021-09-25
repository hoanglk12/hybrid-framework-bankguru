package com.liveguru.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTestThreadLocal;

import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.PageGeneratorManager;

public class Login_01_Register_And_Login_ThreadLocal extends BaseTestThreadLocal {
	private WebDriver driver;
	
	String email, firstName, lastName, password, confirmPassword;
	
	@Parameters({"url","browser"})
	@BeforeClass
	public void initBrowser(String appUrl, String browserName) {
		driver = getBrowserDriver(browserName, appUrl);
		email = generateEmail();
	}

	@Test
	public void Login_00_Open_Home_Page() {
		homePage = PageGeneratorManager.getHomePage(driver);
		System.out.println("homePage instance = " + homePage.toString());
		Assert.assertTrue(homePage.isLogoPageDisplayed());
		homePage.clickToMyAccountLinkAtFooter();
	}

	@Test
	public void Login_01_Empty_Email_Password() {
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToEmailAddressTextbox("");
		loginPage.enterToPasswordTextbox("");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getEmptyEmailErrorMessage(), "This is a required field.");
		Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(), "This is a required field.");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailAddressTextbox("123@nn");
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getInvalidEmailErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void Login_03_Invalid_Password() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailAddressTextbox(email);
		loginPage.enterToPasswordTextbox("12345");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getInvalidPasswordErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void Login_04_Incorrect_Email() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailAddressTextbox("hp9@mail.com");
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getIncorrectEmailPasswordErrorMessage(), "Invalid login or password.");

	}

	@Test
	public void Login_05_Incorrect_Password() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailAddressTextbox("hp95@mail.com");
		loginPage.enterToPasswordTextbox("123457");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getIncorrectEmailPasswordErrorMessage(), "Invalid login or password.");

	}

	@Test
	public void Login_06_Correct_Email_Password() {
		loginPage.refreshCurrentPage(driver);
		loginPage.enterToEmailAddressTextbox("hp95@mail.com");
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickToLoginButton();
		myDashboardPage = PageGeneratorManager.getMyDashboardPage(driver);
		Assert.assertTrue(myDashboardPage.isMyDashboardHeaderDisplayed());

	}

	@AfterClass
	public void closeBrowser() {
		removeDriver();
		
	}



	HomePageObject homePage;
	LoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
}
