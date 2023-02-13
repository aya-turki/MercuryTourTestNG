package com.visaops.ustest.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.visaops.ustest.customexception.FrameworkException;
import com.visaops.ustest.utils.ExcelReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;

/***
 * @Author Aya Turki
 * This class runs webdriver and config file
 * @return this method will retun webdriver
 */
public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	ExcelReader excelReader = new ExcelReader();
	String browserName, country, url;
	Locations location = new Locations();
	 
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	/**
	 * This method is used to initialize the driver on the basis of given browser name
	 * The mission name is retrieved from Location Class using RetrieveURL Method
	 * @param prop
	 * @return
	 * @throws Exception 
	 */

	public WebDriver init_driver() throws Exception {
		//browserName = prop.getProperty("browser").trim();
		excelReader.setExcelFile("./src/main/resources/testData/TestDataAutomation.xlsx", "signup"); 
		browserName = excelReader.getCellData("Browser", 1).trim();
		System.out.println("browser name is:  " + browserName);
			
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase("safari")) {
			tldriver.set(new SafariDriver());
		} else {
			System.out.println("Please enter a driver name");
			System.out.print("This is Test Enviornment for > " + country);
		}
		return getDriver();
		
	}
	
	public WebDriver navigateURL() throws Exception {
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();  
		url = location.retrieveURL();
		getDriver().get(url);
		 
		return driver;
		      
  	 
	}

	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	
/**
 * This method is used to initialize the properties from the respective config file
 * 
 * @return this returns class object with all the config properties
 */

public Properties init_prop() {

	FileInputStream ip = null;
	prop = new Properties();

	String envName = System.getProperty("env");
	System.out.println("Running test on environment  " + envName);

	if(envName == null) {
		System.out.println("No env is given .... hence running in QA Environment");

		try {
			ip= new FileInputStream("./src/main/resources/conf/config.properties");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}              

	} else {

		try {
			switch(envName.toLowerCase()) {
			
			case "qa" :
				ip = new FileInputStream("./src/main/resources/conf/config.properties");
				break;

			case "qa2" :
				ip = new FileInputStream("./src/main/resources/conf/qa.properties");
				break;

			case "dev":
				ip = new FileInputStream("./src/main/resources/conf/dev.config.properties");
				break;

			case "cert":
				ip = new FileInputStream("./src/main/resources/conf/cert.config.properties");     
				break;

			default:
				System.out.println("Please pass the right environment value  " + envName);
				throw new FrameworkException("no env found ...");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(FrameworkException e) {
			e.fillInStackTrace();
		}
	}
	try {
		prop.load(ip);
	} catch (IOException e) {
		e.printStackTrace();
	}

	return prop;
}

/**
 *This method capture the screenshot
 *
 */

public String getScreenshot(String methodName) {
	Calendar cal = Calendar.getInstance();
	java.util.Date time = cal.getTime();
	String timeStamp = time.toString().replace(":", "").replace(" ", ".");
	
	File srcFile = (((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE));
	String path = System.getProperty("/Users/ayaturki/Eclipse_Project/AisTestAutomationBolivia/screenshots"+methodName+timeStamp + ".png");
	File destination = new File(path);
	try {
		FileUtils.copyFile(srcFile, destination);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return path;
}

}



