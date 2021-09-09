package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class NewCustomerPageObject extends BasePage {
	protected WebDriver driver;
	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
