package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.bankGuru.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public String getTextUserID() {
		return getTextElement(driver, RegisterPageUI.USER_ID_TEXT);
		
	}
	public String getTextPassword() {
		return getTextElement(driver, RegisterPageUI.PASSWORD_TEXT);
		
	}

}
