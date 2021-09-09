package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
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

import pageUIs.admin.nopCommerce.AdminBasePageUI;
import pageUIs.admin.nopCommerce.CustomersPageUI;
import pageUIs.admin.nopCommerce.ProductDetailsPageUI;
import pageUIs.bankGuru.BankGuruBasePageUI;
import pageUIs.user.nopCommerce.BasePageUI;
import pageUIs.user.nopCommerce.HomePageUI;

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
	public Set<Cookie> getAllCookies(WebDriver driver){
		return driver.manage().getCookies();
	}
	
	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies){
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
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
	public void clickToElementByAction(WebDriver driver, String locator, String...params) {
		WebElement element = getElement(driver, getDynamicLocator(locator, params));
		action = new Actions(driver);
		action.click(element).perform();
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
	public void enterOnElementInCustomDropdown(WebDriver driver, String locator, String expectedItem) {
		sendkeyToElement(driver, locator, expectedItem);
		sleepInSecond(1);
		getElement(driver, locator).sendKeys(Keys.ENTER);
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
	public int getElementSize(WebDriver driver, String locator, String...params) {
		return getElements(driver, getDynamicLocator(locator, params)).size();
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
	public void uncheckToCheckbox(WebDriver driver, String locator, String...params) {
		if(getElement(driver, getDynamicLocator(locator, params)).isSelected()) {
			getElement(driver, getDynamicLocator(locator, params)).click();
		}
	}
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeOut(driver, shortTimeOut);
		List<WebElement> elements = getElements(driver, locator);
		overrideGlobalTimeOut(driver, longTimeOut);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	public void overrideGlobalTimeOut(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
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
	public void hoverToElement(WebDriver driver, String locator, String...params) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, getDynamicLocator(locator, params))).perform();
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
	public void scrollToTopPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,0)");
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
	public void clickToElementByJS(WebDriver driver, String locator, String...params) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, getDynamicLocator(locator, params)));
	}
	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}
	public void scrollToElement(WebDriver driver, String locator, String...params) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, getDynamicLocator(locator, params)));
	}
	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",  getElement(driver, locator));
	}
	public void sendkeyToElementByJS(WebDriver driver, String locator, String value, String...params) {
		jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",  getElement(driver, getDynamicLocator(locator, params)));
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
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	public void waitForElementVisible(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}
	public void waitForAllElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}
	public void waitForAllElementVisible(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicLocator(locator, params))));
	}
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	public void waitForElementInvisible(WebDriver driver, String locator, String...params) {
		explicitWait = new WebDriverWait(driver, shortTimeOut);
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
	public void enterToTextboxByNameJs(WebDriver driver, String value, String textboxName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, textboxName);
		sendkeyToElementByJS(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, value, textboxName);
	}
	public void checkToCheckboxOrRadioByName(WebDriver driver, String checboxAndRadioName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, checboxAndRadioName);
		checkToCheckboxOrRadio(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, checboxAndRadioName);
	}
	public void uncheckToCheckboxByName(WebDriver driver, String checboxName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, checboxName);
		uncheckToCheckbox(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, checboxName);
	}
	public String getAttributeValueFromTextboxByName(WebDriver driver, String attributeValue, String textboxName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, textboxName);
		return getAttributeValue(driver, BasePageUI.DYNAMIC_TEXTBOX_INPUT_LOCATOR, attributeValue, textboxName);
	}
	public void clickToButtonByIdAttribute(WebDriver driver, String idValue) {
		waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_BUTTON_INPUT, idValue);
		clickToElement(driver, AdminBasePageUI.DYNAMIC_BUTTON_INPUT, idValue);
	}
	public void clickToButtonByNameAttribute(WebDriver driver, String nameValue) {
		waitForElementClickable(driver, BankGuruBasePageUI.DYNAMIC_BUTTON_BY_NAME, nameValue);
		clickToElement(driver, BankGuruBasePageUI.DYNAMIC_BUTTON_BY_NAME, nameValue);
	}
	public void openExpandIconByCardTitle(WebDriver driver, String attribute, String cardTitle) {
		waitForElementClickable(driver, ProductDetailsPageUI.DYNAMIC_EXPAND_ICON_BY_CARD_TITLE, cardTitle);
		if (getAttributeValue(driver, ProductDetailsPageUI.DYNAMIC_EXPAND_ICON_BY_CARD_TITLE, attribute, cardTitle).contains("fa-plus")) {
			clickToElement(driver, ProductDetailsPageUI.DYNAMIC_EXPAND_ICON_BY_CARD_TITLE, cardTitle);
		}
	}
	public void openExpandIconSearchForm(WebDriver driver, String attribute) {
		waitForElementClickable(driver, AdminBasePageUI.EXPAND_ICON_SEARCH_FORM);
		if (getAttributeValue(driver, AdminBasePageUI.EXPAND_ICON_SEARCH_FORM, attribute).contains("fa-angle-down")) {
			clickToElement(driver, AdminBasePageUI.EXPAND_ICON_SEARCH_FORM);
		}
	}
	public void selectItemInDropdownByAttributeName(WebDriver driver, String textExpected, String nameValueDropdown) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, nameValueDropdown);
		selectItemInDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, textExpected, nameValueDropdown);
	}
	public void enterToTextAreaByAttributeId(WebDriver driver, String value, String nameValueTextArea) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, nameValueTextArea);
		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTAREA_BY_ID, value, nameValueTextArea);
	}
	public void clickToEditButtonByRow(WebDriver driver, String email, String name, String customerRoles, String companyName) {
		waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_EDIT_BUTTON_BY_ROW_VALUE, email, name, customerRoles, companyName);
		clickToElement(driver, AdminBasePageUI.DYNAMIC_EDIT_BUTTON_BY_ROW_VALUE, email, name, customerRoles, companyName);
	}
	public void uploadMultipleFiles(WebDriver driver, String...fileNames) {
		String filePath = GlobalConstants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getElement(driver, AdminBasePageUI.UPLOAD_FILE_BUTTON).sendKeys(fullFileName);
	}
	public void closeDefaultItemOfCustomerRoles(WebDriver driver) {
		waitForElementVisible(driver, CustomersPageUI.CLOSE_ICON_CUSTOMER_ROLES_ITEM);
		if (isElementDisplayed(driver, CustomersPageUI.CLOSE_ICON_CUSTOMER_ROLES_ITEM)) {
			clickToElementByJS(driver, CustomersPageUI.CLOSE_ICON_CUSTOMER_ROLES_ITEM);
		}
	}
	public void clickToButtonLinkByName(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, CustomersPageUI.DYNAMIC_BUTTON_LINK, buttonName);
		if (driver.toString().contains("firefox")) {
			clickToElementByJS(driver, CustomersPageUI.DYNAMIC_BUTTON_LINK, buttonName);
		}
		clickToElement(driver, CustomersPageUI.DYNAMIC_BUTTON_LINK, buttonName);
	}
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_BUTTON_INPUT_BY_TEXT, buttonText);
		if (driver.toString().contains("firefox")) {
			clickToElementByJS(driver, AdminBasePageUI.DYNAMIC_BUTTON_INPUT_BY_TEXT, buttonText);
		}
		clickToElement(driver, AdminBasePageUI.DYNAMIC_BUTTON_INPUT_BY_TEXT, buttonText);
	}
	public boolean isRowValueDisplayed(WebDriver driver, String firstRow, String secondRow, String thirdRow, String fourthRow) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_ROW_VALUE, firstRow, secondRow, thirdRow, fourthRow);
		return isElementDisplayed(driver, AdminBasePageUI.DYNAMIC_ROW_VALUE, firstRow, secondRow, thirdRow, fourthRow);
	}
	public int getTotalEditButton(WebDriver driver) {
		waitForAllElementVisible(driver, AdminBasePageUI.EDIT_BUTTON);
		return getElementSize(driver, AdminBasePageUI.EDIT_BUTTON);
	}
	public void clickToButtonByTextByJs(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_BUTTON_INPUT_BY_TEXT, buttonName);
		clickToElementByJS(driver, AdminBasePageUI.DYNAMIC_BUTTON_INPUT_BY_TEXT, buttonName);
	}
	public void hoverToMenuByText(WebDriver driver, String menuText) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_MENU_BY_TEXT, menuText);
		if (driver.toString().contains("firefox")) {
			scrollToElement(driver,BasePageUI.DYNAMIC_MENU_BY_TEXT, menuText);
			hoverToElement(driver, BasePageUI.DYNAMIC_MENU_BY_TEXT, menuText);
		}
		hoverToElement(driver, BasePageUI.DYNAMIC_MENU_BY_TEXT, menuText);
		
	}
	
	public void clickToSubMenuByText(WebDriver driver, String subMenuText) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_SUBMENU_BY_TEXT, subMenuText);
		clickToElement(driver, BasePageUI.DYNAMIC_SUBMENU_BY_TEXT, subMenuText);
	}
	public List<String> getListProductTitles(WebDriver driver) {
		return getElements(driver, BasePageUI.PRODUCT_TITLE_LINK).stream().map(title->title.getText()).collect(Collectors.toList());
	}
	public List<String> getListProductPrices(WebDriver driver) {
		return getElements(driver, BasePageUI.PRODUCT_PRICES).stream().map(title->title.getText()).collect(Collectors.toList());
	}
	
	public boolean isListSortedByOrder(WebDriver driver, List<String> arrayList) {
		boolean isSortedByOrder = true;
		for (int i = 0; i < arrayList.size()-1; i++) {
			if (arrayList.get(i + 1).compareToIgnoreCase(arrayList.get(i)) < 0) {
				isSortedByOrder = false;
				break;
			}
			isSortedByOrder = true;
		}
		return isSortedByOrder;

	}
	public boolean isListSortedByReverseOrder(WebDriver driver, List<String> arrayList) {
		boolean isSortedByReverseOrder = true;
		for (int i = 0; i < arrayList.size()-1; i++) {
			if (arrayList.get(i + 1).compareToIgnoreCase(arrayList.get(i)) > 0) {
				isSortedByReverseOrder = false;
				break;
			}
			isSortedByReverseOrder = true;

		}
		return isSortedByReverseOrder;

	}
	public void clickToLinkText(WebDriver driver, String textLink) {
		waitForElementClickable(driver, BankGuruBasePageUI.DYNAMIC_LINK_BY_TEXT, textLink);
		clickToElement(driver, BankGuruBasePageUI.DYNAMIC_LINK_BY_TEXT, textLink);
	}
	public void enterToTextBoxByTextTagAndName(WebDriver driver, String value, String text, String tagType, String nameAttribute) {
		waitForElementVisible(driver, BankGuruBasePageUI.DYNAMIC_TEXTBOX_TEXTAREA, text, tagType, nameAttribute);
		sendkeyToElement(driver, BankGuruBasePageUI.DYNAMIC_TEXTBOX_TEXTAREA, value, text, tagType, nameAttribute);;
	}
	public void clickToPaginationLinkByText(WebDriver driver, String textPage) {
		waitForElementVisible(driver, HomePageUI.PAGINATION_LINK_BY_TEXT, textPage);
		clickToElement(driver, HomePageUI.PAGINATION_LINK_BY_TEXT, textPage);
	}
	private Alert alert;
	private WebDriverWait explicitWait; 
	private long timeOut = 30;
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;
	private Select select;
	private JavascriptExecutor jsExecutor;
	private Actions action;
}

