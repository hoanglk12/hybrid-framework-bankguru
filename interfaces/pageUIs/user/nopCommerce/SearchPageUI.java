package pageUIs.user.nopCommerce;

public class SearchPageUI {
	public static final String SEARCH_TEXTBOX = "//input[@id='q']";
	public static final String SEARCH_BUTTON = "//div[@class='buttons']//button[text()='Search']";
	public static final String ADD_YOUR_REVIEW_LINK = "//a[text()='Add your review']";
	public static final String SUBMIT_REVIEW_BUTTON = "//div[@class='buttons']/button[text()='Submit review']";
	public static final String DYNAMIC_ERROR_MESSAGE_SEARCH = "//div[@class='products-wrapper']/div[@class='%s']";
	public static final String SKU_TEXT = "//span[text()='SKU:']/following-sibling::span";
	public static final String PRODUCT_PRICE_TEXT = "//div[@class='product-price']/span";
	public static final String QUANTITY_TEXTBOX = "//div[@class='add-to-cart']//input";
	public static final String ADD_TO_WISHLIST_BUTTON = "//div[@class='add-to-wishlist']/button";
	public static final String ADD_TO_COMPARE_LIST_BUTTON = "//div[@class='compare-products']//input";
	public static final String SUCCESS_ADDED_WISHLIST_MSG = "//p[@class='content' and contains(.,'The product has been added to your wishlist')]";
}
