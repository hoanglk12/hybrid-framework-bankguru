package pageUIs.user.nopCommerce;

public class RegisterPageUI {
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "//button[@id='register-button']";
	public static final String SUCCESS_MESSAGE = "//div[@class='result' and text()='Your registration completed']";
	public static final String LOGOUT_LINK = "//a[text()='Log out']";
	public static final String EMPTY_FIRSTNAME_ERROR_MSG = "//label[text()='First name:']//following-sibling::span[@class='field-validation-error']/span";
	public static final String EMPTY_LASTNAME_ERROR_MSG = "//label[text()='Last name:']//following-sibling::span[@class='field-validation-error']/span";
	public static final String EMPTY_EMAIL_ERROR_MSG = "//label[text()='Email:']//following-sibling::span[@class='field-validation-error']/span";
	public static final String EMPTY_PASS_ERROR_MSG = "//label[text()='Password:']//following-sibling::span[@class='field-validation-error']/span";
	public static final String EMPTY_CONFIRM_PASS_ERROR_MSG = "//label[text()='Confirm password:']//following-sibling::span[@class='field-validation-error']/span";
	public static final String INVALID_EMAIL_ERROR_MSG = "//div[@class='page-body']//li";
	public static final String EXIST_EMAIL_ERROR_MSG = "//div[@class='page-body']//li";
	public static final String PASS_LESS_THAN_SIX_ERROR_MSG = "//span[@class='field-validation-error' and contains(.,'Password must meet the following rules: must have at least 6 characters')]";
	public static final String PASS_NOT_MATCH_CONFIRMPASS_ERROR_MSG = "//label[text()='Confirm password:']//following-sibling::span[@class='field-validation-error']/span";
	
}