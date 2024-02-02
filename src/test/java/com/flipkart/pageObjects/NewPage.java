package com.flipkart.pageObjects;

import com.flipkart.testCases.BaseTest;
import com.flipkart.utilities.ReadConfig;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Sushant
 */
public class NewPage {


    WebDriver driver;
    public static Logger logger = LogManager.getLogger(BaseTest.class);
    ReadConfig readConfig = new ReadConfig();
    public static String getKey() {
        return key;
    }
    public static void setKey(String key) {
        NewPage.key = key;
    }
    public static String key;

    public NewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Set<String> AllLinks() {
        WebDriverWait wait= new WebDriverWait(driver, readConfig.getWaitTime());
        Set<String> listOfLinks = new HashSet<>();
        String[] s;
        List<WebElement> list = driver.findElements(By.tagName("a"));
     /*   Iterator<WebElement> it = list.iterator();
         while(it.hasNext()) // soniya Singh[]
        WebElement el =it.next();
          el.click();
            */
        for (int i = 0; i <= 5; i++) {
            try {
                String element = new String();
                element = list.get(i).getAttribute("href");
                logger.info(" Href : " + element);
                listOfLinks.add(element);
                System.out.println("Element List Get Data: " + list.get(i));
            }
            catch (StaleElementReferenceException e) {
                logger.info("Exception called " + e);
            }
        }
        return listOfLinks;
    }


    public void windowHandles(Set<String> pageLinks, String expPageTitel) {
        Map<String, String> winMap = new HashMap<>();
        Iterator<String> it = pageLinks.stream().iterator();


        WebElement el = driver.findElement(By.xpath("test"));
        Select sc = new Select(el);
        sc.selectByValue("test");
        Actions ac = new Actions(driver);
        ac.click().build().perform();
        ac.sendKeys(Keys.ESCAPE).build().perform();
        driver.switchTo().frame(el);
        driver.switchTo().frame(1);
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(readConfig.getWaitTime(), TimeUnit.SECONDS);
        WebDriverWait waits= new WebDriverWait(driver, readConfig.getWaitTime());
        waits.until(ExpectedConditions.elementToBeClickable(el));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("Window.scrollTo:100,2000",el);
       String s= driver.getWindowHandle();
       driver.switchTo().window(s);



        while (it.hasNext()) {
            String tmp = it.next();
            logger.info("links : " + tmp);
            JavascriptExecutor jss = (JavascriptExecutor) driver;
            jss.executeScript("window.open('" + tmp + "')");

        }

        Set<String> windowsHands = driver.getWindowHandles();
        Iterator<String> itwh = windowsHands.iterator();
        while (itwh.hasNext()) {
            String windowH = itwh.next();
            driver.switchTo().window(windowH);
            String title = driver.getTitle();
            logger.info("GetTitle: " + title);
            winMap.put(title, windowH);
            driver.manage().timeouts().pageLoadTimeout(readConfig.getWaitTime(), TimeUnit.SECONDS);
        }
        Iterator<Map.Entry<String, String>> winMapIt = winMap.entrySet().iterator();
        while (winMapIt.hasNext()) {

            String keys = winMapIt.next().getKey();
            if (keys.equalsIgnoreCase(expPageTitel)) {
                setKey(keys);
                String val = winMap.get(expPageTitel);
                logger.info("Get-Key: " + getKey());
                logger.info("Get-Value: " + val);
                driver.switchTo().window(val);
                logger.info("title of the page:" + driver.getTitle());

            }
        }

    }
}
