package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	// Page Factory - Object Repository
	
	@FindBy(id="Email")
	WebElement email;

	@FindBy(id="Password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//input[contains(@class,'button-1 register-button')]")
	WebElement registerBtn;
	
	@FindBy(xpath="//div[@class='header-logo']/a/img") 
	WebElement demowebShopLogo;
	
	// initialising the page object
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	// Actions : 
	public String validatePageTitle(){
		return driver.getTitle();
	}

	public boolean validateDemoLogo(){
		return demowebShopLogo.isDisplayed();
	}
	
	public HomePage login(String uname, String pwd){
		email.sendKeys(uname);
		password.sendKeys(pwd);
		loginBtn.click();
		return new HomePage();
	}
	
	public RegisterPage clickRegister(){
		registerBtn.click();
		return new RegisterPage();
	}
}


