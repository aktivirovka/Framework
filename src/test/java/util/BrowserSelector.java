package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserSelector {

    private final Logger logger = LogManager.getRootLogger();
    private static boolean isJavascriptExecutorUsed = false;
    int millisecondsForSleeping = 150;

    public void trySelectingOption(WebDriver driver, WebElement dropdown, String optionXpath) {
        for (int i = 0; i < 2; i++) {
            try {
                if (isJavascriptExecutorUsed) {

                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(dropdown));
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", dropdown);

                    Thread.sleep(millisecondsForSleeping);
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(optionXpath))));
                    WebElement element = driver.findElement(By.xpath(optionXpath));
                    Thread.sleep(millisecondsForSleeping);
                    executor.executeScript("arguments[0].click();", element);

                    return;

                } else {
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(dropdown));
                    dropdown.click();

                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(optionXpath))));

                    driver.findElement(By.xpath(optionXpath)).click();
                    return;
                }
            } catch (Exception e) {
                logger.log(Level.ERROR, "Cannot select the option. Switching the section mechanism");
                isJavascriptExecutorUsed = true;
            }
        }
    }

    public void clickElement(WebDriver driver, WebElement webElement) {
        try {
            if (isJavascriptExecutorUsed) {
                new WebDriverWait(driver, 15)
                        .until(ExpectedConditions.elementToBeClickable(webElement));

                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", webElement);

            } else {
                new WebDriverWait(driver, 15)
                        .until(ExpectedConditions.elementToBeClickable(webElement));
                webElement.click();
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "Cannot press the button.");
        }
    }

    public void switchToFrame(WebDriver driver) {
        if (isJavascriptExecutorUsed) {
            new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//section[@class='devsite-wrapper']//iframe")));
            driver.switchTo().frame("myFrame");
        }
    }

}

