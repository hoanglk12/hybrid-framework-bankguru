package pageUIs.user.nopCommerce;

public class HomePageUI {
	public static final String HOME_PAGE_SLIDER = "//div[@id='nivo-slider']";
	public static final String REGISTER_LINK = "//a[@class='ico-register']";
	public static final String LOGIN_LINK = "//a[@class='ico-login']";
	public static final String PAGINATION = "//div[@class='pager']//a";
	public static final String NEXT_ICON_PAGING = "//div[@class='pager']//a[text()='Next']";
	public static final String PREVIOUS_ICON_PAGING = "//div[@class='pager']//a[text()='Previous']";
	public static final String PAGINATION_LINK_BY_TEXT = "//div[@class='pager']//a[text()='%s']";
	public static final String SUCCESS_MSG_ADDED_COMPARE_LIST = "//p[@class='content' and contains(.,'The product has been added to your product comparison')]";
	public static final String DYNAMIC_PRODUCT_PRICE_BY_TEXT = "//h2[contains(.,'%s')]/following-sibling::div[@class='add-info']/div[@class='prices']/span";

}
