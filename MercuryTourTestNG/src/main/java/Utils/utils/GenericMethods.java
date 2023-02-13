package com.visaops.ustest.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


public class GenericMethods {

    private WebDriver driver;

    public GenericMethods(WebDriver driver)
    {
        this.driver = driver;
    }

    public By getBy(String locatorType, String locatorValue) {
        By locator = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                locator = By.id(locatorValue);
                break;
            case "name":
                locator = By.name(locatorValue);
                break;
            case "classname":
                locator = By.className(locatorValue);
                break;
            case "xpath":
                locator = By.xpath(locatorValue);
                break;
            case "cssSelector":
                locator = By.cssSelector(locatorValue);
                break;
            case "linktext":
                locator = By.linkText(locatorValue);
                break;
            case "partiallinkText":
                locator = By.partialLinkText(locatorValue);
                break;
            case "tagname":
                locator = By.tagName(locatorValue);
                break;
            case "JS":
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                if (locator.equals("getElementById")) {
                    jse.executeScript("document.getElementById");
                } else if (locator.equals("getElementByClass")) {
                    Object List = null;
                    List elements = (List) jse.executeScript("return document.getElementsByClass");
                    for (Object object : elements) {
                        jse.executeScript("arguments[0].click()", object);
                    }
                }
                break;
            default:
                System.out.println("Please pass the right locator ....");
                break;
        }
        return locator;
    }
    // Generic methods for basic Actions in Selenium (click, findElement, Sendkeys ..)
    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public void doSendKey(By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }
    public void doActionsSenKeys(By locator, String value) {
        Actions act = new Actions(driver);
        act.sendKeys(getElement(locator), value).perform();
    }

    public void doActionsClick(By locator) {
        Actions act = new Actions(driver);
        act.click(getElement(locator)).perform();
    }

    public void doMouseAction(By locator) throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement targetElement = getElement(locator);
        action.moveToElement(targetElement).build().perform();
        targetElement.click();
        Thread.sleep(5000);
    }

    public void jsDocumentSelector(By locator) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement targetElm = (WebElement) locator;
        jse.executeScript("argument[0].click(), targetElm");
    }

    public int doRadioBtnCount(By locator) {

           List<WebElement> checkBoxes = driver.findElements(locator);
           int numberOfBoxes = checkBoxes.size();

            return numberOfBoxes;
     }

    public void doGetConditionsQuestions(By locator, String value) {

    List<WebElement> conditionQuestions = driver.findElements(locator);

	   if(conditionQuestions.size() != 0) 
	   {
		   //Reporter.log(conditionQuestions.size() + " Elements found by TagName as checkbox \n");
		   Reporter.log("========================" + " Select Condition that Applies Page " + " ========================");

		   for(WebElement inputElement : conditionQuestions) 
		   {

			   Reporter.log(inputElement.getAttribute(value));

		   }
		   Reporter.log("===============================================================================================");	   
	  }
    }

    public void doMulitipleCheckBoxes(By locator) {

       List<WebElement> checkboxes = driver.findElements(locator);

       		for(int i=0; i<checkboxes.size(); i++) {
       			if(checkboxes.get(i).isDisplayed() && checkboxes.get(i).isEnabled()) {
       				checkboxes.get(i).click();
       			}    
           }
    }

    public int getElementsCount(By locator) {
        return getElements(locator).size();
    }

    private List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public String doGetElementText(By locator) {
        return getElement(locator).getText();
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public String doGetAttributeValue(By locator, String attributeName) {
        return getElement(locator).getAttribute(attributeName);
    }

    public boolean doIsDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }

    public void clickOnElementFromSection(By locator, String value) {
        List<WebElement> eleList = getElements(locator);
        System.out.println(eleList.size());
        for (WebElement e : eleList) {
            String text = e.getText();
            System.out.println("==================================" + text + "==================================");
            if (text.equals(value)) {
                e.click();
                break;
            }
        }
    }
    public int getElementsListCount(By locator) {
        return getElements(locator).size();
    }

    public List<String> getElementsTextList(By locator) {
        List<String> eleTextList = new ArrayList<String>();
        List<WebElement> eleList = getElements(locator);
        for (WebElement e : eleList) {
            String text = e.getText();
            eleTextList.add(text);
        }
        return eleTextList;
    }
    public void printAllElementsText(By locator) {
        List<WebElement> eleList = getElements(locator);
        for (WebElement e : eleList) {
            String text = e.getText();
            System.out.println("====================" + text + "====================");
        }
    }
    // ***************** DropDownUtils ***********************************
    public void doSelectDropDownByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }
    public void doSelectDropDownByVisibleText(By locator, String visibleText) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(visibleText);
    }
    public void doSelectDropDownByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }
    public int doSelectDropDownByValueCount(By locator, int value) {
        return getElements(locator).size();
    }

    public List<String> doSelectDropDownByValueList(By locator) {
        List<String> valueList = new ArrayList<String>();
        Select select = new Select(getElement(locator));
        List<WebElement> optionList = select.getOptions();
        for (WebElement e : optionList) {
            String text = e.getText();
            valueList.add(text);
        }
        return valueList;
    }
    public void SelectValueFromSelectDropDown(By locator, String value) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionList = select.getOptions();

        for (WebElement e : optionList) {
            String text = e.getText();
            if (text.contains(value)) {
                e.click();
                break;
            }
        }
    }
    public void printSelectDropDownValue(By locator) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionList = select.getOptions();
        System.out.println(optionList.size());

        for (WebElement e : optionList) {
            String text = e.getText();
            System.out.println("===================  " + text + " ===================  ");
        }
    }

    public void selectValueFromDropDown(By locator, String value) {
        List<WebElement> countryOptions = getElements(locator);
        System.out.println(countryOptions.size());
        for (int i = 0; i < countryOptions.size(); i++) {
            String text = countryOptions.get(i).getText();
            System.out.println("================= " + text + " ================= ");
            if (text.contains(value)) {
                countryOptions.get(i).click();
                break;
            }
        }
    }
    //************************* Wait utils **************************

    /**
     * An expectation for checking that the title contains a case-sensitive
     * substring
     *
     * @param titleFraction
     * @param timeOut
     * @return
     */

    public String waitForTitleContains(String titleFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
            return driver.getTitle();
        }
        return null;
    }

    /**
     * An expectation for checking the title page
     *
     * @param titleValue
     * @param timeOut
     * @return
     */
    public String waitForTitlesIs(String titleValue, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.titleIs(titleValue))) {
            return  driver.getTitle();
        }
        return null;
    }
    public String waitForUrl(String urlFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
            return driver.getCurrentUrl();
        }
        return null;
    }
    public String waitForUrlls(String urlValue, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
            return driver.getCurrentUrl();
        }
        return null;
    }
    /**
     * An expecation for checking that an element is present on the DOM of a page
     * This does not necessarily mean that the element is visible
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public WebElement waitForElementPresent(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a heigh and width that is greater than 0
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public List<WebElement> waitForElementToBeClickable(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return Collections.singletonList(wait.until(ExpectedConditions.elementToBeClickable(locator)));
    }
    //Calender Handler
    public void selectDate(By locator) {

    	//driver.findElement(locator)+"dateNum+").click();

    }
}
