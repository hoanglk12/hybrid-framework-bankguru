package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
	

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
	public static ComparePageObject getComparePage(WebDriver driver) {
		return new ComparePageObject(driver);
	}
	public static RecentlyViewedPageObject getRecentlyViewedPageObject(WebDriver driver) {
		return new RecentlyViewedPageObject(driver);
	}

}
