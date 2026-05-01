package com.demoqa.utilites;

import java.io.FileInputStream;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

 	public static ExtentReports extent;
	
	public static ExtentReports getExtentReport() {
		if(extent==null) {
			String path = System.getProperty("user.dir")+"\\src\\test\\resources\\ExtentReport\\ExtentReport.html";
			ExtentSparkReporter spark = new ExtentSparkReporter(path);
			
			spark.config().setDocumentTitle("Automation Testing");
			spark.config().setReportName("DemoQA Test Execution");
			
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Tester", "Your Name");
			extent.setSystemInfo("OS", "Windows");			
		}
		return extent;
	}
}
