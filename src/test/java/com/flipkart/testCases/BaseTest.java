package com.flipkart.testCases;

import com.flipkart.utilities.DriverInit;
import com.flipkart.utilities.ReadConfig;
import com.flipkart.utilities.Screenshot;
import com.flipkart.utilities.WaitUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
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

    public static ExtentReports reports;
    public static ExtentTest    ExTest;

    @BeforeTest
    @Parameters("browser")
    public void setup(String br) {

        String log4jConfPath = getProperty("user.dir") + "/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        logger = Logger.getLogger(" Selenium Hybrid Framework");
        driver = DriverInit.getInstance().webDriverOpen(br);

        logger.info("Browser Name :" + br);
        driver.manage().window().maximize();
        reports = new ExtentReports("./Reports/TestReport.html", true);
        ExTest = reports.startTest("Extent Report :");
         driver.get(readConfig.getBaseUrl());
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

        driver.close();
        reports.endTest(ExTest);
        reports.flush();

    }

}
