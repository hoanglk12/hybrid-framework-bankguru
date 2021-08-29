package com.liveguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;

public class Login_01_Register_And_Login_By_PageObject {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email, firstName, lastName, password, confirmPassword;

	@BeforeClass
	public void initBrowser() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = generateEmail();
	}

	@Test
	public void Login_00_Open_Home_Page() {
		homePage = new HomePageObject(driver);
		homePage.openPageUrl(driver, "http://live.demoguru99.com/");
		Assert.assertTrue(homePage.isLogoPageDisplayed());
		homePage.clickToMyAccountLinkAtFooter();
	}

	@Test
	public void Login_01_Empty_Email_Password() {
		loginPage = new LoginPageObject(driver);
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
		myDashboardPage = new MyDashboardPageObject(driver);
		Assert.assertTrue(myDashboardPage.isMyDashboardHeaderDisplayed());

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
	MyDashboardPageObject myDashboardPage;
}
