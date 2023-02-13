package com.visaops.ustest.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

/***
 * @Author Aya Turki
 * This class run the option for the driver
 */
public class OptionsManager {
    private ChromeOptions co;
    private FirefoxOptions fo;
    private Properties prop;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }
    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            co.setHeadless(true);
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
        }
        return co;
    }
    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            fo.setHeadless(true);
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            fo.addArguments("--incognito");
        }
        return fo;

    }
}