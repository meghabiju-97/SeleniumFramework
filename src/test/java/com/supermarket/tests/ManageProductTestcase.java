package com.supermarket.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.supermarket.constants.Constants;
import com.supermarket.extentreport.TestListener;
import com.supermarket.pages.AddExpensePage;
import com.supermarket.pages.AddProductPage;
import com.supermarket.pages.DashboardPage;
import com.supermarket.pages.LoginPages;

import com.supermarket.pages.ManageProductPage;
import com.supermarket.utilities.ExcelUtil;
import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class ManageProductTestcase extends BaseClass{


	LoginPages loginpageobj;
	ExcelUtil excelobj;
	DashboardPage dashboardpageobj;
	GeneralUtil generalutilobj;
	ManageProductPage manageproductpageobj;
	AddProductPage addproductpageobj;
	
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(description="TC_0016_Verify addition of new Product")
	public void validateAddProduct() throws IOException, InterruptedException, AWTException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageproductpageobj=new ManageProductPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		addproductpageobj=new AddProductPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageProduct();
		manageproductpageobj.clickNew();
		addproductpageobj.addProduct(excelobj.readDataFromExcel(0, 1,"AddProduct"), excelobj.readDataFromExcel(1, 1,"AddProduct"), excelobj.readDataFromExcel(2, 1,"AddProduct"), excelobj.readDataFromExcel(3, 1,"AddProduct"), excelobj.readDataFromExcel(4, 1,"AddProduct"), excelobj.readDataFromExcel(5, 1,"AddProduct"), excelobj.readDataFromExcel(6, 1,"AddProduct"));
		addproductpageobj.waitForAddProductSuccessMessage();
		Assert.assertEquals(addproductpageobj.getAddProductSuccessMessage(), Constants.expectedProductAddMessage,"Product is not added");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}

	@Test(description="TC_0017_Verify search of existing Product",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class,groups="sanity")
	public void validateSearchProduct() throws IOException, InterruptedException, AWTException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageproductpageobj=new ManageProductPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		addproductpageobj=new AddProductPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageProduct();
		manageproductpageobj.clickNew();
		addproductpageobj.addProduct(excelobj.readDataFromExcel(0, 1,"AddProduct"), excelobj.readDataFromExcel(1, 1,"AddProduct"), excelobj.readDataFromExcel(2, 1,"AddProduct"), excelobj.readDataFromExcel(3, 1,"AddProduct"), excelobj.readDataFromExcel(4, 1,"AddProduct"), excelobj.readDataFromExcel(5, 1,"AddProduct"), excelobj.readDataFromExcel(6, 1,"AddProduct"));
		addproductpageobj.waitForAddProductSuccessMessage();
		dashboardpageobj.clickManageProduct();
		manageproductpageobj.waitForProductListPage();
		String productcode=manageproductpageobj.getProductCode();
		String finalCode=generalutilobj.replaceChar(productcode);
		manageproductpageobj.clickSearch();
		manageproductpageobj.waitForProductSearchPage();
		manageproductpageobj.searchWithProductCode(finalCode);
		boolean result1=manageproductpageobj.tableTest(1,1,excelobj.readDataFromExcel(0, 1,"AddProduct"),1);
		Assert.assertFalse(result1,"Search not working");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}

	@Test(description="TC_0018_Verify editing of existing Product",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class,groups="sanity")
	public void validateEditProduct() throws IOException, InterruptedException, AWTException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageproductpageobj=new ManageProductPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		addproductpageobj=new AddProductPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageProduct();
		manageproductpageobj.clickNew();
		addproductpageobj.addProduct(excelobj.readDataFromExcel(0, 1,"AddProduct"), excelobj.readDataFromExcel(1, 1,"AddProduct"), excelobj.readDataFromExcel(2, 1,"AddProduct"), excelobj.readDataFromExcel(3, 1,"AddProduct"), excelobj.readDataFromExcel(4, 1,"AddProduct"), excelobj.readDataFromExcel(5, 1,"AddProduct"), excelobj.readDataFromExcel(6, 1,"AddProduct"));
		addproductpageobj.waitForAddProductSuccessMessage();
		dashboardpageobj.clickManageProduct();
		manageproductpageobj.waitForProductListPage();
		String productcode=manageproductpageobj.getProductCode();
		String finalCode=generalutilobj.replaceChar(productcode);
		manageproductpageobj.clickSearch();
		manageproductpageobj.waitForProductSearchPage();
		manageproductpageobj.searchWithProductCode(finalCode);
		manageproductpageobj.clickEdit();
		addproductpageobj.editProduct(excelobj.readDataFromExcel(7, 1,"AddProduct"),excelobj.readDataFromExcel(8, 1,"AddProduct"));
		addproductpageobj.waitForUpdateProductSuccessMessage();
		boolean result1=manageproductpageobj.tableTest(1,1,excelobj.readDataFromExcel(7, 1,"AddProduct"),1);
		Assert.assertFalse(result1,"Search not working");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");
	}


	@Test(description="TC_0019_Verify deleting of existing Product")
	public void validateDeleteProduct() throws IOException, InterruptedException, AWTException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageproductpageobj=new ManageProductPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		addproductpageobj=new AddProductPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageProduct();
		manageproductpageobj.clickNew();
		addproductpageobj.addProduct(excelobj.readDataFromExcel(0, 1,"AddProduct"), excelobj.readDataFromExcel(1, 1,"AddProduct"), excelobj.readDataFromExcel(2, 1,"AddProduct"), excelobj.readDataFromExcel(3, 1,"AddProduct"), excelobj.readDataFromExcel(4, 1,"AddProduct"), excelobj.readDataFromExcel(5, 1,"AddProduct"), excelobj.readDataFromExcel(6, 1,"AddProduct"));
		addproductpageobj.waitForAddProductSuccessMessage();
		dashboardpageobj.clickManageProduct();
		manageproductpageobj.waitForProductListPage();
		String productcode=manageproductpageobj.getProductCode();
		String finalCode=generalutilobj.replaceChar(productcode);
		manageproductpageobj.clickSearch();
		manageproductpageobj.waitForProductSearchPage();
		manageproductpageobj.searchWithProductCode(finalCode);
		manageproductpageobj.deleteProduct();
		Assert.assertEquals(manageproductpageobj.getProductDeleteMessage(), Constants.expectedProductDeleteMessage,"Delete functionality is not working properly");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");


	}



	@Test(description="TC_0020_Verify the number of Products displaying for List Products page is correct",retryAnalyzer=com.supermarket.utilities.RetryAnalyzerUtil.class,groups="sanity")
	public void validateNumberOfProducts() throws IOException, InterruptedException {
		loginpageobj=new LoginPages(driver);
		excelobj=new ExcelUtil();
		manageproductpageobj=new ManageProductPage(driver);
		dashboardpageobj=new DashboardPage(driver);
		addproductpageobj=new AddProductPage(driver);
		generalutilobj=new GeneralUtil();
		extentTest.get().log(Status.INFO, "TESTCASE STARTS");
		loginpageobj.login(excelobj.readDataFromExcel(0, 1,"Login"), excelobj.readDataFromExcel(1, 1,"Login"));
		dashboardpageobj.clickManageProduct();
		String productTitleInitial=manageproductpageobj.getProductNumber();
		String productCountString=generalutilobj.replaceChar(productTitleInitial);
		int  productCountInitial= Integer.parseInt(productCountString);
		int expectedFinalNumber=productCountInitial-1;
		manageproductpageobj.deleteProduct();
		String productTitleFinal=manageproductpageobj.getProductNumber();
		String productCountString1=generalutilobj.replaceChar(productTitleFinal);
		int  productCountFinal= Integer.parseInt(productCountString1);
		Assert.assertEquals(productCountFinal, expectedFinalNumber,"Display product count is not correct");
		extentTest.get().log(Status.INFO, "TESTCASE ENDS");




	}
}
