package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.MyAccountPageUI;

public class MyAccountPageObject extends BasePage {
	WebDriver driver;
	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToGenderFemaleRadioButton() {
		waitForElementVisible(driver, BasePageUI.GENDER_FEMALE_RADIO);
		clickToElement(driver, BasePageUI.GENDER_FEMALE_RADIO);
		
	}
	public void enterToFirstnameTextbox(String firstName) {
		waitForElementVisible(driver, BasePageUI.FIRSTNAME_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.FIRSTNAME_TEXTBOX, firstName);
		
	}
	public void enterToLastnameTextbox(String lastName) {
		waitForElementVisible(driver, BasePageUI.LASTNAME_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.LASTNAME_TEXTBOX, lastName);
	}
	public void selectDayFromDayDropdown(String dayValue) {
		waitForElementVisible(driver, BasePageUI.DAY_DROPDOWN);
		selectItemInDropdown(driver, BasePageUI.DAY_DROPDOWN, dayValue);
	}
	public void selectMonthFromMonthDropdown(String monthValue) {
		waitForElementVisible(driver, BasePageUI.MONTH_DROPDOWN);
		selectItemInDropdown(driver, BasePageUI.MONTH_DROPDOWN, monthValue);
		
	}
	public void selectYearFromYearDropdown(String yearValue) {
		waitForElementVisible(driver, BasePageUI.YEAR_DROPDOWN);
		selectItemInDropdown(driver, BasePageUI.YEAR_DROPDOWN, yearValue);
		
		
	}
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, BasePageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.EMAIL_TEXTBOX, email);
	}
	public void enterToCompanyName(String companyName) {
		waitForElementVisible(driver, BasePageUI.COMPANY_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.COMPANY_TEXTBOX, companyName);
		
	}
	public void clickToSaveButton() {
		waitForElementClickable(driver, MyAccountPageUI.SAVE_BUTTON);
		clickToElement(driver, MyAccountPageUI.SAVE_BUTTON);
	}
	public boolean isGenderFemaleRadioSelected() {
		waitForElementVisible(driver, BasePageUI.GENDER_FEMALE_RADIO);
		return isElementSelected(driver, BasePageUI.GENDER_FEMALE_RADIO);
	}
	public String getValueFirstNameTextbox() {
		waitForElementVisible(driver, BasePageUI.FIRSTNAME_TEXTBOX);
		return getAttributeValue(driver, BasePageUI.FIRSTNAME_TEXTBOX, "value");
	}
	public String getValueLastNameTexbox() {
		waitForElementVisible(driver, BasePageUI.LASTNAME_TEXTBOX);
		return getAttributeValue(driver, BasePageUI.LASTNAME_TEXTBOX, "value");
	}
	public String getSelectedItemFromDayDropdown() {
		waitForElementVisible(driver, BasePageUI.DAY_DROPDOWN);
		return getSelectedItemInDropdown(driver, BasePageUI.DAY_DROPDOWN);
	}
	public String getSeletecItemFromMonthDropdown() {
		waitForElementVisible(driver, BasePageUI.MONTH_DROPDOWN);
		return getSelectedItemInDropdown(driver, BasePageUI.MONTH_DROPDOWN);

	}
	public String getSelectedItemFromYearDropdown() {
		waitForElementVisible(driver, BasePageUI.YEAR_DROPDOWN);
		return getSelectedItemInDropdown(driver, BasePageUI.YEAR_DROPDOWN);
	}
	public String getValueCompanyTextbox() {
		waitForElementVisible(driver, BasePageUI.COMPANY_TEXTBOX);
		return getAttributeValue(driver, BasePageUI.COMPANY_TEXTBOX, "value");
	}
	
}
