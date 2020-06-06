package com.qa.testcases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.LoginPage;
import com.qa.pages.RegisterPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RegistrationPageTest extends TestBase {
	LoginPage loginPage;
	RegisterPage registerPage;
	ExtentReports extent;
	ExtentTest extentTest;

	@BeforeTest
	public void setExtent() {
		// extent.startTest(verifyRegistration);
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Extent.html", true);
		extent.addSystemInfo("Host Name", "Priya Machine");
		extent.addSystemInfo("User Name", "Priya Windows");

	}

	@BeforeMethod
	public void setup() {
		init();
		loginPage = new LoginPage();
		registerPage = loginPage.clickRegister();
	}

	@Test
	public void verifyRegistration() {
		extentTest = extent.startTest("verifyRegistration");
		registerPage.fillRegistrationForm("Male", "abc6", "def6", "abc6.def6@test.com", "Tosca@1234");
		boolean successByEmail = registerPage.verifyRegistrationSuccessByAccountId("abc6.def6@test.com");
		assertEquals(true, successByEmail);
		boolean successByMsg = registerPage.verifyRegistrationSuccessBySuccessMsg();
		assertEquals(true, successByMsg);
		
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "Test Case failed is : " + result.getName());
			extentTest.log(LogStatus.FAIL, "Test Case failed is : " + result.getThrowable());
			String screenShotPath = getScreenShot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenShotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case skip is : " + result.getName());	
			String screenShotPath = getScreenShot(driver, result.getName());
			extentTest.log(LogStatus.SKIP, extentTest.addScreenCapture(screenShotPath));
		}else if(result.getStatus() == ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case passed is : " + result.getName());
			String screenShotPath = getScreenShot(driver, result.getName());
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenShotPath));
		}
		extent.endTest(extentTest);
		driver.quit();
	}

	
	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}
	
	public String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String DateName = new SimpleDateFormat("MMddyyyy").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestScreenShots/" + screenshotName + DateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}
}
