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
	
	@Override
	public void onTestStart(ITestResult result) {
//		test = extent.createTest(result.getMethod().getMethodName());
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
//		test.log(Status.FAIL, "Test Failed: " + result.getName());
//		test.get().fail("Test Failed: " + result.getName().addScreenCaptureFromPath(path));
		String fileName = result.getName() + "_" + System.currentTimeMillis();
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\ScreenShot\\" + fileName + ".png";
		
//		WebDriver driver = driver;
//		 WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		WebDriver driver = BaseClass.getDriver();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File descr = new File(path);
		
		try {
			FileUtils.copyFile(source, descr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		test.addScreenCaptureFromPath(path);
		test.get().fail("Test Failed : " + result.getName()).addScreenCaptureFromPath(path);
		
		System.out.println("Screenshot saved at: " + path);
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
