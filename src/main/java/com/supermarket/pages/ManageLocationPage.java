package com.supermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;


public class ManageLocationPage {

	GeneralUtil generalutilobj=new GeneralUtil();

	WebDriver driver;

	@FindBy(xpath="//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath="(//a[@class=\"btn btn-sm btn btn-danger btncss\"])[1]")
	private WebElement deleteButton;
	@FindBy(xpath="//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchButton;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement deleteSuccessMessage;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr")
	private List<WebElement> rElement;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr[1]/td")
	private List<WebElement> cElement;
	@FindBy(xpath="(//a[@class='btn btn-sm btn btn-primary btncss'])[1]")
	private WebElement editButton;


	String tPath="//table[@class='table table-bordered table-hover table-sm']/tbody";




	public void clickNew() {

		generalutilobj.clickOnElement(newButton);


	}

	public void clickSearch() {

		generalutilobj.clickOnElement(searchButton);


	}

	public void clickEdit() {

		generalutilobj.clickOnElement(editButton);


	}
	public void locationDelete() throws InterruptedException {
		generalutilobj.clickOnElement(deleteButton);
		generalutilobj.generalSleep();
		generalutilobj.acceptAlert(driver);
	}

	public String getdeleteSuccessMessage() {
		return generalutilobj.getElementText(deleteSuccessMessage);
	}

	public void waitForLocationDeleteMessage() {
		WaitUtil.waitForVisibility(driver, deleteSuccessMessage);
	}


	public boolean tableTest(int rowstart,int columnstart,String expected,int columnnum) {
		return generalutilobj.tableIteration(driver, rElement, cElement, rowstart, columnstart, expected, tPath,columnnum);


	}

	public ManageLocationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
