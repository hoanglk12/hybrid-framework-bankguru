package pageUIs.admin.nopCommerce;

public class DashboardPageUI {
	public static final String DYNAMIC_ADMIN_SEARCH_DROPDOWN = "//select[@id='%s']";
	public static final String DASHBOARD_HEADER = "//div[@class='content-header']/h1";
	public static final String CATALOG_MENU = "//ul[@role='menu' and @data-widget='treeview']//p[contains(text(),'Catalog') and not(contains(text(),'settings'))]";
	public static final String PRODUCTS_SUBMENU = "//ul[@role='menu' and @data-widget='treeview']//p[contains(text(),'Products') and not(contains(text(),'never purchased'))]";
	public static final String DYNAMIC_MENU = "//ul[@role='menu']/li/a/p[contains(text(),'%s')]";
	public static final String DYNAMIC_SUBMENU = "//ul[contains(@class,'treeview') and @style]//p[contains(text(),'%s')]";
}
