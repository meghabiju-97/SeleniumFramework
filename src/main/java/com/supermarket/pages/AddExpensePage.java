package com.supermarket.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.FileUploadUtil;
import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class AddExpensePage  {


	WebDriver driver;
	GeneralUtil generalutilobj=new GeneralUtil();
	FileUploadUtil uploadutilobj=new FileUploadUtil();
	ManageExpensePage manageexpensepageobj;
	DashboardPage dashboardpageobj;


	@FindBy(xpath="//select[@id='user_id']")
	private WebElement user;
	@FindBy(xpath="//input[@id='ex_date']") 
	private WebElement date;
	@FindBy(xpath="//select[@id='ex_cat']") 
	private WebElement category;
	@FindBy(xpath="//select[@id='order_id']") 
	private WebElement orderid;
	@FindBy(xpath="//select[@id='purchase_id']") 
	private WebElement purchaseid;
	@FindBy(xpath="//select[@id='ex_type']") 
	private WebElement expensetype;
	@FindBy(xpath="//input[@id='amount']") 
	private WebElement amount;
	@FindBy(xpath="//input[@name='userfile']") 
	private WebElement choosefile;
	@FindBy(xpath="//button[@name='create']") 
	private WebElement save;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']") 
	private WebElement addedmessage;
	@FindBy(xpath="//button[@name='update']") 
	private WebElement update;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']") 
	private WebElement deletedmessage;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']") 
	private WebElement updatemessage;






	String uploadFilePath="\\src\\main\\resources\\uploadFile\\sampleFile.jpeg";


	public AddExpensePage(WebDriver driver) {
		this.driver=driver;
		this.dashboardpageobj = new DashboardPage(driver);
		this.manageexpensepageobj = new ManageExpensePage(driver);
		PageFactory.initElements(driver, this);
	}

	public void addDate(String selectDate,Keys enter) {
		generalutilobj.clickOnElement(date);
		generalutilobj.clearText(date);
		generalutilobj.enterText(date, selectDate);
		generalutilobj.keyPress(date, enter, driver);
		generalutilobj.keyRelease(date, enter, driver);

	}

	public void editExpense(String userValue) throws InterruptedException {
		generalutilobj.selectDropdownbyText(user, userValue);
		generalutilobj.scrollToElement(driver, update);
		generalutilobj.generalSleep();
		generalutilobj.clickOnElement(update);




	}

	public void waitForUserDropdown() {
		WaitUtil.waitForVisibility(driver,user);
	}

	public void addExpense(String userValue,String selectDate,Keys enter,String categoryValue,String orderIdValue,String purchaseId,String expenseType,String amt) {
		generalutilobj.selectDropdownbyText(user, userValue);
		addDate(selectDate,enter);
		generalutilobj.selectDropdownbyText(category, categoryValue);
		String orderIdValue1=orderIdValue.replace(".0", "");
		generalutilobj.selectDropdownbyText(orderid, orderIdValue1);
		String purchaseId1=purchaseId.replace(".0", "");
		generalutilobj.selectDropdownbyText(purchaseid, purchaseId1);
		generalutilobj.selectDropdownbyText(expensetype, expenseType);
		generalutilobj.enterText(amount, amt);
		uploadutilobj.upload(choosefile, uploadFilePath);
		generalutilobj.clickOnElement(save);




	}
	public String getSucessMessage() {
		return generalutilobj.getElementText(addedmessage);
	}

	public String getDeleteSucessMessage() {
		return generalutilobj.getElementText(deletedmessage);
	}

	public String getUpdateSucessMessage() {
		return generalutilobj.getElementText(updatemessage);
	}
	public void waitForDeleteMessage() {
		WaitUtil.waitForVisibility(driver,deletedmessage);
	}

	public void waitForUpdateMessage() {
		WaitUtil.waitForVisibility(driver,updatemessage);
	}
	
	public String getTargetDate(String dateinput,String monthinput,String yearinput) {
		String date1=generalutilobj.replaceChar(dateinput);
		String month1=generalutilobj.replaceChar(monthinput);
		String year1=generalutilobj.replaceChar(yearinput);
		String targetDate=date1+"-"+month1+"-"+year1;
		return targetDate;
	}
	
	
	public void checkAndDeleteExpense(boolean result,String dateinput,String monthinput,String yearinput,String userValue,String categoryValue,String orderIdValue,String purchaseId,String expenseType,String amt,String expense) throws InterruptedException {
		
		if(result==true) {
			manageexpensepageobj.waitForDeleteButton();
			manageexpensepageobj.deleteExpense();
			waitForDeleteMessage();
		}
		else {
			generalutilobj.scrollToTop(driver);
			generalutilobj.generalSleep();
			manageexpensepageobj.clickNew();
			String targetDate=getTargetDate(dateinput, monthinput,yearinput);
			addExpense(userValue,targetDate, Keys.ENTER, categoryValue,orderIdValue, purchaseId, expenseType, amt);
			dashboardpageobj.clickManageExpenseSubMenu();
			manageexpensepageobj.searchExpense(expense);
			generalutilobj.verticalScrollBy1500(driver);
			generalutilobj.generalSleep();
			manageexpensepageobj.waitForDeleteButton();
			manageexpensepageobj.deleteExpense();
			waitForDeleteMessage();
		}
	}
	
	public void checkAndEditExpense(boolean result,String editInput,String expenseCategory,String dateinput,String monthinput,String yearinput,String userValue,String categoryValue,String orderIdValue,String purchaseId,String expenseType,String amt,String expense) throws InterruptedException {
	if(result==true) {
		manageexpensepageobj.clickEdit();
		waitForUserDropdown();
		editExpense(editInput);
		waitForUpdateMessage();
	}
	else {
		if(dashboardpageobj.driver != null && manageexpensepageobj.driver != null) {
			dashboardpageobj.clickManageExpense();
			dashboardpageobj.clickExpenseCategory();
			manageexpensepageobj.checkAndAddNewExpenseCategory(expenseCategory);
			dashboardpageobj.clickManageExpenseSubMenu();
			manageexpensepageobj.clickNew();
			String targetDate=getTargetDate(dateinput, monthinput,yearinput);
			addExpense(userValue,targetDate, Keys.ENTER, categoryValue,orderIdValue, purchaseId, expenseType, amt);
			dashboardpageobj.clickManageExpenseSubMenu();
			manageexpensepageobj.searchExpense(expense);
			generalutilobj.verticalScrollBy1500(driver);
			generalutilobj.generalSleep();
			manageexpensepageobj.clickEdit();
			waitForUserDropdown();
			editExpense(editInput);
			waitForUpdateMessage();
		}
		else {
			System.out.println("dashboardpageobject or manageexpensepageobj driver is null..!");
		}

	}

	}

}
