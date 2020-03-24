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

public class DropdownSelector {

    private final Logger logger = LogManager.getRootLogger();
    private boolean isJavascriptExecutorUsed = false;

    public void trySelectingOption(WebDriver driver, WebElement dropdown, String optionXpath) {
        for (int i = 0; i < 2; i++) {
            try {
                if (isJavascriptExecutorUsed) {
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(dropdown));
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    //Thread.sleep(500);
                    executor.executeScript("arguments[0].click();", dropdown);
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(optionXpath))));
                    WebElement element = driver.findElement(By.xpath(optionXpath));
                    //Thread.sleep(500);
                    executor.executeScript("arguments[0].click();", element);
                    return;

                }
                else {
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(dropdown));
                    dropdown.click();
                    //Thread.sleep(500);
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(optionXpath))));
                    driver.findElement(By.xpath(optionXpath)).click();
                    //Thread.sleep(500);
                    return;
                }
            }
            catch (Exception e) {
                logger.log(Level.ERROR, "Cannot select the option. Switching the section mechanism");
                isJavascriptExecutorUsed = true;
            }
        }
    }
}
