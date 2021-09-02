package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.DashboardPageUI;

public class ProductDetailsPageObject extends BasePage {
	WebDriver driver;

	public ProductDetailsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public DashboardPageObject openDashboardMenu(String menu) {
		waitForElementClickable(driver, DashboardPageUI.DYNAMIC_MENU, menu);
		clickToElementByJS(driver, DashboardPageUI.DYNAMIC_MENU, menu);
		return PageGeneratorManager.getDashboardPage(driver);
	}
	
}
