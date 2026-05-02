package com.demoqa.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoqa.base.BaseClass;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.LoginPage;
import com.demoqa.utilites.DataProviders;
import com.demoqa.utilites.ExcelUpdateRead;

public class UserLoginTest extends BaseClass{
	
//Add retry mechanism for failed tests	
	
	@Test(dataProvider = "registerData", dataProviderClass = DataProviders.class)
	public void registerTest(String fName, String lName, String uName, String password) throws IOException {

	    HomePage homepage = new HomePage();
	    LoginPage loginpage = homepage.clickBookStoreIcon();

	    loginpage.clickOnIcon();
	    loginpage.clickNewUserButton();
	    loginpage.enterUserDeatils(fName, lName, uName, password);
	    loginpage.clickRegisterButton();

	    ExcelUpdateRead excel = new ExcelUpdateRead();

	    try {
	        // Handle alert (DemoQA behavior)
	        String alertText = getDriver().switchTo().alert().getText();

	        if (alertText.contains("User Register Successfully")) {
	            excel.writeData(1, 2, "Pass");
	        } else {
	            excel.writeData(1, 2, "Fail");
	        }

	        getDriver().switchTo().alert().accept();

	    } catch (Exception e) {
	        // If no alert → registration likely failed (captcha etc.)
	        excel.writeData(1, 2, "Fail");
	    }

	    loginpage.clickOnBackToLoginButton();
	}
	
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
	public void loginTest(String username, String password) throws IOException {

	    HomePage homepage = new HomePage();
	    LoginPage loginpage = homepage.clickBookStoreIcon();

	    loginpage.clickOnIcon();
	    loginpage.enterUserLoginDetails(username, password);
	    loginpage.clickLoginButton();

	    String actualUser = loginpage.getLoggedInUserName();

	    ExcelUpdateRead excel = new ExcelUpdateRead();

	    if (actualUser.equals(username)) {
	        excel.writeData(1, 2, "Pass");
	    } else {
	        excel.writeData(1, 2, "Fail");
	    }

	    Assert.assertEquals(actualUser, username);

	    loginpage.clickOnLogOutButton();
	}
	
}
