package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.DashboardPageUI;
import pageUIs.admin.nopCommerce.ProductSearchPageUI;


public class ProductSearchPageObject extends BasePage {
	WebDriver driver;
	public ProductSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToSearchButton() {
		waitForElementVisible(driver, ProductSearchPageUI.SEARCH_PRODUCT_BUTTON);
		clickToElement(driver, ProductSearchPageUI.SEARCH_PRODUCT_BUTTON);
		
	}

	
	public void selectItemInDropdownByAttributeId(String textExpected, String idValueDropdown) {
		waitForElementVisible(driver, ProductSearchPageUI.DYNAMIC_ADMIN_SEARCH_DROPDOWN, idValueDropdown);
		selectItemInDropdown(driver, ProductSearchPageUI.DYNAMIC_ADMIN_SEARCH_DROPDOWN, textExpected, idValueDropdown);
	}
	public boolean isNoDataMsgDisplayed() {
		waitForElementVisible(driver, ProductSearchPageUI.NO_DATA_MSG);
		return isElementDisplayed(driver, ProductSearchPageUI.NO_DATA_MSG);
	}
	public int getTotalImageProduct() {
		waitForAllElementVisible(driver, ProductSearchPageUI.PRODUCT_IMG);
		return getElementSize(driver, ProductSearchPageUI.PRODUCT_IMG);
	}
	public DashboardPageObject openDashboardMenu(String menu) {
		waitForElementClickable(driver, DashboardPageUI.DYNAMIC_MENU, menu);
		clickToElementByJS(driver, DashboardPageUI.DYNAMIC_MENU, menu);
		return PageGeneratorManager.getDashboardPage(driver);
	}
}
