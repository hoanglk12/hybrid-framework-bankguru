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
import utilities.DataHelper;

public class New_Customer_01_Validate_Name extends BaseTest {
	WebDriver driver;
	String  nameBlank, nameNumeric, nameSpecialChar, nameFirstCharBlank, welcomeMessage,
	nameBlankErrorMsg, nameNotNumericErrorMsg, nameNotSpecialCharErrorMsg, nameNotFirstCharBlankErrorMsg;
	
	@Parameters({"browser","urlBankGuru"})
	@BeforeClass
	public void initBrowser(String browser, String urlBankGuru) {
		driver = getBrowser(browser, urlBankGuru);
		dataHelper = DataHelper.getDataHelper();
		welcomeMessage = "Welcome To Manager's Page of Guru99 Bank";
		nameBlank = "";
		nameNumeric = dataHelper.getFirstName() + String.valueOf(dataHelper.getRandomNumber());
		nameSpecialChar = dataHelper.getFirstName() + "%$#";
		nameFirstCharBlank = " " + dataHelper.getFirstName();
		nameBlankErrorMsg = "Customer name must not be blank";
		nameNotNumericErrorMsg = "Numbers are not allowed";
		nameNotSpecialCharErrorMsg = "Special characters are not allowed";
		nameNotFirstCharBlankErrorMsg = "First character can not have space";
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
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, nameBlank, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_01 - Step 4 - Press Tab to move to next field");
		newCustomerPage.pressKeyboardToElementByTextTagAndName(driver, Keys.TAB, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_01 - Step 5 - Verify error message is displayed with content '" + nameBlankErrorMsg + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessage(), nameBlankErrorMsg);
		
	}
	@Test
	public void  New_Customer_01_Validate_Name_02_Not_Numeric() {
		log.info("New_Customer_01_Validate_Name_02 - Step 1 - Refresh New Customer Page");
		newCustomerPage.refreshCurrentPage(driver);
		
		log.info("New_Customer_01_Validate_Name_02 - Step 2 - Enter to Customer Name textbox with data '" + nameNumeric + "'");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, nameNumeric, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_02 - Step 3 - Verify error message is displayed with content '" + nameBlankErrorMsg + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessage(), nameNotNumericErrorMsg);
	}
	@Test
	public void  New_Customer_01_Validate_Name_03_Not_Special_Characters() {
		log.info("New_Customer_01_Validate_Name_02 - Step 1 - Refresh New Customer Page");
		newCustomerPage.refreshCurrentPage(driver);
		
		log.info("New_Customer_01_Validate_Name_02 - Step 2 - Enter to Customer Name textbox with data '" + nameSpecialChar + "'");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, nameSpecialChar, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_02 - Step 3 - Verify error message is displayed with content '" + nameBlankErrorMsg + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessage(), nameNotSpecialCharErrorMsg);
	
	}
	@Test
	public void  New_Customer_01_Validate_Name_04_Not_First_Character_Blank() {
		log.info("New_Customer_01_Validate_Name_02 - Step 1 - Refresh New Customer Page");
		newCustomerPage.refreshCurrentPage(driver);
		
		log.info("New_Customer_01_Validate_Name_02 - Step 2 - Enter to Customer Name textbox with data '" + nameSpecialChar + "'");
		newCustomerPage.enterToTextboxTextareaByTextTagAndName(driver, nameFirstCharBlank, "Customer Name", "input", "name");
		
		log.info("New_Customer_01_Validate_Name_02 - Step 3 - Verify error message is displayed with content '" + nameBlankErrorMsg + "'");
		verifyEquals(newCustomerPage.getErrorValidationMessage(), nameNotFirstCharBlankErrorMsg);
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
	DataHelper dataHelper;
}
