package pageUIs.nopCommerce;

public class AdminPageUI {
	
	public static final String PRODUCT_NAME_TEXTBOX = "//input[@id='SearchProductName']";
	public static final String DYNAMIC_ADMIN_SEARCH_DROPDOWN = "//select[@id='%s']";
	public static final String DASHBOARD_HEADER = "//div[@class='content-header']/h1";
	public static final String CATALOG_MENU = "//ul[@role='menu' and @data-widget='treeview']//p[contains(text(),'Catalog') and not(contains(text(),'settings'))]";
	public static final String PRODUCTS_SUBMENU = "//ul[@role='menu' and @data-widget='treeview']//p[contains(text(),'Products') and not(contains(text(),'never purchased'))]";
	public static final String SEARCH_PRODUCT_BUTTON = "//button[@id='search-products']";
	public static final String DYNAMIC_ROW_VALUE = "//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(@class,'text-center')]/i";
	public static final String NO_DATA_MSG = "//tr[@class='odd']/td[text()='No data available in table']";
	public static final String PRODUCT_IMG = "//table[@id='products-grid']//img";
}
