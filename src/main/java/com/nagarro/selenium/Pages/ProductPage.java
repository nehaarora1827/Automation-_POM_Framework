package com.nagarro.selenium.Pages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.nagarro.selenium.Base.BaseClass;

public class ProductPage extends BaseClass {

	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	public static Logger log = Logger.getLogger(ProductPage.class.getName());

	public static String selectedProductTitle;
	
    //Define Product_Category page locators
	@FindBy(xpath = "//*[@class='a-size-medium a-color-base a-text-normal']")
	public WebElement productTitle;

	@FindBy(xpath = "//*[@id='productTitle']")
	public WebElement productHeader;
	
    //Click on product link
	public void clickingOnFirstProductLink() {
		waitForElement(productTitle);
		productTitle.click();
		log.info("Clicking on first product title!!!");
	}

	//Getting the product title
	public String getFirstProductTitleFromResults() {
		selectedProductTitle = productTitle.getText();
		log.info("Get first product title!!!");
		return selectedProductTitle;
	}

	//Product is opening in new tab
	public void verifyNewTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		log.info("Window is opening in new tab!!!");
	}

	//Get the product title 
	public String getTheProductTitleInNewTab() {
		String producttext = productHeader.getText();
		System.out.println(producttext);
		log.info("Get the product title opened in new tab!!!");
		return producttext;
	}

}
