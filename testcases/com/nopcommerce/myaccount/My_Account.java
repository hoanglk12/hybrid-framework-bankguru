package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.MyAccountPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;

public class My_Account extends BaseTest {
	WebDriver driver;
	String email, password, firstName, lastName , confirmPassword, 
	editFirstName, editLastName, editFullName, editEmail, newPassword, newConfirmPassword,
	dayItem, monthItem, yearItem, editCompanyName;


	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		driver = getBrowser(browserName, urlNopCommerce);
		email = "mufc_" + generateEmail();
		firstName = "Cristiano";
		lastName = "Ronaldo";
		password = "123456";
		confirmPassword = password;
		editFirstName = "Automation";
		editLastName = "FC";
		editFullName = editFirstName + editLastName;
		newPassword = "123457";
		newConfirmPassword = newPassword;
		dayItem = "1";
		monthItem = "January";
		yearItem = "1999";
		editEmail = "automationfc.vn" + generateEmail();
		editCompanyName = "Automation FC";
	}
	@Test
	public void My_Account_00_0_Register_With_All_Info_Correct() {
		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToFirstnameTextbox(firstName);
		registerPage.enterToLastnameTextbox(lastName);
		registerPage.enterToEmailTextbox(email);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmPasswordTextbox(confirmPassword);
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void My_Account_00_1_Correct_Email_Password() {
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		loginPage = homePage.clickToLoginLink();
		loginPage.enterToEmailTextbox(email);
		loginPage.enterToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
	}

	@Test
	public void My_Account_01_Update_Customer_Info() {
		myAccountPage = homePage.openMyAccountPage(driver);
		myAccountPage.clickToGenderFemaleRadioButton();
		myAccountPage.enterToFirstnameTextbox(editFirstName);
		myAccountPage.enterToLastnameTextbox(editLastName);
		myAccountPage.selectDayFromDayDropdown(dayItem);
		myAccountPage.selectMonthFromMonthDropdown(monthItem);
		myAccountPage.selectYearFromYearDropdown(yearItem);
		myAccountPage.enterToEmailTextbox(editEmail);
		myAccountPage.enterToCompanyName(editCompanyName);
		myAccountPage.clickToSaveButton();
		
		Assert.assertTrue(myAccountPage.isGenderFemaleRadioSelected());
		Assert.assertEquals(myAccountPage.getValueFirstNameTextbox(), editFirstName);
		Assert.assertEquals(myAccountPage.getValueLastNameTexbox(), editLastName);
		Assert.assertEquals(myAccountPage.getSelectedItemFromDayDropdown(), dayItem);
		Assert.assertEquals(myAccountPage.getSeletecItemFromMonthDropdown(), monthItem);
		Assert.assertEquals(myAccountPage.getSelectedItemFromYearDropdown(), yearItem);
		Assert.assertEquals(myAccountPage.getValueCompanyTextbox(), editCompanyName);
	}
	@Test
	public void My_Account_02_Add_Addresses() {
		
	}
	@Test
	public void My_Account_03_Change_Info() {
		
	}
	@Test
	public void My_Account_04_Add_Review() {
		
	}

	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MyAccountPageObject myAccountPage;
}
