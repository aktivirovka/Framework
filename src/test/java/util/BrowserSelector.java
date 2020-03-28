package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserSelector {

    private final Logger logger = LogManager.getRootLogger();
    private static boolean isJavascriptExecutorUsed = false;
    int millisecondsForSleeping = 150;

    public void trySelectingOption(WebDriver driver, WebElement webElement, String optionXpath) {
        for (int i = 0; i < 2; i++) {
            try {
                if (isJavascriptExecutorUsed) {

                    DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, webElement);
                    /*new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(webElement));*/
                    /*JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", webElement);*/
                    JavaScriptExecutorUtils.clickThroughJS(driver, webElement);

                    Thread.sleep(millisecondsForSleeping);

                    DriverUtils.waitUntilElementToBeClickableUsingXpath(driver, optionXpath);

                    /*new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(optionXpath))));*/

                    WebElement element = driver.findElement(By.xpath(optionXpath));
                    Thread.sleep(millisecondsForSleeping);
                    JavaScriptExecutorUtils.clickThroughJS(driver, element);
                    //  executor.executeScript("arguments[0].click();", element);
                    return;

                } else {
                    /*new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(webElement));*/
                    DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, webElement);
                    webElement.click();

                   /* new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(optionXpath))));*/
                    DriverUtils.waitUntilElementToBeClickableUsingXpath(driver, optionXpath);
                    driver.findElement(By.xpath(optionXpath)).click();
                    return;
                }
            } catch (Exception e) {
                logger.log(Level.ERROR, "Cannot select the option. Switching the section mechanism " + e.getMessage());
                isJavascriptExecutorUsed = true;
            }
        }
    }

    public void clickElement(WebDriver driver, WebElement webElement) {
        try {
            if (isJavascriptExecutorUsed) {
                /*new WebDriverWait(driver, 15)
                        .until(ExpectedConditions.elementToBeClickable(webElement));*/
                DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, webElement);
                JavaScriptExecutorUtils.clickThroughJS(driver, webElement);
               /* JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", webElement);*/

            } else {
                /*new WebDriverWait(driver, 15)
                        .until(ExpectedConditions.elementToBeClickable(webElement));*/
                DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, webElement);
                webElement.click();
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "Cannot press the button." + e.getMessage());
        }
    }

    public void switchToFrameIfJavaScriptUsed(WebDriver driver, By xpathParent, String nameOrId) {
        if (isJavascriptExecutorUsed) {
            DriverUtils.switchToFrame(driver, xpathParent, nameOrId);
            /*new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//section[@class='devsite-wrapper']//iframe")));
            driver.switchTo().frame("myFrame");*/
        }
    }


}

