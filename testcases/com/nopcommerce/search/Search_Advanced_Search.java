package com.nopcommerce.search;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.myaccount.My_Account;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.SearchPageObject;

public class Search_Advanced_Search extends BaseTest {
	WebDriver driver;
	String emptyErrorMsg, noDataErrorMsg, lenovoIdeaTitle, lenovoThinkpadTitle, macbookFoundMsg;
	@Parameters({"browser", "url"})
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		log.info("Pre-Condition - Open browser '" + browserName + "' with url: '" + urlNopCommerce + "'");
		driver = getBrowser(browserName, urlNopCommerce);
		homePage = PageGeneratorManager.getHomePage(driver);
		emptyErrorMsg = "Search term minimum length is 3 characters";
		noDataErrorMsg = "No products were found that matched your criteria.";
		lenovoIdeaTitle = "Lenovo IdeaCentre 600 All-in-One PC";
		lenovoThinkpadTitle = "Lenovo Thinkpad X1 Carbon Laptop";
		macbookFoundMsg = "Apple MacBook Pro 13-inch";
		
	}

	@Test
	public void Search_Advanced_Search_00_Login_To_Page() {
		loginPage = homePage.clickToLoginLink();
		loginPage.enterToEmailTextbox(My_Account.editEmail);
		loginPage.enterToPasswordTextbox(My_Account.newPassword);
		homePage = loginPage.clickToLoginButton();
		loginPage.sleepInSecond(1);
		verifyTrue(homePage.isSliderHomePageDisplayed());
		
	}
	@Test
	public void Search_Advanced_Search_01_Search_With_Empty_Data() {
		log.info("Search_Advanced_Search_01 - Step 1: Open Search link at footer >>> Navigate to Search Page");
		homePage.openPageFooterByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		
		log.info("Search_Advanced_Search_01 - Step 2: Leave blank at Search keyword textbox");
		searchPage.enterToTextboxByName(driver, "", "q");
		
		log.info("Search_Advanced_Search_01 - Step 3: Click to Search button");
		searchPage.clickToSearchButton();
		
		log.info("Search_Advanced_Search_01 - Step 4: Verify error message '" + emptyErrorMsg + "'" + "is displayed");
		verifyEquals(searchPage.getTextErrorMsgSearchPage("warning"), emptyErrorMsg);
	}
	@Test
	public void Search_Advanced_Search_02_Search_With_Data_Exist() {
		log.info("Search_Advanced_Search_02 - Step 1: Enter 'Macbook Pro 2050' at Search keyword textbox");
		searchPage.enterToTextboxByName(driver, "Macbook Pro 2050", "q");

		log.info("Search_Advanced_Search_02 - Step 2: Click to Search button");
		searchPage.clickToSearchButton();

		log.info("Search_Advanced_Search_02 - Step 3: Verify error message '" + noDataErrorMsg + "'" + " is displayed");
		verifyEquals(searchPage.getTextErrorMsgSearchPage("no-result"), noDataErrorMsg);
	}
	@Test
	public void Search_Advanced_Search_03_Search_With_Product_Name_Relatively() {
		log.info("Search_Advanced_Search_03 - Step 1: Enter 'Lenovo' at Search keyword textbox");
		searchPage.enterToTextboxByName(driver, "Lenovo", "q");
		
		log.info("Search_Advanced_Search_03 - Step 2: Click to Search button");
		searchPage.clickToSearchButton();
		
		log.info("Search_Advanced_Search_03 - Step 3: Verify 2 products '" + lenovoIdeaTitle + "'" + "," + "'" + lenovoThinkpadTitle + "'" + " are displayed");
		verifyTrue(searchPage.getListProductTitles().contains(lenovoIdeaTitle));
		verifyTrue(searchPage.getListProductTitles().contains(lenovoThinkpadTitle));
		verifyEquals(searchPage.getListProductTitles().size(), 2);
	}
	@Test
	public void Search_Advanced_Search_04_Search_With_Product_Name_Precisely() {
		log.info("Search_Advanced_Search_04 - Step 1: Enter 'Think' at Search keyword textbox");
		searchPage.enterToTextboxByName(driver, "ThinkPad X1 Carbon", "q");

		log.info("Search_Advanced_Search_04 - Step 2: Click to Search button");
		searchPage.clickToSearchButton();

		log.info("Search_Advanced_Search_04 - Step 3: Verify only 1 product '" + lenovoThinkpadTitle + " is displayed");
		verifyTrue(searchPage.getListProductTitles().contains(lenovoThinkpadTitle));
		verifyEquals(searchPage.getListProductTitles().size(), 1);
	}
	
	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void closeBrowser(String browserName) {
		log.info("Post-Condition - Close browser '" + browserName + "'");
		closeBrowserAndDriver();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	SearchPageObject searchPage;
}
