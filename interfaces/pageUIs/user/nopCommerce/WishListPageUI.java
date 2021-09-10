package pageUIs.user.nopCommerce;

public class WishListPageUI {
	public static final String DYNAMIC_ROW_WISHLIST = "//td[@class='sku' and contains(.,'%s')]/following-sibling::td[@class='product' and contains(.,'%s')]/following-sibling::td[@class='unit-price' and contains(.,'$%s')]/following-sibling::td[@class='quantity']/input[@value='%s']";
	public static final String DYNAMIC_ROW_TOTAL_WISHLIST = "td[@class='subtotal' and contains(.,'%s')]";
}
