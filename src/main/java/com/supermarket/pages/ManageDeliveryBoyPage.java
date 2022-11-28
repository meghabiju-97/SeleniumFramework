package com.supermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class ManageDeliveryBoyPage {

	WebDriver driver;
	GeneralUtil generalutilobj=new GeneralUtil();

	@FindBy(xpath="//a[@class='btn btn-rounded btn-danger']") 
	private WebElement newButton;
	@FindBy(xpath="//a[@class='btn btn-rounded btn-primary']") 
	private WebElement searchButton;
	@FindBy(xpath="//button[@name='Search']")
	private WebElement searchButton1;
	@FindBy(xpath="(//a[@class='btn btn-sm btn btn-primary btncss'])[1]")
	private WebElement editButton;
	@FindBy(xpath="(//a[@class='btn btn-sm btn btn-danger btncss'])[1]")
	private WebElement deleteButton;
	@FindBy(xpath="//input[@id='un']")
	private WebElement searchName;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr")
	private List<WebElement> rElement;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr[1]/td")
	private List<WebElement> cElement;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement deliveryBoyAddMessage;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement deliveryBoyDeleteMessage;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement deliveryBoyUpdateMessage;



	String tPath="//table[@class='table table-bordered table-hover table-sm']/tbody";








	public ManageDeliveryBoyPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public void clickNew() {

		generalutilobj.clickOnElement(newButton);



	}

	public void clickEdit() {

		generalutilobj.clickOnElement(editButton);



	}

	public void deliveryBoyDelete() throws InterruptedException {

		generalutilobj.clickOnElement(deleteButton);
		generalutilobj.generalSleep();
		generalutilobj.acceptAlert(driver);



	}

	public void clickSearch(String name1) {

		generalutilobj.clickOnElement(searchButton);
		WaitUtil.waitForVisibility(driver, searchName);
		generalutilobj.enterText(searchName, name1);
		generalutilobj.clickOnElement(searchButton1);



	}

	public void waitForAddSucessMessage() {
		WaitUtil.waitForVisibility(driver, deliveryBoyAddMessage);
	}

	public void waitForDeleteSucessMessage() {
		WaitUtil.waitForVisibility(driver, deliveryBoyDeleteMessage);
	}

	public void waitForUpdateeSucessMessage() {
		WaitUtil.waitForVisibility(driver, deliveryBoyUpdateMessage);
	}

	public String getDeleteSucessMessage() {
		return generalutilobj.getElementText(deliveryBoyDeleteMessage);
	}

	public String getUpdateSucessMessage() {
		return generalutilobj.getElementText(deliveryBoyUpdateMessage);
	}

	public boolean tableTest(int rowstart,int columnstart,String expected, int columnnum) {
		return generalutilobj.tableIteration(driver, rElement, cElement, rowstart, columnstart, expected, tPath,columnnum);


	}



}
