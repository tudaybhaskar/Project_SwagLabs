package com.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

	public ProductsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private By productsPageHeader = By.xpath("//span[@class='title' and text()='Products']");
	private By links_a = By.tagName("a");
	private By products_name = By.xpath("//div[@class='inventory_item_name']");
	private By products_price = By.xpath("//div[@class='inventory_item_name']/parent::a/parent::div//following-sibling::div[@class='pricebar']");
	private By button_sideMenu = By.id("react-burger-menu-btn");
	private By logOut_link = By.id("logout_sidebar_link");
	private By productsPrices = By.xpath("//div[@class='inventory_item_name']/parent::a[1]/parent::div/following-sibling::div[@class='pricebar']/div");
	
	public By getLocator_ProductPrices(int i) {
		return By.xpath("(//div[@class='inventory_item_name'])["+i+"]/parent::a["+i+"]/parent::div/following-sibling::div[@class='pricebar']/div");
	
	}
	
	public By getLocator_ProductNames(int i) {
		return By.xpath("(//div[@class='inventory_item_name'])["+i+"]");
	
	}
	
	public Map getMap_ProductsAndPrices() {
		Map<String,String> map =  new HashMap<String,String>();
		List<WebElement> li_products = getListOfProducts();
		int i = li_products.size();
		for(int j = 1; j<=i ; j++) {
			By productDescription = getLocator_ProductNames(j);
			By productPrice = getLocator_ProductPrices(j);
			String name_product = getWebElement(productDescription).getText();
			String price_product = getWebElement(productDescription).getText();
			map.put(name_product, price_product);
		}
		return map;
		
	}
	
	public boolean verifyProductName_ForPrice(Map map,String exp_productDescription,String exp_ProductPrice) {
		boolean flag = false;
		Set set = map.entrySet();
		Iterator itr = set.iterator();
		while(itr.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)itr.next();
			if(entry.getKey().equalsIgnoreCase(exp_productDescription)) {
				System.out.println("Actual Product Description : "+entry.getKey()+ "--- Product Price : "+entry.getValue());
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public String getProductsPageHeader() {
		return getWebElement(productsPageHeader).getText();
	}
	
	public List<WebElement> getListElementsWithLinks() {
		return getWebElements(links_a);
	}
	
	public List<WebElement> getListOfProducts() {
		return getWebElements(products_name);
		
	}
	
	public List<WebElement> getListOfPrices(){
		return getWebElements(products_price);
	}
	
	public boolean verify_ProductName(List<WebElement> list,String exp_productName) {
		boolean flag = false;
		Iterator<WebElement> i = list.iterator();
		while(i.hasNext()) {
			String productName = i.next().getText();
			if(productName.equalsIgnoreCase(exp_productName)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void logOut() {
		getWebElement(button_sideMenu).click();
		getWebElement(logOut_link).click();
	}
	
}
