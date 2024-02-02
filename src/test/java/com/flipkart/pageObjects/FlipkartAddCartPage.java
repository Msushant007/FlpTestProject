package com.flipkart.pageObjects;

import com.flipkart.utilities.ReadConfig;
import com.flipkart.utilities.Screenshot;
import com.flipkart.utilities.WaitUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FlipkartAddCartPage {
    WebDriver  driver;
    Screenshot screenshot;

    WaitUtils          waitUtils;
    JavascriptExecutor jse;
    Actions            actions;
    public static String     scrollDownPageTitle="Mobile Phones Online at Best Prices in India";
    public static Logger     logger;
    public static ReadConfig readConfig;

    @FindBy(how = How.XPATH, using = "//img[@alt=\"Mobiles\"]")                                                   WebElement mobileCategoryPageElement;
    @FindBy(how = How.XPATH, using = "(//div[@class=\"_3E8aIl Zic51i\"]/descendant::div[@class=\"_3YgSsQ\"])[2]") WebElement scrolledDownitemElement;

    public FlipkartAddCartPage(WebDriver driver, Screenshot screenshot, WaitUtils waitUtils) {
        this.driver = driver;
        this.screenshot = screenshot;
        this.waitUtils = waitUtils;
        logger = Logger.getLogger("FlipkartAddCartPage");
        readConfig = new ReadConfig();
        PageFactory.initElements(driver, this);
    }

    public int getAllLinks() {

        logger.debug("Wait Time: " + readConfig.getWaitTime());
        screenshot.captureScreenshot(driver, "FlipkartAddCartPage-AllLinks ");
        driver.manage().timeouts().pageLoadTimeout(readConfig.getWaitTime(), TimeUnit.MINUTES);
        logger.info("Get Title : " + driver.getTitle());
        jse = (JavascriptExecutor) driver;
        List<WebElement> listOfLinks = driver.findElements(By.tagName("a"));
        logger.info("Count of links " + listOfLinks.size());
        //  String ListKeyLink = listOfLinks.get(2).getAttribute("href");
        //  logger.info("open link url :" + ListKeyLink);
        //   jse.executeScript("window.open('" + ListKeyLink + "')");
        return listOfLinks.size();
    }

    public void findProductByScrollingDown() throws InterruptedException {

        logger.info("findProductByScrollingDown");
        driver.manage().timeouts().pageLoadTimeout(readConfig.getWaitTime(), TimeUnit.MINUTES);
        jse = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        jse.executeScript("window.scrollTo(0,2700)");
        waitUtils.waitTillVisibleWebElement(driver, readConfig.getWaitTime(), scrolledDownitemElement);
        actions.moveToElement(scrolledDownitemElement).doubleClick().build().perform();

    }

    public boolean isAlertPresent()
    {
        try {
            driver.switchTo().alert().accept();
            return true;
        }
        catch (NoAlertPresentException Ex) {
            return false;
        }
    }
}
