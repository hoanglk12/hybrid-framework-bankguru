package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isSliderHomePageDisplayed() {
		waitForElementVisible(driver, HomePageUI.HOME_PAGE_SLIDER);
		return isElementDisplayed(driver, HomePageUI.HOME_PAGE_SLIDER);
	}
	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}
	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}
	public boolean isProductTitlesSortedAlphabeticalOrder() {
		return isListSortedByOrder(driver, getListProductTitles(driver));
	}
	public boolean isProductTitlesSortedReverseAlphabeticalOrder() {
		return isListSortedByReverseOrder(driver, getListProductTitles(driver));
	}
	public boolean isProductPricesIncreasing() {
		return isListSortedByOrder(driver, getListProductPrices(driver));
	}
	public boolean isProductPricesDecreasing() {
		return isListSortedByReverseOrder(driver, getListProductPrices(driver));
	}
	public boolean isProductSizeLessOrEqualThan(int number) {
		return getListProductTitles(driver).size() <= number ? true : false;
	}
	public boolean isPaginationExist() {
		return isElementUndisplayed(driver, HomePageUI.PAGINATION) ? false : true;
	}
	public boolean isNextOrPreviousIconDisplayed(String iconName) {
		return isElementDisplayed(driver, HomePageUI.PAGINATION_LINK_BY_TEXT, iconName);
	}
	

}
