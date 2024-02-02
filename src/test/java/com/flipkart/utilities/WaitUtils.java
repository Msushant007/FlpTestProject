package com.flipkart.utilities;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

        // time should be grab from config file only
    public  WebDriverWait waitToVisibilityOfByLocated(WebDriver driver, int second, final By locator) {

        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return wait;
    }


    public  WebDriverWait waitTillByLocatorIsClickable(WebDriver driver, int second, final By locator) {
        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return wait;
    }

    public  WebDriverWait waitTillWebElementIsClickable(WebDriver driver, int second,final WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return wait;
    }


    public  WebDriverWait waitTillVisibleWebElement(WebDriver driver,  int second, final WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return wait;
    }


    public  Wait fluentWait(WebDriver driver, int duration, int pollingTime) {
        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(duration,
                TimeUnit.SECONDS).pollingEvery(pollingTime, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        return fluentWait;
    }

}




