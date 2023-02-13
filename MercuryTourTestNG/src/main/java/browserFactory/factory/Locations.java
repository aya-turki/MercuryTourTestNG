package com.visaops.ustest.factory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import com.visaops.ustest.utils.ExcelReader;

import java.io.*;


public class Locations {

	static String username = "visaoops";	
	static String password = "CSC789!";
	static String country = null;
	static String adminPwd = "turki789";
	static String url;
	static protected int i = 1;


	private static Map<String, String> mission = new HashMap<String, String>();

	//No-Ar-constructor to initialize fields
	public Locations() {

	}

	public static String codedValue(String value) {

		byte[] encodedUsername = Base64.encodeBase64(username.getBytes());
		byte[] encodedpassword = Base64.encodeBase64(password.getBytes());
		byte[] encodedadminPwd = Base64.encodeBase64(adminPwd.getBytes());
		System.out.println("encoded string   " + new String(encodedpassword));
		System.out.println("encoded string   " + new String(encodedUsername));	
		System.out.println("encoded string   " + new String(encodedadminPwd));


		byte [] decodedusername = Base64.decodeBase64(encodedUsername);
		byte [] decodedpassword = Base64.decodeBase64(encodedpassword);
		byte [] decodedadminPwd = Base64.decodeBase64(encodedadminPwd);


		System.out.println("decoded username:  " + new String(decodedusername));
		System.out.println("decoded Password:  " + new String(decodedpassword));
		System.out.println("decoded Admin Password:  " + new String(decodedadminPwd));

		return value;		

	}

	public static void seturl() {

		//The Americas (17 mission)

		mission.put("Argentine", 	"https://visaops:CSC789!@ais.ustest-info.com/en-ar/niv");
		mission.put("Bahamas", 		"https://visaops:CSC789!@ais.ustest-info.com/en-bs/niv");
		mission.put("Barbados", 	"https://visaops:CSC789!@ais.ustest-info.com/en-bb/niv");
		mission.put("Belize", 		"https://visaops:CSC789!@ais.ustest-info.com/en-bz/niv");
		mission.put("Tanzania", 	"https://visaops:CSC789!@ais.ustest-info.com/en-tz/niv");
		mission.put("Bolivia", 		"https://visaops:CSC789!@ais.ustest-info.com/en-bo/niv");
		mission.put("Canada", 		"https://visaops:CSC789!@ais.ustest-info.com/en-ca/niv");
		mission.put("Chile", 		"https://visaops:CSC789!@ais.ustest-info.com/en-cl/niv");
		mission.put("Colombia", 	"https://visaops:CSC789!@ais.ustest-info.com/en-co/niv");
		mission.put("Ecuador", 		"https://visaops:CSC789!@ais.ustest-info.com/en-ec/niv");
		mission.put("Guyana", 		"https://visaops:CSC789!@ais.ustest-info.com/en-gy/niv");
		mission.put("Jamaica", 		"https://visaops:CSC789!@ais.ustest-info.com/en-jm/niv");
		mission.put("Mexico", 		"https://visaops:CSC789!@ais.ustest-info.com/en-mx/niv");
		mission.put("Paraguay", 	"https://visaops:CSC789!@ais.ustest-info.com/en-py/niv");
		mission.put("Peru", 		"https://visaops:CSC789!@ais.ustest-info.com/en-pe/niv");
		mission.put("Trinidad", 	"https://visaops:CSC789!@ais.ustest-info.com/en-tt/niv");
		mission.put("Uraguay", 		"https://visaops:CSC789!@ais.ustest-info.com/en-uy/niv");

		// Europe (19 mission)

		mission.put("Albania", 		"https://visaops:CSC789!@ais.ustest-info.com/en-al/niv");
		mission.put("Armenia", 		"https://visaops:CSC789!@ais.ustest-info.com/en-am/niv");
		mission.put("Azerbaijan", 	"https://visaops:CSC789!@ais.ustest-info.com/en-az/niv");
		mission.put("Belgium", 		"https://visaops:CSC789!@ais.ustest-info.com/en-be/niv");
		mission.put("Bosnia", 		"https://visaops:CSC789!@ais.ustest-info.com/en-ba/niv");
		mission.put("Croatia", 		"https://visaops:CSC789!@ais.ustest-info.com/en-hr/niv");
		mission.put("Cyprus", 		"https://visaops:CSC789!@ais.ustest-info.com/en-cy/niv");
		mission.put("France", 		"https://visaops:CSC789!@ais.ustest-info.com/en-fr/niv");
		mission.put("Greece", 		"https://visaops:CSC789!@ais.ustest-info.com/en-gr/niv");
		mission.put("Italy", 		"https://visaops:CSC789!@ais.ustest-info.com/en-it/niv");
		mission.put("Irelan", 		"https://visaops:CSC789!@ais.ustest-info.com/en-ie/niv");
		mission.put("Kosovo", 		"https://visaops:CSC789!@ais.ustest-info.com/en-kv/niv");
		mission.put("Macedonia", 	"https://visaops:CSC789!@ais.ustest-info.com/en-mk/niv");
		mission.put("Netherland", 	"https://visaops:CSC789!@ais.ustest-info.com/en-nl/niv");
		mission.put("Portugal", 	"https://visaops:CSC789!@ais.ustest-info.com/en-pt/niv");
		mission.put("Serbia", 		"https://visaops:CSC789!@ais.ustest-info.com/en-rs/niv");
		mission.put("Spain", 		"https://visaops:CSC789!@ais.ustest-info.com/en-es/niv");
		mission.put("Turkiye", 		"https://visaops:CSC789!@ais.ustest-info.com/en-tr/niv");
		mission.put("UK", 			"https://visaops:CSC789!@ais.ustest-info.com/en-gb/niv");



		//Middle East
		mission.put("TAJ", 			"https://visaops:CSC789!@aais.ustest-info.com/en-il/niv");
		mission.put("UAE", 			"https://visaops:CSC789!@aais.ustest-info.com/en-ae/niv");

		//Africa (12 mission)
		mission.put("Angola", 		"https://visaops:CSC789!@ais.ustest-info.com/en-ao/niv");
		mission.put("Cameroon", 	"https://visaops:CSC789!@ais.ustest-info.com/en-cm/niv");
		mission.put("Cabo Verde", 	"https://visaops:CSC789!@ais.ustest-info.com/en-cv/niv");
		mission.put("Congo", 		"https://visaops:CSC789!@ais.ustest-info.com/en-cd/niv");
		mission.put("Ethiopia", 	"https://visaops:CSC789!@ais.ustest-info.com/en-et/niv");
		mission.put("Kenya", 		"https://visaops:CSC789!@ais.ustest-info.com/en-ke/niv");
		mission.put("Rwanda", 		"https://visaops:CSC789!@ais.ustest-info.com/en-rw/niv");
		mission.put("Senegal", 		"https://visaops:CSC789!@ais.ustest-info.com/en-sn/niv");
		mission.put("Tanzania", 	"https://visaops:CSC789!@ais.ustest-info.com/en-tz/niv");
		mission.put("South Africa", "https://visaops:CSC789!@ais.ustest-info.com/en-za/niv");
		mission.put("Uganda", 		"https://visaops:CSC789!@ais.ustest-info.com/en-ug/niv");
		mission.put("Zambia", 		"https://visaops:CSC789!@ais.ustest-info.com/en-zm/niv"); 

	}

	public static String getValue() throws Exception {

		ExcelReader excelReader = new ExcelReader();
		excelReader = new ExcelReader();
		excelReader.setExcelFile("./src/main/resources/testData/TestDataAutomation.xlsx", "Signup");
		country = excelReader.getCellData("Mission", i).trim();

		System.out.println(country);

		Collection<String> values=mission.values();

		for (String str : values) {
		}	
		if (country.contains("Argentina")) { url = mission.get("Argentina"); }


		else if (country.contains("The Bahamas")) { url = mission.get("Bahamas"); }

		else if (country.contains("Barbados")) { url = mission.get("Barbados"); }

		else if (country.contains("Belize")) { url = mission.get("Belize"); }

		else if (country.contains("Brazil")) { url = mission.get("Brazil"); }

		else if (country.contains("Bolivia")) { url = mission.get("Bolivia"); }

		else if (country.contains("Canada")) { url = mission.get("Canada");  }

		else if (country.contains("Chile")) { url = mission.get("Chile"); }

		else if (country.contains("Colombia")) { url = mission.get("Colombia"); }

		else if (country.contains("Ecuador")) { url = mission.get("Ecuador"); }

		else if (country.contains("Guyana")) { url = mission.get("Guyana"); }

		else if (country.contains("Jamaica")) { url = mission.get("Jamaica"); }

		else if (country.contains("Mexico")) { url = mission.get("Mexico"); }

		else if (country.contains("Paraguay")) { url = mission.get("Paraguay"); }

		else if (country.contains("Peru")) { url = mission.get("Peru"); }

		else if (country.contains("Trinidad and Tobago")) { url = mission.get("Trinidad"); }

		else if (country.contains("Uruguay")) { url = mission.get("uraguay"); }

		else if (country.contains("Albania")) { url = mission.get("Albania"); }

		else if (country.contains("Armenia")) { url = mission.get("Armenia"); }

		else if (country.contains("Azerbaijan")) { url = mission.get("Azerbaijan");}

		else if (country.contains("Belgium")) { url = mission.get("Belgium"); }

		else if (country.contains("Bosnia and Herzegovina")) { url = mission.get("Bosnia"); }

		else if (country.contains("Croatia")) { url = mission.get("Croatia"); }

		else if (country.contains("Cyprus")) { url = mission.get("Cyprus"); }

		else if (country.contains("Greece")) { url = mission.get("Greece"); }

		else if (country.contains("Italy")) { url = mission.get("Italy"); }

		else if (country.contains("Ireland")) { url = mission.get("Ireland"); }

		else if (country.contains("Kosova")) { url = mission.get("Kosova"); }

		else if (country.contains("North Macedonia")) { url = mission.get("Macedonia"); }

		else if (country.contains("The Netherlands")) { url = mission.get("Netherlands"); }

		else if (country.contains("Portugal")) {url = mission.get("Portugal"); }

		else if (country.contains("Serbia")) {	url = mission.get("Serbia"); }

		else if (country.contains("Spain and Andorra")) { 	url = mission.get("Spain"); }

		else if (country.contains("Turkiye")) { url = mission.get("Turkiye"); }

		else if (country.contains("United Kingdom")) { url = mission.get("UK"); }

		else if (country.contains("Israel")) { url = mission.get("TAJ"); }

		else if (country.contains("Angola")) { url = mission.get("Angola"); }

		else if (country.contains("Cabo Verde")) { url = mission.get("Cabo Verde"); }

		else if (country.contains("The Democratic Republic of the Congo")) { 	url = mission.get("Congo"); }

		else if (country.contains("Ethipia")) { url = mission.get("Ethiopia"); }

		else if (country.contains("Kenya")) { url = mission.get("Kenya"); }

		else if (country.contains("Rwanda")) { 	url = mission.get("Rwanda"); }

		else if (country.contains("Senegal")) { url = mission.get("Senegal"); }

		else if (country.contains("Tanzania")) { url = mission.get("Tanzania"); }

		else if (country.contains("South Africa")) { 	url = mission.get("South Africa"); }

		else if (country.contains("Uganda")) { 	url = mission.get("Uganda"); }

		else if (country.contains("Zambia")) { 	url = mission.get("Zambia"); }

		else {
			System.out.println("Please pass the Mission/Location");
		}
		return url;

	}

	public String retrieveURL() throws Exception {
		seturl();
		getValue();
		return url;
	}

	public static void main(String[] args) throws Exception {

		//retrieveURL();
		//decodingValues();


	}
}



