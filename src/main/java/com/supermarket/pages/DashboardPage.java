package com.supermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;

public class DashboardPage {

	WebDriver driver;
	GeneralUtil generalutilobj=new GeneralUtil();

	@FindBy(xpath="//ol[@class='breadcrumb float-sm-right']")
	private WebElement loginverify;
	@FindBy(xpath="//ul[@role='menu']/li[2]")
	private WebElement manageExpense;
	@FindBy(xpath="//a[@href='https://groceryapp.uniqassosiates.com/admin/expense-category']")
	private WebElement expenseCategory;
	@FindBy(xpath="//ul[@role='menu']/li[2]/ul[2]")
	private WebElement manageExpenseSub;
	@FindBy(xpath="//ul[@role='menu']/li[18]")
	private WebElement adminUser;
	@FindBy(xpath="//ul[@role='menu']/li[4]")
	private WebElement manageProduct;
	@FindBy(xpath="//ul[@role='menu']/li[14]")
	private WebElement manageDeliveryBoy;
	@FindBy(xpath="//ul[@role='menu']/li[3]")
	private WebElement manageOrders;
	@FindBy(xpath="//ul[@role='menu']/li[9]")
	private WebElement manageLocation;



	public DashboardPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public boolean logoDisplayed() {

		boolean flag=generalutilobj.isElementDisplayed(loginverify);
		return flag;



	}

	public void clickManageExpense() {
		generalutilobj.clickOnElement(manageExpense);
	}

	public void clickManageOrder() {
		generalutilobj.clickOnElement(manageOrders);
	}

	public void clickManageDeliveryBoy() {
		generalutilobj.clickOnElement(manageDeliveryBoy);
	}

	public void clickExpenseCategory() {
		generalutilobj.clickOnElement(expenseCategory);
	}

	public void clickManageProduct() {
		generalutilobj.clickOnElement(manageProduct);
	}

	public void clickManageExpenseSubMenu() {
		generalutilobj.clickOnElement(manageExpense);
		generalutilobj.clickOnElement(manageExpenseSub);

	}

	public void clickAdminUser() {
		generalutilobj.clickOnElement(adminUser);
	}

	public void clickManageLocation() {
		generalutilobj.clickOnElement(manageLocation);
	}

}
