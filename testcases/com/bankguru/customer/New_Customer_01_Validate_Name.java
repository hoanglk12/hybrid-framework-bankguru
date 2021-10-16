package com.bankguru.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.data.Customer.New_Customer_01;
import com.bankguru.login.Login_01_Register_And_Login;

import commons.BaseTestThreadLocal;
import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.NewCustomerPageObject;
import pageObjects.bankGuru.PageGeneratorManager;
import pageObjects.bankGuru.RegisterPageObject;

public class New_Customer_01_Validate_Name extends BaseTestThreadLocal {
	WebDriver driver;
	@Parameters({"browser","urlBankGuru"})
	@BeforeClass
	public void initBrowser(String browser, String urlBankGuru) {
		driver = getBrowserDriver(browser, urlBankGuru);
		System.out.println("Driver at Test Class : " + driver.toString());
		newCustomerData01 = New_Customer_01.getNewCustomer01();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToTextBoxByTextTagAndName(driver, Login_01_Register_And_Login.userID, "UserID", "input", "uid");
		loginPage.enterToTextBoxByTextTagAndName(driver, Login_01_Register_And_Login.password, "Password", "input", "password");
		loginPage.clickToButtonByNameAttribute(driver, "btnLogin");
		
	}
	@Test
	public void  New_Customer_01_Validate_Name_01_Not_Empty() {
		log.info("New_Customer_01_Validate_Name_01 - Step 1 - Verify Welcome message is displayed at Home Page");
		homePage = PageGeneratorManager.getHomePage(driver);
	
		String welcomeMessage = "Welcome To Manager's Page of Guru99 Bank";
		verifyEquals(homePage.getWelcomeTextMessage(), welcomeMessage);
		
		log.info("New_Customer_01_Validate_Name_01 - Step 2 - Click on menu 'New Customer' >>> Navigate to New Customer Page");
		homePage.clickToLinkText(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getCustomerPage(driver);
		
		log.info("New_Customer_01_Validate_Name_01 - Step 3 - Leave blank at Customer Textbox");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, newCustomerData01.NAME_BLANK, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_01 - Step 4 - Press Tab to move to next field");
		newCustomerPage.pressKeyboardToElementByTextTagAndName(driver, Keys.TAB, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_01 - Step 5 - Verify error message is displayed with content '" + newCustomerData01.ERROR_MSG_NAME_BLANK + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessageByField("Customer Name"), newCustomerData01.ERROR_MSG_NAME_BLANK);
		
	}
	@Test
	public void  New_Customer_01_Validate_Name_02_Not_Numeric() {
		log.info("New_Customer_01_Validate_Name_02 - Step 1 - Refresh New Customer Page");
		newCustomerPage.refreshCurrentPage(driver);
		
		log.info("New_Customer_01_Validate_Name_02 - Step 2 - Enter to Customer Name textbox with data '" + newCustomerData01.NAME_NUMERIC + "'");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, newCustomerData01.NAME_NUMERIC, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_02 - Step 3 - Verify error message is displayed with content '" + newCustomerData01.ERROR_MSG_NAME_NOT_NUMERIC + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessageByField("Customer Name"), newCustomerData01.ERROR_MSG_NAME_NOT_NUMERIC);
	}
	@Test
	public void  New_Customer_01_Validate_Name_03_Not_Special_Characters() {
		log.info("New_Customer_01_Validate_Name_02 - Step 1 - Refresh New Customer Page");
		newCustomerPage.refreshCurrentPage(driver);
		
		log.info("New_Customer_01_Validate_Name_02 - Step 2 - Enter to Customer Name textbox with data '" + newCustomerData01.NAME_SPECIAL_CHAR + "'");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, newCustomerData01.NAME_SPECIAL_CHAR, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_02 - Step 3 - Verify error message is displayed with content '" + newCustomerData01.ERROR_MSG_NOT_SPECIAL_CHAR + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessageByField("Customer Name"), newCustomerData01.ERROR_MSG_NOT_SPECIAL_CHAR);
	
	}
	@Test
	public void  New_Customer_01_Validate_Name_04_Not_First_Character_Blank() {
		log.info("New_Customer_01_Validate_Name_02 - Step 1 - Refresh New Customer Page");
		newCustomerPage.refreshCurrentPage(driver);
		
		log.info("New_Customer_01_Validate_Name_02 - Step 2 - Enter to Customer Name textbox with data '" + newCustomerData01.NAME_FIRST_CHAR_BLANK + "'");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, newCustomerData01.NAME_FIRST_CHAR_BLANK, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_02 - Step 3 - Verify error message is displayed with content '" + newCustomerData01.ERROR_MSG_FIRST_CHAR_BLANK + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessageByField("Customer Name"), newCustomerData01.ERROR_MSG_FIRST_CHAR_BLANK);
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
	New_Customer_01 newCustomerData01;

}