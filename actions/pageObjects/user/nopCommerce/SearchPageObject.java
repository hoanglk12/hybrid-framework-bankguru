package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.BasePageUI;
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
		waitForElementClickable(driver, BasePageUI.PRODUCT_TITLE_LINK);
		clickToElement(driver, BasePageUI.PRODUCT_TITLE_LINK);
		
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

	public String getTextSku() {
		waitForElementVisible(driver, SearchPageUI.SKU_TEXT);
		return getTextElement(driver, SearchPageUI.SKU_TEXT);
	}

	public String getTextPrice() {
		waitForElementVisible(driver, SearchPageUI.PRODUCT_PRICE_TEXT);
		return getTextElement(driver, SearchPageUI.PRODUCT_PRICE_TEXT);
	}

	public String getTextQuantity() {
		waitForElementVisible(driver, SearchPageUI.QUANTITY_TEXTBOX);
		return getTextElement(driver, SearchPageUI.QUANTITY_TEXTBOX);
	}

	public void enterToQuantityTextbox(String quantity) {
		waitForElementVisible(driver, SearchPageUI.QUANTITY_TEXTBOX);
		sendkeyToElement(driver, SearchPageUI.QUANTITY_TEXTBOX, quantity);
	}

	public void clickToAddToWishListButton() {
		waitForElementVisible(driver, SearchPageUI.ADD_TO_WISHLIST_BUTTON);
		clickToElement(driver, SearchPageUI.ADD_TO_WISHLIST_BUTTON);
	}

	public boolean isAddedToWishlistSuccessMsgDisplayed() {
		waitForElementVisible(driver, SearchPageUI.SUCCESS_ADDED_WISHLIST_MSG);
		return isElementDisplayed(driver, SearchPageUI.SUCCESS_ADDED_WISHLIST_MSG);
	}

}
