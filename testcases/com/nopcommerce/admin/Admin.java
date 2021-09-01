package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.nopCommerce.AdminPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;

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
		adminPage = PageGeneratorManager.getAdminPage(driver);
		adminPage.enterToTextboxByName(driver, email, "Email");
		adminPage.enterToTextboxByName(driver, password, "Password");
		adminPage.clickToLoginButton();
		
		Assert.assertTrue(adminPage.isDashBoardHeaderDisplayed());
	}
	@Test
	public void Admin_01_Search_With_Product_Name() {
		adminPage = PageGeneratorManager.getAdminPage(driver);
		adminPage.clickToCatalogMenuByJs();
		adminPage.clickToProductsSubMenu();
		adminPage.sleepInSecond(1);
		adminPage.enterToTextboxByName(driver, productName, "SearchProductName");
		adminPage.clickToSearchButton();
		
		Assert.assertTrue(adminPage.isRowValueDisplayed(productName,sku,price,stockQuantity));
		Assert.assertEquals(adminPage.getTotalImageProduct(), 1);
	}
	@Test
	public void Admin_02_Search_With_Product_Name_Parent_Category_Unchecked() {
		adminPage.refreshCurrentPage(driver);
		adminPage.sleepInSecond(1);
		adminPage.enterToTextboxByName(driver, productName, "SearchProductName");
		adminPage.selectItemInDropdownByName("Computers","SearchCategoryId");
		adminPage.clickToSearchButton();
		
		
		Assert.assertTrue(adminPage.isNoDataMsgDisplayed());
		
	}
	@Test
	public void Admin_03_Search_With_Product_Name_Parent_Category_Checked() {
		adminPage.refreshCurrentPage(driver);
		adminPage.sleepInSecond(1);
		adminPage.enterToTextboxByName(driver, productName, "SearchProductName");
		adminPage.selectItemInDropdownByName("Computers","SearchCategoryId");
		adminPage.checkToCheckboxOrRadioByName(driver, "SearchIncludeSubCategories");
		adminPage.clickToSearchButton();

		
		Assert.assertTrue(adminPage.isRowValueDisplayed(productName,sku,price,stockQuantity));
		Assert.assertEquals(adminPage.getTotalImageProduct(), 1);
	}
	

	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	AdminPageObject adminPage;
	BasePage basePage;
}
