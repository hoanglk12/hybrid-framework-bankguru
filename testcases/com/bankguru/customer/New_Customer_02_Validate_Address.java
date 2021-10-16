package com.bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.data.Customer.New_Customer_02;
import com.bankguru.login.Login_01_Register_And_Login;

import commons.BaseTestThreadLocal;
import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.NewCustomerPageObject;
import pageObjects.bankGuru.PageGeneratorManager;
import pageObjects.bankGuru.RegisterPageObject;

public class New_Customer_02_Validate_Address extends BaseTestThreadLocal {
	WebDriver driver;
	@Parameters({"browser","urlBankGuru"})
	@BeforeClass
	public void initBrowser(String browser, String urlBankGuru) {
		driver = getBrowserDriver(browser, urlBankGuru);
		System.out.println("Driver at Test Class : " + driver.toString());
		newCustomerData02 = New_Customer_02.getNewCustomer02();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToTextBoxByTextTagAndName(driver, Login_01_Register_And_Login.userID, "UserID", "input", "uid");
		loginPage.enterToTextBoxByTextTagAndName(driver, Login_01_Register_And_Login.password, "Password", "input", "password");
		loginPage.clickToButtonByNameAttribute(driver, "btnLogin");
		
	}
	@Test
	public void  New_Customer_02_Validate_Address_05_Address_Not_Empty() {
		log.info("New_Customer_02_Validate_Address_05 - Step 1 - Verify Welcome message is displayed at Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
		String welcomeMessage = "Welcome To Manager's Page of Guru99 Bank";
		verifyEquals(homePage.getWelcomeTextMessage(), welcomeMessage);
		
		log.info("New_Customer_02_Validate_Address_05 - Step 2 - Click on menu 'New Customer' >>> Navigate to New Customer Page");
		homePage.clickToLinkText(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getCustomerPage(driver);
		
		log.info("New_Customer_02_Validate_Address_05 - Step 3 - Leave blank at Address Textbox");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, newCustomerData02.ADDRESS_BLANK, "Address", "textarea", "addr");
		
		log.info("New_Customer_02_Validate_Address_05 - Step 4 - Press Tab to move to next field");
		newCustomerPage.pressKeyboardToElementByTextTagAndName(driver, Keys.TAB, "Address", "textarea", "addr");
		newCustomerPage.sleepInSecond(1);
		
		log.info("New_Customer_02_Validate_Address_05 - Step 5 - Verify error message is displayed with content '" + newCustomerData02.ERROR_MSG_ADDRESS_BLANK + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessageByField("Address"), newCustomerData02.ERROR_MSG_ADDRESS_BLANK);
		
	}
	@Test
	public void  New_Customer_02_Validate_Address_06_Address_Not_First_Character_Blank() {
		log.info("New_Customer_02_Validate_Address_06 - Step 1 - Refresh New Customer Page");
		newCustomerPage.refreshCurrentPage(driver);
		
		log.info("New_Customer_02_Validate_Address_06 - Step 2 - Enter to Customer Name textbox with data '" + newCustomerData02.ADDRESS_FIRST_CHAR_BLANK + "'");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, newCustomerData02.ADDRESS_FIRST_CHAR_BLANK, "Address", "textarea", "addr");
		
		log.info("New_Customer_02_Validate_Address_06 - Step 3 - Verify error message is displayed with content '" + newCustomerData02.ERROR_MSG_ADDRESS_FIRST_CHAR_BLANK + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessageByField("Address"), newCustomerData02.ERROR_MSG_ADDRESS_FIRST_CHAR_BLANK);
		
	}

	
	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void closeBrowser(String browserName) {
		log.info("Post-Condition - Close browser '" + browserName + "'");
		removeDriver();
	}
	
	public HomePageObject homePage;
	public LoginPageObject loginPage;
	public RegisterPageObject registerPage;
	public NewCustomerPageObject newCustomerPage;
	New_Customer_02 newCustomerData02;

}