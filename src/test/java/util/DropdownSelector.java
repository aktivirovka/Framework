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
    private static boolean isJavascriptExecutorUsed = false;
    int millisecondsForSleeping = 150;

    public void trySelectingOption(WebDriver driver, WebElement dropdown, String optionXpath) {

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!isJavascriptExecutorUsed begin = " + isJavascriptExecutorUsed);
        for (int i = 0; i < 2; i++) {
            try {
                if (isJavascriptExecutorUsed) {

                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!trySelectingOption DROPDOWN CHROME");
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(dropdown));
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", dropdown);

                    Thread.sleep(millisecondsForSleeping);
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!trySelectingOption ELEMENT CHROME");
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(optionXpath))));
                    WebElement element = driver.findElement(By.xpath(optionXpath));
                    Thread.sleep(millisecondsForSleeping);
                    executor.executeScript("arguments[0].click();", element);

                    return;

                } else {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!trySelectingOption DROPDOWN FIREFOX");
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(dropdown));

                    dropdown.click();

                    //  Thread.sleep(500);
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!trySelectingOption ELEMENT FIREFOX");
                    new WebDriverWait(driver, 15)
                            .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(optionXpath))));

                    driver.findElement(By.xpath(optionXpath)).click();
                    //Thread.sleep(500);
                    return;
                }
            } catch (Exception e) {
                logger.log(Level.ERROR, "Cannot select the option. Switching the section mechanism");
                isJavascriptExecutorUsed = true;
            }
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!isJavascriptExecutorUsed begin = " + isJavascriptExecutorUsed);
    }

    public void clickElement(WebDriver driver, WebElement webElement) {

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!in clickElement METHOD");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!isJavascriptExecutorUsed begin = " + isJavascriptExecutorUsed);
        try {
            if (isJavascriptExecutorUsed) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!Click button in Chrome");

                //JavascriptExecutor executor = (JavascriptExecutor) driver;
                new WebDriverWait(driver, 15)
                        .until(ExpectedConditions.elementToBeClickable(webElement));

                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", webElement);



               /* Thread.sleep(millisecondsForSleeping);
                executor.executeScript("arguments[0].click();", webElement);
                return;*/
            } else {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!Click button in Firefox");
                new WebDriverWait(driver, 15)
                        .until(ExpectedConditions.elementToBeClickable(webElement));
                webElement.click();
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "Cannot press the button.");
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!isJavascriptExecutorUsed end = " + isJavascriptExecutorUsed);
    }

    public void switchToFrame(WebDriver driver){
        if (isJavascriptExecutorUsed) {
             System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SWITCH TO FRAME");
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//section[@class='devsite-wrapper']//iframe")));
        driver.switchTo().frame("myFrame");
        }
    }

    public String getTextFromWebElement(WebDriver driver, WebElement webElement) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! getTextFromWebElement");
        String text = null;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!isJavascriptExecutorUsed begin = " + isJavascriptExecutorUsed);

        try {
           /* if (isJavascriptExecutorUsed) {

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! getTextFromWebElement INSIDE METHOD CHROME");


                new WebDriverWait(driver, 15)
                        .until(ExpectedConditions.elementToBeClickable(webElement));
                JavascriptExecutor executor = (JavascriptExecutor) driver;

                text = (String) executor.executeScript("return arguments[0].text;", webElement);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! webElement.getText(); INSIDE METHOD CHROME");
                text = webElement.getText();


            } else {*/
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! getTextFromWebElement INSIDE METHOD FIREFOX");
                new WebDriverWait(driver, 15)
                        .until(ExpectedConditions.elementToBeClickable(webElement));
                text = webElement.getText();
            //}
        } catch (Exception e) {
            logger.log(Level.ERROR, "Cannot get text from web.");
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!isJavascriptExecutorUsed end = " + isJavascriptExecutorUsed);
        return text;
    }


}

