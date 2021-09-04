package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {

	private PageGeneratorManager(){
		
	}
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	public static ProductSearchPageObject getProductSearchPage(WebDriver driver) {
		return new ProductSearchPageObject(driver);
	}
	public static ProductDetailsPageObject getProductDetailsPage(WebDriver driver) {
		return new ProductDetailsPageObject(driver);
	}
	public static CustomersPageObject getCustomersPage(WebDriver driver) {
		return new CustomersPageObject(driver);
	}
	public static CustomerEditPageObject getCustomerEditPage(WebDriver driver) {
		return new CustomerEditPageObject(driver);
	}
	public static AddressPageObject getAddressPage(WebDriver driver) {
		return new AddressPageObject(driver);
	}

}
