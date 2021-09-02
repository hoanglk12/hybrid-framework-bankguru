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
	public void openMenuSubMenuByName(String menu, String subMenu) {
		waitForElementClickable(driver, DashboardPageUI.DYNAMIC_MENU, menu);
		clickToElementByJS(driver, DashboardPageUI.DYNAMIC_MENU, menu);
		waitForElementClickable(driver, DashboardPageUI.DYNAMIC_SUBMENU, subMenu);
		clickToElement(driver, DashboardPageUI.DYNAMIC_SUBMENU, subMenu);
	}
}
