package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.BrowserSelector;
import util.ExecutorUtils;

public class FillEmailPage extends BasePage {
    private final BrowserSelector browserSelector = new BrowserSelector();

    public FillEmailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'Email')]/../input[@name='description']")
    private WebElement xpathFieldEmailAddress;
    @FindBy(xpath = "//*[contains(text(),'Send Email')]/.")
    private WebElement buttonSendEmail;
    @FindBy(xpath = "//*[contains(text(),'Email Your Estimate')]")
    private WebElement fillingFormWindow;

    public FillEmailPage switchAndUseEmailAddress(String emailAddress) {
        switchTabByIndex(0);
        browserSelector.switchToFrame(driver);
        ExecutorUtils.scrollToElement(driver, fillingFormWindow);
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(xpathFieldEmailAddress));
        xpathFieldEmailAddress.sendKeys(emailAddress);
        return this;
    }

    public void clickButtonSendEmail() {
        ExecutorUtils.scrollToElement(driver, xpathFieldEmailAddress);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonSendEmail);

    }
}
