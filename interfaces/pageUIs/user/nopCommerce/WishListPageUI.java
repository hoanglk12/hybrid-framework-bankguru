package pageUIs.user.nopCommerce;

public class WishListPageUI {
	public static final String DYNAMIC_ROW_WISHLIST = "//td[@class='sku' and contains(.,'%s')]/following-sibling::td[@class='product' and contains(.,'%s')]/following-sibling::td[@class='unit-price' and contains(.,'$%s')]/following-sibling::td[@class='quantity']/input[@value='%s']";
	public static final String DYNAMIC_ROW_TOTAL_WISHLIST = "td[@class='subtotal' and contains(.,'%s')]";
	public static final String WISHLIST_SHARE_URL = "//span[text()='Your wishlist URL for sharing:']/following-sibling::a";
	public static final String WISHLIST_HEADER_TEXT = "//div[@class='page-title']/h1";
	public static final String DYNAMIC_LINK_TEXT = "//a[contains(.,'%s')]";
	public static final String EMPTY_WISHLIST_MSG = "//div[@class='page-body']/div[@class='no-data']";
	public static final String DYNAMIC_PRODUCT_VALUE_BY_ROW = "//table//td[.='%s']/following-sibling::td[.='%s']";
	public static final String REMOVE_ICON_COMPARE_TABLE = "//table//td[.='Remove']";
}
