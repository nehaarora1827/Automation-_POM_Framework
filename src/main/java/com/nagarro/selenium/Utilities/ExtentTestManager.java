package com.nagarro.selenium.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	
	static ExtentReports extent = ExtentManager.getInstance();
	static ExtentTest test;

	public static ExtentTest getTest() {
		
		return test;
	}

	public static void endTest() {
		extent.flush();
	}

	public static  ExtentTest startTest(String testName) {
		 test = extent.createTest(testName);
		return test;
	}
}
