package com.nopcommerce.login;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.register.Register_Account;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

public class Login_To_Page extends BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email, password, firstName, lastName, confirmPassword;

	@Parameters({"browser","url"})
	@BeforeClass
	public void initBrowser(String browserName, String urlNopCommerce) {
		driver = getBrowser(browserName, urlNopCommerce);
		email = generateEmail(); 
		firstName = "Cristiano"; 
		lastName = "Ronaldo"; 
		email = "hp95@mail.com";
		password = "123456";
	}
	

	@Test
	public void Login_06_Correct_Email_Password() {
		//Step 1: HomePage -> Click to Login link
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
		homePage.clickToLoginLink();
		
		//Step 2: LoginPage
		loginPage = new LoginPageObject(driver);
		
		//Step 3: Enter to emailtextbox
		loginPage.enterToEmailTextbox(email);
		
		//Step 4: Enter to passwordtextbox
		loginPage.enterToPasswordTextbox(password);
		
		//Step 5: Click to Login button
		loginPage.clickToLoginButton();
		
		//Step 1: HomePage -> Verify slider is displayed
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isSliderHomePageDisplayed());
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	public String generateEmail() {
		Random rand = new Random();
		return "cr7_" + rand.nextInt(99999) + "@mail.com";
	}
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	Register_Account registerAccount = new Register_Account();
}
