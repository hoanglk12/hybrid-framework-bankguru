package com.nopcommerce.search;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.SearchPageObject;

public class Search_Advanced_Search extends BaseTest {
	WebDriver driver;
	String emptyErrorMsg, noDataErrorMsg, lenovoIdeaTitle, lenovoThinkpadTitle, macbookFoundMsg;
	@Parameters({ "browser", "url" })
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
	public void Search_Advanced_Search_01_Search_With_Empty_Data() {
		log.info("Search_Advanced_Search_01 - Step 1: Open Search link at footer >>> Navigate to Search Page");
		homePage.openPageFooterByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		
		log.info("Search_Advanced_Search_01 - Step 2: Leave blank at Search keyword textbox");
		
		log.info("Search_Advanced_Search_01 - Step 3: Click to Search button");
		
		log.info("Search_Advanced_Search_01 - Step 4: Verify error message '" + emptyErrorMsg + "'" + "is displayed");
		
		
	}
	
	@AfterClass
	public void closeBrowser() {
		log.info("Post-Condition - Close browser");
		driver.quit();
	}

	HomePageObject homePage;
	SearchPageObject searchPage;
}
