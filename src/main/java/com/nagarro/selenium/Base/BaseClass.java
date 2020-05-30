package com.nagarro.selenium.Base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import com.nagarro.selenium.Utilities.ExcelReader;
import com.nagarro.selenium.Utilities.ReadingConfigFile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	static ReadingConfigFile readingPropertiesFile = new ReadingConfigFile();
	public static WebDriver driver;
	public static ExcelReader exl = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\TestData.xlsx");
	public static ExtentTest test;
	public static Logger log = Logger.getLogger(BaseClass.class.getName());


	// Initializing the browser
	public WebDriver initializeBrowser() {

		String browserName = readingPropertiesFile.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info(" ***Chrome browser launched*** ");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("***Firefox browser launched***");
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			log.info("***IE browser launched***");
		}
		// Maximize the window
		driver.manage().window().maximize();
		// Define Implicit wait to wait for element
		int implicitTime = Integer.parseInt(readingPropertiesFile.getProperty("implicit.wait"));
		driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);
		return driver;
	}

	// Define Explicit wait to wait for element
	public void waitForElement(WebElement element) {
		int explicitTime = Integer.parseInt(readingPropertiesFile.getProperty("explicit.wait"));
		WebDriverWait wait = new WebDriverWait(driver, explicitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Calling initialize browser method and navigate to amazon site
	@BeforeTest(groups = { "Regression", "Sanity" })
	public void initialize() {
		initializeBrowser();
		driver.get(readingPropertiesFile.getProperty("testsiteurl"));
		log.info(" ***Navigated to : " + readingPropertiesFile.getProperty("testsiteurl") + "url***");
	}

	// Closing the browser
	@AfterTest(groups = { "Regression", "Sanity" })
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.info(" ***Test execution completed*** ");
	}
}
