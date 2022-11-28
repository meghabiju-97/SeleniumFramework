package com.supermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;



public class LoginPages {

	GeneralUtil generalutilobj;

	WebDriver driver;
	@FindBy(xpath="//input[@placeholder='Username']")
	private WebElement username;
	@FindBy(xpath="//input[@placeholder='Password']")
	private WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement login;
	@FindBy(xpath="//ol[@class='breadcrumb float-sm-right']")
	private WebElement loginverify;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement invalidmessage;

	public void login(String username1,String password1) {
		generalutilobj=new GeneralUtil();

		generalutilobj.enterText(username, username1);
		generalutilobj.enterText(password, password1);
		generalutilobj.clickOnElement(login);


	}

	public void waitForLogin() {
		WaitUtil.fluentwaitForVisibility(driver,loginverify);
	}

	
	public void waitForInvalidMessage() {
		WaitUtil.waitForVisibility(driver,invalidmessage);
	}
	public String getInvalidMessage() {
		return generalutilobj.getElementText(invalidmessage);


	}



	public LoginPages(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


}
