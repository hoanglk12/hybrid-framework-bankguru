package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.AddressesPageUI;

public class AddressesPageObject extends BasePage {
	WebDriver driver;
	public AddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToAddNewButton() {
		waitForElementClickable(driver, AddressesPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AddressesPageUI.ADD_NEW_BUTTON);
	}
	public void selectToCountryDropdown(String countryValue) {
		waitForElementVisible(driver, AddressesPageUI.ADDRESS_COUNTRY_DROPDOWN);
		selectItemInDropdown(driver, AddressesPageUI.ADDRESS_COUNTRY_DROPDOWN, countryValue);
		
	}
	public void clickToSaveButton() {
		waitForElementClickable(driver, AddressesPageUI.SAVE_BUTTON);
		clickToElement(driver, AddressesPageUI.SAVE_BUTTON);
		
	}
	public boolean isAddressInfoContains(String addressInfoName, String textExpected) {
		waitForElementVisible(driver, AddressesPageUI.DYNAMIC_ADDRESS_INFO, addressInfoName);
		return getTextElement(driver, AddressesPageUI.DYNAMIC_ADDRESS_INFO, addressInfoName).contains(textExpected);
	}
	public void enterToAddressTextboxByName(WebDriver driver, String value, String textboxName) {
		waitForElementVisible(driver, AddressesPageUI.DYNAMIC_ADDRESS_TEXTBOX, textboxName);
		sendkeyToElement(driver, AddressesPageUI.DYNAMIC_ADDRESS_TEXTBOX, value, textboxName);
	}
}
