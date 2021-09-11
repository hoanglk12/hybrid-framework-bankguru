package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.WishListPageUI;

public class WishlistPageObject extends BasePage {
	WebDriver driver;
	public WishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isAllProductInfoDisplayed(String sku, String productName, String productPrice, String quantity) {
		return isElementDisplayed(driver, WishListPageUI.DYNAMIC_ROW_WISHLIST, sku, productName, productPrice, quantity);
	}
	public void clickToWishlistUrl() {
		waitForElementClickable(driver, WishListPageUI.WISHLIST_SHARE_URL);
		clickToElement(driver, WishListPageUI.WISHLIST_SHARE_URL);
		
	}
	public String getTextHeaderWishlistUrl() {
		return getTextElement(driver, WishListPageUI.WISHLIST_HEADER_TEXT);
	}
	public boolean isProductRemovedFromWishlist(String productNumber) {
		return isElementDisplayed(driver, WishListPageUI.DYNAMIC_LINK_TEXT, productNumber);
	}
	public boolean isProductAddedToShoppingCart(String productNumber) {
		return isElementDisplayed(driver, WishListPageUI.DYNAMIC_LINK_TEXT, productNumber);
	}
	public String getTextEmptyWishlistMsg() {
		return getTextElement(driver, WishListPageUI.EMPTY_WISHLIST_MSG).trim();
	}
	
	public boolean isAllProductInfoUndisplayed(String sku, String productName, String productPrice, String quantity) {
		return isElementUndisplayed(driver, WishListPageUI.DYNAMIC_ROW_WISHLIST, sku, productName, productPrice, quantity);
	}
}
