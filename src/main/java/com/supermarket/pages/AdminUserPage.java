package com.supermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class AdminUserPage {


	WebDriver driver;
	GeneralUtil generalutilobj=new GeneralUtil();

	@FindBy(xpath="//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath="//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchButton;
	@FindBy(xpath="(//a[@class='btn btn-sm btn btn-primary btncss'])[1]")
	private WebElement editButton;
	@FindBy(xpath="(//a[@class='btn btn-sm btn btn-danger btncss'])[1]")
	private WebElement deleteButton;
	@FindBy(xpath="//input[@id='username']")
	private WebElement user;
	@FindBy(xpath="//input[@id='password']")
	private WebElement password;
	@FindBy(xpath="//select[@id='user_type']")
	private WebElement usertype;
	@FindBy(xpath="//input[@id='un']")
	private WebElement searchUsername;
	@FindBy(xpath="//button[@name='Search']")
	private WebElement searchButton1;
	@FindBy(xpath="//button[@name='Create']")
	private WebElement save;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement useraddmessage;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr")
	private List<WebElement> rElement;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr[1]/td")
	private List<WebElement> cElement;
	@FindBy(xpath="//button[@name='Update']")
	private WebElement update;



	String tPath="//table[@class='table table-bordered table-hover table-sm']/tbody";







	public AdminUserPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}




	public void addNewUser(String username,String password1,String type) {
		generalutilobj.clickOnElement(newButton);
		generalutilobj.enterText(user, username);
		generalutilobj.enterText(password,password1);
		generalutilobj.selectDropdownbyText(usertype, type);
		generalutilobj.clickOnElement(save);

	}

	public String getUserAddMessage() {
		return generalutilobj.getElementText(useraddmessage);
	}

	public void waitForUserAddMessage() {
		WaitUtil.waitForVisibility(driver, useraddmessage);
	}

	public void searchUser(String username) {
		generalutilobj.clickOnElement(searchButton);
		generalutilobj.enterText(searchUsername, username);
		generalutilobj.clickOnElement(searchButton1);

	}

	public void editUser(String type) {
		generalutilobj.clickOnElement(editButton);
		generalutilobj.selectDropdownbyText(usertype, type);
		generalutilobj.clickOnElement(update);
	}

	public boolean tableTest(int rowstart,int columnstart,String expected,int columnnum) {
		return generalutilobj.tableIteration(driver, rElement, cElement, rowstart, columnstart, expected, tPath,columnnum);


	}

	public void deleteUser() throws InterruptedException {
		generalutilobj.clickOnElement(deleteButton);
		generalutilobj.generalSleep();
		generalutilobj.acceptAlert(driver);
	}
}
