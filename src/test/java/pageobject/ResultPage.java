package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.DropdownSelector;

public class ResultPage extends BasePage {
    private final DropdownSelector dropdownSelector = new DropdownSelector();
    public ResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email_quote")
    private WebElement buttonEmailEstimate;

    public FillEmailPage clickButtonEmailEstimate() {

        /*JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", buttonEmailEstimate);*/

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!! clickButtonEmailEstimate");
        dropdownSelector.clickElement(driver,buttonEmailEstimate);

       // dropdownSelector.pressButton(driver, buttonEmailEstimate);
       // buttonEmailEstimate.click();
        return new FillEmailPage(driver);
    }
}
