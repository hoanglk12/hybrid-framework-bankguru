package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.CustomerEditPageUI;

public class CustomerEditPageObject extends BasePage {
	WebDriver driver;
	public CustomerEditPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
}
