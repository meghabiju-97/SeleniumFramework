package com.supermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class ManageOrderPage {

	WebDriver driver;
	GeneralUtil generalutilobj=new GeneralUtil();

	@FindBy(xpath="(//a[@class=\"btn btn-warning btn-sm\"])[1]")
	private WebElement assignDeliveryBoy;
	@FindBy(xpath="//div[@class='modal show']//select[@name='delivery_boy_id']")
	private WebElement deliveryBoyDropdown;
	@FindBy(xpath="//div[@class='modal show']//button[@name='assign_del']")
	private WebElement update;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement deliveryBoyAssignedSucessMessage;
	@FindBy(xpath="//div[@class=\"card \"]/div/h4")
	private WebElement title;
	@FindBy(xpath="//table[@class='table table-bordered table-hover table-sm']/tbody/tr")
	private List<WebElement> rElement;



	public ManageOrderPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public void assignDeliveryBoy(String name) {

		generalutilobj.clickOnElement(assignDeliveryBoy);
		generalutilobj.selectDropdownbyText(deliveryBoyDropdown, name);
		generalutilobj.clickOnElement(update);



	}

	public void waitForDeliveryBoyAssignedSucessMessage() {
		WaitUtil.waitForVisibility(driver, deliveryBoyAssignedSucessMessage);
	}

	public String getDeliveryBoyAssignedSucessMessage() {
		return generalutilobj.getElementText(deliveryBoyAssignedSucessMessage);
	}

	public String getOrderTitle() {
		return generalutilobj.getElementText(title);
	}


	public int getOrderCount(int rstart) {
		return generalutilobj.tableRowNumber(driver, rElement, rstart);
	}
}
