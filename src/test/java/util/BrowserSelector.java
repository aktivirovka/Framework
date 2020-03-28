package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserSelector {

    private final Logger logger = LogManager.getRootLogger();
    private static boolean isJavascriptExecutorUsed = false;
    int millisecondsForSleeping = 150;

    public void trySelectingOption(WebDriver driver, WebElement webElement, String optionXpath) {
        for (int i = 0; i < 2; i++) {
            try {
                if (isJavascriptExecutorUsed) {
                    DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, webElement);
                    JavaScriptExecutorUtils.clickThroughJS(driver, webElement);
                    Thread.sleep(millisecondsForSleeping);
                    DriverUtils.waitUntilElementToBeClickableUsingXpath(driver, optionXpath);
                    WebElement element = driver.findElement(By.xpath(optionXpath));
                    JavaScriptExecutorUtils.clickThroughJS(driver, element);
                    return;
                } else {
                    DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, webElement);
                    webElement.click();
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
                DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, webElement);
                JavaScriptExecutorUtils.clickThroughJS(driver, webElement);
            } else {
                DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, webElement);
                webElement.click();
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "Cannot press the button." + e.getMessage());
        }
    }

    public void switchToFrameIfJavaScriptUsed(WebDriver driver) {
        if (isJavascriptExecutorUsed) {
            DriverUtils.switchToFrame(driver);
        }
    }
}

