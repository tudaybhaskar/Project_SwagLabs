package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	
	public static WebDriver driver;
	WebDriverWait wait;
	
	
	Page(WebDriver driver){
		this.driver = driver;
		wait =  new WebDriverWait(driver,20);
	}
	public abstract String getPageHeader(By locator);
	
	public abstract String getPageTitle();
	
	public abstract WebElement getWebElement(By locator);
	
	public abstract List<WebElement> getWebElements(By locator);
	
	public abstract void waitForElementPresent(By locator);
	
	public abstract void waitForElementVisible(By locator);
	
	public abstract void waitForPageTitle(String title);
	
	public abstract void waitForElementClickable(By locator);
	
	public abstract void moveToElement(By locator);
	
	public abstract void selectByValue(By locator,String Svalue);
	
	public abstract void sendKeys(By locator,String Svalue);
	
	public abstract void click(By locator);
	
	public <TPage extends BasePage>TPage getInstance(Class<TPage> pageClass){
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
