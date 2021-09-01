package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
	
//	private static HomePageObject homePage;
//	private static LoginPageObject loginPage;
//	private static RegisterPageObject registerPage;
//	private static MyAccountPageObject myAccountPage;
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
	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}
	public static SearchPageObject getSearchPage(WebDriver driver) {
		return new SearchPageObject(driver);
	}
	public static OrdersPageObject getOrdersPage(WebDriver driver) {
		return new OrdersPageObject(driver);
	}
	public static WishlistPageObject getWishlistPage(WebDriver driver) {
		return new WishlistPageObject(driver);
	}
	public static AddressesPageObject getAddressesPage(WebDriver driver) {
		return new AddressesPageObject(driver);
	}
	public static AdminPageObject getAdminPage(WebDriver driver) {
		return new AdminPageObject(driver);
	}
}
