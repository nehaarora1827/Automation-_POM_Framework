package com.nagarro.selenium.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.nagarro.selenium.Base.BaseClass;

public class AddToCartPage extends BaseClass {

	public AddToCartPage(WebDriver driver) {
		super(driver);
	}

	public static Logger log = Logger.getLogger(AddToCartPage.class.getName());

	@FindBy(xpath = "//input[@id='add-to-cart-button']")
	public WebElement addToCartButtonFirst;

	@FindBy(xpath = "//a[@id='buybox-see-all-buying-choices-announce']")
	public WebElement seeAllBuyingButton;

	@FindBy(xpath = "//input[@name='submit.addToCart']")
	public WebElement addToCartButtonSecond;

	@FindBy(xpath = "//div[@id='huc-v2-order-row-messages']")
	public WebElement confirmationMessage;

	public void clickingOnAddToCartButton() {

		if (addToCartButtonFirst.isDisplayed()) {
			waitForElement(addToCartButtonFirst);
			addToCartButtonFirst.click();
		} else if (seeAllBuyingButton.isDisplayed()) {
			waitForElement(seeAllBuyingButton);
			seeAllBuyingButton.click();

			waitForElement(addToCartButtonSecond);
			addToCartButtonSecond.click();
		} else {

			System.out.println("Button is not displayed");
		}

		log.info("Clicking on Add to Cart button!!!");
	}

	public boolean isConfirmationMessageDisplayed() {
		waitForElement(confirmationMessage);
		Boolean IsConfirmationDisplayed = confirmationMessage.isDisplayed();
		log.info("Checking the confirmation message is dispalyed!!!");
		return IsConfirmationDisplayed;

	}

}
