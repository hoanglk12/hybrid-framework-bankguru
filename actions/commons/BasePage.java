package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.nopCommerce.BasePageUI;

public class BasePage {
	
	public static BasePage getBasePage()  {
		return new BasePage();
	}
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	public String getCurrentPageUrl(WebDriver driver) {
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
	public String getDynamicLocator(String locator, String...params) {
		return String.format(locator, (Object[])params);
	}
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}
	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}
	public void clickToElement(WebDriver driver, String locator, String...params) {
		getElement(driver, getDynamicLocator(locator, params)).click();
	}
	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}
	public void sendkeyToElement(WebDriver driver, String locator, String value, String...params) {
		getElement(driver, getDynamicLocator(locator, params)).clear();
		getElement(driver, getDynamicLocator(locator, params)).sendKeys(value);
	}
	public void selectItemInDropdown(WebDriver driver, String locator, String textItem) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(textItem);
	}
	public void selectItemInDropdown(WebDriver driver, String locator, String textItem, String...params) {
		select = new Select(getElement(driver, getDynamicLocator(locator, params)));
		select.selectByVisibleText(textItem);
	}
	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		clickToElement(driver, parentLocator);
		sleepInSecond(2);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}

		}

	}
	public String getAttributeValue(WebDriver driver, String locator, String attribute) {
		return getElement(driver, locator).getAttribute(attribute);
	}
	public String getAttributeValue(WebDriver driver, String locator, String attribute, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).getAttribute(attribute);
	}
	public String getTextElement(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}
	public String getTextElement(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).getText();
	}
	public String getCssValue(WebDriver driver, String locator, String cssValue) {
		return getElement(driver, locator).getCssValue(cssValue);
	}
	public String convertRgbaToHexa(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	public int getElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}
	
	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if(!getElement(driver, locator).isSelected()) {
			getElement(driver, locator).click();
		}
	}
	public void checkToCheckboxOrRadio(WebDriver driver, String locator, String...params) {
		if(!getElement(driver, getDynamicLocator(locator, params)).isSelected()) {
			getElement(driver, getDynamicLocator(locator, params)).click();
		}
	}
	
	public void uncheckToCheckbox(WebDriver driver, String locator) {
		if(getElement(driver, locator).isSelected()) {
			getElement(driver, locator).click();
		}
	}
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}
	public boolean isElementDisplayed(WebDriver driver, String locator, String...params) {
		return getElement(driver, getDynamicLocator(locator, params)).isDisplayed();
	}
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}
	public WebDriver switchToFrame(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}
	public WebDriver switchToDefaultContent(WebDriver driver, String locator) {
		return driver.switchTo().defaultContent();
	}
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}
	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}
	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}
	public void dragAndDrop(WebDriver driver, String sourceLocator, String targetlocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetlocator)).perform();
	}
	public void pressKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action.sendKeys(getElement(driver, locator),key).perform();
	}
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor)driver;
		return jsExecutor.executeScript(javaScript);
	}
	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor)driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor)driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}
	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}
	public void hightlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor)driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}
	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}
	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",  getElement(driver, locator));
	}
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
	}
	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor)driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}
	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor)driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
		return status;
	}
	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		jsExecutor = (JavascriptExecutor)driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	public boolean isJQueryAndAjaxIconLoadedSuccess(WebDriver driver) {
		jsExecutor = (JavascriptExecutor)driver;
		explicitWait = new WebDriverWait(driver, timeOut);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return $('.raDiv').is('visible')").toString().equals("false");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(ajaxIconLoading);
	}
	public boolean isJQueryAndPageLoadedSuccess(WebDriver driver) {
		jsExecutor = (JavascriptExecutor)driver;
		explicitWait = new WebDriverWait(driver, timeOut);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	public void waitForElementVisible(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}
	public void waitForAllElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	public void waitForElementInvisible(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	public void waitForElementClickable(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, timeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));
	}
	public void sleepInSecond(long timeOutSecond) {
		try {
			Thread.sleep(timeOutSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void handleUnexpectedAlert(WebDriver driver) {
		try {
			driver.switchTo().alert().dismiss();
			sleepInSecond(2);
		} catch (Exception e) {

		}
	}
	public void openPageFooterByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_LOCATOR_FOOTER, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_LOCATOR_FOOTER, pageName);
	}
	public void enterToTextboxByName(WebDriver driver, String value, String textboxName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, textboxName);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, value, textboxName);
	}
	public void checkToCheckboxOrRadioByName(WebDriver driver, String checboxAndRadioName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, checboxAndRadioName);
		clickToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, checboxAndRadioName);
	}
	public String getAttributeValueFromTextboxByName(WebDriver driver, String attributeValue, String textboxName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, textboxName);
		return getAttributeValue(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, attributeValue, textboxName);
	}
	private Alert alert;
	private WebDriverWait explicitWait; 
	private long timeOut = 30;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;
}

