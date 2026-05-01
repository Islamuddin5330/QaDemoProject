package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demoqa.actiondriver.ActionClass;
import com.demoqa.base.BaseClass;

public class HomePage extends ActionClass{

//	public HomePage(WebDriver driver) {
//		super(driver);
//		PageFactory.initElements(getDriver(), this);
//	}
	//if we are using THreadlocal then we need to use below one
	public HomePage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
//	@FindBy(xpath="//h5[text()='Book Store Application']")
//	private WebElement bookStoreIcon;
	
	By bookStoreIcon = By.xpath("//h5[text()='Book Store Application']/parent::div");

	public LoginPage clickBookStoreIcon() {
	    waitForElemet(bookStoreIcon); // wait until clickable
	    WebElement icon = getDriver().findElement(bookStoreIcon);
	    scrollToElement(icon);
	    System.out.println("User is going to click on iconButton");
	    
	    // Perform the click
	    javaClick(bookStoreIcon);
	    System.out.println("User is clicked on Button");
	    
	    return new LoginPage();
	}

}
