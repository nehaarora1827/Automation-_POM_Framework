package com.nagarro.selenium.Utilities;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.nagarro.selenium.Base.BaseClass;

public class Listener implements ITestListener {
	public static ExtentReports report;
	public static Logger log = Logger.getLogger(Listener.class.getName());

	public void onStart(ITestContext context) {
		log.info("*** Test Suite " + context.getName() + " started ***");
		report = ExtentManager.getInstance();
	}

	public void onFinish(ITestContext context) {
		log.info(("*** Test Suite " + context.getName() + " Ending ***"));
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		BaseClass.test = report.createTest(result.getName());
		BaseClass.test.log(Status.INFO, result.getName() +  " : Test Case Started...");
	}

	public void onTestSuccess(ITestResult result) {
		//log.info(("*** Executed " + result.getName() + " Test Case Successfully  ***"));
		BaseClass.test.log(Status.PASS, " Test Case Passed: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		BaseClass.test.log(Status.FAIL, " TestCase failed IS" + result.getName());
		BaseClass.test.log(Status.FAIL, " TestCase failed IS" + result.getThrowable());

		try {
			String screenshotPath = CaptureScreenshot.getScreenshot(BaseClass.driver, result.getName());
			BaseClass.test.addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		BaseClass.test.log(Status.SKIP, "Test Case Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		BaseClass.test.log(Status.FAIL, " Test Failed ");
	}

}
