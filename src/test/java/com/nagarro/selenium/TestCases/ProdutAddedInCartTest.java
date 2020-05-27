package com.nagarro.selenium.TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.selenium.Base.BaseClass;
import com.nagarro.selenium.Pages.AddToCartPage;
import com.nagarro.selenium.Pages.CartPage;
import com.nagarro.selenium.Pages.HeaderPage;
import com.nagarro.selenium.Pages.ProductPage;
import com.nagarro.selenium.TestData.DataProviderClass;


public class ProdutAddedInCartTest extends BaseClass {

	HeaderPage menu;
	ProductPage item;
	AddToCartPage addToCart;
	CartPage cart;
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider="getData", groups= {"Regression", "Sanity"})
    
	public void verifyProductOnSearchResultsPage(Hashtable<String,String> data) {
		menu = new HeaderPage(driver);
		item = new ProductPage(driver);

		menu.clickingOnAllDropdown();
		menu.selectTheOptionFromAllDropdown(data.get("Category"));
		menu.enteringValueInSearchField(data.get("Search_Term"));
		menu.clickingOnSearchIcon();
		String expectedText = item.getFirstProductTitleFromResults();
		item.clickingOnFirstProductLink();
		item.verifyNewTab();
		String actualText = item.getTheProductTitleInNewTab();
		Assert.assertTrue(actualText.contains(expectedText));

	}

	@Test(dependsOnMethods = { "verifyProductOnSearchResultsPage" }, groups= {"Regression"})
	public void verifyAddToCartPage() {
		addToCart = new AddToCartPage(driver);
		addToCart.clickingOnAddToCartButton();

		Assert.assertTrue(addToCart.isConfirmationMessageDisplayed(), "Confirmation message not displayed");
	}

	@Test(dependsOnMethods = { "verifyProductOnSearchResultsPage", "verifyAddToCartPage" }, groups= {"Regression"})
	public void clickingOnCartButtonFromHeader() {
		cart = new CartPage(driver);
		cart.cartButtonFromHeader();
		Assert.assertTrue(cart.getProductTextFromCartPage().contains(ProductPage.selectedProductTitle),"is Product displayed on the Cart ?");
	}
	
	

}
