package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage extends BasePage {
    public ResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email_quote")
    private WebElement buttonEmailEstimate;

    public FillEmailPage clickEmailEstimate() {
        buttonEmailEstimate.click();
        return new FillEmailPage(driver);
    }
}
