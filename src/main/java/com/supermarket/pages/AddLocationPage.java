package com.supermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class AddLocationPage {


	GeneralUtil generalutilobj=new GeneralUtil();

	WebDriver driver;

	@FindBy(xpath="//select[@id='country_id']")
	private WebElement country;
	@FindBy(xpath="//select[@id='st_id']")
	private WebElement state;
	@FindBy(xpath="//input[@id='location']")
	private WebElement location;
	@FindBy(xpath="//input[@id='delivery']")
	private WebElement deliveryCharge;
	@FindBy(xpath="//button[@name='create']")
	private WebElement save;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement locationAddMessage;
	@FindBy(xpath="//button[@name='Search']")
	private WebElement search;
	@FindBy(xpath="//button[@name='update']")
	private WebElement update;


	public void addLocation(String stateName,String location1,String charge) {

		generalutilobj.selectDropdownbyText(state, stateName);
		generalutilobj.enterText(location,location1);
		generalutilobj.enterText(deliveryCharge, charge);
		generalutilobj.clickOnElement(save);


	}

	public void searchLocation(String stateName,String location1) {

		generalutilobj.selectDropdownbyText(state, stateName);
		generalutilobj.enterText(location,location1);
		generalutilobj.clickOnElement(search);


	}

	public void editLocation(String location1) {

		generalutilobj.clearText(location);
		generalutilobj.enterText(location,location1);
		generalutilobj.clickOnElement(update);


	}
	public String getaddSuccessMessage() {
		return generalutilobj.getElementText(locationAddMessage);
	}

	public void waitForLocationAddMessage() {
		WaitUtil.waitForVisibility(driver, locationAddMessage);
	}




	public AddLocationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
}
