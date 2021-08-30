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
		// TODO Auto-generated method stub
		return null;
	}
	public String getEmptyLastNameErrorMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getEmptyEmailErrorMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getEmptyPasswordErrorMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getEmptyConfirmPasswordErrorMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getInvalidEmailErrorMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getEmailExistErrorMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isPasswordLessThanSixErrorMsgDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getPassNotMatchConfirmPassErrorMsg() {
		// TODO Auto-generated method stub
		return null;
	}
}
