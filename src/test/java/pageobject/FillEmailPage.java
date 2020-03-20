package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class FillEmailPage extends BasePage {
    public FillEmailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'Email')]/../input[@name='description']")
    private WebElement xpathFieldEmailAddress;
    @FindBy (xpath = "//*[contains(text(),'Send Email')]/.")
    private WebElement buttonSendEmail;

    public FillEmailPage switchAndUseEmailAddress(String emailAdress) {
        switchTabByIndex(0);
        xpathFieldEmailAddress.click();
        xpathFieldEmailAddress.sendKeys(emailAdress);
        return this;
    }

    public void clickButtonSendEmail() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", xpathFieldEmailAddress);
        buttonSendEmail.click();
    }
}
