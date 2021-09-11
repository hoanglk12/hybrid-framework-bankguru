package pageUIs.user.nopCommerce;

public class BasePageUI {
	public static final String GENDER_MALE_RADIO = "//input[@id='gender-male']";
	public static final String GENDER_FEMALE_RADIO = "//input[@id='gender-female']";
	public static final String FIRSTNAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LASTNAME_TEXTBOX = "//input[@id='LastName']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String DAY_DROPDOWN = "//select[@name='DateOfBirthDay']";
	public static final String MONTH_DROPDOWN = "//select[@name='DateOfBirthMonth']";
	public static final String YEAR_DROPDOWN = "//select[@name='DateOfBirthYear']";
	public static final String COMPANY_TEXTBOX = "//input[@id='Company']";
	public static final String DYNAMIC_LOCATOR_FOOTER = "//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_INPUT_LOCATOR = "//input[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_TEXTAREA_BY_ID = "//textarea[@id='%s']";
	public static final String DYNAMIC_CHECKBOX_RADIO_BY_TEXT = "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_MENU_BY_TEXT = "//ul[contains(@class,'notmobile')]//a[contains(text(),'%s')]";
	public static final String DYNAMIC_SUBMENU_BY_TEXT = "//ul[contains(@class,'notmobile')]//ul[contains(@class,'sublist')]//a[contains(text(),'%s')]";
	public static final String PRODUCT_TITLE_LINK_BY_TEXT = "//h2[@class='product-title']/a[contains(.,'%s')]";
	public static final String PRODUCT_TITLE_LINK = "//h2[@class='product-title']/a";
	public static final String PRODUCT_PRICES = "//div[@class='prices']/span";
	public static final String DYNAMIC_BUTTON_BY_PRODUCT = "//h2[contains(.,'%s')]/following-sibling::div[@class='add-info']//button[text()='%s']";
	public static final String TABLE_HEADER_BY_ID_AND_NAME = "//table[@class='%s']//th[.='%s']/preceding-sibling::th";
	public static final String TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX = "//table[@class='%s']/tbody//tr[%s]//td[%s]/%s";
	
}
