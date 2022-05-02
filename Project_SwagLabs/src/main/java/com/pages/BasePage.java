package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
//changes in base page
	@Override
	public String getPageHeader(By locator) {
		return getWebElement(locator).getText();
	}
	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}
	@Override
	public WebElement getWebElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		}catch(Exception e) {
			System.out.println("Some error occurred while creating the element: " + locator.toString());
			e.printStackTrace();
		}
		return element;
	}
	@Override
	public List<WebElement> getWebElements(By locator){
		List<WebElement> li = new ArrayList<WebElement>();
		try {
			li = driver.findElements(locator);
			return li;
		}catch(Exception e) {
			System.out.println("Some Error has occured while fetching the element: " + locator.toString());
			e.printStackTrace();
		}
		return li;	
	}
	public List<WebElement> getListElementsWithLinks() {
		return getWebElements(By.tagName("a"));
	}
	@Override
	public void waitForElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}catch(Exception e) {
			System.out.println("Some Exception or error occurred while waiting for the Element present: " + locator.toString());
		}		
	}
	@Override
	public void waitForPageTitle(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));
		}catch(Exception e) {
			System.out.println("Some Exception or error occurred while waiting for the Page Title: " + title);
		}
		
	}
	@Override
	public void waitForElementVisible(By locator) {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		}catch(Exception e) {
			System.out.println("Some Exception or error occurred while waiting for the Visibility of element : " + locator);	
		}
		
	}

	@Override
	public void waitForElementClickable(By locator) {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
				
		}catch(Exception e) {
			System.out.println("Some Exception or error occurred while waiting for the element to be clickable : " + locator);	
		}
	}
	@Override
	public void moveToElement(By locator) {
		// TODO Auto-generated method stub
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement(locator)).perform();
	}
	@Override
	public void selectByValue(By locator,String Svalue) {
		// TODO Auto-generated method stub
		Select se = new Select(getWebElement(locator));
		se.selectByValue(Svalue);	
	}
	@Override
	public void sendKeys(By locator, String Svalue) {
		// TODO Auto-generated method stub
		moveToElement(locator);
		getWebElement(locator).sendKeys(Svalue);	
	}
	@Override
	public void click(By locator) {
		// TODO Auto-generated method stub
		moveToElement(locator);
		getWebElement(locator).click();
	}
	
	

}
