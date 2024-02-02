package com.flipkart.testCases;

import com.flipkart.pageObjects.FlipkartAddCartPage;
import static com.flipkart.pageObjects.FlipkartAddCartPage.scrollDownPageTitle;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlipkartAddCarTest extends BaseTest {
    public static Logger logger = LogManager.getLogger(BaseTest.class);
    FlipkartAddCartPage addCartPageObj;

    // @Test(priority = 1,retryAnalyzer=RetryFailedTest.class)

    @Test(priority = 1)
    public void scrollToItemTest() throws InterruptedException {
        addCartPageObj = new FlipkartAddCartPage(driver, screenshot, waitUtils);
        logger.info("scrollToItemTest Running..");
        ExTest.log(LogStatus.INFO, "Test Case scroll To Item is Started");
        addCartPageObj.findProductByScrollingDown();
        logger.info("title:" + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), scrollDownPageTitle);
        ExTest.log(LogStatus.PASS, "scrollToItemTest Pass");
    }

    @Test(priority = 2)
    public void getAllLinksTest() {
        addCartPageObj = new FlipkartAddCartPage(driver, screenshot, waitUtils);
        ExTest.log(LogStatus.INFO, "Test Case Get All Links Test is Started");
        int count = addCartPageObj.getAllLinks();
        Assert.assertNotNull(count);
        ExTest.log(LogStatus.PASS, "Get All Links Test Pass");
    }

    @Test
    public void testFail() {
        addCartPageObj = new FlipkartAddCartPage(driver, screenshot, waitUtils);
        ExTest.log(LogStatus.FAIL, "Fail Test");
    }

    @Test
    public void testSkip() {
        addCartPageObj = new FlipkartAddCartPage(driver, screenshot, waitUtils);
        ExTest.log(LogStatus.SKIP, "Skip Test");
    }

}
