package com.tests;

import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.LoginPage;
import com.pages.ProductsPage;
import com.utils.AdditionalTesting;


public class ProductsPageTest extends BaseTest {
	ProductsPage productsPage;
	LoginPage loginPage;
	
	@Test(priority=1,groups = {"Test-Groups"})
	@Parameters(value = {"chrome"})
	public void verify_HyperLinks() {
		LoginPage loginPage = page.getInstance(LoginPage.class);
		productsPage = loginPage.doLogin("standard_user", "secret_sauce");
		String Header = productsPage.getProductsPageHeader();
		Assert.assertEquals(Header, "PRODUCTS");
		AdditionalTesting additionalTesting = new AdditionalTesting(driver);
		additionalTesting.iterate_HttpRequestForLinks(additionalTesting.getPageLinks_List());
		int li_size = additionalTesting.list_BrokenLinks.size();
		Assert.assertEquals(li_size, 0, "Broken links are present in Page");
		productsPage.logOut();
	}
	
	@Test(priority=2,groups = {"Test-Groups"})
	public void verify_ListOfBags_Expected_6() {
		// To get the list of bags and verify its count is equal to 6.
		loginPage = page.getInstance(LoginPage.class);
		productsPage = loginPage.doLogin("standard_user", "secret_sauce");
		List<WebElement> li = productsPage.getListOfProducts();
		int no_Of_Products = li.size();
		Assert.assertEquals(no_Of_Products, 6);
		productsPage.logOut();
		
		
	}
	@Test(priority=3)
	public void verify_SauceLabsFleeceJacket_In_ProductsPage() {
		loginPage = page.getInstance(LoginPage.class);
		productsPage = loginPage.doLogin("standard_user", "secret_sauce");
		List<WebElement> li_products = productsPage.getListOfProducts();
		String exp_ProductName = "Sauce Labs Fleece Jacket";
		boolean actual_booleanValue = productsPage.verify_ProductName(li_products, exp_ProductName);
		Assert.assertEquals(actual_booleanValue, true);
		productsPage.logOut();
		
	}
	
	@Test(priority=0, groups = {"Test-Groups"})
	public void verify_SauceLabsFleeceJacket_In_ProductsPage_Price() {
		String exp_productDescription = "Sauce Labs Fleece Jacket";
		String exp_ProductPrice = "$49.99";
		loginPage = page.getInstance(LoginPage.class);
		productsPage = loginPage.doLogin("standard_user", "secret_sauce");
		Map<String,String> map = productsPage.getMap_ProductsAndPrices();
		boolean verifyPrice = productsPage.verifyProductName_ForPrice(map, exp_productDescription, exp_ProductPrice);
		Assert.assertEquals(verifyPrice, true);
		productsPage.logOut();
		
	}
	
	


}
