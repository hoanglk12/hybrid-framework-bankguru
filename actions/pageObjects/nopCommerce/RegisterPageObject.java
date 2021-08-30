package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToGenderMaleRadioButton() {
		waitForElementClickable(driver, RegisterPageUI.GENDER_MALE_RADIO);
		clickToElement(driver, RegisterPageUI.GENDER_MALE_RADIO);
	}
	public void enterToFirstnameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}
	public void enterToLastnameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}
	public void enterToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	public void clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
	}
	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.SUCCESS_MESSAGE);
	}
	public String getEmptyFirstNameErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.EMPTY_FIRSTNAME_ERROR_MSG);
		return getTextElement(driver, RegisterPageUI.EMPTY_FIRSTNAME_ERROR_MSG);
		
		
	}
	public String getEmptyLastNameErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.EMPTY_LASTNAME_ERROR_MSG);
		return getTextElement(driver, RegisterPageUI.EMPTY_LASTNAME_ERROR_MSG);
	}
	public String getEmptyEmailErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.EMPTY_EMAIL_ERROR_MSG);
		return getTextElement(driver, RegisterPageUI.EMPTY_EMAIL_ERROR_MSG);
	}
	public String getEmptyPasswordErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.EMPTY_PASS_ERROR_MSG);
		return getTextElement(driver, RegisterPageUI.EMPTY_PASS_ERROR_MSG);
	}
	public String getEmptyConfirmPasswordErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.EMPTY_CONFIRM_PASS_ERROR_MSG);
		return getTextElement(driver, RegisterPageUI.EMPTY_CONFIRM_PASS_ERROR_MSG);
	}
	public String getInvalidEmailErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.INVALID_EMAIL_ERROR_MSG);
		return getTextElement(driver, RegisterPageUI.INVALID_EMAIL_ERROR_MSG);
	}
	public String getEmailExistErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.EXIST_EMAIL_ERROR_MSG);
		return getTextElement(driver, RegisterPageUI.EXIST_EMAIL_ERROR_MSG);
	}
	public boolean isPasswordLessThanSixErrorMsgDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.PASS_LESS_THAN_SIX_ERROR_MSG);
		return isElementDisplayed(driver, RegisterPageUI.PASS_LESS_THAN_SIX_ERROR_MSG);
	}
	public String getPassNotMatchConfirmPassErrorMsg() {
		waitForElementVisible(driver, RegisterPageUI.PASS_NOT_MATCH_CONFIRMPASS_ERROR_MSG);
		return getTextElement(driver, RegisterPageUI.PASS_NOT_MATCH_CONFIRMPASS_ERROR_MSG);
	}
}
