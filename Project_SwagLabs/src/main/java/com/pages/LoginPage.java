package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

	LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	//Page Locators
	private By userName = By.id("user-name");
	private By password = By.id("password");
	private By loginBtn = By.id("login-button");
	private By LoginPageTitle = By.tagName("title");
	private By unAuthorization_error = By.xpath("//*[contains(text(),'Username and password do not match any user in this service')]");
	
	public WebElement getUserName() {
		return getWebElement(userName);
	}
	
	public WebElement getPassword() {
		return getWebElement(password);
	}
	
	public WebElement getLoginButton() {
		return getWebElement(loginBtn);
	}
	
	public String getLoginPageTitle() {
		return getPageTitle();
	}
	
	public ProductsPage doLogin(String username,String pwd) {
		getWebElement(userName).sendKeys(username);
		getWebElement(password).sendKeys(pwd);
		getWebElement(loginBtn).click();
		return getInstance(ProductsPage.class);
	}

	public void doLogin() {
		getWebElement(userName).sendKeys("");
		getWebElement(password).sendKeys("");
		getWebElement(loginBtn).click();
	}
	
	public void doLogin_WithWrongPassword(String username,String wpwd) {
		getWebElement(userName).sendKeys(username);
		getWebElement(password).sendKeys(wpwd);
		getWebElement(loginBtn).click();
	}
	public boolean verify_UnAuthorizationError() {
		String error = getWebElement(unAuthorization_error).getText();
		boolean flag =true;
		if(error.contains("Username and password do not match")) {
			flag = true;
		}
		else {
			flag =false;
		}
		return flag;
		
	}
	
	
	

}
