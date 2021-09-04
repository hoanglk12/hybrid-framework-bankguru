package pageUIs.admin.nopCommerce;

public class AdminBasePageUI {
	public static final String UPLOAD_FILE_BUTTON = "//div[@class='upload-button-container']//input[@type='file']";
	public static final String DYNAMIC_BUTTON_INPUT = "//button[@id='%s']";
	public static final String DYNAMIC_BUTTON_INPUT_BY_TEXT = "//button[contains(text(),'%s')]";
	public static final String SAVE_BUTTON = "//button[contains(.,'Save') and @name='save']";
	public static final String DELETE_BUTTON_TABLE_DATA = "//a[contains(.,'Delete')]";
	public static final String SAVE_AND_CONTINUE_EDIT_BUTTON = "//button[contains(.,'Save and Continue Edit') and @name='save-continue']";
	public static final String DYNAMIC_MESSAGE_SUCCESS_CREATE_UPDATE = "//div[@class='content-wrapper']//div[contains(.,'%s')]";
	public static final String DYNAMIC_ROW_VALUE = "//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(@class,'text-center')]/i";
	public static final String DYNAMIC_EDIT_BUTTON_BY_ROW_VALUE = "//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(@class,'button-column')]/a[contains(text(),'Edit')]";
	public static final String EDIT_BUTTON = "//td[contains(@class,'button-column')]/a[contains(text(),'Edit')]";
	public static final String DYNAMIC_CHECKBOX_TABLE_BY_NAME = "//table[@id='%s']//input[@name='%s']";
	public static final String EXPAND_ICON_SEARCH_FORM = "//div[@class='icon-collapse']/i";
}
