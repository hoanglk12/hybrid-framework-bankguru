package com.bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.login.Login_01_Register_And_Login;

import commons.BaseTest;
import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.NewCustomerPageObject;
import pageObjects.bankGuru.PageGeneratorManager;
import pageObjects.bankGuru.RegisterPageObject;

public class New_Customer_01_Validate_Name extends BaseTest {
	WebDriver driver;
	String  name, dob, address, city, state, pin, phone, customerID, welcomeMessage,
	blankCustomerName;
	
	
	@Parameters({"browser","urlBankGuru"})
	@BeforeClass
	public void initBrowser(String browser, String urlBankGuru) {
		driver = getBrowser(browser, urlBankGuru);
		welcomeMessage = "Welcome To Manager's Page of Guru99 Bank";
		blankCustomerName = "";
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToTextBoxByTextTagAndName(driver, Login_01_Register_And_Login.userID, "UserID", "input", "uid");
		loginPage.enterToTextBoxByTextTagAndName(driver, Login_01_Register_And_Login.password, "Password", "input", "password");
		loginPage.clickToButtonByNameAttribute(driver, "btnLogin");
		
	}
	@Test
	public void  New_Customer_01_Validate_Name_01_Not_Empty() {
		
		log.info("New_Customer_01_Validate_Name_01 - Step 1 - Verify Welcome message is displayed at Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyEquals(homePage.getWelcomeTextMessage(), welcomeMessage);
		
		log.info("New_Customer_01_Validate_Name_01 - Step 2 - Click on menu 'New Customer' >>> Navigate to New Customer Page");
		homePage.clickToLinkText(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getCustomerPage(driver);
		
		log.info("New_Customer_01_Validate_Name_01 - Step 3 - Leave blank at Customer Textbox");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, blankCustomerName, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_01 - Step 4 - Press Tab to move to next field");
		newCustomerPage.pressKeyboardToElementByTextTagAndName(driver, Keys.TAB, "Customer Name", "input", "name");
		
		
		
	}
	@Test
	public void  New_Customer_01_Validate_Name_02_Not_Numeric() {
		
	}
	@Test
	public void  New_Customer_01_Validate_Name_03_Not_Special_Characters() {
	
	}
	@Test
	public void  New_Customer_01_Validate_Name_04_Not_First_Character_Blank() {
		
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
	public NewCustomerPageObject newCustomerPage;
}
