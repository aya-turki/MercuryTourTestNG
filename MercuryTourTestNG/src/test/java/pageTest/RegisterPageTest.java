package pageTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.ExtentReportListener;


@Listeners ({ExtentReportListener.class})

public class RegisterPageTest extends BaseTest {

	@DataProvider
	public Object [][] registrationData() {
		Object data [][] = {};
		return data;
	}
  @Test (priority = 0, description = "Validate the Registration Form", dataProvider = "registrationData")
  public void  verifyRegisterTab (String firstName, String lastName, String phone, String email, 
		  String address, String city, String state, String postalCode, String country) {
	  registerPage.setVerifyRegisterTab();
	  registerPage.setContactInformationForm(firstName, lastName, phone, email);
	  registerPage.setMailingInformationForm(address, city, state, postalCode, country);
	  registerPage.setUserInformationForm();
  }
  @Test (priority = 1, description = "Submit the Registration Form")
  public void submitRegistrationForm (){
	  registerPage.setSubmitRegistrationFormBtn();
  }
  @Test
  public void validateUserRegistration () {
	  registerPage.setValidateRegistrationText();
  }
}
