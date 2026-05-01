package com.demoqa.test;

import org.testng.annotations.Test;

import com.demoqa.base.BaseClass;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.LoginPage;

public class UserLoginTest extends BaseClass{
	
	@Test
	public void loginTest() {
		HomePage homepage = new HomePage();
		LoginPage loginpage = homepage.clickBookStoreIcon();
		loginpage.clickOnIcon();
		loginpage.enterUserLoginDetails("Naveen01", "naveen@1234");
		loginpage.clickLoginButton();
		loginpage.enterUserDeatils("Naveen","Govinda", "Naveen01", "naveen@1234");
		loginpage.clickRegisterButton();
	}

}
