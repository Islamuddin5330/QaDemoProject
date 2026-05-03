package com.demoqa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

//	public static WebDriver driver;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public Properties pro;
	
	
	@BeforeMethod
	public void setUp() throws IOException {
		pro = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
		pro.load(fis);
		
		String browser = pro.getProperty("browser");
		
//		if(browser.equalsIgnoreCase("chrome")) {
//			driver.set(new ChromeDriver());			
//		}else if(browser.equalsIgnoreCase("edge")) {
//			driver.set(new EdgeDriver());
//		}else if(browser.equalsIgnoreCase("firefox")) {
//			driver.set(new FirefoxDriver());
//		}else {
//			throw new RuntimeException("Invalid browser" + browser);
//		}
		
		 if(browser.equalsIgnoreCase("chrome")) {
		        WebDriverManager.chromedriver().setup();  // ✅ ADD THIS
		        driver.set(new ChromeDriver());            
		    } else if(browser.equalsIgnoreCase("edge")) {
		        WebDriverManager.edgedriver().setup();
		        driver.set(new EdgeDriver());
		    } else if(browser.equalsIgnoreCase("firefox")) {
		        WebDriverManager.firefoxdriver().setup();
		        driver.set(new FirefoxDriver());
		    } else {
		        throw new RuntimeException("Invalid browser " + browser);
		    }
		
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String url = pro.getProperty("url");
		getDriver().get(url);
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	@AfterMethod
	public void tearDown() {
		if(getDriver() != null) {
			getDriver().quit();
			driver.remove();
		}
	}
}
