package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.AdminBasePageUI;
import pageUIs.admin.nopCommerce.CustomerEditPageUI;

public class CustomerEditPageObject extends BasePage {
	WebDriver driver;
	public CustomerEditPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void enterItemInCustomerRolesDropdown(WebDriver driver, String expectedText) {
		enterOnElementInCustomDropdown(driver, CustomerEditPageUI.CUSTOMER_ROLES_PARENT_LOCATOR, expectedText);
	}
	public void clickToSaveAndContinueEditButton() {
		waitForElementClickable(driver, AdminBasePageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
		clickToElement(driver, AdminBasePageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
	}
	public boolean isAddedSuccessMsgDisplayed(String messageContent) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_MESSAGE_SUCCESS_CREATE_UPDATE, messageContent);
		return isElementDisplayed(driver, AdminBasePageUI.DYNAMIC_MESSAGE_SUCCESS_CREATE_UPDATE, messageContent);
	}
	
}
