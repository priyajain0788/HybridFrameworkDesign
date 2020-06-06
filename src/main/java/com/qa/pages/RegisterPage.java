package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class RegisterPage extends TestBase {

	// Page objects or Object Repository

	@FindBy(xpath = "//h1[contains(text(),'Register')]")
	WebElement registerHeader;

	@FindBy(id = "gender-male")
	WebElement genderMale;

	@FindBy(id = "gender-female")
	WebElement genderFemale;

	@FindBy(id = "FirstName")
	WebElement fName;

	@FindBy(id = "LastName")
	WebElement lName;

	@FindBy(id = "Email")
	WebElement emailForRegistration;

	@FindBy(id = "Password")
	WebElement createPwd;

	@FindBy(id = "ConfirmPassword")
	WebElement createConfirmPwd;

	@FindBy(id = "register-button")
	WebElement submitRegistrationForm;

	@FindBy(xpath = "//div[contains(text(),'Your registration completed')]")
	WebElement successfulRegistration;

	@FindBy(xpath = "//a[@class='account']")
	WebElement accountId;

	// Constructor
	public RegisterPage() {
		PageFactory.initElements(driver, this);
	}

	public void fillRegistrationForm(String gender, String FirstName, String LastName, String email, String Password) {

		if (gender.equals("Male")) {
			genderMale.click();
		} else {
			genderFemale.click();
		}

		fName.sendKeys(FirstName);
		lName.sendKeys(LastName);
		emailForRegistration.sendKeys(email);
		createPwd.sendKeys(Password);
		createConfirmPwd.sendKeys(Password);

		submitRegistrationForm.click();

	}

	public boolean verifyRegistrationSuccessByAccountId(String email) {

		if (accountId.getText().equals(email)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyRegistrationSuccessBySuccessMsg() {
		
		if(successfulRegistration.getText().equals("Your registration completed")){
			return true;
		}
		else{
			return false;
		}
	}
}
