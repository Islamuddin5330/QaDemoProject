package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.actiondriver.ActionClass;
import com.demoqa.base.BaseClass;

public class LoginPage extends ActionClass{
	
//	public LoginPage(WebDriver driver) {
//		super(driver);
//		PageFactory.initElements(driver, this);
//	}
//	if we are using Threadlocal
	public LoginPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	By loginIcon = By.xpath("//span[text()='Login']");
	
	By userField = By.id("userName");
	By passField = By.cssSelector("#password");
	By newUserButton = By.id("newUser");
	By loginButton = By.cssSelector("#login");
	
	By firstName = By.id("firstname");
	By lastName = By.cssSelector("#lastname");
	By userName = By.id("userName");
	By passWord = By.cssSelector("#password");
	By registerButton = By.id("register");
	
	public void clickOnIcon() {
		waitForElemet(loginIcon);
		WebElement iconLogin = getDriver().findElement(loginIcon);
		scrollToElement(iconLogin);
		javaClick(loginIcon);
	}
	
	public void enterUserLoginDetails(String userName, String PassWord) {
		waitForElemet(userField);
		WebElement enterUserDetails = getDriver().findElement(userField);
		scrollToElement(enterUserDetails);
		enterText(enterUserDetails, userName);		
		WebElement enterPassDetails = getDriver().findElement(passField);
		enterText(enterPassDetails, PassWord);		
//		WebElement newButton = driver.findElement(newUserButton);
	}
	public void clickLoginButton() {
		javaClick(newUserButton);		
	}
	
	public void enterUserDeatils(String fName, String lName, String uName, String uPassword) {
		waitForElemet(firstName);
		WebElement fn = getDriver().findElement(firstName);
		enterText(fn, fName);
		WebElement ln = getDriver().findElement(lastName);
		enterText(ln, lName);
		WebElement un = getDriver().findElement(userName);
		enterText(un, uName);
		WebElement up = getDriver().findElement(passWord);
		enterText(up, uPassword);
		
	}
	
	public void clickRegisterButton() {
		javaClick(registerButton);	
	}

	
}
