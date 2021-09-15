package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.bankGuru.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getWelcomeTextMessage() {
		return getTextElement(driver, HomePageUI.WELCOME_TEXT_MESSAGE);
	}
}
