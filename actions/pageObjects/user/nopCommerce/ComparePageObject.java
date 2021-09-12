package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.ComparePageUI;

public class ComparePageObject extends BasePage {
	WebDriver driver;
	public ComparePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public int getSizeRemoveIcons() {
		return getElementSize(driver, ComparePageUI.REMOVE_ICONS);
	}
	public boolean isProductNameDisplayedInCompareList(String productName) {
		return isElementDisplayed(driver, ComparePageUI.DYNAMIC_PRODUCT_NAME_BY_TEXT, productName);
	}
	public boolean isProductPriceDisplayedInCompareList(String productName) {
		return isElementDisplayed(driver, ComparePageUI.DYNAMIC_PRODUCT_PRICE_BY_TEXT, productName);
	}
	public String getTextEmptyCompareListMsg() {
		return getTextElement(driver, ComparePageUI.EMPTY_COMPARE_LIST_MSG).trim();
	}
	public boolean isProductNameUndisplayedInCompareList(String productName) {
		return isElementUndisplayed(driver, ComparePageUI.DYNAMIC_PRODUCT_NAME_BY_TEXT, productName);
	}
	public boolean isProductPriceUndisplayedInCompareList(String productName) {
		return isElementUndisplayed(driver, ComparePageUI.DYNAMIC_PRODUCT_PRICE_BY_TEXT, productName);
	}
	
}
