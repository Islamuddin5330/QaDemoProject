package com.demoqa.utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "loginData")
	public String[][] getLoginData() throws IOException {
		ExcelDate excel = new ExcelDate();
		return excel.getData("Sheet1"); //sheet name
	}
	
	@DataProvider(name = "registerData")
	public Object[][] getRegisterData() throws IOException {
	    ExcelDate excel = new ExcelDate();

	    String[][] data = excel.getData("Register");

	    Object[][] newData = new Object[data.length][data[0].length + 1];

	    for (int i = 0; i < data.length; i++) {
	        newData[i][0] = i + 1; // row index
	        System.arraycopy(data[i], 0, newData[i], 1, data[i].length);
	    }

	    return newData;
	}
    
    @DataProvider(name = "login")
    public Object getLogin() throws IOException {
    	ExcelUpdateRead excel = new ExcelUpdateRead();
    	return excel.getDataFromExcel("Sheet1");
    }

}
