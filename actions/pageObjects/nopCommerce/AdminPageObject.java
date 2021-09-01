package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.AdminPageUI;
import pageUIs.nopCommerce.LoginPageUI;

public class AdminPageObject extends BasePage {
	WebDriver driver;
	public AdminPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSearchButton() {
		waitForElementVisible(driver, AdminPageUI.SEARCH_PRODUCT_BUTTON);
		clickToElement(driver, AdminPageUI.SEARCH_PRODUCT_BUTTON);
		
	}
	public void clickToLoginButton() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	public boolean isDashBoardHeaderDisplayed() {
		waitForElementVisible(driver, AdminPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver, AdminPageUI.DASHBOARD_HEADER);
	}
	public boolean isRowValueDisplayed(String productName, String sku, String price, String stockQuantity) {
		waitForElementVisible(driver, AdminPageUI.DYNAMIC_ROW_VALUE, productName, sku, price, stockQuantity);
		return isElementDisplayed(driver, AdminPageUI.DYNAMIC_ROW_VALUE, productName, sku, price, stockQuantity);
	}
	public void clickToCatalogMenuByJs() {
		waitForElementVisible(driver, AdminPageUI.CATALOG_MENU);
		clickToElementByJS(driver, AdminPageUI.CATALOG_MENU);
	}
	public void clickToProductsSubMenu() {
		waitForElementVisible(driver, AdminPageUI.PRODUCTS_SUBMENU);
		clickToElement(driver, AdminPageUI.PRODUCTS_SUBMENU);
	}
	public void selectItemInDropdownByName(String textExpected, String dropdownName) {
		waitForElementVisible(driver, AdminPageUI.DYNAMIC_ADMIN_SEARCH_DROPDOWN, dropdownName);
		selectItemInDropdown(driver, AdminPageUI.DYNAMIC_ADMIN_SEARCH_DROPDOWN, textExpected, dropdownName);
	}
	public boolean isNoDataMsgDisplayed() {
		waitForElementVisible(driver, AdminPageUI.NO_DATA_MSG);
		return isElementDisplayed(driver, AdminPageUI.NO_DATA_MSG);
	}
	public int getTotalImageProduct() {
		waitForAllElementVisible(driver, AdminPageUI.PRODUCT_IMG);
		return getElementSize(driver, AdminPageUI.PRODUCT_IMG);
	}
	
}
