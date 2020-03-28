package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import util.BrowserSelector;
import util.DriverUtils;
import util.JavaScriptExecutorUtils;

public class TimeSitePage extends BasePage {
    private final Logger logger = LogManager.getRootLogger();
    private final BrowserSelector browserSelector = new BrowserSelector();

    public TimeSitePage(WebDriver driver) {
        super(driver);
        url = "https://dropmail.me/ru/";
    }

    @FindBy(className = "email")
    private WebElement mailAddress;
    @FindBy(xpath = "//*[contains(text(), 'Compute User')]")
    private WebElement getMessageElement;
    private By getMessage = By.xpath("//*[contains(text(), 'Compute User')]");

    public TimeSitePage openSiteInNewTab() {
        JavaScriptExecutorUtils.createNewTab(driver);
        switchTabByIndex(1);
        driver.get(url);
        return this;
    }

    public String copyEmailAddress() {
        JavaScriptExecutorUtils.waitForPageToLoad(driver);
        DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, mailAddress);
        return mailAddress.getText();
    }

    public String getMessage() {
        switchTabByIndex(1);
        DriverUtils.waitUntilPresenceOfElement(driver, getMessage);
        return getMessageElement.getText();
    }

    public boolean isCostTrue(String totalCost, String message) {
        String[] arrayWords = message.split(":", 2);
        String[] arrayWords2 = arrayWords[1].split(":", 2);
        String[] arrayWords3 = arrayWords2[1].split("\n\n", 2);
        return arrayWords3[0].trim().equals(totalCost);
    }
}
