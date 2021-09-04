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
	public void clickToSaveButton() {
		waitForElementClickable(driver, AdminBasePageUI.SAVE_BUTTON);
		clickToElement(driver, AdminBasePageUI.SAVE_BUTTON);
	}
	public boolean isAddedEditedSuccessMsgDisplayed(String messageContent) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_MESSAGE_SUCCESS_CREATE_UPDATE, messageContent);
		return isElementDisplayed(driver, AdminBasePageUI.DYNAMIC_MESSAGE_SUCCESS_CREATE_UPDATE, messageContent);
	}
	public boolean isAddressInfoDisplayed(String firstName, String lastName, String email, String phone, String fax, String company, String address1, String address2, String city, String zip, String country) {
		waitForElementVisible(driver, CustomerEditPageUI.DYNAMIC_ROW_CUSTOMER_ADDRESS, firstName, lastName, email, phone, fax, company, address1, address2, city, zip, country);
		return isElementDisplayed(driver, CustomerEditPageUI.DYNAMIC_ROW_CUSTOMER_ADDRESS, firstName, lastName, email, phone, fax, company, address1, address2, city, zip, country);
	}
	public void acceptToDeleteAddressAlert() {
		waitForAlertPresence(driver);
		acceptAlert(driver);
	}
	public boolean isAddressDeleteMsgDisplayed() {
		waitForElementVisible(driver, CustomerEditPageUI.NO_DATA_ADDRESS_MSG);
		return isElementDisplayed(driver, CustomerEditPageUI.NO_DATA_ADDRESS_MSG);
	}
	public void clickToDeleteButton() {
		waitForElementClickable(driver, AdminBasePageUI.DELETE_BUTTON_TABLE_DATA);
		clickToElement(driver, AdminBasePageUI.DELETE_BUTTON_TABLE_DATA);
	}
	
	
}
