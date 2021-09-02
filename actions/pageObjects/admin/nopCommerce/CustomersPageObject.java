package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.CustomersPageUI;

public class CustomersPageObject extends BasePage {
	WebDriver driver;

	public CustomersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToButtonLinkByName(String buttonName) {
		waitForElementClickable(driver, CustomersPageUI.DYNAMIC_BUTTON_LINK, buttonName);
		clickToElement(driver, CustomersPageUI.DYNAMIC_BUTTON_LINK, buttonName);
	}

	public void closeDefaultItemOfCustomerRoles() {
		waitForElementVisible(driver, CustomersPageUI.CLOSE_ICON_CUSTOMER_ROLES_ITEM);
		if (isElementDisplayed(driver, CustomersPageUI.CLOSE_ICON_CUSTOMER_ROLES_ITEM)) {
			clickToElementByJS(driver, CustomersPageUI.CLOSE_ICON_CUSTOMER_ROLES_ITEM);
		}
	}

}