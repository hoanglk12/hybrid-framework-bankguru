package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.BasePageUI;
import pageUIs.user.nopCommerce.MyAccountPageUI;
import pageUIs.user.nopCommerce.RegisterPageUI;

public class MyAccountPageObject extends BasePage {
	WebDriver driver;
	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToGenderFemaleRadioButton() {
		waitForElementClickable(driver, BasePageUI.GENDER_FEMALE_RADIO);
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
	public void clickToChangePasswordLink() {
		waitForElementClickable(driver, MyAccountPageUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, MyAccountPageUI.CHANGE_PASSWORD_LINK);
	}
	public void clickToChangePasswordButton() {
		waitForElementClickable(driver, MyAccountPageUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, MyAccountPageUI.CHANGE_PASSWORD_BUTTON);
	}
	public String getTextPasswordSuccessMsg() {
		waitForElementVisible(driver, MyAccountPageUI.SUCCESS_CHANGE_PASSWORD_MSG);
		return getTextElement(driver, MyAccountPageUI.SUCCESS_CHANGE_PASSWORD_MSG);
	}
	public void clickToMyProductsReviewLink() {
		waitForElementClickable(driver, MyAccountPageUI.MY_PRODUCTS_REVIEW_LINK);
		clickToElement(driver, MyAccountPageUI.MY_PRODUCTS_REVIEW_LINK);
		
	}
	public String getTextReviewTitle() {
		waitForElementVisible(driver, MyAccountPageUI.REVIEW_TITLE);
		return getTextElement(driver, MyAccountPageUI.REVIEW_TITLE);
	}
	public String getTextReviewText() {
		waitForElementVisible(driver, MyAccountPageUI.REVIEW_TEXT);
		return getTextElement(driver, MyAccountPageUI.REVIEW_TEXT);
	}
	public void clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
		
		
	}
	
}
