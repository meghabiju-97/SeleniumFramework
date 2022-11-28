package com.supermarket.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

	public static final long EXPLICIT_WAIT = 7;
	public static final long IMPLICIT_WAIT = 5;
	public static final long FLUENT_WAIT = 20;
	public static final long POLLING = 5;


	public static void waitForVisibility(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(EXPLICIT_WAIT));

		wait.until(ExpectedConditions.visibilityOf(element));


	}

	public static void waitForClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(EXPLICIT_WAIT));

		wait.until(ExpectedConditions.elementToBeClickable(element));


	}

	public static void waitForCheckingSelected(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(EXPLICIT_WAIT));

		wait.until(ExpectedConditions.elementToBeSelected(element));



	}

	public static void waitForPresence(WebDriver driver,By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(EXPLICIT_WAIT));

		wait.until(ExpectedConditions.presenceOfElementLocated(locator));



	}

	public static void fluentwaitForVisibility(WebDriver driver,WebElement element) {
		
		  FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver)
		 .withTimeout(Duration.ofSeconds(FLUENT_WAIT))
		 .pollingEvery(Duration.ofSeconds(POLLING))
		 .ignoring(Exception.class);
		  wait.until(ExpectedConditions.visibilityOf(element));


	}





}
