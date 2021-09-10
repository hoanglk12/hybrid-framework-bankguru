package com.bankguru.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.PageGeneratorManager;
import pageObjects.bankGuru.RegisterPageObject;

public class Login_01_Register_And_Login extends BaseTest {
	WebDriver driver;
	String  name, dob, address, city, state, pin, phone, customerID ;
	public static String userID, password, loginPageUrl, email;
	
	@Parameters({"browser","urlBankGuru"})
	@BeforeTest
	public void initBrowser(String browser, String urlBankGuru) {
		driver = getBrowser(browser, urlBankGuru);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPageUrl = loginPage.getCurrentPageUrl(driver);
		email = "johnwick_" + generateEmail();
		loginPage.clickToLinkText(driver, "here");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.enterToTextBoxByTextTagAndName(driver, email , "Email ID", "input", "emailid");
		registerPage.clickToButtonByNameAttribute(driver, "btnLogin");
		userID = registerPage.getTextUserID();
		password = registerPage.getTextPassword();
		closeDriverInstance();
	}

	
	
	public HomePageObject homePage;
	public LoginPageObject loginPage;
	public RegisterPageObject registerPage;
}
