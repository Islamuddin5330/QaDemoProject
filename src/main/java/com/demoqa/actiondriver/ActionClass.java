package com.demoqa.actiondriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demoqa.base.BaseClass;

public class ActionClass {

//	public WebDriver driver;
//	
//	public ActionClass(WebDriver driver) {
//		this.driver = driver;
//	}
	
	//After Threadlocal use this one
	protected WebDriver getDriver() {
		return BaseClass.getDriver();
	}
	
	//click
	public void click(WebElement element) {
		element.click();
	}
	
	public void javaClick(By locator) {
		WebElement element = getDriver().findElement(locator);
		((JavascriptExecutor)getDriver()).executeScript("arguments[0].click();", element);
	}
	
	//Enter
	public void enterText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
		String enterdText = element.getAttribute("value");
		System.out.println("Text entered is: " + enterdText);
	}
	
	//getText
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	//Wait
	public void waitForElemet(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void waitForElementClickable(By locator) {
	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	//scroll to element
	public void scrollToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			js.executeScript("arguments[0].scrollIntoView({block:'center'})", element);
		} catch (Exception e) {
		System.out.println("Unable to scroll to element: " + e.getMessage());
		}
	}
	
	//Take screenshot
	public String takescreenShot(String fileName) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\ScreenShot"+ fileName + "_" + timestamp + ".png";
		
		TakesScreenshot ts = (TakesScreenshot)getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(path);
		FileUtils.copyFile(source,destination);
		
		return path;
		
	}
}
