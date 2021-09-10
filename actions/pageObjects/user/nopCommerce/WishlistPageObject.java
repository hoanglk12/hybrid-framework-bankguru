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
	
}
