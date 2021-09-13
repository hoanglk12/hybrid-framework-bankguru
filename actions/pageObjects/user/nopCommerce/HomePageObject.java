package pageObjects.user.nopCommerce;

import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.BasePageUI;
import pageUIs.user.nopCommerce.HomePageUI;

public class HomePageObject extends BasePage {
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
		return isDataStringSortedAscending(driver, BasePageUI.PRODUCT_TITLE_LINK);
	}

	public boolean isProductTitlesSortedReverseAlphabeticalOrder() {
		return isDataStringSortedDescending(driver, BasePageUI.PRODUCT_TITLE_LINK);
	}

	public boolean isProductPricesIncreasing() {
		return isDataFloatSortedAscending(driver, BasePageUI.PRODUCT_PRICES);
	}

	public boolean isProductPricesDecreasing() {
		return isDataFloatSortedDescending(driver, BasePageUI.PRODUCT_PRICES);
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

	public boolean isAddedProductToCompareListMsgDisplayed() {
		return isElementDisplayed(driver, HomePageUI.SUCCESS_MSG_ADDED_COMPARE_LIST);
	}

	public String getTextProductNamePrice(String productName) {
		return getTextElement(driver, HomePageUI.DYNAMIC_PRODUCT_PRICE_BY_TEXT, productName).trim();
	}

	public void viewFiveRandomProducts(List<String> fiveRandomProducts) {
		fiveRandomProducts.stream().forEach(product -> {
			clickToProductTitleByText(driver, product);
			backToPage(driver);
		});
	}

	public List<String> getLastThreeProductsAtHomePage(List<String> fiveRandomProducts) {
		List<String> getLastThreeProductsAtHomePage = fiveRandomProducts.subList(2, 5);
		return getLastThreeProductsAtHomePage;
	}
}
