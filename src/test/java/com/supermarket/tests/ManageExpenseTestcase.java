package com.supermarket.tests;

import org.testng.annotations.Test;

import org.testng.annotations.Test;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.supermarket.constants.Constants;
import com.supermarket.extentreport.TestListener;
import com.supermarket.pages.AddExpensePage;
import com.supermarket.pages.DashboardPage;
import com.supermarket.pages.LoginPages;
import com.supermarket.pages.ManageExpensePage;
import com.supermarket.utilities.ExcelUtil;
import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class ManageExpenseTestcase extends BaseClass{

	LoginPages loginpageobj;
	ExcelUtil excelobj;
	DashboardPage dashboardpageobj;
	GeneralUtil generalutilobj;
	AddExpensePage addexpensepageobj;
	ManageExpensePage manageexpensepageobj;
	
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description="TC_003_Verify addition of new Expense Category",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateAddExpenseCategory() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageexpensepageobj=new ManageExpensePage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageExpense();
		dashboardpageobj.clickExpenseCategory();
		manageexpensepageobj.addNewExpenseCategory(excelobj.readDataFromExcel(0, 1, "ManageExpense"));
		boolean flag=manageexpensepageobj.displayCategoryExistMessage();
		manageexpensepageobj.checkDeleteAndAddExpenseCategory(flag, excelobj.readDataFromExcel(0, 1, "ManageExpense"));
		Assert.assertEquals(manageexpensepageobj.getSuccessMessage(), Constants.expectedCategoryAddMessage,"Expense Category is not added");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}


	@Test(description="TC_004_Verify search of existing Expense Category")
	public void validateSearchExpenseCategory() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageexpensepageobj=new ManageExpensePage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageExpense();
		dashboardpageobj.clickExpenseCategory();
		manageexpensepageobj.searchExpenseCategory(excelobj.readDataFromExcel(0, 1, "ManageExpense"));
		boolean searchResult=manageexpensepageobj.getSearchResult(manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(0, 1, "ManageExpense"),1), manageexpensepageobj.tableTest(1, 1, excelobj.readDataFromExcel(2, 1, "ManageExpense"),1));
		Assert.assertTrue(searchResult, "Search functionality is not working");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}

	@Test(description="TC_005_Verify delete of existing Expense Category")
	public void validateDeleteExpenseCategory() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageexpensepageobj=new ManageExpensePage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageExpense();
		dashboardpageobj.clickExpenseCategory();
		manageexpensepageobj.searchExpenseCategory(excelobj.readDataFromExcel(0, 1, "ManageExpense"));
		manageexpensepageobj.deleteExpenseCategory(excelobj.readDataFromExcel(0, 1, "ManageExpense"), manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(0, 1, "ManageExpense"),1));
		manageexpensepageobj.searchExpenseCategory(excelobj.readDataFromExcel(0, 1, "ManageExpense"));
		boolean result1=manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(0, 1, "ManageExpense"),1);
		Assert.assertFalse(result1,"Expense Category not deleted");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
		}

	@Test(description="TC_006_Verify edit of existing Expense Category")
	public void validateEditExpenseCategory() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageexpensepageobj=new ManageExpensePage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageExpense();
		dashboardpageobj.clickExpenseCategory();
		manageexpensepageobj.searchExpenseCategory(excelobj.readDataFromExcel(1, 1, "ManageExpense"));
		boolean result1=manageexpensepageobj.tableTest(1, 1, excelobj.readDataFromExcel(1, 1, "ManageExpense"),1);
		manageexpensepageobj.checkAndDeleteNewCategory(result1);
		manageexpensepageobj.searchExpenseCategory(excelobj.readDataFromExcel(0, 1, "ManageExpense"));
		boolean result=manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(0, 1, "ManageExpense"),1);
		manageexpensepageobj.checkAndEditExpenseCategory(excelobj.readDataFromExcel(1, 1, "ManageExpense"),excelobj.readDataFromExcel(0, 1, "ManageExpense"), result);
		manageexpensepageobj.searchExpenseCategory(excelobj.readDataFromExcel(1, 1, "ManageExpense"));
		boolean result2=manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(1, 1, "ManageExpense"),1);
		Assert.assertTrue(result2,"Expense Category not deleted");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");

	}

	@Test(description="TC_007_Verify addition of new Expense")
	public void validateAddExpense() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageexpensepageobj=new ManageExpensePage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		addexpensepageobj=new AddExpensePage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageExpense();
		dashboardpageobj.clickExpenseCategory();
		manageexpensepageobj.checkAndAddNewExpenseCategory(excelobj.readDataFromExcel(0, 1, "ManageExpense"));
		dashboardpageobj.clickManageExpenseSubMenu();
		manageexpensepageobj.clickNew();
		String targetDate=addexpensepageobj.getTargetDate(excelobj.readDataFromExcel(1, 1, "AddExpense"), excelobj.readDataFromExcel(2, 1, "AddExpense"), excelobj.readDataFromExcel(3, 1, "AddExpense"));
		addexpensepageobj.addExpense(excelobj.readDataFromExcel(4, 1, "AddExpense"),targetDate, Keys.ENTER, excelobj.readDataFromExcel(0, 1, "ManageExpense"),excelobj.readDataFromExcel(6, 1, "AddExpense"), excelobj.readDataFromExcel(7, 1, "AddExpense"), excelobj.readDataFromExcel(8, 1, "AddExpense"), excelobj.readDataFromExcel(9, 1, "AddExpense"));
		Assert.assertEquals(addexpensepageobj.getSucessMessage(), Constants.expectedExpenseAddMessage,"Expense not recorded successfully");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}


	@Test(description="TC_008_Verify search of existing Expense")
	public void validateSearchExpense() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageexpensepageobj=new ManageExpensePage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		addexpensepageobj=new AddExpensePage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageExpenseSubMenu();
		manageexpensepageobj.searchExpense(excelobj.readDataFromExcel(11, 1, "AddExpense"));
		generalutilobj.verticalScrollBy1500(driver);
		generalutilobj.generalSleep();
		boolean result=manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(11, 1, "AddExpense"),1);
		boolean result1=manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(12, 1, "AddExpense"),1);
		boolean searchResult=manageexpensepageobj.getSearchResult(result, result1);
		Assert.assertTrue(searchResult, "Search functionality is not working");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}


	@Test(description="TC_009_Verify delete of existing Expense")
	public void validateDeleteExpense() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageexpensepageobj=new ManageExpensePage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		addexpensepageobj=new AddExpensePage(driver);
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageExpenseSubMenu();
		manageexpensepageobj.searchExpense(excelobj.readDataFromExcel(11, 1, "AddExpense"));
		generalutilobj.verticalScrollBy1500(driver);
		generalutilobj.generalSleep();
		boolean result=manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(11, 1, "AddExpense"),1);
		addexpensepageobj.checkAndDeleteExpense(result, excelobj.readDataFromExcel(1, 1, "AddExpense"), excelobj.readDataFromExcel(2, 1, "AddExpense"), excelobj.readDataFromExcel(3, 1, "AddExpense"), excelobj.readDataFromExcel(4, 1, "AddExpense"),excelobj.readDataFromExcel(0, 1, "ManageExpense"),excelobj.readDataFromExcel(6, 1, "AddExpense"), excelobj.readDataFromExcel(7, 1, "AddExpense"), excelobj.readDataFromExcel(8, 1, "AddExpense"), excelobj.readDataFromExcel(9, 1, "AddExpense"),excelobj.readDataFromExcel(11, 1, "AddExpense"));
		Assert.assertEquals(addexpensepageobj.getDeleteSucessMessage(), Constants.expectedExpenseDeleteMessage,"Expense not deleted successfully");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");

	}




	@Test(description="TC_0010_Verify edit of existing Expense",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class)
	public void validateEditExpense() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageexpensepageobj=new ManageExpensePage(driver);
		dashboardpageobj=new DashboardPage(driver);
		addexpensepageobj=new AddExpensePage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageExpenseSubMenu();
		manageexpensepageobj.searchExpense(excelobj.readDataFromExcel(11, 1, "AddExpense"));
		generalutilobj.verticalScrollBy1500(driver);
		generalutilobj.generalSleep();
		boolean result=manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(11, 1, "AddExpense"),1);
		addexpensepageobj.checkAndEditExpense(result,excelobj.readDataFromExcel(13, 1, "AddExpense"),excelobj.readDataFromExcel(0, 1, "ManageExpense"),excelobj.readDataFromExcel(1, 1, "AddExpense"),excelobj.readDataFromExcel(2, 1, "AddExpense"),excelobj.readDataFromExcel(3, 1, "AddExpense"),excelobj.readDataFromExcel(4, 1, "AddExpense"),excelobj.readDataFromExcel(0, 1, "ManageExpense"),excelobj.readDataFromExcel(6, 1, "AddExpense"),excelobj.readDataFromExcel(7, 1, "AddExpense"),excelobj.readDataFromExcel(8, 1, "AddExpense"),excelobj.readDataFromExcel(9, 1, "AddExpense"),excelobj.readDataFromExcel(11, 1, "AddExpense"));
		Assert.assertEquals(addexpensepageobj.getUpdateSucessMessage(), Constants.expectedExpenseUpdateMessage,"Expense not updated successfully");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");

	}

	@Test(description="TC_011_Verify restriction for duplicate Expense Category",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class,groups="sanity")
	public void validateRestrictDuplicateExpenseCategory() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageexpensepageobj=new ManageExpensePage(driver);
		dashboardpageobj=new DashboardPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageExpense();
		dashboardpageobj.clickExpenseCategory();
		boolean flag=manageexpensepageobj.tableTest(1,1,excelobj.readDataFromExcel(0, 1, "ManageExpense"),1);
		manageexpensepageobj.addDuplicateExpenseCategory(flag, excelobj.readDataFromExcel(0, 1, "ManageExpense"));
		Assert.assertEquals(manageexpensepageobj.getCategoryExistMessage(), Constants.expectedCategoryExistingMessage,"Restriction feature of Category duplication is not working"); 
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}

}
