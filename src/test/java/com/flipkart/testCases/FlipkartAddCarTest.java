package com.flipkart.testCases;

import com.flipkart.extentReport.ExtentReportListener;
import com.flipkart.pageObjects.FlipkartAddCartPage;
import static com.flipkart.pageObjects.FlipkartAddCartPage.scrollDownPageTitle;
import com.flipkart.pageObjects.FlipkartLoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlipkartAddCarTest extends BaseTest {
    public static Logger logger = LogManager.getLogger(BaseTest.class);

    FlipkartAddCartPage addCartPageObj ;
    FlipkartLoginPage flipkartLoginPageObj;

    ExtentReportListener dd = new ExtentReportListener();


    ExtentTest extentTest = new ExtentTest("FlipkartAddCarTest", " FlipkartAddCarTest Status:");
   // @Test (priority = 1)
    public void loginAlertTest(){

        flipkartLoginPageObj=new FlipkartLoginPage(driver,screenshot,waitUtils);
        flipkartLoginPageObj.removeLoginPopup();
        Assert.assertTrue(true);
        extentTest.log(LogStatus.FAIL,"logingAlert Test","test is fail");


    }

   // @Test(priority = 1,retryAnalyzer=RetryFailedTest.class)
    public void getAllLinksTest(){
        addCartPageObj= new FlipkartAddCartPage(driver,screenshot, waitUtils);

        int count= addCartPageObj.getAllLinks();
        Assert.assertNotNull(count);
        extentTest.log(LogStatus.PASS,"getAllLinksTest","test is Pass ");


    }

    @Test(priority = 1)
    public void scrollToItemTest() throws InterruptedException {
        logger.info("scrollToItemTest Running..");
        addCartPageObj = new FlipkartAddCartPage(driver,screenshot, waitUtils);
         addCartPageObj.findProductByScrollingDown();
        logger.info("title:"+driver.getTitle());
        Assert.assertEquals(driver.getTitle(),scrollDownPageTitle);

    }



}
