package com.supermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class ManageProductPage {


	WebDriver driver;
	GeneralUtil generalutilobj=new GeneralUtil();

	@FindBy(xpath="//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath="//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchButton;
	@FindBy(xpath="(//button[@class=\"btn btn-xs btn-success\"])[1]")
	private WebElement productcode;
	@FindBy(xpath="//input[@placeholder='Product Code']")
	private WebElement productCodeText;
	@FindBy(xpath="//button[@name='Search']")
	private WebElement searchButton1;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr")
	private List<WebElement> rElement;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr[1]/td")
	private List<WebElement> cElement;
	@FindBy(xpath="(//a[@class='btn btn-sm btn btn-primary btncss'])[1]")
	private WebElement editButton;
	@FindBy(xpath="(//a[@class='btn btn-sm btn btn-danger btncss'])[1]")
	private WebElement deleteButton;
	@FindBy(xpath="//div[@class='card ']//div[@class='card-header']//h4/b")
	private WebElement productNumber;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement productDeleteSucessMessage;


	String tPath="//table[@class='table table-bordered table-hover table-sm']/tbody";






	public ManageProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public void clickNew() {

		generalutilobj.clickOnElement(newButton);



	}

	public void clickEdit() {

		generalutilobj.clickOnElement(editButton);



	}

	public void clickSearch() {

		generalutilobj.clickOnElement(searchButton);



	}



	public void deleteProduct() throws InterruptedException {
		generalutilobj.clickOnElement(deleteButton);
		generalutilobj.generalSleep();
		generalutilobj.acceptAlert(driver);
	}


	public String getProductCode() {

		return generalutilobj.getElementText(productcode);



	}

	public String getProductNumber() {

		return generalutilobj.getElementText(productNumber);



	}

	public String getProductDeleteMessage() {

		return generalutilobj.getElementText(productDeleteSucessMessage);



	}


	public void searchWithProductCode(String productCode) {

		generalutilobj.enterText(productCodeText, productCode);
		generalutilobj.clickOnElement(searchButton1);



	}

	public boolean tableTest(int rowstart,int columnstart,String expected, int columnnum) {
		return generalutilobj.tableIteration(driver, rElement, cElement, rowstart, columnstart, expected, tPath,columnnum);


	}

	public int getRowNumber(int rowstart) {
		return generalutilobj.tableRowNumber(driver, rElement, rowstart);

	}

	public void waitForProductSearchPage() {
		WaitUtil.waitForVisibility(driver, productCodeText);
	}

	public void waitForProductListPage() {
		WaitUtil.waitForVisibility(driver, searchButton);
	}

}
