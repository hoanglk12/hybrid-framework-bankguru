package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.bankGuru.NewCustomerPageUI;

public class NewCustomerPageObject extends BasePage {
	WebDriver driver;
	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public String getErrorValidationMessage() {
		return getTextElement(driver, NewCustomerPageUI.ERROR_MESSGAE_VALIDATION);
	}

}
