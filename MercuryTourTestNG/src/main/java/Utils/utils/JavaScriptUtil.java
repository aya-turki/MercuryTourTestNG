package com.visaops.ustest.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	private WebDriver driver;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}
	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 15; i++) {
			changeColor("#000000", element, driver); //1
			changeColor(bgcolor, element, driver); //2
		}
	}
	
	public static void changeColor(String color, WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + " ' " + element);
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			
		}
		
	}
	public static void drawBorder(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.bordr = '3px solid red'", element);
		js.executeScript("argument[0].style.border= '3px solid green'", element);
		 
	}

	JavascriptExecutor js = new JavascriptExecutor() {
		@Override
		public Object executeScript(String script, Object... args) {
			return null;
		}

		@Override
		public Object executeAsyncScript(String script, Object... args) {
			return null;

		}
	};
	
}
