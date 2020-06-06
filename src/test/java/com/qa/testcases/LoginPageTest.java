package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void setUp() {
		init();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void pageTitleTest() {
		String pageTitle = loginPage.validatePageTitle();
		Assert.assertEquals(pageTitle, "Demo Web Shop. Login");
	}

	@Test(priority = 2)
	public void validateLogo() {
		boolean logo = loginPage.validateDemoLogo();
		Assert.assertTrue(logo);
	}

	@Test(priority = 3)
	public void login() {
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
