package pageUIs.bankGuru;

public class BankGuruBasePageUI {
	public static final String DYNAMIC_LINK_BY_TEXT = "//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_TEXTAREA = "//td[text()='%s']/parent::tr//%s[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_NAME = "//input[@name='%s']";
	public static final String GENDER_MALE_RADIO = "//input[@value='m']";
	public static final String GENDER_FEMALE_RADIO = "//input[@value='f']";
	public static final String ERROR_MESSAGE = "//label[text()='%s']";
}
