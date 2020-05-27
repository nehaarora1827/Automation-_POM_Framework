package com.nagarro.selenium.Pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.nagarro.selenium.Base.BaseClass;

public class CartPage extends BaseClass {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public static Logger log = Logger.getLogger(CartPage.class.getName());

	@FindBy(xpath = "//*[@id='nav-tools']/a[1]/following-sibling::a[4]")
	public WebElement cart;

	@FindBy(xpath = "//*[@class='a-size-medium sc-product-title']")
	public List<WebElement> CartProductName;

	public void cartButtonFromHeader() {
		cart.click();
		log.info("Clicking on Cart button from header!!!");
	}

	public List<String> getProductTextFromCartPage() {
		List<String> cartProductTitle = new ArrayList<String>();

		// verify product in cart
		List<WebElement> products = CartProductName;
		System.out.println("element.size : " + products.size());
		for (WebElement ele : products) {
			System.out.println("............" + ele.getText());
			cartProductTitle.add(ele.getText());

		}
		System.out.println("List.size : " + cartProductTitle.toString());
		log.info("Checking product is displaying on Shopping Cart list!!!");
		return cartProductTitle;

	}

}
