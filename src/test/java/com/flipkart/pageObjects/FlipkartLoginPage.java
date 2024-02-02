package com.flipkart.pageObjects;

import static com.flipkart.pageObjects.FlipkartAddCartPage.readConfig;
import com.flipkart.utilities.ReadConfig;
import com.flipkart.utilities.Screenshot;
import com.flipkart.utilities.WaitUtils;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Sushant
 */
public class FlipkartLoginPage {

    WebDriver  driver;
    Screenshot screenshot;
    WaitUtils  waitUtils;
    public static Logger logger;

    @FindBy(how = How.XPATH, using = "//*[span=\"Login\"]") WebElement loginAlert;

    @FindBy(how = How.XPATH, using = "//*[@Class=\"_2KpZ6l _2doB4z\"]") WebElement crossButton;

    public FlipkartLoginPage(WebDriver driver, Screenshot screenshot, WaitUtils waitUtils) {
        this.driver = driver;
        this.screenshot = screenshot;
        this.waitUtils = waitUtils;
        logger = Logger.getLogger("FlipkartLoginPage");
        readConfig = new ReadConfig();
        PageFactory.initElements(driver, this);
    }

    public boolean getLoginAlert() {

        if (loginAlert.isDisplayed()) {
            logger.info("Login Popup visible");
        } else {
            return false;
        }
        return true;
    }

    public void removeLoginPopup() {
        driver.manage().timeouts().pageLoadTimeout(readConfig.getWaitTime(), TimeUnit.MINUTES);
        logger = Logger.getLogger("FlipkartLoginPage");
        if (getLoginAlert() == true) {
            driver.manage().timeouts().pageLoadTimeout(readConfig.getWaitTime(), TimeUnit.MINUTES);
            Actions ac = new Actions(driver);

            waitUtils.waitTillWebElementIsClickable(driver, readConfig.getWaitTime(), loginAlert);

            ac.sendKeys(Keys.ESCAPE);
            ac.moveToElement(crossButton).click().perform();

        }
    }

}
