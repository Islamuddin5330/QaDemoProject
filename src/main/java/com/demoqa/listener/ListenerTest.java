package com.demoqa.listener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demoqa.base.BaseClass;
import com.demoqa.utilites.ExtentManager;

public class ListenerTest implements ITestListener{

	ExtentReports extent = ExtentManager.getExtentReport();
	ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
//	@Override
//	public void onTestStart(ITestResult result) {
////		test = extent.createTest(result.getMethod().getMethodName());
//		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//		test.set(extentTest);
//	}
	@Override
	public void onTestStart(ITestResult result) {
	    ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
	    test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		test.log(Status.PASS, "Test Passed: " + result.getName());
		test.get().pass("Test Passed : " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {

	    ExtentTest extentTest = test.get();

	    if (extentTest == null) {
	        extentTest = extent.createTest(result.getMethod().getMethodName());
	        test.set(extentTest);
	    }

	    String fileName = result.getName() + "_" + System.currentTimeMillis();
	    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\ScreenShot\\" + fileName + ".png";

	    try {
	        WebDriver driver = BaseClass.getDriver();

	        if (driver != null) {
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            File source = ts.getScreenshotAs(OutputType.FILE);
	            File dest = new File(path);
	            FileUtils.copyFile(source, dest);

	            extentTest.fail("Test Failed").addScreenCaptureFromPath(path);
	        } else {
	            extentTest.fail("Driver is NULL");
	        }

	    } catch (Exception e) {
	        extentTest.fail("Failure handling error: " + e.getMessage());
	    }
	}

	@Override
	public void onTestSkipped(ITestResult result) {
//		test.log(Status.SKIP, "Test Skiped: " + result.getName());
		test.get().skip("Test Skiped : " + result.getName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
