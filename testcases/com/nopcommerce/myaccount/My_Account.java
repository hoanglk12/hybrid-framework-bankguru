package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_Login;

import commons.BaseTest;
import pageObjects.user.nopCommerce.AddressesPageObject;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;

public class My_Account extends BaseTest {
	WebDriver driver;
	String email, password, firstName, lastName , confirmPassword, 
	editFirstName, editLastName, editFullName,  newConfirmPassword,
	dayItem, monthItem, yearItem, editCompanyName, addressFirstName, addressLastName, addressFullName,
	addressEmail, addressCompany, addressCountry, addressState, addressCity, addressAddress1, addressAddress2, 
	addressPostCode, addressPhone, addressFax, reviewTitle, reviewText, passwordSuccessMsg;
	public static String newPassword, editEmail;
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
		reviewTitle = "Worth money" ;
		reviewText = "Excellent product";
		passwordSuccessMsg = "Password was changed";
	}
	@Test
	public void My_Account_00_Login_To_Page() {
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isSliderHomePageDisplayed());
		loginPage = homePage.clickToLoginLink();
		
		loginPage.enterToEmailTextbox(Common_Login.email);
		loginPage.enterToPasswordTextbox(Common_Login.password);
		homePage = loginPage.clickToLoginButton();
		verifyTrue(homePage.isSliderHomePageDisplayed());

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
		
		verifyTrue(myAccountPage.isGenderFemaleRadioSelected());
		verifyEquals(myAccountPage.getAttributeValueFromTextboxByName(driver, "value", "FirstName"), editFirstName);
		verifyEquals(myAccountPage.getAttributeValueFromTextboxByName(driver, "value", "LastName"), editLastName);
		verifyEquals(myAccountPage.getSelectedItemFromDayDropdown(), dayItem);
		verifyEquals(myAccountPage.getSeletecItemFromMonthDropdown(), monthItem);
		verifyEquals(myAccountPage.getSelectedItemFromYearDropdown(), yearItem);
		verifyEquals(myAccountPage.getAttributeValueFromTextboxByName(driver, "value", "Company"), editCompanyName);
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
		
		verifyTrue(addressesPage.isAddressInfoContains("name", addressFullName));
		verifyTrue(addressesPage.isAddressInfoContains("email", addressEmail));
		verifyTrue(addressesPage.isAddressInfoContains("fax", addressFax));
		verifyTrue(addressesPage.isAddressInfoContains("company", addressCompany));
		verifyTrue(addressesPage.isAddressInfoContains("address1", addressAddress1));
		verifyTrue(addressesPage.isAddressInfoContains("address2", addressAddress2));
		verifyTrue(addressesPage.isAddressInfoContains("city-state-zip", addressCity));
		verifyTrue(addressesPage.isAddressInfoContains("city-state-zip", addressPostCode));
		verifyTrue(addressesPage.isAddressInfoContains("country", addressCountry));
		
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
		
		verifyEquals(myAccountPage.getTextPasswordSuccessMsg(), passwordSuccessMsg);
	}
	@Test
	public void My_Account_04_Add_Review() {
		myAccountPage.openPageFooterByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		searchPage.enterToTextboxByName(driver, "MacBook","q");
		searchPage.clickToSearchButton();
		searchPage.clickToProductTitleLink();
		searchPage.clickToAddYourReviewLink();
		searchPage.enterToTextboxByName(driver, reviewTitle, "AddProductReview_Title");
		searchPage.enterToReviewTextArea(reviewText);
		searchPage.clickToSubmitReviewButton();
		searchPage.openPageFooterByName(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
		myAccountPage.clickToMyProductsReviewLink();
		
		verifyEquals(myAccountPage.getTextReviewTitle(), reviewTitle);
		verifyEquals(myAccountPage.getTextReviewText(), reviewText);
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
	MyAccountPageObject myAccountPage;
	AddressesPageObject addressesPage;
	SearchPageObject searchPage;
}
