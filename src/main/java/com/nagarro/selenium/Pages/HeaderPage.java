package com.nagarro.selenium.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.nagarro.selenium.Base.BaseClass;

public class HeaderPage extends BaseClass {

	public HeaderPage(WebDriver driver) {
		super(driver);
	}

	public static Logger log = Logger.getLogger(HeaderPage.class.getName());

    //Define Header page locators
	@FindBy(xpath = "//*[@id='nav-search']/form/div[1]/div/div")
	public WebElement dropdown;

	@FindBy(id = "searchDropdownBox")
	public WebElement categories;

	@FindBy(xpath = "//*[@id='twotabsearchtextbox']")
	public WebElement searchBar;

	@FindBy(xpath = "//*[@type='submit']")
	public WebElement icon;

	//Click on All dropdown
	public void clickingOnAllDropdown() {
		dropdown.click();
		log.info("Clicking on All dropdown!!!");
	}

	//Select product category
	public void selectTheOptionFromAllDropdown(String option) {
		Select dropDown = new Select(categories);
		dropDown.selectByVisibleText(option);
		log.info("Selecting a Category from All dropdown!!!");
	}

	//Enter the value by selecting the product category
	public void enteringValueInSearchField(String value) {
		searchBar.sendKeys(value);
		log.info("Typing in an Search textbox!!!");
	}

	//Click on Search icon
	public void clickingOnSearchIcon() {
		icon.click();
		log.info("Clicking on Search icon!!!");
	}
}
