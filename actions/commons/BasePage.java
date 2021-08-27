package commons;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public void getPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	public Alert waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, timeOut);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	public void acceptAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.accept();
	}
	public void cancelAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.dismiss();
	}
	public String getTextAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		return alert.getText();
	}
	public void sendkeyToAlert(WebDriver driver, String value) {
		alert = waitForAlertPresence(driver);
		alert.sendKeys(value);
	}
	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String windowId : allWindowIds) {
			driver.switchTo().window(windowId);
			String currentWindowTitle = driver.getTitle();
			if(currentWindowTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
	public void switchToWindowById(WebDriver driver, String parentWindowId) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String windowId : allWindowIds) {
			if(!windowId.equals(parentWindowId)) {
				driver.switchTo().window(windowId);
				break;
			}
		}
	}
	public void closeAllWindowsWithoutParent(WebDriver driver, String parentWindowId) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String windowId : allWindowIds) {
			if(!windowId.equals(parentWindowId)) {
				driver.switchTo().window(windowId);
				driver.close();
				break;
			}
		}
		driver.switchTo().window(parentWindowId);
	}
	public void sleepInSecond(long timeOutSecond) {
		try {
			Thread.sleep(timeOutSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private Alert alert;
	private WebDriverWait explicitWait; 
	private long timeOut = 30;
}
