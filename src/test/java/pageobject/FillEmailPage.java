package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import util.BrowserSelector;
import util.DriverUtils;
import util.JavaScriptExecutorUtils;

public class FillEmailPage extends BasePage {

    private String emailAddress;

    public FillEmailPage(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public FillEmailPage() {
        super();
    }

    private final BrowserSelector browserSelector = new BrowserSelector();

    @FindBy(xpath = "//*[contains(text(),'Email')]/../input[@name='description']")
    private WebElement fieldEmailAddress;
    @FindBy(xpath = "//*[contains(text(),'Send Email')]/.")
    private WebElement buttonSendEmail;
    @FindBy(xpath = "//*[contains(text(),'Email Your Estimate')]")
    private WebElement fillingFormWindow;

    public FillEmailPage switchAndUseEmailAddress() {
        switchTabByIndex(0);
        browserSelector.switchToFrameIfJavaScriptUsed(driver);
        JavaScriptExecutorUtils.scrollToElement(driver, fillingFormWindow);
        DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, fieldEmailAddress);
        fieldEmailAddress.sendKeys(emailAddress);
        return this;
    }

    public TimeSitePage clickButtonSendEmail() {
        JavaScriptExecutorUtils.scrollToElement(driver, fieldEmailAddress);
        JavaScriptExecutorUtils.clickThroughJS(driver, buttonSendEmail);
        return new TimeSitePage();
    }

    public TimeSitePage openTimeSiteInNewTab() {
        JavaScriptExecutorUtils.createNewTab(driver);
        switchTabByIndex(1);
        TimeSitePage timePage = new TimeSitePage();
        timePage.goToPage();
        return timePage;
    }
}
