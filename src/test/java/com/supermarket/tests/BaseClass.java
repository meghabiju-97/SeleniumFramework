package com.supermarket.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.supermarket.constants.Constants;
import com.supermarket.extentreport.ExtentManager;
import com.supermarket.utilities.GeneralUtil;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {

	WebDriver driver;


	GeneralUtil generalutilobj;
	public static Properties po;
	public static void testBasic() throws IOException {
		po=new Properties();
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+Constants.CONFIGFILE);
		po.load(file);

	}
	@Parameters("browser")
	@BeforeMethod(groups="sanity",alwaysRun=true)
	public void beforeMethod(String browser) throws IOException {
		generalutilobj=new GeneralUtil();
		testBasic();
		driver=generalutilobj.browserLaunch(browser, po.getProperty("URL"));
	}

	@AfterMethod(groups="sanity",alwaysRun=true)
	public void afterMethod(ITestResult result) throws IOException {

		if(ITestResult.FAILURE==result.getStatus()) {
			generalutilobj.captureScreenshotonFailure(driver, result.getName());
		}

		driver.quit();
	}


	@BeforeClass
	public void beforeClass() {


	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}


	@AfterSuite
	public void afterSuite() {
	}

}
