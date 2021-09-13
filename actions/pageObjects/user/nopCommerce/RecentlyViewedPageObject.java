package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.RecentlyViewedPageUI;

public class RecentlyViewedPageObject extends BasePage {
	WebDriver driver;
	public RecentlyViewedPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isRecentlyViewedProductHeaderDisplayed() {
		return isElementDisplayed(driver, RecentlyViewedPageUI.RECENTLY_VIEWED_PRODUCT_HEADER);
	}
	
}
