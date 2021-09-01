package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void enterToEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}
	public String getEmailErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MSG);
		return getTextElement(driver, LoginPageUI.EMAIL_ERROR_MSG);
	}
	public boolean isUnregEmailEmptyWrongPassErrorMsgDisplayed() {
		waitForElementVisible(driver, LoginPageUI.UNREG_EMAIL_EMPTY_PASS_ERROR_MSG);
		return isElementDisplayed(driver, LoginPageUI.UNREG_EMAIL_EMPTY_PASS_ERROR_MSG);

	}
	
	
}
