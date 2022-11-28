package com.supermarket.tests;




import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.supermarket.constants.Constants;
import com.supermarket.extentreport.TestListener;
import com.supermarket.pages.DashboardPage;
import com.supermarket.pages.LoginPages;
import com.supermarket.utilities.DataProviderUtil;
import com.supermarket.utilities.ExcelUtil;
import com.supermarket.utilities.GeneralUtil;



import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTestcase extends BaseClass {

	GeneralUtil generalutilobj;
	LoginPages loginpageobj;
	ExcelUtil excelobj;
	DashboardPage dashboardpageobj;

	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();


	@Test(description="TC_001_Verify login with valid user credentials",priority=1)
	public void validateWithValidCredentials() throws Exception  {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		dashboardpageobj=new DashboardPage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		loginpageobj.waitForLogin();
		Assert.assertTrue(dashboardpageobj.logoDisplayed(), "Login Failure");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}



	@Test(description="TC_002_Verify login with invalid user credentials",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class,dataProvider = "loginData",dataProviderClass=DataProviderUtil.class,priority=2)
	public void validateWithInValidCredentials(String username,String password) throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(username,password);
		loginpageobj.waitForInvalidMessage();
		Assert.assertEquals(loginpageobj.getInvalidMessage(), Constants.expectedErrorMessage,"Invalid messsage not displayed");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}




}
