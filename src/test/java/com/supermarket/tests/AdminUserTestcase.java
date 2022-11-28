package com.supermarket.tests;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.supermarket.constants.Constants;
import com.supermarket.extentreport.TestListener;
import com.supermarket.pages.AdminUserPage;
import com.supermarket.pages.DashboardPage;
import com.supermarket.pages.LoginPages;
import com.supermarket.utilities.ExcelUtil;
import com.supermarket.utilities.GeneralUtil;



public class AdminUserTestcase extends BaseClass{

	LoginPages loginpageobj;
	ExcelUtil excelobj;
	DashboardPage dashboardpageobj;
	GeneralUtil generalutilobj;
	AdminUserPage adminuserpageobj;

	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();


	@Test(description="TC_0012_Verify addition of new User")
	public void validateAddUser() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		adminuserpageobj=new AdminUserPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickAdminUser();
		String uniqueTimestamp=generalutilobj.getTimestamp();
		String username=excelobj.readDataFromExcel(0, 1,"AdminUser")+uniqueTimestamp;
		adminuserpageobj.addNewUser(username, excelobj.readDataFromExcel(1, 1,"AdminUser"),excelobj.readDataFromExcel(2, 1,"AdminUser"));
		adminuserpageobj.waitForUserAddMessage();
		Assert.assertEquals(adminuserpageobj.getUserAddMessage(), Constants.expectedUserAddMessage,"User not added successfully");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
		

	}

	@Test(description="TC_0013_Verify search of existing User")
	public void validateSearchUser() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		adminuserpageobj=new AdminUserPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickAdminUser();
		String uniqueTimestamp=generalutilobj.getTimestamp();
		String username=excelobj.readDataFromExcel(0, 1,"AdminUser")+uniqueTimestamp;
		adminuserpageobj.addNewUser(username, excelobj.readDataFromExcel(1, 1,"AdminUser"),excelobj.readDataFromExcel(2, 1,"AdminUser"));
		adminuserpageobj.waitForUserAddMessage();
		adminuserpageobj.searchUser(username);
		boolean result=adminuserpageobj.tableTest(1,1,username,1);
		Assert.assertTrue(result, "Search functionality is not working");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");

	}

	@Test(description="TC_0014_Verify delete of existing User")
	public void validateDeleteUser() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		adminuserpageobj=new AdminUserPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickAdminUser();
		String uniqueTimestamp=generalutilobj.getTimestamp();
		String username=excelobj.readDataFromExcel(0, 1,"AdminUser")+uniqueTimestamp;
		adminuserpageobj.addNewUser(username, excelobj.readDataFromExcel(1, 1,"AdminUser"),excelobj.readDataFromExcel(2, 1,"AdminUser"));
		adminuserpageobj.waitForUserAddMessage();
		adminuserpageobj.searchUser(username);
		adminuserpageobj.deleteUser();
		adminuserpageobj.searchUser(username);
		boolean result=adminuserpageobj.tableTest(1,1,username,1);
		Assert.assertFalse(result, "Delete functionality is not working");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");

	}

	@Test(description="TC_0015_Verify edit of existing User")
	public void validateEditUser() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		adminuserpageobj=new AdminUserPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickAdminUser();
		String uniqueTimestamp=generalutilobj.getTimestamp();
		String username=excelobj.readDataFromExcel(0, 1,"AdminUser")+uniqueTimestamp;
		adminuserpageobj.addNewUser(username, excelobj.readDataFromExcel(1, 1,"AdminUser"),excelobj.readDataFromExcel(2, 1,"AdminUser"));
		adminuserpageobj.waitForUserAddMessage();
		adminuserpageobj.searchUser(username);
		adminuserpageobj.editUser(excelobj.readDataFromExcel(3, 1,"AdminUser"));
		boolean result=adminuserpageobj.tableTest(1,1,excelobj.readDataFromExcel(3, 1,"AdminUser"),2);
		Assert.assertTrue(result, "Edit functionality is not working");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");

	}
}


