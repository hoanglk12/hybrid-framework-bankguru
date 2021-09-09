package com.bankguru.customer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.login.Login_01_Register_And_Login;

import commons.BaseTest;
import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.PageGeneratorManager;
import pageObjects.bankGuru.RegisterPageObject;

public class New_Customer extends BaseTest {
	WebDriver driver;
	String  name, dob, address, city, state, pin, phone, customerID;
	
	
	@Parameters({"browser","urlBankGuru"})
	@BeforeClass
	public void initBrowser(String browser, String urlBankGuru) {
		driver = getBrowser(browser, urlBankGuru);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToTextBoxByTextTagAndName(driver, Login_01_Register_And_Login.userID, "UserID", "input", "uid");
		loginPage.enterToTextBoxByTextTagAndName(driver, Login_01_Register_And_Login.password, "Password", "input", "password");
		loginPage.clickToButtonByNameAttribute(driver, "btnLogin");
		
	}
	@Test
	public void TC_01() {
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyEquals(homePage.getWelcomeTextMessage(),"Welcome To Manager's Page of Guru99 Bank");
	}

	
	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void closeBrowser(String browserName) {
		log.info("Post-Condition - Close browser '" + browserName + "'");
		closeBrowserAndDriver();
	}
	
	public HomePageObject homePage;
	public LoginPageObject loginPage;
	public RegisterPageObject registerPage;
}
