package commons;

import java.io.File;

public class GlobalConstants {
	public static final String DEV_URL = "https://demo.nopcommerce.com/";
	public static final long SHORT_TIMEOUT = 7;
	public static final long LONG_TIMEOUT = 30;
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FOLDER_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FOLDER_PATH = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOGS_FOLDER_PATH = PROJECT_PATH + File.separator + "browserLogs";
	
	public static final String BROWSER_USERNAME = "hoangpham_scY9vm";
	public static final String BROWSER_AUTOMATE_KEY = "84zwepVtsPqAEXxVApGh";
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	public static final String SAUCE_LABS_USERNAME = "oauth-hoangrvp12-d58a8";
	public static final String SAUCE_LABS_AUTOMATE_KEY = "875cae56-d164-4bae-9672-ccf3c12c06c1";
	public static final String SAUCE_LABS_URL = "https://" + SAUCE_LABS_USERNAME + ":" + SAUCE_LABS_AUTOMATE_KEY + "@ondemand.apac-southeast-1.saucelabs.com:443/wd/hub";
}
