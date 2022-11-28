package com.supermarket.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.supermarket.constants.Constants;
import com.supermarket.extentreport.TestListener;
import com.supermarket.pages.AddDeliveryBoyPage;
import com.supermarket.pages.AddExpensePage;
import com.supermarket.pages.DashboardPage;
import com.supermarket.pages.LoginPages;
import com.supermarket.pages.ManageDeliveryBoyPage;
import com.supermarket.pages.ManageExpensePage;
import com.supermarket.utilities.ExcelUtil;
import com.supermarket.utilities.GeneralUtil;

public class ManageDeliveryBoyTestcase extends BaseClass {

	LoginPages loginpageobj;
	ExcelUtil excelobj;
	DashboardPage dashboardpageobj;
	GeneralUtil generalutilobj;
	ManageDeliveryBoyPage managedeliveryboypageobj;
	AddDeliveryBoyPage adddeliveryboypageobj;
	
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description="TC_0021_Verify addition of new Delivery Boy",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateAddDeliveryBoy() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		managedeliveryboypageobj=new ManageDeliveryBoyPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageDeliveryBoy();
		managedeliveryboypageobj.clickNew();
		adddeliveryboypageobj=new AddDeliveryBoyPage(driver);
		String uniqueTimestamp=generalutilobj.getTimestamp();
		String username=excelobj.readDataFromExcel(4, 1,"DeliveryBoy")+uniqueTimestamp;
		adddeliveryboypageobj.addDeliveryBoy(excelobj.readDataFromExcel(0, 1,"DeliveryBoy"), excelobj.readDataFromExcel(1, 1,"DeliveryBoy"), excelobj.readDataFromExcel(2, 1,"DeliveryBoy"), excelobj.readDataFromExcel(3, 1,"DeliveryBoy"), username, excelobj.readDataFromExcel(5, 1,"DeliveryBoy"));
		managedeliveryboypageobj.waitForAddSucessMessage();
		boolean flag=managedeliveryboypageobj.tableTest(1, 1, username, 5);
		Assert.assertTrue(flag, "New Delivery Boy is not added");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}

	@Test(description="TC_0022_Verify search of Delivery Boy",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateSearchDeliveryBoy() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		managedeliveryboypageobj=new ManageDeliveryBoyPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageDeliveryBoy();
		managedeliveryboypageobj.clickNew();
		adddeliveryboypageobj=new AddDeliveryBoyPage(driver);
		String uniqueTimestamp=generalutilobj.getTimestamp();
		String username=excelobj.readDataFromExcel(4, 1,"DeliveryBoy")+uniqueTimestamp;
		adddeliveryboypageobj.addDeliveryBoy(excelobj.readDataFromExcel(0, 1,"DeliveryBoy"), excelobj.readDataFromExcel(1, 1,"DeliveryBoy"), excelobj.readDataFromExcel(2, 1,"DeliveryBoy"), excelobj.readDataFromExcel(3, 1,"DeliveryBoy"), username, excelobj.readDataFromExcel(5, 1,"DeliveryBoy"));
		managedeliveryboypageobj.waitForAddSucessMessage();
		managedeliveryboypageobj.clickSearch(excelobj.readDataFromExcel(0, 1,"DeliveryBoy"));
		boolean flag=managedeliveryboypageobj.tableTest(1, 1, username, 5);
		Assert.assertTrue(flag, "Search functionality is not working properly");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}


	@Test(description="TC_0023_Verify deletion of Delivery Boy",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateDeleteDeliveryBoy() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		managedeliveryboypageobj=new ManageDeliveryBoyPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageDeliveryBoy();
		managedeliveryboypageobj.clickNew();
		adddeliveryboypageobj=new AddDeliveryBoyPage(driver);
		String uniqueTimestamp=generalutilobj.getTimestamp();
		String username=excelobj.readDataFromExcel(4, 1,"DeliveryBoy")+uniqueTimestamp;
		adddeliveryboypageobj.addDeliveryBoy(excelobj.readDataFromExcel(0, 1,"DeliveryBoy"), excelobj.readDataFromExcel(1, 1,"DeliveryBoy"), excelobj.readDataFromExcel(2, 1,"DeliveryBoy"), excelobj.readDataFromExcel(3, 1,"DeliveryBoy"), username, excelobj.readDataFromExcel(5, 1,"DeliveryBoy"));
		managedeliveryboypageobj.waitForAddSucessMessage();
		managedeliveryboypageobj.deliveryBoyDelete();
		managedeliveryboypageobj.waitForDeleteSucessMessage();
		Assert.assertEquals(managedeliveryboypageobj.getDeleteSucessMessage(), Constants.expectedDeliveryBoyDeleteMessage,"Delete functionality is not working properly");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}



	@Test(description="TC_0024_Verify editing of Delivery Boy",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateEditDeliveryBoy() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		managedeliveryboypageobj=new ManageDeliveryBoyPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageDeliveryBoy();
		managedeliveryboypageobj.clickNew();
		adddeliveryboypageobj=new AddDeliveryBoyPage(driver);
		String uniqueTimestamp=generalutilobj.getTimestamp();
		String username=excelobj.readDataFromExcel(4, 1,"DeliveryBoy")+uniqueTimestamp;
		adddeliveryboypageobj.addDeliveryBoy(excelobj.readDataFromExcel(0, 1,"DeliveryBoy"), excelobj.readDataFromExcel(1, 1,"DeliveryBoy"), excelobj.readDataFromExcel(2, 1,"DeliveryBoy"), excelobj.readDataFromExcel(3, 1,"DeliveryBoy"), username, excelobj.readDataFromExcel(5, 1,"DeliveryBoy"));
		managedeliveryboypageobj.waitForAddSucessMessage();
		managedeliveryboypageobj.clickEdit();
		adddeliveryboypageobj.editDeliveryBoy(excelobj.readDataFromExcel(6, 1,"DeliveryBoy"));
		managedeliveryboypageobj.waitForUpdateeSucessMessage();
		Assert.assertEquals(managedeliveryboypageobj.getUpdateSucessMessage(), Constants.expectedDeliveryBoyUpdateMessage,"Update functionality is not working properly");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}


}


