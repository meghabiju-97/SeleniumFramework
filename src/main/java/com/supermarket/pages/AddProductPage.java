package com.supermarket.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.FileUploadUtil;
import com.supermarket.utilities.GeneralUtil;
import com.supermarket.utilities.WaitUtil;

public class AddProductPage {


	WebDriver driver;
	GeneralUtil generalutilobj=new GeneralUtil();
	FileUploadUtil uploadutilobj=new FileUploadUtil();

	@FindBy(xpath="//input[@id='title']")
	private WebElement title;
	@FindBy(xpath="//select[@id='cat_id']")
	private WebElement category;
	@FindBy(xpath="//select[@id='sub_id']")
	private WebElement subCategory;
	@FindBy(xpath="//input[@id='purpose2']")
	private WebElement priceTypeLitre;
	@FindBy(xpath="//input[@id='l_minimum']")
	private WebElement litreValue;
	@FindBy(xpath="//select[@id='l_unit']")
	private WebElement litreUnit;
	@FindBy(xpath="//input[@id='l_price']")
	private WebElement litrePrice;
	@FindBy(xpath="//input[@id='l_stock']")
	private WebElement stockAvailability;
	@FindBy(xpath="//input[@id='main_img']")
	private WebElement chooseFile;
	@FindBy(xpath="//button[@name='create']")
	private WebElement save;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement productAddMessage;
	@FindBy(xpath="//input[@id='l_max']")
	private WebElement maximumQuantityCanOrder;
	@FindBy(xpath="//button[@name='update']")
	private WebElement update;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement productUpdateMessage;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	private WebElement productDeleteMessage;

	By subCategoryLocator=By.xpath("//select[@id='sub_id']");
	By litreValueLocator=By.xpath("//input[@id='l_minimum']");


	String uploadFilePath="\\src\\main\\resources\\uploadFile\\sampleFile.jpeg";


	public AddProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public String getAddProductSuccessMessage() {
		return generalutilobj.getElementText(productAddMessage);
	}

	public void waitForAddProductSuccessMessage() {
		WaitUtil.waitForVisibility(driver, productAddMessage);
	}

	public void waitForUpdateProductSuccessMessage() {
		WaitUtil.waitForVisibility(driver, productUpdateMessage);
	}

	public void addProduct(String productTitle,String productCategory,String productSubCategory,String productValue,String unit,String price,String stock) throws InterruptedException, AWTException {

		generalutilobj.enterText(title, productTitle);
		generalutilobj.selectDropdownbyText(category, productCategory);
		generalutilobj.generalSleep();
		generalutilobj.selectDropdownbyText(subCategory, productSubCategory);
		generalutilobj.clickOnElement(priceTypeLitre);
		generalutilobj.generalSleep();
		generalutilobj.enterText(litreValue, productValue);
		generalutilobj.selectDropdownbyValue(litreUnit,unit);
		generalutilobj.enterText(litrePrice, price);
		generalutilobj.enterText(stockAvailability, stock);
		generalutilobj.verticalScrollBy1500(driver);
		generalutilobj.generalSleep();
		uploadutilobj.upload(chooseFile, uploadFilePath);
		generalutilobj.clickOnElement(save);



	}

	public void editProduct(String productTitle,String maximumQuantity) throws InterruptedException {
		generalutilobj.clearText(title);
		generalutilobj.enterText(title, productTitle);
		generalutilobj.enterText(maximumQuantityCanOrder, maximumQuantity);
		generalutilobj.verticalScrollBy1500(driver);
		generalutilobj.generalSleep();
		generalutilobj.clickOnElement(update);

	}



}
