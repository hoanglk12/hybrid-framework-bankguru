package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;

public class Common_Login extends BaseTest {
	WebDriver driver;
	String  confirmPassword;

	public static String firstName, lastName, email, password, homePageUrl;
	public static Set<Cookie> loginPageCookie;
	@Parameters({ "browser", "url" })
	@BeforeTest
	public void initBrowser(String browserName, String urlNopCommerce) {
		driver = getBrowser(browserName, urlNopCommerce);
		email = "cr7_" + generateEmail();
		password = "123456";		
		confirmPassword = password;
		firstName = "Cristiano";
		lastName = "Ronaldo";
		
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
		loginPage = homePage.clickToLoginLink();
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		loginPageCookie = registerPage.getAllCookies(driver);
		
		closeDriverInstance();
	
	}


	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;

}
