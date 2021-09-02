package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.admin.nopCommerce.CustomerEditPageObject;
import pageObjects.admin.nopCommerce.CustomersPageObject;
import pageObjects.admin.nopCommerce.DashboardPageObject;
import pageObjects.admin.nopCommerce.LoginPageObject;
import pageObjects.admin.nopCommerce.PageGeneratorManager;
import pageObjects.admin.nopCommerce.ProductDetailsPageObject;
import pageObjects.admin.nopCommerce.ProductSearchPageObject;

public class Admin extends BaseTest {
	WebDriver driver;
	String email, password, productName, sku, price, stockQuantity;
	
	@Parameters({ "browser", "urlAdmin" })
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerceAdmin) {
		driver = getBrowser(browserName, urlNopCommerceAdmin);
		email = "admin@yourstore.com";		
		password = "admin";
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		sku = "LE_IC_600";
		price = "500";
		stockQuantity = "10000";
	}
	@Test
	public void Admin_00_Login_To_Admin_Page() {
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToTextboxByName(driver, email, "Email");
		loginPage.enterToTextboxByName(driver, password, "Password");
		dashboardPage = loginPage.clickToLoginButton();
		
		Assert.assertTrue(dashboardPage.isDashBoardHeaderDisplayed());
	}
	@Test
	public void Admin_01_Search_With_Product_Name() {
		dashboardPage.openMenuSubMenuByName("Catalog", "Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		Assert.assertTrue(productSearchPage.isRowValueDisplayed(productName,sku,price,stockQuantity));
		Assert.assertEquals(productSearchPage.getTotalImageProduct(), 1);
	}
	@Test
	public void Admin_02_Search_With_Product_Name_Parent_Category_Unchecked() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.selectItemInDropdownByName("Computers","SearchCategoryId");
		productSearchPage.clickToSearchButton();
		
		Assert.assertTrue(productSearchPage.isNoDataMsgDisplayed());
		
	}
	@Test
	public void Admin_03_Search_With_Product_Name_Parent_Category_Checked() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.selectItemInDropdownByName("Computers","SearchCategoryId");
		productSearchPage.checkToCheckboxOrRadioByName(driver, "SearchIncludeSubCategories");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		Assert.assertTrue(productSearchPage.isRowValueDisplayed(productName,sku,price,stockQuantity));
		Assert.assertEquals(productSearchPage.getTotalImageProduct(), 1);
	}
	@Test
	public void Admin_04_Search_With_Product_Name_Child_Category() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.selectItemInDropdownByName("Computers >> Desktops","SearchCategoryId");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		Assert.assertTrue(productSearchPage.isRowValueDisplayed(productName,sku,price,stockQuantity));
		Assert.assertEquals(productSearchPage.getTotalImageProduct(), 1);
	}
	@Test
	public void Admin_05_Search_With_Product_Name_Child_Category() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.selectItemInDropdownByName("All","SearchCategoryId");
		productSearchPage.selectItemInDropdownByName("Apple","SearchManufacturerId");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		Assert.assertTrue(productSearchPage.isNoDataMsgDisplayed());
	}
	@Test
	public void Admin_06_Go_Directly_To_SKU() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.enterToTextboxByName(driver, sku, "GoDirectlyToSku");
		productSearchPage.clickToButtonByIdAttribute(driver, "go-to-product-by-sku");
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		productDetailsPage.openExpandIconByCardTitle(driver, "class","Product info");
		
		Assert.assertTrue(productDetailsPage.getAttributeValueFromTextboxByName(driver, "value", "Name").equals(productName));
	}
	@Test
	public void Admin_07_Create_New_Customer() {
		productDetailsPage.refreshCurrentPage(driver);
		dashboardPage = productDetailsPage.openDashboardMenu("Dashboard");
		dashboardPage.sleepInSecond(1);
		dashboardPage.openMenuSubMenuByName("Customers", "Customers");
		customersPage = PageGeneratorManager.getCustomersPage(driver);
		customersPage.sleepInSecond(1);
		customersPage.closeDefaultItemOfCustomerRoles();
		customersPage.clickToButtonLinkByName("Add new");
		customersPage.sleepInSecond(1);
		customerEditPage = PageGeneratorManager.getCustomerEditPage(driver);
		customerEditPage.openExpandIconByCardTitle(driver, "class","Customer info");
		
	}

	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	CustomersPageObject customersPage;
	CustomerEditPageObject customerEditPage;
	ProductSearchPageObject productSearchPage;
	ProductDetailsPageObject productDetailsPage;
}
