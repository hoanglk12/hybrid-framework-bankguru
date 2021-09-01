package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.DashboardPageUI;
import pageUIs.user.nopCommerce.LoginPageUI;

public class DashboardPageObject extends BasePage {
	WebDriver driver;
	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToLoginButton() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	public boolean isDashBoardHeaderDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver, DashboardPageUI.DASHBOARD_HEADER);
	}
	
	public void clickToCatalogMenuByJs() {
		waitForElementVisible(driver, DashboardPageUI.CATALOG_MENU);
		clickToElementByJS(driver, DashboardPageUI.CATALOG_MENU);
	}
	public ProductSearchPageObject clickToProductsSubMenu() {
		waitForElementVisible(driver, DashboardPageUI.PRODUCTS_SUBMENU);
		clickToElement(driver, DashboardPageUI.PRODUCTS_SUBMENU);
		return PageGeneratorManager.getProductSearchPage(driver);
	}
	
}
