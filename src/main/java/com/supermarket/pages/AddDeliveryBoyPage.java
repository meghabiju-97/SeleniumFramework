package com.supermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;

public class AddDeliveryBoyPage {


	WebDriver driver;
	GeneralUtil generalutilobj=new GeneralUtil();

	@FindBy(xpath="//input[@id='name']")
	private WebElement name;
	@FindBy(xpath="//input[@id='email']")
	private WebElement email;
	@FindBy(xpath="//input[@id='phone']")
	private WebElement phone;
	@FindBy(xpath="//textarea[@id='address']")
	private WebElement address;
	@FindBy(xpath="//input[@id='username']")
	private WebElement username;
	@FindBy(xpath="//input[@id='password']")
	private WebElement password;
	@FindBy(xpath="//button[@name='create']")
	private WebElement save;
	@FindBy(xpath="//button[@name='update']")
	private WebElement update;





	public AddDeliveryBoyPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public void addDeliveryBoy(String name1,String email1,String phone1,String address1,String username1,String password1) throws InterruptedException {

		generalutilobj.enterText(name, name1);
		generalutilobj.enterText(email, email1);
		generalutilobj.enterText(phone, phone1);
		generalutilobj.enterText(address, address1);
		generalutilobj.enterText(username, username1);
		generalutilobj.enterText(password, password1);
		generalutilobj.verticalScrollBy1500(driver);
		generalutilobj.generalSleep();
		generalutilobj.clickOnElement(save);




	}

	public void editDeliveryBoy(String name1) throws InterruptedException {
		generalutilobj.clearText(name);
		generalutilobj.enterText(name, name1);
		generalutilobj.verticalScrollBy1500(driver);
		generalutilobj.generalSleep();
		generalutilobj.clickOnElement(update);




	}

}
