package browserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @Author Aya Turki
 * This class is responsalbe for the broweser, property file and screenshot method
*/

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("The application is runing on -> " + browserName);
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			tldriver.set(driver = new ChromeDriver(optionsManager.getChromeOptions()));
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
		driver.get(prop.getProperty("url"));
		return getDriver();		
	}
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	public Properties init_prop() {
		
			FileInputStream ip = null;
			prop = new Properties();
			try {
				ip = new FileInputStream("./src/main/resources/configuration/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return prop;
	}

	public String getScreenshot(String methodName) {
		Calendar cal = Calendar.getInstance();
		java.util.Date time = cal.getTime();
		String timeStamp = time.toString().replace(":", "").replace(" ", ".");
		
		File srcFile = (((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE));
		//String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis()+".png";
		String path = System.getProperty("user.dir") + "/screenshots/"+methodName+timeStamp + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	

}
