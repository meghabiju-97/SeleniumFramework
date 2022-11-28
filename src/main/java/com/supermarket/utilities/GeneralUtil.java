package com.supermarket.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GeneralUtil {
	WebDriver driver;


	public WebDriver browserLaunch(String browser,String url) {
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Edge")) {

			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitUtil.IMPLICIT_WAIT));
		driver.get(url);
		return driver;
	}

	public void clickOnElement(WebElement element) {
		element.click();

	}

	public void enterText(WebElement element,String value) {
		element.sendKeys(value);

	}

	public String getElementText(WebElement element) {
		return element.getText();

	}

	public void clearText(WebElement element) {
		element.clear();
	}


	public void moveToWebElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();


	}


	public void doubleClickOnElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	public void rightClickOnElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).build().perform();
	}

	public void clickAndHoldOnElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.clickAndHold(element).build().perform();

	}

	public void releaseElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.release(element).build().perform();

	}
	public void dragAndDropElement(WebElement source,WebElement target, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).build().perform();

	}

	public void keyPress(WebElement element, Keys enter,WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.keyDown(element,enter).build().perform();

	}

	public void keyRelease(WebElement element, Keys enter,WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.keyUp(element, enter).build().perform();

	}

	public void keyToEnter(WebElement element, String key,WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.sendKeys(element, key).build().perform();


	}

	public void selectDropdownbyText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void selectDropdownbyIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void selectDropdownbyValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);

	}

	public Boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public Boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	public Boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public void verticalScrollBy1500(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)"); 
	}

	public void horizontalScrollBy(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(600,0)"); 
	}

	public void scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public void scrollToEnd(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight");
	}

	public void scrollToTop(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void enterTextForAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public static void navigateToBack(WebDriver driver) {
		driver.navigate().back();
	}

	public static void navigateToForward(WebDriver driver) {
		driver.navigate().forward();
	}

	public static void navigateToRefresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public static void navigateToUrl(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

	public void generalSleep() throws InterruptedException {
		Thread.sleep(3000);
	}

	public void javaScriptExecutorClick(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}

	public void captureScreenshotonFailure(WebDriver driver,String screenshotName) throws IOException {
		Date d=new Date();
		String date=d.toString().replace(":", "_").replace(" ", "_")+".png";
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(source, new File("./Screenshots/"+screenshotName+date));
		System.out.println("Screenshot Taken");

	}

	public String getTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestampUnique=String.valueOf(timestamp.getTime());
		return timestampUnique;

	}

	public boolean tableIteration(WebDriver driver,List<WebElement> rElement,List<WebElement> cElement,int rowstart,int columnstart,String expected,String tPath,int colnum) {
		boolean flag = false;
		int rowCount=rElement.size();
		int colCount=cElement.size();

		if(colnum==1) {
			for(int i=rowstart;i<=rowCount;i++) {
				if(flag==true) {
					break;
				}
				for(int j=columnstart;j<=colCount;j++) {
					if(j==1) {
						WebElement element=driver.findElement(By.xpath(""+tPath+"/tr["+i+"]/td["+j+"]"));
						String webData=element.getText();

						if(webData.equalsIgnoreCase(expected)) {
							flag = true;

						}

					}
				}


			}
		}else if(colnum==2){

			for(int i=rowstart;i<=rowCount;i++) {
				if(flag==true) {
					break;
				}
				for(int j=columnstart;j<=colCount;j++) {
					if(j==2) {
						WebElement element=driver.findElement(By.xpath(""+tPath+"/tr["+i+"]/td["+j+"]"));
						String webData=element.getText();

						if(webData.equalsIgnoreCase(expected)) {
							flag = true;

						}

					}
				}

			}
		}else if(colnum==5){

			for(int i=rowstart;i<=rowCount;i++) {
				if(flag==true) {
					break;
				}
				for(int j=columnstart;j<=colCount;j++) {
					if(j==5) {
						WebElement element=driver.findElement(By.xpath(""+tPath+"/tr["+i+"]/td["+j+"]"));
						String webData=element.getText();

						if(webData.equalsIgnoreCase(expected)) {
							flag = true;

						}

					}
				}

			}
		}
		else
		{
			flag=false;
		}

		return flag;
	}


	public int tableRowNumber(WebDriver driver,List<WebElement> rElement,int rowstart) {

		int rowCount=rElement.size();
		return rowCount;
	}
	
	public String replaceChar(String input) {
		String output;
		if(!input.contains(".0")) {
		 output = input.replaceAll("[List Orders()P]","");  
		
	}else {
		output = input.replaceAll(".0","");
	}

		return output;
	}
}


