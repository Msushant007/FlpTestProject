package com.flipkart.testCases;

import com.flipkart.utilities.DriverInit;
import com.flipkart.utilities.ReadConfig;
import com.flipkart.utilities.Screenshot;
import com.flipkart.utilities.WaitUtils;
import java.io.File;
import java.io.IOException;
import static java.lang.System.getProperty;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {

    ReadConfig readConfig = new ReadConfig();
    WebDriver  driver;
    Screenshot screenshot;

    WaitUtils waitUtils = new WaitUtils();
    public static Logger logger;

    @BeforeTest
    @Parameters("browser")
    public void setup(String br) {

        String log4jConfPath = getProperty("user.dir") + "/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        logger = Logger.getLogger(" Selenium Hybrid Framework");
        driver = DriverInit.getInstance().webDriverOpen(br);
        logger.info("Browser Name :" + br);
        driver.manage().window().maximize();
       // driver.get(readConfig.getBaseUrl());
        driver.get("https://www.flipkart.com/mobile-phones-store?fm=neo%2Fmerchandising&iid=M_c0343f2d-9de6-4f62-a768-8f74159c5ebe_1_372UD5BXDFYS_MC.ZRQ4DKH28K8J&otracker=hp_rich_navigation_2_1.navigationCard.RICH_NAVIGATION_Mobiles_ZRQ4DKH28K8J&otracker1=hp_rich_navigation_PINNED_neo%2Fmerchandising_NA_NAV_EXPANDABLE_navigationCard_cc_2_L0_view-all&cid=ZRQ4DKH28K8J");

    }

    public void captureScreeShot(WebDriver driver, String testName) {
        String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(
                "/Users/sushant/Documents/SeleniumTestHelper/SeleniumHibridFreamwork/screenshots/" + testName
                        + timeStamp + ".png");
        try {
            FileUtils.copyFile(source, target);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.println("Screenshot taken");

    }

    @AfterClass
    public void tearDown() {

      //  driver.quit();

    }

}
