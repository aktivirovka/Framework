package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import util.DriverUtils;
import util.JavaScriptExecutorUtils;

public class TimeSitePage extends BasePage {

    public TimeSitePage(WebDriver driver) {
        super(driver);
        url = "https://dropmail.me/ru/";
    }

    protected String resultOfCalculation = null;
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

    /**
     * The method accepts a message with total amount calculations, retrieves
     * this amount from the text message, and returns this amount
     */
    public String getResultOfCalculation(String messageFromCalculator) {
        String[] arrayWords = messageFromCalculator.split(":", 2);
        String[] arrayWords2 = arrayWords[1].split(":", 2);
        String[] arrayWords3 = arrayWords2[1].split("\n\n", 2);
        return arrayWords3[0].trim();
    }
}
