package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
	private PageGeneratorManager(){
		
	}
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static NewCustomerPageObject getCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}
}
