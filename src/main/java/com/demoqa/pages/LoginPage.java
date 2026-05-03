package com.demoqa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.actiondriver.ActionClass;
import com.demoqa.base.BaseClass;
import com.demoqa.utilites.ExcelUpdateRead;

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
	
	By userNameField = By.id("userName");
	By passNameField = By.cssSelector("#password");
	By loginButton = By.cssSelector("#login");
	By newUserButton = By.id("newUser");
	
	By firstName = By.id("firstname");
	By lastName = By.cssSelector("#lastname");
	By userName = By.id("userName");
	By passWord = By.cssSelector("#password");
	By registerButton = By.id("register");
	
	By backButton = By.id("gotologin");
	
	By logOutButton = By.xpath("//button[text()='Logout']");
	
	public void clickOnIcon() {
		waitForElemet(loginIcon);
		WebElement iconLogin = getDriver().findElement(loginIcon);
		scrollToElement(iconLogin);
		javaClick(loginIcon);
	}
	
//	public void enterUserLoginDetails(String userName, String PassWord) {
//		waitForElemet(userField);
//		WebElement enterUserDetails = getDriver().findElement(userField);
//		scrollToElement(enterUserDetails);
//		enterText(enterUserDetails, userName);		
//		WebElement enterPassDetails = getDriver().findElement(passField);
//		enterText(enterPassDetails, PassWord);		
//	WebElement newButton = driver.findElement(newUserButton);
//	}
	public void enterUserLoginDetails(String userName, String password) {
	    waitForElemet(userNameField);

	    WebElement user = getDriver().findElement(userNameField);
	    user.clear();
	    user.sendKeys(userName);
	    System.out.println("Typed username");

	    WebElement pass = getDriver().findElement(passNameField);
	    pass.clear();
	    pass.sendKeys(password);
	    System.out.println("Typed password");
	}
	public void clickLoginButton() {
		 waitForElemet(loginButton);
		javaClick(loginButton);	
		System.out.println("User Submitted Form...");
	}
	public void clickNewUserButton() {
		javaClick(newUserButton);
		System.out.println("User clicked on NewUserButton");
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
	public void clickOnBackToLoginButton() {
		javaClick(backButton);
	}
	
//	public void clickOnLogOutButton() throws IOException {
//		boolean isLoginSuccess = getDriver().findElement(logOutButton).isDisplayed();
//		ExcelUpdateRead update = new ExcelUpdateRead();
//		if(isLoginSuccess) {
//			update.writeData(1, 2, "Pass");
//			System.out.println("Value updated");
//		}else {
//			update.writeData(1, 2, "Fail");
//		}
//	}
//	Page class → only actions not write/Read
	public void clickOnLogOutButton() {
	    javaClick(logOutButton);
	}
	
	public String getLoggedInUserName() {
	    return getDriver().findElement(By.id("userName-value")).getText();
	}
	public String getRegistrationMessage() {
		return getDriver().findElement(By.id("userName-value")).getText();
	}

	
}
