package pageUIs.admin.nopCommerce;

public class CustomerEditPageUI {
	
	public static final String PRODUCT_NAME_TEXTBOX = "//input[@id='SearchProductName']";
	public static final String DYNAMIC_ADMIN_SEARCH_DROPDOWN = "//select[@id='%s']";
	public static final String CATALOG_MENU = "//ul[@role='menu' and @data-widget='treeview']//p[contains(text(),'Catalog') and not(contains(text(),'settings'))]";
	public static final String PRODUCTS_SUBMENU = "//ul[@role='menu' and @data-widget='treeview']//p[contains(text(),'Products') and not(contains(text(),'never purchased'))]";
	public static final String SEARCH_PRODUCT_BUTTON = "//button[@id='search-products']";
	public static final String DYNAMIC_ROW_VALUE = "//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(@class,'text-center')]/i";
	public static final String NO_DATA_MSG = "//tr[@class='odd']/td[text()='No data available in table']";
	public static final String PRODUCT_IMG = "//table[@id='products-grid']//img";
	public static final String DYNAMIC_PRODUCT_CHECKBOX = "//td[text()='%s']/preceding-sibling::td[contains(@class,'text-center')]/input[@name='checkbox_products']";
	public static final String CUSTOMER_ROLES_PARENT_LOCATOR = "//ul[@id='SelectedCustomerRoleIds_taglist']/following-sibling::input";
	public static final String CUSTOMER_ROLES_CHILD_LOCATOR = "//ul[@data-role='staticlist' and @aria-hidden='false']//li";
	public static final String CUSTOMER_ROLES_ITEM_SELECTED = "//li[@role='option']/span[not(@title)]";
	public static final String DYNAMIC_ROW_CUSTOMER_ADDRESS = "//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/div[normalize-space(.) = '%s%s%s%s%s%s']";
	public static final String NO_DATA_ADDRESS_MSG = "//table[@id='customer-addresses-grid']//tr[@class='odd']/td[text()='No data available in table']";
}
