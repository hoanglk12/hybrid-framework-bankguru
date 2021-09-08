package com.nopcommerce.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.SearchPageObject;

public class Sort_Display_Paging extends BaseTest {
	WebDriver driver;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		log.info("Pre-Condition - Open browser '" + browserName + "' with url: '" + urlNopCommerce + "'");
		driver = getBrowser(browserName, urlNopCommerce);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Sort_Display_Paging_01_Sort_With_Name_A_To_Z() {
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Notebooks");
		homePage.selectItemInDropdownByAttributeName(driver, "Name: A to Z", "products-orderby");
		homePage.sleepInSecond(2);
		verifyTrue(homePage.isProductTitlesSortedAlphabeticalOrder());
	}
	@Test
	public void Sort_Display_Paging_02_Sort_With_Name_Z_To_A() {
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Notebooks");
		homePage.selectItemInDropdownByAttributeName(driver, "Name: Z to A", "products-orderby");
		homePage.sleepInSecond(2);
		verifyTrue(homePage.isProductTitlesSortedReverseAlphabeticalOrder());
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