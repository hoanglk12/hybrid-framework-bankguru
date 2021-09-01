package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.AddressesPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.MyAccountPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;
import pageObjects.nopCommerce.SearchPageObject;

public class My_Account extends BaseTest {
	WebDriver driver;
	String email, password, firstName, lastName , confirmPassword, 
	editFirstName, editLastName, editFullName, editEmail, newPassword, newConfirmPassword,
	dayItem, monthItem, yearItem, editCompanyName, addressFirstName, addressLastName, addressFullName,
	addressEmail, addressCompany, addressCountry, addressState, addressCity, addressAddress1, addressAddress2, 
	addressPostCode, addressPhone, addressFax;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		driver = getBrowser(browserName, urlNopCommerce);
		email = "mufc_" + generateEmail();
		firstName = "Cristiano";
		lastName = "Ronaldo";
		password = "123456";
		confirmPassword = password;
		addressFirstName = editFirstName = "Automation";
		addressLastName = editLastName = "FC";
		addressFullName = editFullName = editFirstName + " "+ editLastName;
		newPassword = "123457";
		newConfirmPassword = newPassword;
		dayItem = "1";
		monthItem = "January";
		yearItem = "1999";
		addressEmail = editEmail = "automationfc.vn" + generateEmail();
		editCompanyName = "Automation FC";
		addressCountry = "Viet Nam";
		addressCompany = "Automation FC";
		addressState = "Other";
		addressCity = "Da Nang";
		addressAddress1 = "123/04 Le Lai";
		addressAddress2 = "234/05 Hai Phong";
		addressPostCode = "550000"; 
		addressPhone = "0123456789";
		addressFax = "0987654321";
	}
	@Test
	public void My_Account_00_0_Register_With_All_Info_Correct() {
		homePage = PageGeneratorManager.getHomePage(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToGenderMaleRadioButton();
		registerPage.enterToTextboxByName(driver, firstName, "FirstName");
		registerPage.enterToTextboxByName(driver, lastName, "LastName");
		registerPage.enterToTextboxByName(driver, email, "Email");
		registerPage.enterToTextboxByName(driver, password, "Password");
		registerPage.enterToTextboxByName(driver, confirmPassword, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void My_Account_00_1_Correct_Email_Password() {
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		loginPage = homePage.clickToLoginLink();
		loginPage.enterToTextboxByName(driver, email, "Email");
		loginPage.enterToTextboxByName(driver, password, "Password");
		homePage = loginPage.clickToLoginButton();
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
	}

	@Test
	public void My_Account_01_Update_Customer_Info() {
		homePage.openPageFooterByName(driver,"My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
		myAccountPage.clickToGenderFemaleRadioButton();
		myAccountPage.enterToTextboxByName(driver, editFirstName, "FirstName");
		myAccountPage.enterToTextboxByName(driver, editLastName, "LastName");
		myAccountPage.selectDayFromDayDropdown(dayItem);
		myAccountPage.selectMonthFromMonthDropdown(monthItem);
		myAccountPage.selectYearFromYearDropdown(yearItem);
		myAccountPage.enterToTextboxByName(driver, editEmail, "Email");
		myAccountPage.enterToTextboxByName(driver, editCompanyName, "Company");
		myAccountPage.clickToSaveButton();
		
		Assert.assertTrue(myAccountPage.isGenderFemaleRadioSelected());
		Assert.assertEquals(myAccountPage.getAttributeValueFromTextboxByName(driver, "value", "FirstName"), editFirstName);
		Assert.assertEquals(myAccountPage.getAttributeValueFromTextboxByName(driver, "value", "LastName"), editLastName);
		Assert.assertEquals(myAccountPage.getSelectedItemFromDayDropdown(), dayItem);
		Assert.assertEquals(myAccountPage.getSeletecItemFromMonthDropdown(), monthItem);
		Assert.assertEquals(myAccountPage.getSelectedItemFromYearDropdown(), yearItem);
		Assert.assertEquals(myAccountPage.getAttributeValueFromTextboxByName(driver, "value", "Company"), editCompanyName);
	}
	@Test
	public void My_Account_02_Add_Addresses() {
		myAccountPage.openPageFooterByName(driver, "Addresses");
		addressesPage = PageGeneratorManager.getAddressesPage(driver);
		addressesPage.clickToAddNewButton();
		addressesPage.enterToAddressTextboxByName(driver, addressFirstName, "FirstName");
		addressesPage.enterToAddressTextboxByName(driver, addressLastName, "LastName");
		addressesPage.enterToAddressTextboxByName(driver, addressEmail, "Email");
		addressesPage.enterToAddressTextboxByName(driver, addressCompany, "Company");
		addressesPage.selectToCountryDropdown(addressCountry);
		addressesPage.enterToAddressTextboxByName(driver, addressCity, "City");
		addressesPage.enterToAddressTextboxByName(driver, addressAddress1, "Address1");
		addressesPage.enterToAddressTextboxByName(driver, addressAddress2, "Address2");
		addressesPage.enterToAddressTextboxByName(driver, addressPostCode, "ZipPostalCode");
		addressesPage.enterToAddressTextboxByName(driver, addressPhone, "PhoneNumber");
		addressesPage.enterToAddressTextboxByName(driver, addressFax, "FaxNumber");
		addressesPage.clickToSaveButton();
		
		Assert.assertTrue(addressesPage.isAddressInfoContains("name", addressFullName));
		Assert.assertTrue(addressesPage.isAddressInfoContains("email", addressEmail));
		Assert.assertTrue(addressesPage.isAddressInfoContains("fax", addressFax));
		Assert.assertTrue(addressesPage.isAddressInfoContains("company", addressCompany));
		Assert.assertTrue(addressesPage.isAddressInfoContains("address1", addressAddress1));
		Assert.assertTrue(addressesPage.isAddressInfoContains("address2", addressAddress2));
		Assert.assertTrue(addressesPage.isAddressInfoContains("city-state-zip", addressCity));
		Assert.assertTrue(addressesPage.isAddressInfoContains("city-state-zip", addressPostCode));
		Assert.assertTrue(addressesPage.isAddressInfoContains("country", addressCountry));
		
	}
	@Test
	public void My_Account_03_Change_Info() {
		addressesPage.openPageFooterByName(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
		myAccountPage.clickToChangePasswordLink();
		myAccountPage.enterToTextboxByName(driver, password, "OldPassword");
		myAccountPage.enterToTextboxByName(driver, newPassword, "NewPassword");
		myAccountPage.enterToTextboxByName(driver, newConfirmPassword, "ConfirmNewPassword");
		myAccountPage.clickToChangePasswordButton();
		
		Assert.assertEquals(myAccountPage.getTextPasswordSuccessMsg(), "Password was changed");
	}
	@Test
	public void My_Account_04_Add_Review() {
		myAccountPage.openPageFooterByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		searchPage.enterToSearchTextBox("");
		searchPage.clickToSearchButton();
	}

	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MyAccountPageObject myAccountPage;
	AddressesPageObject addressesPage;
	SearchPageObject searchPage;
}
