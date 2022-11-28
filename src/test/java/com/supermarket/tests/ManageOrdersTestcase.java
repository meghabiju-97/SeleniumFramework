package com.supermarket.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.supermarket.constants.Constants;
import com.supermarket.extentreport.TestListener;
import com.supermarket.pages.AddDeliveryBoyPage;
import com.supermarket.pages.DashboardPage;
import com.supermarket.pages.LoginPages;
import com.supermarket.pages.ManageDeliveryBoyPage;
import com.supermarket.pages.ManageOrderPage;
import com.supermarket.utilities.ExcelUtil;
import com.supermarket.utilities.GeneralUtil;

public class ManageOrdersTestcase extends BaseClass {


	LoginPages loginpageobj;
	ExcelUtil excelobj;
	DashboardPage dashboardpageobj;
	GeneralUtil generalutilobj;
	ManageDeliveryBoyPage managedeliveryboypageobj;
	AddDeliveryBoyPage adddeliveryboypageobj;
	ManageOrderPage manageorderpageobj;
	
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description="TC_0025_Verify assignment of  Delivery Boy to an Order",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateAssignDeliveryBoy() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		managedeliveryboypageobj=new ManageDeliveryBoyPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		manageorderpageobj=new ManageOrderPage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageDeliveryBoy();
		managedeliveryboypageobj.clickNew();
		adddeliveryboypageobj=new AddDeliveryBoyPage(driver);
		String uniqueTimestamp=generalutilobj.getTimestamp();
		String username=excelobj.readDataFromExcel(4, 1,"DeliveryBoy")+uniqueTimestamp;
		adddeliveryboypageobj.addDeliveryBoy(excelobj.readDataFromExcel(0, 1,"DeliveryBoy"), excelobj.readDataFromExcel(1, 1,"DeliveryBoy"), excelobj.readDataFromExcel(2, 1,"DeliveryBoy"), excelobj.readDataFromExcel(3, 1,"DeliveryBoy"), username, excelobj.readDataFromExcel(5, 1,"DeliveryBoy"));
		managedeliveryboypageobj.waitForAddSucessMessage();
		dashboardpageobj.clickManageOrder();
		manageorderpageobj.assignDeliveryBoy(username);
		manageorderpageobj.waitForDeliveryBoyAssignedSucessMessage();
		Assert.assertEquals(manageorderpageobj.getDeliveryBoyAssignedSucessMessage(), Constants.expectedDeliveryBoyAssignedMessage,"Delivery boy is not assigned");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");

	}

	@Test(description="TC_0026_Verify number of orders in a page is correct",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateOrderTotal() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		managedeliveryboypageobj=new ManageDeliveryBoyPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		manageorderpageobj=new ManageOrderPage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageOrder();
		String orderCountString=generalutilobj.replaceChar(manageorderpageobj.getOrderTitle());
		int  orderCount= Integer.parseInt(orderCountString);
		int actualCount=manageorderpageobj.getOrderCount(1);
		Assert.assertEquals(actualCount, orderCount);
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}
}
