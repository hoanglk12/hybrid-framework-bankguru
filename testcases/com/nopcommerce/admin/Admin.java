package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
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
	customerEditCompanyName, customerEditAdminComment, customerAddressCountry, customerAddressState,
	customerAddressCity, customerAddressAddress1, customerAddressAddress2, customerAddressZip,
	customerAddressPhone, customerAddressFax, customerEditAddressCountry, customerEditAddressState,
	customerEditAddressCity, customerEditAddressAddress1, customerEditAddressAddress2, customerEditAddressZip,
	customerEditAddressPhone, customerEditAddressFax;
	
	@Parameters({ "browser", "urlAdmin" })
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerceAdmin) {
		log.info("Pre-Condition - Open browser '" + browserName + "' with url: '" + urlNopCommerceAdmin + "'");
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
		customerAddressCountry = "Viet Nam";
		customerAddressState = "Other";
		customerAddressCity = "Ho Chi Minh";
		customerAddressAddress1 = "743 Le Loi";
		customerAddressAddress2 = "453 Le Lai";
		customerAddressZip = "650000";
		customerAddressPhone = "0987654555";
		customerAddressFax = "+84987654555";
		customerEditAddressCountry = "United States";
		customerEditAddressState = "California";
		customerEditAddressCity = "Albany";
		customerEditAddressAddress1 = "123 PO Box";
		customerEditAddressAddress2 = "356 Los Blancos";
		customerEditAddressZip = "986589";
		customerEditAddressPhone = "0987654666";
		customerEditAddressFax = "+441619998888";
	}
	@Test
	public void Admin_00_Login_To_Admin_Page() {
		log.info("Admin_00 - Step 1: Open 'Admin Nopcommerce' page");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		log.info("Admin_00 - Step 2: Input data to Email textbox " + email);
		loginPage.enterToTextboxByName(driver, email, "Email");
		
		log.info("Admin_00 - Step 3: Input data to Password textbox " + password);
		loginPage.enterToTextboxByName(driver, password, "Password");
		
		log.info("Admin_00 - Step 4: Verify Dashboard Header Text is displayed");
		dashboardPage = loginPage.clickToLoginButton();
		verifyTrue(dashboardPage.isDashBoardHeaderDisplayed());
	}
	@Test
	public void Admin_01_Search_With_Product_Name() {
		log.info("Admin_01 - Step 1: At Dashboard Page, click to menu 'Catalog' and submenu 'Products'");
		dashboardPage.openMenuSubMenuByName("Catalog", "Products");
		productSearchPage = PageGeneratorManager.getProductSearchPage(driver);
		productSearchPage.sleepInSecond(1);
		
		productSearchPage.openExpandIconSearchForm(driver, "class");
		
		log.info("Admin_01 - Step 2: Input '" + productName + "' to textbox Product Name");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		
		log.info("Admin_01 - Step 3: Click to Seach button");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		log.info("Admin_01 - Step 4: Verify only 1 item displayed");
		verifyEquals(productSearchPage.getTotalEditButton(driver), 1);
		
		log.info("Admin_01 - Step 5: Verify data is displayed with '" + productName + "'" + ",'" + sku + "'" + ",'" + price + "'" + ",'" + stockQuantity +"'");
		verifyTrue(productSearchPage.isRowValueDisplayed(driver, productName,sku,price,stockQuantity));
		
	}
	@Test
	public void Admin_02_Search_With_Product_Name_Parent_Category_Unchecked() {
		log.info("Admin_02 - Step 1: Refresh Product Search Page");
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		
		productSearchPage.openExpandIconSearchForm(driver, "class");
		
		log.info("Admin_02 - Step 2: Input '" + productName + "' to textbox Product Name");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		
		log.info("Admin_02 - Step 3: Select 'Computers' from Dropdown Category");
		productSearchPage.selectItemInDropdownByAttributeId("Computers","SearchCategoryId");
		
		log.info("Admin_02 - Step 4: Click to Seach button");
		productSearchPage.clickToSearchButton();
		
		log.info("Admin_02 - Step 5: Verify No Data message is displayed");
		verifyTrue(productSearchPage.isNoDataMsgDisplayed());
		
	}
	@Test
	public void Admin_03_Search_With_Product_Name_Parent_Category_Checked() {
		log.info("Admin_03 - Step 1: Refresh Product Search Page");
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		productSearchPage.openExpandIconSearchForm(driver, "class");
		
		log.info("Admin_03 - Step 2: Input '" + productName + "' to textbox Product Name");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		
		log.info("Admin_03 - Step 3: Select 'Computers' from Dropdown Category");
		productSearchPage.selectItemInDropdownByAttributeId("Computers","SearchCategoryId");
		
		log.info("Admin_03 - Step 4: Check to checkbox Sub Categories");
		productSearchPage.checkToCheckboxOrRadioByName(driver, "SearchIncludeSubCategories");
		
		log.info("Admin_03 - Step 5: Click to Seach button");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		log.info("Admin_03 - Step 6: Verify only 1 item displayed");
		verifyEquals(productSearchPage.getTotalEditButton(driver), 1);
		
		log.info("Admin_03 - Step 7: Verify data is displayed with '" + productName + "'" + ",'" + sku + "'" + ",'" + price + "'" + ",'" + stockQuantity +"'");
		verifyTrue(productSearchPage.isRowValueDisplayed(driver, productName,sku,price,stockQuantity));
	}
	@Test
	public void Admin_04_Search_With_Product_Name_Child_Category() {
		log.info("Admin_04 - Step 1: Refresh Product Search Page");
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		
		productSearchPage.openExpandIconSearchForm(driver, "class");
		
		log.info("Admin_04 - Step 2: Input '" + productName + "' to textbox Product Name");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		
		log.info("Admin_04 - Step 3: Select 'Computers >> Desktops' from Dropdown Category");
		productSearchPage.selectItemInDropdownByAttributeId("Computers >> Desktops","SearchCategoryId");
		
		log.info("Admin_04 - Step 4: Click to Seach button");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(2);
		
		log.info("Admin_04 - Step 5: Verify only 1 item displayed");
		verifyEquals(productSearchPage.getTotalEditButton(driver), 1);
		
		log.info("Admin_03 - Step 6: Verify data is displayed with '" + productName + "'" + ",'" + sku + "'" + ",'" + price + "'" + ",'" + stockQuantity +"'");
		verifyTrue(productSearchPage.isRowValueDisplayed(driver, productName,sku,price,stockQuantity));
	}
	@Test
	public void Admin_05_Search_With_Product_Name_Manufacturer() {
		log.info("Admin_05 - Step 1: Refresh Product Search Page");
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		
		productSearchPage.openExpandIconSearchForm(driver, "class");
		
		log.info("Admin_05 - Step 2: Input '" + productName + "' to textbox Product Name");
		productSearchPage.enterToTextboxByName(driver, productName, "SearchProductName");
		
		log.info("Admin_05 - Step 3: Select 'All' from Dropdown Category");
		productSearchPage.selectItemInDropdownByAttributeId("All","SearchCategoryId");
		
		log.info("Admin_05 - Step 4: Select 'Apple' from Dropdown Manufacturer");
		productSearchPage.selectItemInDropdownByAttributeId("Apple","SearchManufacturerId");
		
		log.info("Admin_05 - Step 5: Click to Seach button");
		productSearchPage.clickToSearchButton();
		productSearchPage.sleepInSecond(1);
		
		log.info("Admin_05 - Step 6: Verify No Data message is displayed");
		verifyTrue(productSearchPage.isNoDataMsgDisplayed());
	}
	@Test
	public void Admin_06_Go_Directly_To_SKU() {
		log.info("Admin_06 - Step 1: Refresh Product Search Page");
		productSearchPage.refreshCurrentPage(driver);
		productSearchPage.sleepInSecond(1);
		
		productSearchPage.openExpandIconSearchForm(driver, "class");
		
		log.info("Admin_06 - Step 2: Input data to 'GoDirectlyToSku' textbox " + sku);
		productSearchPage.enterToTextboxByName(driver, sku, "GoDirectlyToSku");
		
		log.info("Admin_06 - Step 3: Click to button 'Go'");
		productSearchPage.clickToButtonByIdAttribute(driver, "go-to-product-by-sku");
		
		log.info("Admin_06 - Step 4: Navigate to Product Details page");
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		productDetailsPage.sleepInSecond(1);
		
		log.info("Admin_06 - Step 5: Click to expand icon at 'Product info'");
		productDetailsPage.openExpandIconByCardTitle(driver, "class","Product info");
		
		log.info("Admin_06 - Step 6: Verify data is displayed with '" + productName + "'");
		verifyTrue(productDetailsPage.getAttributeValueFromTextboxByName(driver, "value", "Name").equals(productName));
	}
	@Test
	public void Admin_07_Create_New_Customer() {
		log.info("Admin_07 - Step 1: Refresh Product Details Page");
		productDetailsPage.refreshCurrentPage(driver);
		productDetailsPage.sleepInSecond(1);
		
		log.info("Admin_07 - Step 2: Navigate to Dashboard Page");
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
		
		verifyTrue(customerEditPage.isAddedEditedSuccessMsgDisplayed("The new customer has been added successfully."));
		
		customerEditPage.clickToButtonLinkByName(driver, "back to customer list");
		customersPage = PageGeneratorManager.getCustomersPage(driver);
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		
		verifyTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
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
		
		verifyTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
		verifyEquals(customersPage.getTotalEditButton(driver),1);
		
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
		
		verifyTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
		verifyEquals(customersPage.getTotalEditButton(driver),1);
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
		
		verifyTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
		verifyEquals(customersPage.getTotalEditButton(driver),1);
		
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
		
		verifyTrue(customersPage.isRowValueDisplayed(driver, "Guest", "Automation FC", customerRoles, customerCompanyName));
		verifyEquals(customersPage.getTotalEditButton(driver),1);
		
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
		
		verifyTrue(customerEditPage.isAddedEditedSuccessMsgDisplayed("The customer has been updated successfully."));
		
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
		
		verifyTrue(customersPage.isRowValueDisplayed(driver, "Guest", customerEditFirstName + " " + customerEditLastName, customerRoles, customerEditCompanyName));
		verifyEquals(customersPage.getTotalEditButton(driver),1);
	}
	@Test
	public void Admin_13_Add_New_Address() {
		log.info("Admin_13 - Step 1: Refresh Customers Page");
		customersPage.refreshCurrentPage(driver);
		customersPage.sleepInSecond(1);
		
		log.info("Admin_13 - Step 2: Input data to textbox Email with " + customerEditEmail);
		customersPage.enterToTextboxByName(driver, customerEditEmail, "SearchEmail");
		
		log.info("Admin_13 - Step 3: Input data to textbox FirstName with " + customerEditFirstName);
		customersPage.enterToTextboxByName(driver, customerEditFirstName, "SearchFirstName");
		
		log.info("Admin_13 - Step 4: Input data to textbox LastName with " + customerEditLastName);
		customersPage.enterToTextboxByName(driver, customerEditLastName, "SearchLastName");
		
		log.info("Admin_13 - Step 5: Select from dropdown Month with '2'");
		customersPage.selectItemInDropdownByAttributeName(driver,"2","SearchMonthOfBirth");
		
		log.info("Admin_13 - Step 6: Select from dropdown Day with '2'");
		customersPage.selectItemInDropdownByAttributeName(driver,"2","SearchDayOfBirth");
		
		log.info("Admin_13 - Step 7: Input data to textbox LastName with " + customerEditCompanyName);
		customersPage.enterToTextboxByName(driver, customerEditCompanyName, "SearchCompany");
		
		log.info("Admin_13 - Step 8: Close default 'Registered' role of dropdown 'Customer Roles'");
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		
		log.info("Admin_13 - Step 9:  Select from dropdown Customer Roles with data " + customerRoles);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		
		log.info("Admin_13 - Step 10: Click to Search button");
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		customersPage.sleepInSecond(1);
		
		log.info("Admin_13 - Step 11: Click to Edit button on table data");
		customersPage.clickToEditButtonByRow(driver, "Guest", customerEditFirstName + " " + customerEditLastName, customerRoles, customerEditCompanyName);
		
		log.info("Admin_13 - Step 12: Navigate to Customer Edit Page");
		customerEditPage = PageGeneratorManager.getCustomerEditPage(driver);
		
		log.info("Admin_13 - Step 13: Click to expand icon on Addresses");
		customerEditPage.openExpandIconByCardTitle(driver, "class","Addresses");
		
		log.info("Admin_13 - Step 14: Click to 'Add new address' button");
		customerEditPage.clickToButtonByText(driver, "Add new address");
		customerEditPage.sleepInSecond(2);
		
		log.info("Admin_13 - Step 15: Input data to First name textbox");
		customerEditPage.enterToTextboxByName(driver, customerFirstName, "Address_FirstName");
		
		log.info("Admin_13 - Step 16: Input data to Last name textbox");
		customerEditPage.enterToTextboxByName(driver, customerLastName, "Address_LastName");
		
		log.info("Admin_13 - Step 17: Input data to Email textbox");
		customerEditPage.enterToTextboxByName(driver, customerEmail, "Address_Email");
		
		log.info("Admin_13 - Step 18: Input data to Company textbox");
		customerEditPage.enterToTextboxByName(driver, customerCompanyName, "Address_Company");
		
		log.info("Admin_13 - Step 19: Select from Country dropdown with data " + customerAddressCountry);
		customerEditPage.selectItemInDropdownByAttributeName(driver, customerAddressCountry, "Address.CountryId");
		
		log.info("Admin_13 - Step 20: Select from State/province dropdown with data " + customerAddressState);
		customerEditPage.selectItemInDropdownByAttributeName(driver, customerAddressState, "Address.StateProvinceId");

		log.info("Admin_13 - Step 21: Input to City textbox with data " + customerAddressCity);
		customerEditPage.enterToTextboxByName(driver, customerAddressCity, "Address_City");
		
		log.info("Admin_13 - Step 22: Input to Address1 textbox with data " + customerAddressAddress1);
		customerEditPage.enterToTextboxByName(driver, customerAddressAddress1, "Address_Address1");
		
		log.info("Admin_13 - Step 23: Input to Address2 textbox with data " + customerAddressAddress2);
		customerEditPage.enterToTextboxByName(driver, customerAddressAddress2, "Address_Address2");
		
		log.info("Admin_13 - Step 24: Input Zip/postal code textbox with data " + customerAddressZip);
		customerEditPage.enterToTextboxByName(driver, customerAddressZip, "Address_ZipPostalCode");
		
		log.info("Admin_13 - Step 25: Input to Phone number textbox with data " + customerAddressPhone);
		customerEditPage.enterToTextboxByName(driver, customerAddressPhone, "Address_PhoneNumber");
		
		log.info("Admin_13 - Step 26: Input to Fax number textbox with data " + customerAddressPhone);
		customerEditPage.enterToTextboxByName(driver, customerAddressFax, "Address_FaxNumber");
		
		log.info("Admin_13 - Step 27: Click to Save button");
		customerEditPage.clickToButtonByText(driver, "Save");
		
		log.info("Admin_13 - Step 28: Verify address added success message is displayed");
		verifyTrue(customerEditPage.isAddedEditedSuccessMsgDisplayed("The new address has been added successfully."));
		
		log.info("Admin_13 - Step 29: Click to 'back to customer details'");
		customerEditPage.clickToButtonLinkByName(driver, "back to customer details");
		customerEditPage.sleepInSecond(2);
		
		log.info("Admin_13 - Step 30: Click to expand icon on Addresses");
		customerEditPage.openExpandIconByCardTitle(driver, "class","Addresses");
		customerEditPage.sleepInSecond(1);
		customerEditPage.scrollToBottomPage(driver);
		
		log.info("Admin_13 - Step 31: Verify address displayed in Addresses");
		verifyTrue(customerEditPage.isAddressInfoDisplayed(customerFirstName,customerLastName,customerEmail, customerAddressPhone, customerAddressFax, customerCompanyName, customerAddressAddress1, customerAddressAddress2, customerAddressCity + ",", customerAddressZip, customerAddressCountry));
	}
	@Test
	public void Admin_14_Edit_Address() {
		log.info("Admin_14 - Step 1: Click to 'back to customer list'");
		customerEditPage.clickToButtonLinkByName(driver, "back to customer list");
		customerEditPage.sleepInSecond(1);
		
		log.info("Admin_14 - Step 2: Input data to textbox Email with " + customerEditEmail);
		customersPage.enterToTextboxByName(driver, customerEditEmail, "SearchEmail");
		
		log.info("Admin_14 - Step 3: Input data to textbox FirstName with " + customerEditFirstName);
		customersPage.enterToTextboxByName(driver, customerEditFirstName, "SearchFirstName");
		
		log.info("Admin_14 - Step 4: Input data to textbox LastName with " + customerEditLastName);
		customersPage.enterToTextboxByName(driver, customerEditLastName, "SearchLastName");
		
		log.info("Admin_14 - Step 5: Select from dropdown Month with '2'");
		customersPage.selectItemInDropdownByAttributeName(driver,"2","SearchMonthOfBirth");
		
		log.info("Admin_14 - Step 6: Select from dropdown Day with '2'");
		customersPage.selectItemInDropdownByAttributeName(driver,"2","SearchDayOfBirth");
		
		log.info("Admin_14 - Step 7: Input data to textbox LastName with " + customerEditCompanyName);
		customersPage.enterToTextboxByName(driver, customerEditCompanyName, "SearchCompany");
		
		log.info("Admin_14 - Step 8: Close default 'Registered' role of dropdown 'Customer Roles'");
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		
		log.info("Admin_14 - Step 9:  Select from dropdown Customer Roles with data " + customerRoles);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		
		log.info("Admin_14 - Step 10: Click to Search button");
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		customersPage.sleepInSecond(1);
		
		log.info("Admin_14 - Step 11: Click to Edit button on table data");
		customersPage.clickToEditButtonByRow(driver, "Guest", customerEditFirstName + " " + customerEditLastName, customerRoles, customerEditCompanyName);
		
		log.info("Admin_14 - Step 12: Navigate to Customer Edit Page");
		customerEditPage = PageGeneratorManager.getCustomerEditPage(driver);
		
		log.info("Admin_14 - Step 13: Click to expand icon on Addresses");
		customerEditPage.openExpandIconByCardTitle(driver, "class","Addresses");
		
		log.info("Admin_14 - Step 14: Click Edit button link on Addresses table data");
		customerEditPage.clickToButtonLinkByName(driver, "Edit");
		customerEditPage.sleepInSecond(2);
		
		log.info("Admin_14 - Step 15: Input data to First name textbox");
		customerEditPage.enterToTextboxByName(driver, customerEditFirstName, "Address_FirstName");
		
		log.info("Admin_14 - Step 16: Input data to Last name textbox");
		customerEditPage.enterToTextboxByName(driver, customerEditLastName, "Address_LastName");
		
		log.info("Admin_14 - Step 17: Input data to Email textbox");
		customerEditPage.enterToTextboxByName(driver, customerEmail, "Address_Email");
		
		log.info("Admin_14 - Step 18: Input data to Company textbox");
		customerEditPage.enterToTextboxByName(driver, customerEditCompanyName, "Address_Company");
		
		log.info("Admin_14 - Step 19: Select from Country dropdown with data " + customerAddressCountry);
		customerEditPage.selectItemInDropdownByAttributeName(driver, customerEditAddressCountry, "Address.CountryId");
		
		log.info("Admin_14 - Step 20: Select from State/province dropdown with data " + customerAddressState);
		customerEditPage.selectItemInDropdownByAttributeName(driver, customerEditAddressState, "Address.StateProvinceId");

		log.info("Admin_14 - Step 21: Input to City textbox with data " + customerAddressCity);
		customerEditPage.enterToTextboxByName(driver, customerEditAddressCity, "Address_City");
		
		log.info("Admin_14 - Step 22: Input to Address1 textbox with data " + customerAddressAddress1);
		customerEditPage.enterToTextboxByName(driver, customerEditAddressAddress1, "Address_Address1");
		
		log.info("Admin_14 - Step 23: Input to Address2 textbox with data " + customerAddressAddress2);
		customerEditPage.enterToTextboxByName(driver, customerEditAddressAddress2, "Address_Address2");
		
		log.info("Admin_14 - Step 24: Input Zip/postal code textbox with data " + customerAddressZip);
		customerEditPage.enterToTextboxByName(driver, customerEditAddressZip, "Address_ZipPostalCode");
		
		log.info("Admin_14 - Step 25: Input to Phone number textbox with data " + customerAddressPhone);
		customerEditPage.enterToTextboxByName(driver, customerEditAddressPhone, "Address_PhoneNumber");
		
		log.info("Admin_14 - Step 26: Input to Fax number textbox with data " + customerAddressPhone);
		customerEditPage.enterToTextboxByName(driver, customerEditAddressFax, "Address_FaxNumber");
		
		log.info("Admin_14 - Step 27: Click to Save button");
		customerEditPage.clickToSaveButton();
		customerEditPage.sleepInSecond(1);
		
		log.info("Admin_14 - Step 28: Verify address added success message is displayed");
		verifyTrue(customerEditPage.isAddedEditedSuccessMsgDisplayed("The address has been updated successfully."));
		
		log.info("Admin_14 - Step 29: Click to 'back to customer details'");
		customerEditPage.clickToButtonLinkByName(driver, "back to customer details");
		customerEditPage.sleepInSecond(2);
		
		log.info("Admin_14 - Step 30: Click to expand icon on Addresses");
		customerEditPage.openExpandIconByCardTitle(driver, "class","Addresses");
		customerEditPage.sleepInSecond(1);
		
		
		log.info("Admin_14 - Step 31: Verify address displayed in Addresses");
		verifyTrue(customerEditPage.isAddressInfoDisplayed(customerEditFirstName, customerEditLastName, customerEmail, customerEditAddressPhone, customerEditAddressFax, customerEditCompanyName, customerEditAddressAddress1, customerEditAddressAddress2, customerEditAddressCity + "," + customerEditAddressState + ",", customerEditAddressZip, customerEditAddressCountry));
		
	}
	@Test
	public void Admin_15_Delete_Address() {
		log.info("Admin_15 - Step 1: Click to 'back to customer list'");
		customerEditPage.clickToButtonLinkByName(driver, "back to customer list");
		customerEditPage.sleepInSecond(1);
		
		log.info("Admin_15 - Step 2: Input data to textbox Email with " + customerEditEmail);
		customersPage.enterToTextboxByName(driver, customerEditEmail, "SearchEmail");
		
		log.info("Admin_15 - Step 3: Input data to textbox FirstName with " + customerEditFirstName);
		customersPage.enterToTextboxByName(driver, customerEditFirstName, "SearchFirstName");
		
		log.info("Admin_15 - Step 4: Input data to textbox LastName with " + customerEditLastName);
		customersPage.enterToTextboxByName(driver, customerEditLastName, "SearchLastName");
		
		log.info("Admin_15 - Step 5: Select from dropdown Month with '2'");
		customersPage.selectItemInDropdownByAttributeName(driver,"2","SearchMonthOfBirth");
		
		log.info("Admin_15 - Step 6: Select from dropdown Day with '2'");
		customersPage.selectItemInDropdownByAttributeName(driver,"2","SearchDayOfBirth");
		
		log.info("Admin_15 - Step 7: Input data to textbox LastName with " + customerEditCompanyName);
		customersPage.enterToTextboxByName(driver, customerEditCompanyName, "SearchCompany");
		
		log.info("Admin_15 - Step 8: Close default 'Registered' role of dropdown 'Customer Roles'");
		customersPage.closeDefaultItemOfCustomerRoles(driver);
		
		log.info("Admin_15 - Step 9:  Select from dropdown Customer Roles with data " + customerRoles);
		customersPage.enterItemInCustomerRolesDropdown(driver, customerRoles);
		
		log.info("Admin_15 - Step 10: Click to Search button");
		customersPage.clickToButtonByIdAttribute(driver, "search-customers");
		customersPage.sleepInSecond(1);
		
		log.info("Admin_15 - Step 11: Click to Edit button on table data");
		customersPage.clickToEditButtonByRow(driver, "Guest", customerEditFirstName + " " + customerEditLastName, customerRoles, customerEditCompanyName);
		
		log.info("Admin_15 - Step 12: Navigate to Customer Edit Page");
		customerEditPage = PageGeneratorManager.getCustomerEditPage(driver);
		
		log.info("Admin_15 - Step 13: Click to expand icon on Addresses");
		customerEditPage.openExpandIconByCardTitle(driver, "class","Addresses");
		
		log.info("Admin_15 - Step 14: Click Edit button link on Addresses table data");
		customerEditPage.clickToDeleteButton();
		customerEditPage.sleepInSecond(1);
		
		log.info("Admin_15 - Step 15: Accept alert");
		customerEditPage.acceptToDeleteAddressAlert();
		
		log.info("Admin_15 - Step 16: Verify delete address message displayed in Addresses");
		verifyTrue(customerEditPage.isAddressDeleteMsgDisplayed());
		
	}
	
	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void closeBrowser(String browserName) {
		log.info("Post-Condition - Close browser '" + browserName + "'");
		closeBrowserAndDriver();
	}

	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	CustomersPageObject customersPage;
	CustomerEditPageObject customerEditPage;
	ProductSearchPageObject productSearchPage;
	ProductDetailsPageObject productDetailsPage;
}
