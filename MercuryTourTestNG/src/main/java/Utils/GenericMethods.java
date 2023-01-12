package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GenericMethods {

	WebDriver driver;
	GenericMethods gm;

	public GenericMethods(WebDriver driver) {
		this.driver = driver;
	}

	public By getBy(String locatorType, String value) {

		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id" :  locator = By.id(value);
		case "name" : locator = By.name(value); 
		case "className" :  locator = By.className(value);
		case "cssSelector": locator = By.cssSelector(value);
		case "xpath" :  locator = By.xpath(value);
		case "partiallink": locator = By.partialLinkText(value);
		case "linkText" : locator = By.linkText(value);	
		}
		return locator;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);	
	}
	public void doClick (By locator) {
		driver.findElement(locator).click();
	}
	
	public void doSendKey(By locator, String value) {	 
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(value);
	}

	public void doSelectByVisibleText(By locator, String value) {
		Select drpdown = new Select((WebElement) driver.findElement(locator));
		drpdown.selectByVisibleText(value);

	}
	public String doGetText(By locator) {
		return driver.findElement(locator).getText();
	}
}
