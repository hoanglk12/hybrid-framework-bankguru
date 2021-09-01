package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public DashboardPageObject clickToLoginButton() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getDashboardPage(driver);
	}
	
	
}
