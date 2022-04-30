package com.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.pages.BasePage;

public class AdditionalTesting extends BasePage{
	public AdditionalTesting(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	String HomeUrl = "https://www.saucedemo.com";
	public String url = "";
	HttpURLConnection huc = null;
	int responseCode = 0;
	List<WebElement> li = null;
	boolean flag_link = true;
	public List<String> list_BrokenLinks = new ArrayList<String>();

	
	//ProductsPage productsPage = getInstance(ProductsPage.class);
	//List<WebElement> li = productsPage.getNumber_Links();
	
	public List<WebElement> getPageLinks_List() {
		li = this.getInstance(this.getClass()).getListElementsWithLinks();
		return li;
	}
	
	public void iterate_HttpRequestForLinks(List<WebElement> list) {
		Iterator<WebElement> it = list.iterator();
		while(it.hasNext()) {
			url = it.next().getAttribute("href");
			System.out.println("URL is : " + url);
			if(url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for a tag or is empty");
				continue;
			}else if(!url.startsWith(HomeUrl)) {
				System.out.println(" URL belongs to Another domain, skipping it ");
				continue;
			}
			send_HttpRequest_Verify();
		}
	}
	
	
	
	
	public void send_HttpRequest_Verify() {
		
		
		try {
			huc = (HttpURLConnection)(new URL(url).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			this.responseCode = huc.getResponseCode();
			
			if(responseCode >= 400) {
				System.out.println(url + ": is a broken Link");
				flag_link = false;
				list_BrokenLinks.add(url);
				
			}else if(responseCode >= 500) {
				System.out.println(url + ": is not a valid link");
				flag_link = false;
				list_BrokenLinks.add(url);
			}
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
			flag_link = false;
		}catch(IOException e) {
			e.printStackTrace();
			flag_link = false;
		}
		
	}
	
	

}
