package com.nopcommerce.orders;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_Login;

import commons.BaseTest;
import pageObjects.user.nopCommerce.ComparePageObject;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RecentlyViewedPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;
import pageObjects.user.nopCommerce.WishlistPageObject;

public class Orders extends BaseTest {
	WebDriver driver;
	String productName, sku, price, quantity, productLenovoFullName, productBuildComputerFullName,
	productBuildComputerPrice, productLenovoPrice;
	List<String> fiveRandomProducts = Arrays.asList("Apple MacBook Pro 13-inch","Asus N551JK-XO076H Laptop","Samsung Series 9 NP900X4C Premium Ultrabook","HP Spectre XT Pro UltraBook","HP Envy 6-1180ca 15.6-Inch Sleekbook");
	@Parameters({"browser", "url"})
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		log.info("Pre-Condition - Open browser '" + browserName + "' with url: '" + urlNopCommerce + "'");
		driver = getBrowser(browserName, urlNopCommerce);
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		quantity = "1";
		productLenovoFullName = productName;
		productBuildComputerFullName = "Build your own computer";
		homePage = PageGeneratorManager.getHomePage(driver);
		loginPage = homePage.clickToLoginLink();
		
		loginPage.enterToEmailTextbox(Common_Login.email);
		loginPage.enterToPasswordTextbox(Common_Login.password);
		homePage = loginPage.clickToLoginButton();
		loginPage.sleepInSecond(1);
		
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
	}
	@Test
	public void Orders_01_Add_Product_To_Cart() {
		log.info("Orders_01 - Step 1: Open 'Search' link at Footer page");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Desktops");
		
		log.info("Orders_02 - Step 2: Click to product title with data '" + productBuildComputerFullName + "'");
		homePage.clickToProductTitleByText(driver, productBuildComputerFullName);
		
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
	WishlistPageObject wishListPage;
	ComparePageObject comparePage;
	RecentlyViewedPageObject recentlyViewedPage;
}