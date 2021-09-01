package pageUIs.user.nopCommerce;

public class LoginPageUI {
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String LOGIN_BUTTON = "//button[text()='Log in']";
	public static final String EMAIL_ERROR_MSG = "//span[@id='Email-error']";
	public static final String UNREG_EMAIL_EMPTY_PASS_ERROR_MSG = "//div[@class='message-error validation-summary-errors' and contains(.,'Login was unsuccessful. Please correct the errors and try again.No customer account found')]";
}
