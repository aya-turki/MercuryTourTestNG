package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import Utils.GenericMethods;

public class RegisterPage {
	
	private WebDriver driver;
	private GenericMethods gm;

	//1. Object Repository
	private By registerTab = By.xpath("//a[normalize-space()='REGISTER']");
	//  Contact Information Section Locators 
	private By firstName = By.xpath("//input[@name='firstName']");
	private By lastName = By.xpath("//input[@name='lastName']");
	private By phone = By.xpath("//input[@name='phone']");
	private By email = By.xpath("//input[@name='userName']");
	
	//  Mailing Information Section Locators
	private By address = By.xpath("//input[@name='address1']");
	private By city = By.xpath("//input[@name='city']");
	private By state = By.xpath("//input[@name='state']");
	private By postalCode = By.xpath("//input[@name='postalCode']");
	private By country = By.xpath("//select[@name='country']");
	
	// User Information Section Locators
	private By userName = By.xpath("//input[@name='email']");
	private By password = By.xpath("//input[@name='password']");
	private By confirmPwd = By.xpath("//input[@name='confirmPassword']");
	private By submitBtn = By.xpath("//input[@name='submit']");
	private By validateRegistration = By.xpath("(//tbody//td/p)[5]");
	
	//2. constructor 
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		gm = new GenericMethods(this.driver);
	}
	
	//2 Actions Repository
	public void setVerifyRegisterTab () {
		gm.doClick(registerTab);
	}
	public void setContactInformationForm(String firstName, String lastName, String phone, String email) {
		gm.doSendKey(this.firstName, "Joe");
		gm.doSendKey(this.lastName, "Doe");
		gm.doSendKey(this.phone, "5555555555");
		gm.doSendKey(this.email, "Joe@gmail.com");
		
	}
	public void setMailingInformationForm(String address, String city, String state, String postalCode, String country) {
		gm.doSendKey(this.address, address);	
		gm.doSendKey(this.city, city);
		gm.doSendKey(this.state, state);
		gm.doSendKey(this.postalCode, "22038");
		gm.doSelectByVisibleText(this.country, "TURKEY");
		
	}
	public void setUserInformationForm() {
		gm.doSendKey(userName, "Joe");
		gm.doSendKey(password, "Joe123");
		gm.doSendKey(confirmPwd, "Joe123");		
	}
	
	public void setSubmitRegistrationFormBtn() {
		gm.doClick(submitBtn);
	}
	public void setValidateRegistrationText() {
		String expectedText = "Thank you for registering. You may now sign-in using the user name and password you've just entered";
		String actualText = gm.doGetText(validateRegistration);
		if (expectedText.equals(actualText)) {
			Reporter.log("The Registration was submited succesfully for -> " + firstName + " " + lastName);
		}
		
	}
}
