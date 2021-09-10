package com.nopcommerce.wishlist;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_Login;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.SearchPageObject;
import pageObjects.user.nopCommerce.WishlistPageObject;

public class Wishlist_Compare_RecentView extends BaseTest {
	WebDriver driver;
	String productName, sku, price, quantity;
	@Parameters({"browser", "url"})
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		log.info("Pre-Condition - Open browser '" + browserName + "' with url: '" + urlNopCommerce + "'");
		driver = getBrowser(browserName, urlNopCommerce);
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		quantity = "1";
		homePage = PageGeneratorManager.getHomePage(driver);
		loginPage = homePage.clickToLoginLink();
		
		loginPage.enterToEmailTextbox(Common_Login.email);
		loginPage.enterToPasswordTextbox(Common_Login.password);
		homePage = loginPage.clickToLoginButton();
		loginPage.sleepInSecond(1);
		
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
	}

	@Test
	public void Wishlist_Compare_RecentView_01_Add_To_Wishlist() {
		log.info("Wishlist_Compare_RecentView_01 - Step 1: Open 'Search' link at Footer page");
		homePage.openPageFooterByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		
		log.info("Wishlist_Compare_RecentView_01 - Step 2: Enter 'ThinkPad X1 Carbon' at Search keyword textbox");
		searchPage.enterToTextboxByName(driver, "Lenovo IdeaCentre 600 All-in-One PC", "q");
		
		log.info("Wishlist_Compare_RecentView_01 - Step 3: Click to Search button");
		searchPage.clickToSearchButton();
		
		log.info("Wishlist_Compare_RecentView_01 - Step 4: Click to product title");
		searchPage.clickToProductTitleByText(driver, productName);
		
		log.info("Wishlist_Compare_RecentView_01 - Step 5: Enter to Quantity textbox with data");
		searchPage.enterToQuantityTextbox(quantity);
		
		log.info("Wishlist_Compare_RecentView_01 - Step 6: Get text sku, price, quantity");
		sku = searchPage.getTextSku();
		price = searchPage.getTextPrice().substring(1);
				
		log.info("Wishlist_Compare_RecentView_01 - Step 7: Click to button link 'Add to wishlist'");
		searchPage.clickToAddToWishListButton();
		
		log.info("Wishlist_Compare_RecentView_01 - Step 8: Verify success message is displayed with content 'The product has been added to your wishlist'");
		searchPage.isAddedToWishlishSuccessMsgDisplayed();
		
		log.info("Wishlist_Compare_RecentView_01 - Step 9: Click to 'Wishlist' at footer page");
		searchPage.openPageFooterByName(driver, "Wishlist");
		wishListPage = PageGeneratorManager.getWishlistPage(driver);
		
		log.info("Wishlist_Compare_RecentView_01 - Step 10: Verify product title, sku, price and quantity displayed as in Search Page");
		verifyTrue(wishListPage.isAllProductInfoDisplayed(sku, productName, price, quantity));
		
		log.info("Wishlist_Compare_RecentView_01 - Step 11: Verify product title, sku, price and quantity displayed as in Search Page");
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
}