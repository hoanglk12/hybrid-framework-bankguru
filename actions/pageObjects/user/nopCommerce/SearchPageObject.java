package pageObjects.user.nopCommerce;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.MyAccountPageUI;
import pageUIs.user.nopCommerce.SearchPageUI;

public class SearchPageObject extends BasePage {
	WebDriver driver;
	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON);
	}

	public void clickToProductTitleLink() {
		waitForElementClickable(driver, SearchPageUI.PRODUCT_TITLE_LINK);
		clickToElement(driver, SearchPageUI.PRODUCT_TITLE_LINK);
		
	}

	public void clickToAddYourReviewLink() {
		waitForElementClickable(driver, SearchPageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver, SearchPageUI.ADD_YOUR_REVIEW_LINK);
		
	}

	public void clickToSubmitReviewButton() {
		waitForElementClickable(driver, SearchPageUI.SUBMIT_REVIEW_BUTTON);
		clickToElement(driver, SearchPageUI.SUBMIT_REVIEW_BUTTON);
	}

	public void enterToReviewTextArea(String reviewText) {
		waitForElementVisible(driver, MyAccountPageUI.REVIEW_TEXT_TEXTAREA);
		sendkeyToElement(driver, MyAccountPageUI.REVIEW_TEXT_TEXTAREA, reviewText);
	}

	public String getTextErrorMsgSearchPage(String messageType) {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_ERROR_MESSAGE_SEARCH, messageType);
		return getTextElement(driver, SearchPageUI.DYNAMIC_ERROR_MESSAGE_SEARCH, messageType);
	}

	public List<String> getListProductTitles() {
		return getElements(driver, SearchPageUI.PRODUCT_TITLE_LINK).stream().map(title->title.getText()).collect(Collectors.toList());
	}
	
}
