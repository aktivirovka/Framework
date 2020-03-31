package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.BrowserSelector;

public class ResultPage extends BasePage {
    private final BrowserSelector browserSelector = new BrowserSelector();

    @FindBy(id = "email_quote")
    private WebElement buttonEmailEstimate;

    public FillEmailPage clickButtonEmailEstimate() {
        browserSelector.clickElement(driver,buttonEmailEstimate);
        return new FillEmailPage();
    }
}
