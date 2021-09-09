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
		log.info("Sort_Display_Paging_01 - Step 1: Click on menu 'Computers' >>> submenu 'Notebooks'");
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Notebooks");
		
		log.info("Sort_Display_Paging_01 - Step 2: Select option 'Name: A to Z' in dropdown 'Sort By'");
		homePage.selectItemInDropdownByAttributeName(driver, "Name: A to Z", "products-orderby");
		homePage.sleepInSecond(2);
		
		log.info("Sort_Display_Paging_01 - Step 3: Verify product titles are sorted by alphabetical order");
		verifyTrue(homePage.isProductTitlesSortedAlphabeticalOrder());
	}
	@Test
	public void Sort_Display_Paging_02_Sort_With_Name_Z_To_A() {
		log.info("Sort_Display_Paging_02 - Step 1: Click on menu 'Computers' >>> submenu 'Notebooks'");
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Notebooks");
		
		log.info("Sort_Display_Paging_02 - Step 2: Select option 'Name: Z to A' in dropdown 'Sort By'");
		homePage.selectItemInDropdownByAttributeName(driver, "Name: Z to A", "products-orderby");
		homePage.sleepInSecond(2);
		
		log.info("Sort_Display_Paging_02 - Step 3: Verify product titles are sorted by reverse alphabetical order");
		verifyTrue(homePage.isProductTitlesSortedReverseAlphabeticalOrder());
	}
	@Test
	public void Sort_Display_Paging_03_Sort_With_Price_Low_To_High() {
		log.info("Sort_Display_Paging_03 - Step 1: Click on menu 'Computers' >>> submenu 'Notebooks'");
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Notebooks");
		
		log.info("Sort_Display_Paging_03 - Step 2: Select option 'Price: Low to High' in dropdown 'Sort By'");
		homePage.selectItemInDropdownByAttributeName(driver, "Price: Low to High", "products-orderby");
		homePage.sleepInSecond(2);
		
		log.info("Sort_Display_Paging_03 - Step 3: Verify product prices are displayed with increasing order");
		verifyTrue(homePage.isProductPricesIncreasing());
	}
	@Test
	public void Sort_Display_Paging_04_Sort_With_Price_High_To_Low() {
		log.info("Sort_Display_Paging_04 - Step 1: Click on menu 'Computers' >>> submenu 'Notebooks'");
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Notebooks");
		
		log.info("Sort_Display_Paging_04 - Step 2: Select option 'Price: High to Low' in dropdown 'Sort By'");
		homePage.selectItemInDropdownByAttributeName(driver, "Price: High to Low", "products-orderby");
		homePage.sleepInSecond(2);
		
		log.info("Sort_Display_Paging_04 - Step 3: Verify product prices are displayed with decreasing order");
		verifyTrue(homePage.isProductPricesDecreasing());
	}
	@Test
	public void Sort_Display_Paging_05_Display_Three_Products_Per_Page() {
		log.info("Sort_Display_Paging_05 - Step 1: Click on menu 'Computers' >>> submenu 'Notebooks'");
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Notebooks");
		
		log.info("Sort_Display_Paging_05 - Step 2: Select option '3' in dropdown 'Display'");
		homePage.selectItemInDropdownByAttributeName(driver, "3", "products-pagesize");
		homePage.sleepInSecond(2);
		
		log.info("Sort_Display_Paging_05 - Step 3: Verify <= 3 products are displayed with page 1");
		verifyTrue(homePage.isProductSizeLessOrEqualThan(3));
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