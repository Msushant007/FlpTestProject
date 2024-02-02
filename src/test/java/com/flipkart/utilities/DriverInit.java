package com.flipkart.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Sushant
 */

// Singleton Class Created for WebDriver for prevent to created multiple driver object;
public class DriverInit {
    ReadConfig readConfig = new ReadConfig();
    private        WebDriver  driver;
    public static  Logger     logger;
    private static DriverInit driverInit = null;

    private DriverInit() {  // Constructor created for initialization singleton class

    }

    public static DriverInit getInstance() { //Singleton get instance method.
        if (driverInit == null) {
            driverInit = new DriverInit();
        }
        return driverInit;
    }

 public WebDriver webDriverOpen(String browserName) {
        logger = Logger.getLogger("Flipkart Selenium Hybrid Framework: Browser Invoke");
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", this.readConfig.getChromeDriverPath());
            driver = new ChromeDriver();
            logger.info("Driver invoked :" + browserName);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", this.readConfig.getFireFoxDriverPath());
            driver = new FirefoxDriver();
            logger.info("Driver invoked :" + browserName);
        }
        return driver;
    }

}
