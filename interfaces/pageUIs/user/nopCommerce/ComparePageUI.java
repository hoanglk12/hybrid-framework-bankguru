package pageUIs.user.nopCommerce;

public class ComparePageUI {
	public static final String REMOVE_ICONS = "//table[@class='compare-products-table']//button[text()='Remove']";
	public static final String DYNAMIC_PRODUCT_NAME_BY_TEXT = "//table[@class='compare-products-table']//td[.='Name']/following-sibling::td[.='%s']";
	public static final String DYNAMIC_PRODUCT_PRICE_BY_TEXT = "//table[@class='compare-products-table']//td[.='Price']/following-sibling::td[.='%s']";
	public static final String EMPTY_COMPARE_LIST_MSG = "//div[@class='page-body']/div[@class='no-data']";
}
