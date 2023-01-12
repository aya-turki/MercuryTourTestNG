package pageTest;

import Pages.RegisterPage;
import Utils.GenericMethods;
import browserFactory.DriverFactory;
import browserFactory.OptionsManager;

import org.testng.annotations.BeforeTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class BaseTest {
	
	WebDriver driver;
	protected DriverFactory df;
	OptionsManager optionManager;
	protected Properties prop;
	protected GenericMethods gm;
	protected RegisterPage registerPage;
	
 
  @BeforeTest
  public void setup() {
	  df = new DriverFactory();
	  prop = df.init_prop();
	  driver = df.init_driver(prop);
	  registerPage = new RegisterPage(driver);
	  
  }

  @AfterTest
  public void teardown() {
  }

}
