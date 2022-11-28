package com.supermarket.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.supermarket.constants.Constants;
import com.supermarket.extentreport.TestListener;
import com.supermarket.pages.AddLocationPage;
import com.supermarket.pages.DashboardPage;
import com.supermarket.pages.LoginPages;

import com.supermarket.pages.ManageLocationPage;

import com.supermarket.utilities.ExcelUtil;
import com.supermarket.utilities.GeneralUtil;

public class ManageLocationTestcase extends BaseClass {


	LoginPages loginpageobj;
	ExcelUtil excelobj;
	DashboardPage dashboardpageobj;
	GeneralUtil generalutilobj;
	AddLocationPage addlocationpageobj;
	ManageLocationPage managelocationpageobj;

	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	@Test(description="TC_0027_Verify addition of new location",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateAddLocation() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		managelocationpageobj=new ManageLocationPage(driver);
		addlocationpageobj=new AddLocationPage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageLocation();
		managelocationpageobj.clickNew();
		addlocationpageobj.addLocation(excelobj.readDataFromExcel(0, 1,"AddLocation"), excelobj.readDataFromExcel(1, 1,"AddLocation"), excelobj.readDataFromExcel(2, 1,"AddLocation"));
		addlocationpageobj.waitForLocationAddMessage();
		Assert.assertEquals(addlocationpageobj.getaddSuccessMessage(), Constants.expectedLocationAddMessage,"Location is not added properly");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");

	}

	@Test(description="TC_0028_Verify deletion location",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateDeleteLocation() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		managelocationpageobj=new ManageLocationPage(driver);
		addlocationpageobj=new AddLocationPage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageLocation();
		managelocationpageobj.clickNew();
		addlocationpageobj.addLocation(excelobj.readDataFromExcel(0, 1,"AddLocation"), excelobj.readDataFromExcel(1, 1,"AddLocation"), excelobj.readDataFromExcel(2, 1,"AddLocation"));
		addlocationpageobj.waitForLocationAddMessage();
		dashboardpageobj.clickManageLocation();
		managelocationpageobj.locationDelete();
		managelocationpageobj.waitForLocationDeleteMessage();
		Assert.assertEquals(managelocationpageobj.getdeleteSuccessMessage(), Constants.expectedLocationDeleteMessage,"Location is not deleted properly");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}

	@Test(description="TC_0029_Verify search of location",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateSearchLocation() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		managelocationpageobj=new ManageLocationPage(driver);
		addlocationpageobj=new AddLocationPage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageLocation();
		managelocationpageobj.clickNew();
		addlocationpageobj.addLocation(excelobj.readDataFromExcel(0, 1,"AddLocation"), excelobj.readDataFromExcel(1, 1,"AddLocation"), excelobj.readDataFromExcel(2, 1,"AddLocation"));
		addlocationpageobj.waitForLocationAddMessage();
		dashboardpageobj.clickManageLocation();
		managelocationpageobj.clickSearch();
		addlocationpageobj.searchLocation(excelobj.readDataFromExcel(0, 1,"AddLocation"), excelobj.readDataFromExcel(1, 1,"AddLocation"));
		boolean flag=managelocationpageobj.tableTest(1, 1, excelobj.readDataFromExcel(1, 1,"AddLocation"), 1);
		Assert.assertTrue(flag, "Search functionality is not working");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");

	}

	@Test(description="TC_0030_Verify edit of location",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateEditLocation() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		managelocationpageobj=new ManageLocationPage(driver);
		addlocationpageobj=new AddLocationPage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageLocation();
		managelocationpageobj.clickNew();
		addlocationpageobj.addLocation(excelobj.readDataFromExcel(0, 1,"AddLocation"), excelobj.readDataFromExcel(1, 1,"AddLocation"), excelobj.readDataFromExcel(2, 1,"AddLocation"));
		addlocationpageobj.waitForLocationAddMessage();
		dashboardpageobj.clickManageLocation();
		managelocationpageobj.clickEdit();
		addlocationpageobj.editLocation(excelobj.readDataFromExcel(3, 1,"AddLocation"));
		boolean flag=managelocationpageobj.tableTest(1, 1, excelobj.readDataFromExcel(3, 1,"AddLocation"), 1);
		Assert.assertTrue(flag, "Edit functionality is not working");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}
}
