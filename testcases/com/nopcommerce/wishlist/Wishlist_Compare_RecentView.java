package com.nopcommerce.wishlist;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_Login;
import com.nopcommerce.register.Register_Account;

import commons.BaseTest;
import pageObjects.user.nopCommerce.ComparePageObject;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RecentlyViewedPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;
import pageObjects.user.nopCommerce.WishlistPageObject;

public class Wishlist_Compare_RecentView extends BaseTest {
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
		
		loginPage.enterToEmailTextbox(Register_Account.email);
		loginPage.enterToPasswordTextbox(Register_Account.password);
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
		verifyTrue(searchPage.isAddedToWishlistSuccessMsgDisplayed());
		
		log.info("Wishlist_Compare_RecentView_01 - Step 9: Click to 'Wishlist' at footer page");
		searchPage.openPageFooterByName(driver, "Wishlist");
		wishListPage = PageGeneratorManager.getWishlistPage(driver);
		
		log.info("Wishlist_Compare_RecentView_01 - Step 10: Verify product title, sku, price and quantity displayed as in Search Page");
		verifyTrue(wishListPage.isAllProductInfoDisplayed(sku, productName, price, quantity));
		
		log.info("Wishlist_Compare_RecentView_01 - Step 11: Click to Wishlist url");
		wishListPage.clickToWishlistUrl();
		
		log.info("Wishlist_Compare_RecentView_01 - Step 12: Verify wishlist title is displayed");
		verifyEquals(wishListPage.getTextHeaderWishlistUrl(), "Wishlist of " + Common_Login.firstName + " " + Common_Login.lastName);
	}
	@Test
	public void Wishlist_Compare_RecentView_02_Add_To_Cart_From_Wishlist() {
		log.info("Wishlist_Compare_RecentView_01 - Step 1: Click to 'Wishlist (1)' at header page");
		wishListPage.clickToButtonLinkByName(driver, "Wishlist (1)");
		
		log.info("Wishlist_Compare_RecentView_02 - Step 2: Check to checbox 'Add to cart'");
		wishListPage.clickToButtonByNameAttribute(driver, "addtocart");
		
		log.info("Wishlist_Compare_RecentView_02 - Step 3: Click to button 'Add to cart'");
		wishListPage.clickToButtonByText(driver, "Add to cart");
		
		log.info("Wishlist_Compare_RecentView_02 - Step 4: Verify Shoppingcart(1) are displayed");
		verifyTrue(wishListPage.isProductAddedToShoppingCart("Shopping cart (1)"));
	}
	@Test
	public void Wishlist_Compare_RecentView_03_Remove_Product_From_Wishlist() {
		log.info("Wishlist_Compare_RecentView_03 - Step 1: Click to 'Wishlist (1)' at header page");
		wishListPage.openPageFooterByName(driver, "Wishlist");
		
		log.info("Wishlist_Compare_RecentView_03 - Step 2: Click to icon 'Remove'");
		wishListPage.clickToIconByRowNumber(driver,"cart","Remove","1","button");
		
		log.info("Wishlist_Compare_RecentView_03 - Step 3: Verify message 'The wishlist is empty!' is displayed");
		verifyEquals(wishListPage.getTextEmptyWishlistMsg(), "The wishlist is empty!");
		
		log.info("Wishlist_Compare_RecentView_03 - Step 4: Verify all product info is undisplayed");
		verifyTrue(wishListPage.isAllProductInfoUndisplayed(sku, productName, price, quantity));
	}
	@Test
	public void Wishlist_Compare_RecentView_04_Add_Product_To_Compare() {
		log.info("Wishlist_Compare_RecentView_04 - Step 1: Click on menu 'Computers' >>> submenu 'Notebooks'");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Desktops");
		
		log.info("Wishlist_Compare_RecentView_04 - Step 2: Add 1 product to compare list with data '" + productBuildComputerFullName + "'");
		productBuildComputerPrice = homePage.getTextProductNamePrice(productBuildComputerFullName);
		homePage.clickToButtonByProductNameAndText(driver, productBuildComputerFullName, "Add to compare list");
		homePage.sleepInSecond(1);
		verifyTrue(homePage.isAddedProductToCompareListMsgDisplayed());
		
		log.info("Wishlist_Compare_RecentView_04 - Step 3: Add 1 products to compare list with data '" + productLenovoFullName + "'");
		productLenovoPrice = homePage.getTextProductNamePrice(productLenovoFullName);
		homePage.clickToButtonByProductNameAndText(driver, productLenovoFullName, "Add to compare list");
		homePage.sleepInSecond(1);
		verifyTrue(homePage.isAddedProductToCompareListMsgDisplayed());
		
		log.info("Wishlist_Compare_RecentView_04 - Step 4: Click to 'Compare products list' at footer page >>> Navigate to Compare list product page");
		homePage.openPageFooterByName(driver, "Compare products list");
		comparePage = PageGeneratorManager.getComparePage(driver);
		
		log.info("Wishlist_Compare_RecentView_04 - Step 5: Verify 2 remove icons are displayed");
		verifyEquals(comparePage.getSizeRemoveIcons(), 2);
		
		log.info("Wishlist_Compare_RecentView_04 - Step 6: Verify name and price of 2 products displayed in Compare list and match with Home Page");
		verifyTrue(comparePage.isProductNameDisplayedInCompareList(productBuildComputerFullName));
		verifyTrue(comparePage.isProductNameDisplayedInCompareList(productLenovoFullName));
		verifyTrue(comparePage.isProductPriceDisplayedInCompareList(productBuildComputerPrice));
		verifyTrue(comparePage.isProductPriceDisplayedInCompareList(productLenovoPrice));
		
		log.info("Wishlist_Compare_RecentView_04 - Step 7: Click to button 'Clear list'");
		comparePage.clickToButtonLinkByName(driver, "Clear list");
		
		log.info("Wishlist_Compare_RecentView_04 - Step 8: Verify message 'You have no items to compare.' is displayed");
		verifyEquals(wishListPage.getTextEmptyWishlistMsg(), "You have no items to compare.");
		
		log.info("Wishlist_Compare_RecentView_04 - Step 9: Verify name and price of 2 products NOT displayed in Compare list");
		verifyTrue(comparePage.isProductNameUndisplayedInCompareList(productBuildComputerFullName));
		verifyTrue(comparePage.isProductNameUndisplayedInCompareList(productLenovoFullName));
		verifyTrue(comparePage.isProductPriceUndisplayedInCompareList(productBuildComputerPrice));
		verifyTrue(comparePage.isProductPriceUndisplayedInCompareList(productLenovoPrice));
	
	}
	@Test
	public void Wishlist_Compare_RecentView_05_Recently_Viewed_Products() {
		log.info("Wishlist_Compare_RecentView_05 - Step 1: Click on menu 'Computers' >>> submenu 'Notebooks'");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.hoverToMenuByText(driver, "Computers");
		homePage.clickToSubMenuByText(driver, "Notebooks");
		
		log.info("Wishlist_Compare_RecentView_05 - Step 2: View 5 random products");
		homePage.viewFiveRandomProducts(fiveRandomProducts);
		
		log.info("Wishlist_Compare_RecentView_05 - Step 3: Open 'Recently viewed products' at footer page >> Navigate to 'Recently viewed products' page ");
		homePage.openPageFooterByName(driver, "Recently viewed products");
		recentlyViewedPage = PageGeneratorManager.getRecentlyViewedPageObject(driver);
		
		log.info("Wishlist_Compare_RecentView_05 - Step 4: Verify header 'Recently viewed products' is displayed");
		verifyTrue(recentlyViewedPage.isRecentlyViewedProductHeaderDisplayed());
		recentlyViewedPage.sleepInSecond(3);
		
		log.info("Wishlist_Compare_RecentView_05 - Step 5: Verify ONLY last 3 product viewed are displayed");
		Collections.sort(recentlyViewedPage.getListProductTitles(driver));
		Collections.sort(homePage.getLastThreeProductsAtHomePage(fiveRandomProducts));
		verifyEquals(recentlyViewedPage.getListProductTitles(driver), homePage.getLastThreeProductsAtHomePage(fiveRandomProducts));
	
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