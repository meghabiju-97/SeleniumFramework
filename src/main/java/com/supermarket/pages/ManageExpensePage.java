package com.supermarket.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class ManageExpensePage {
	WebDriver driver;
	GeneralUtil generalutilobj=new GeneralUtil();

	@FindBy(xpath="//a[@class='btn btn-rounded btn-danger']") 
	private WebElement newButton;
	@FindBy(xpath="//input[@id='name']")
	private WebElement title;
	@FindBy(xpath="//button[@name='Create']")
	private WebElement saveButton;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement categoryaddmessage;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr")
	private List<WebElement> rElement;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr[1]/td")
	private List<WebElement> cElement;
	@FindBy(xpath="//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchButton;
	@FindBy(xpath="//input[@id='un']")
	private WebElement searchTitle;
	@FindBy(xpath="//button[@name='Search']") 
	private WebElement searchButton1;
	@FindBy(xpath="(//a[@class='btn btn-sm btn btn-danger btncss'])[1]") 
	private WebElement deleteButton;
	@FindBy(xpath="(//a[@class='btn btn-sm btn btn-primary btncss'])[1]")
	private WebElement editButton;
	@FindBy(xpath="//input[@id='name']")
	private WebElement editTitle;
	@FindBy(xpath="//button[@name='Update']")
	private WebElement updateButton;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement categoryExistMessage;
	@FindBy(xpath="//input[@id='ti']")
	private WebElement searchTitleManageExpense;



	String tPath="//table[@class='table table-bordered table-hover table-sm']/tbody";





	public ManageExpensePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void addNewExpenseCategory(String name) {
		generalutilobj.clickOnElement(newButton);
		generalutilobj.enterText(title, name);
		generalutilobj.clickOnElement(saveButton);


	}

	public void clickNew() {
		generalutilobj.clickOnElement(newButton);
	}

	public void clickEdit() {
		generalutilobj.clickOnElement(editButton);
	}

	public void searchExpenseCategory(String name) {
		generalutilobj.clickOnElement(searchButton);
		generalutilobj.enterText(searchTitle, name);
		generalutilobj.clickOnElement(searchButton1);

	}

	public void deleteExpenseCategory1() throws InterruptedException {
		generalutilobj.clickOnElement(deleteButton);
		generalutilobj.generalSleep();
		generalutilobj.acceptAlert(driver);
	}


	public void deleteExpense() throws InterruptedException {
		generalutilobj.clickOnElement(deleteButton);
		generalutilobj.generalSleep();
		generalutilobj.acceptAlert(driver);
	}

	public void editExpenseCategory(String text) {
		generalutilobj.clickOnElement(editButton);
		generalutilobj.clearText(editTitle);
		generalutilobj.enterText(editTitle, text);
		generalutilobj.clickOnElement(updateButton);
	}



	public String getSuccessMessage() {
		return generalutilobj.getElementText(categoryaddmessage);



	}

	public String getCategoryExistMessage() {
		return generalutilobj.getElementText(categoryExistMessage);
	}

	public void searchExpense(String name) {
		generalutilobj.clickOnElement(searchButton);
		generalutilobj.enterText(searchTitleManageExpense, name);
		generalutilobj.clickOnElement(searchButton1);
	}


	public boolean displayCategoryExistMessage() {
		try {
			return generalutilobj.isElementDisplayed(categoryExistMessage);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean tableTest(int rowstart,int columnstart,String expected, int columnnum) {
		return generalutilobj.tableIteration(driver, rElement, cElement, rowstart, columnstart, expected, tPath,columnnum);


	}

	public void waitForDeleteButton() {
		WaitUtil.waitForVisibility(driver,deleteButton);
	}

	public void deleteAndAddExpenseCategory(String name) throws InterruptedException {
		searchExpenseCategory(name);

		deleteExpenseCategory1();

		addNewExpenseCategory(name);
	}
	
	public void deleteExpenseCategory(String name,boolean result) throws InterruptedException {
		
		if(result==true) {
			deleteExpenseCategory1();
		}
		else {
			addNewExpenseCategory(name);

			searchExpenseCategory(name);

			deleteExpenseCategory1();

		}
	}

	public boolean getSearchResult(boolean result,boolean result1) {
		boolean searchResult=false;
		if(result==true || result1==true)
		{
			searchResult=true;
		}else {
			searchResult=false;
		}
		return searchResult;
	}
	
	public void checkAndEditExpenseCategory(String input1,String input2,boolean result) {
		if(result==true) {
			editExpenseCategory(input1);
		}
		else {
			addNewExpenseCategory(input2);

			searchExpenseCategory(input2);

			editExpenseCategory(input1);

		}
	}
	
	public void checkAndDeleteNewCategory(boolean result1) throws InterruptedException {
		if(result1==true) {
			deleteExpenseCategory1();
		}
	}
	
	public void checkDeleteAndAddExpenseCategory(boolean flag,String input) throws InterruptedException {
		if(flag==true) {
			deleteAndAddExpenseCategory(input);

		}
	}
	
	public void checkAndAddNewExpenseCategory(String input) throws InterruptedException {
		addNewExpenseCategory(input);
		boolean flag=displayCategoryExistMessage();
		if(flag==true) {
			searchExpenseCategory(input);
			deleteExpenseCategory1();
			addNewExpenseCategory(input);

		}
	}
	
	public void addDuplicateExpenseCategory(boolean flag,String input) {
		if(flag==true) {
			addNewExpenseCategory(input);
		}
		else {
			clickNew();
			addNewExpenseCategory(input);
			clickNew();
			addNewExpenseCategory(input);
		}
	}
}
