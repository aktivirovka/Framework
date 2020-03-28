package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
    private static int TIME_FOR_WAIT = 15;

    public static void waitUntilElementToBeClickableUsingXpath(WebDriver driver, String optionXpath) {
        new WebDriverWait(driver, TIME_FOR_WAIT)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(optionXpath))));
    }

    public static void waitUntilElementToBeClickableUsingWebElement(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, TIME_FOR_WAIT)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitUntilPresenceOfElement(WebDriver driver, By xpath) {
        new WebDriverWait(driver, TIME_FOR_WAIT)
                .until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    public static void waitUntilVisibilityOfElement(WebDriver driver, String xpath) {
        new WebDriverWait(driver, TIME_FOR_WAIT)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    public static void switchToFrame(WebDriver driver, By xpathParent, String nameOrId) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(xpathParent));
        driver.switchTo().frame(nameOrId);
    }
}
