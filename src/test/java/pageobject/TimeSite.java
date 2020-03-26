package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.BrowserSelector;

public class TimeSite extends BasePage {
    private final Logger logger = LogManager.getRootLogger();
    private final BrowserSelector browserSelector = new BrowserSelector();

    public TimeSite(WebDriver driver) {
        super(driver);
        url = "https://dropmail.me/ru/";
    }

    @FindBy(className = "email")
    private WebElement mailAddress;
    @FindBy(xpath = "//*[contains(text(), 'Compute User')]")
    private WebElement getMessage;


    public TimeSite openSiteInNewTab() {
        createNewTab();
        switchTabByIndex(1);
        driver.get(url);
        return this;
    }

    public String copyEmailAddress() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver).executeScript(
                "return document.readyState"
        ).equals("complete"));

        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(mailAddress));

        return mailAddress.getText();
    }


    public String getMessage() {
        switchTabByIndex(1);
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Compute User')]")));
        return getMessage.getText();
    }

    public boolean isCostTrue(String totalCost, String message) {
        String[] arrayWords = message.split(":", 2);
        String[] arrayWords2 = arrayWords[1].split(":", 2);
        String[] arrayWords3 = arrayWords2[1].split("\n\n", 2);
        return arrayWords3[0].trim().equals(totalCost);
    }
}
