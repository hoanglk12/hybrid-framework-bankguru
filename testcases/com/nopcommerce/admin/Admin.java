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
	String email, password, productName, sku, price, stockQuantity,
	customerEmail, customerPassword, customerFirstName, customerLastName,
	customerDOB, customerCompanyName, customerRoles, customerAdminComment,
	customerEditEmail, customerEditFirstName, customerEditLastName, customerEditDOB,
	customerEditCompanyName, customerEditAdminComment;
	
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
		customerEmail = "automationfc_" + generateEmail();
		customerPassword = "123456";
		customerFirstName = "Automation";
		customerLastName = "FC";
		customerDOB = "1/1/2000";
		customerCompanyName = customerFirstName + " " + customerLastName;
		customerRoles = "Guests";
		customerAdminComment = "Add new Customer (Guests)";
		customerEditEmail = "edit_" + customerEmail;
		customerEditFirstName = "Edit" + " " + customerFirstName;
		customerEditLastName = "Edit" + " " + customerLastName;
		customerEditDOB = "2/2/2000";
		customerEditCompanyName = "Edit" + " " + customerCompanyName;
		customerEditAdminComment = "Edit" + " " + customerAdminComment;
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
		productSearchPage.openExpandIconSearchForm(driver, "class");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		Assert.assertTrue(productSearchPage.isRowValueDisplayed(driver, productName,sku,price,stockQuantity));
		Assert.assertEquals(productSearchPage.getTotalEditButton(driver), 1);
	}
	@Test
	public void Admin_02_Search_With_Product_Name_Parent_Category_Unchecked() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.openExpandIconSearchForm(driver, "class");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.selectItemInDropdownByAttributeId("Computers","SearchCategoryId");
		productSearchPage.clickToSearchButton();
		
		Assert.assertTrue(productSearchPage.isNoDataMsgDisplayed());
		
	}
	@Test
	public void Admin_03_Search_With_Product_Name_Parent_Category_Checked() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.openExpandIconSearchForm(driver, "class");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.selectItemInDropdownByAttributeId("Computers","SearchCategoryId");
		productSearchPage.checkToCheckboxOrRadioByName(driver, "SearchIncludeSubCategories");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		Assert.assertTrue(productSearchPage.isRowValueDisplayed(driver, productName,sku,price,stockQuantity));
		Assert.assertEquals(productSearchPage.getTotalEditButton(driver), 1);
	}
	@Test
	public void Admin_04_Search_With_Product_Name_Child_Category() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.openExpandIconSearchForm(driver, "class");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.selectItemInDropdownByAttributeId("Computers >> Desktops","SearchCategoryId");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		Assert.assertTrue(productSearchPage.isRowValueDisplayed(driver, productName,sku,price,stockQuantity));
		Assert.assertEquals(productSearchPage.getTotalEditButton(driver), 1);
	}
	@Test
	public void Admin_05_Search_With_Product_Name_Child_Category() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.openExpandIconSearchForm(driver, "class");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		productSearchPage.selectItemInDropdownByAttributeId("All","SearchCategoryId");
		productSearchPage.selectItemInDropdownByAttributeId("Apple","SearchManufacturerId");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		Assert.assertTrue(productSearchPage.isNoDataMsgDisplayed());
	}
	@Test
	public void Admin_06_Go_Directly_To_SKU() {
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.openExpandIconSearchForm(driver, "class");
		productSearchPage.enterToTextboxByName(driver, sku, "GoDirectlyToSku");
		productSearchPage.clickToButtonByIdAttribute(driver, "go-to-product-by-sku");
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		productSearchPage.sleepInSecond(1);
		productDetailsPage.openExpandIconByCardTitle(driver, "class","Product info");
		
		Assert.assertTrue(productDetailsPage.getAttributeValueFromTextboxByName(driver, "value", "Name").equals(productName));
	}
	@Test
	public void Admin_07_Create_New_Customer() {
		productDetailsPage.refreshCurrentPage(driver);
		dashboardPage.sleepInSecond(1);
		dashboardPage = productDetailsPage.openDashboardMenu("Dashboard");
		dashboardPage.sleepInSecond(1);
		dashboardPage.openMenuSubMenuByName("Customers", "Customers");
		customersPage = PageGeneratorManager.getCustomersPage(driver);
		customersPage.sleepInSecond(1);
		customersPage.clickToButtonLinkByName("Add new");
		customersPage.sleepInSecond(1);
		customerEditPage = PageGeneratorManager.getCustomerEditPage(driver);
		customerEditPage.openExpandIconByCardTitle(driver, "class","Customer info");
		customerEditPage.closeDefaultItemOfCustomerRoles(driver);
		customerEditPage.enterToTextboxByName(driver, customerEmail, "Email");
		customerEditPage.enterToTextboxByName(driver, customerPassword, "Password");
		customerEditPage.enterToTextboxByName(driver, customerFirstName, "FirstName");
		customerEditPage.enterToTextboxByName(driver, customerLastName, "LastName");
		customerEditPage.checkToCheckboxOrRadioByName(driver, "Gender_Male");
		customerEditPage.enterToTextboxByName(driver, customerDOB, "DateOfBirth");
		customerEditPage.enterToTextboxByName(driver, customerCompanyName, "Company");
		customerEditPage.checkToCheckboxOrRadioByName(driver, "IsTaxExempt");
		customerEditPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		customerEditPage.checkToCheckboxOrRadioByName(driver, "Active");
		customerEditPage.enterToTextAreaByAttributeId(driver, customerAdminComment, "AdminComment");
		customerEditPage.clickToSaveAndContinueEditButton();
		customerEditPage.sleepInSecond(1);
		
		Assert.assertTrue(customerEditPage.isAddedEditedSuccessMsgDisplayed("The new customer has been added successfully."));
		
		customerEditPage.clickToButtonLinkByName(driver, "back to customer list");
		customersPage = PageGeneratorManager.getCustomersPage(driver);
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		
		Assert.assertTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
	}
	@Test
	public void Admin_08_Search_Customer_With_Email() {
		customersPage.refreshCurrentPage(driver);
		customersPage.sleepInSecond(1);
		customersPage.enterToTextboxByName(driver, customerEmail, "SearchEmail");
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		customersPage.sleepInSecond(1);
		
		Assert.assertTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
		Assert.assertEquals(customersPage.getTotalEditButton(driver),1);
		
	}
	@Test
	public void Admin_09_Search_Customer_With_FirstName_LastName() {
		customersPage.refreshCurrentPage(driver);
		customersPage.sleepInSecond(1);
		customersPage.enterToTextboxByName(driver, customerFirstName, "SearchFirstName");
		customersPage.enterToTextboxByName(driver, customerLastName, "SearchLastName");
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		customersPage.sleepInSecond(3);
		
		Assert.assertTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
		Assert.assertEquals(customersPage.getTotalEditButton(driver),1);
	}
	@Test
	public void Admin_10_Search_Customer_With_Company() {
		customersPage.refreshCurrentPage(driver);
		customersPage.sleepInSecond(1);
		customersPage.enterToTextboxByName(driver, customerCompanyName, "SearchCompany");
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		customersPage.sleepInSecond(3);
		
		Assert.assertTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
		Assert.assertEquals(customersPage.getTotalEditButton(driver),1);
		
	}
	@Test
	public void Admin_11_Search_Customer_With_Full_Data() {
		customersPage.refreshCurrentPage(driver);
		customersPage.sleepInSecond(1);
		customersPage.enterToTextboxByName(driver, customerEmail, "SearchEmail");
		customersPage.enterToTextboxByName(driver, customerFirstName, "SearchFirstName");
		customersPage.enterToTextboxByName(driver, customerLastName, "SearchLastName");
		customersPage.selectItemInDropdownByAttributeName(driver,"1","SearchMonthOfBirth");
		customersPage.selectItemInDropdownByAttributeName(driver,"1","SearchDayOfBirth");
		customersPage.enterToTextboxByName(driver, customerCompanyName, "SearchCompany");
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		customersPage.sleepInSecond(1);
		
		Assert.assertTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
		Assert.assertEquals(customersPage.getTotalEditButton(driver),1);
		
	}
	@Test
	public void Admin_12_Edit_Customer() {
		customersPage.refreshCurrentPage(driver);
		customersPage.sleepInSecond(1);
		customersPage.enterToTextboxByName(driver, customerEmail, "SearchEmail");
		customersPage.enterToTextboxByName(driver, customerFirstName, "SearchFirstName");
		customersPage.enterToTextboxByName(driver, customerLastName, "SearchLastName");
		customersPage.selectItemInDropdownByAttributeName(driver,"1","SearchMonthOfBirth");
		customersPage.selectItemInDropdownByAttributeName(driver,"1","SearchDayOfBirth");
		customersPage.enterToTextboxByName(driver, customerCompanyName, "SearchCompany");
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		customersPage.sleepInSecond(1);
		customersPage.clickToEditButtonByRow(driver, "Guest", "Automation FC", customerRoles, customerCompanyName);
		customersPage.sleepInSecond(1);
		
		customerEditPage = PageGeneratorManager.getCustomerEditPage(driver);
		customerEditPage.openExpandIconByCardTitle(driver, "class","Customer info");
		customerEditPage.enterToTextboxByName(driver, customerEditEmail, "Email");
		customerEditPage.enterToTextboxByName(driver, customerEditFirstName, "FirstName");
		customerEditPage.enterToTextboxByName(driver, customerEditLastName, "LastName");
		customerEditPage.enterToTextboxByName(driver, customerEditDOB, "DateOfBirth");
		customerEditPage.enterToTextboxByName(driver, customerEditCompanyName, "Company");
		customerEditPage.enterToTextAreaByAttributeId(driver, customerEditAdminComment, "AdminComment");
		customerEditPage.clickToSaveButton();
		customerEditPage.sleepInSecond(1);
		
		Assert.assertTrue(customerEditPage.isAddedEditedSuccessMsgDisplayed("The customer has been updated successfully."));
		
		customersPage = PageGeneratorManager.getCustomersPage(driver);
		customersPage.refreshCurrentPage(driver);
		customersPage.sleepInSecond(1);
		customersPage.enterToTextboxByName(driver, customerEditEmail, "SearchEmail");
		customersPage.enterToTextboxByName(driver, customerEditFirstName, "SearchFirstName");
		customersPage.enterToTextboxByName(driver, customerEditLastName, "SearchLastName");
		customersPage.selectItemInDropdownByAttributeName(driver,"2","SearchMonthOfBirth");
		customersPage.selectItemInDropdownByAttributeName(driver,"2","SearchDayOfBirth");
		customersPage.enterToTextboxByName(driver, customerEditCompanyName, "SearchCompany");
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		customersPage.sleepInSecond(1);
		
		Assert.assertTrue(customersPage.isRowValueDisplayed(driver, "Guest", customerEditFirstName + " " + customerEditLastName, customerRoles, customerEditCompanyName));
		Assert.assertEquals(customersPage.getTotalEditButton(driver),1);
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
