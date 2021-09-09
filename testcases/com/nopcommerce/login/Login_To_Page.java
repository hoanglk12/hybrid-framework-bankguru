package com.nopcommerce.login;

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

public class Login_To_Page extends BaseTest {
	WebDriver driver;
	String email, password ,confirmPassword;
	String homePageUrl;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		log.info("Pre-Condition - Open browser '" + browserName + "' with url: '" + urlNopCommerce + "'");
		driver = getBrowser(browserName, urlNopCommerce);
		email = "cr7_" + generateEmail();
		password = "123456";
		confirmPassword = password;
	}
	
	

	@Test
	public void Login_01_Empty_Data() {
		log.info("Login_01 - Step 1: open Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePageUrl = homePage.getCurrentPageUrl(driver);
		
		log.info("Login_01 - Step 2: Verify Slider is displayed");
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		
		log.info("Login_01 - Step 3: Click 'Login' link >>> Navigate to 'Login' Page");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login_01 - Step 4: Leave blank in Email textbox");
		loginPage.enterToEmailTextbox("");
		
		log.info("Login_01 - Step 5: Leave blank in Password textbox");
		loginPage.enterToPasswordTextbox("");
		
		log.info("Login_01 - Step 6: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login_01 - Step 7: Verify error msg 'Please enter your email' is displayed");
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		log.info("Login_02 - Step 1: Enter to Email textbox with data '123@'");
		loginPage.enterToEmailTextbox("123@");
		
		log.info("Login_02 - Step 2: Enter to Email textbox with data with data '" + password + "'");
		loginPage.enterToPasswordTextbox(password);
		
		log.info("Login_02 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login_02 - Step 4: Verify error msg 'Wrong email' is displayed");
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
	public void Login_06_Correct_Email_Password() {
		log.info("Login_01 - Step 1: open Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
		loginPage = homePage.clickToLoginLink();
		
		loginPage.enterToEmailTextbox(Common_Login.email);
		loginPage.enterToPasswordTextbox(Common_Login.password);
		homePage = loginPage.clickToLoginButton();
		loginPage.sleepInSecond(1);
		
		
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		
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
