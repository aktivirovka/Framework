package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.DriverUtils;
import util.JavaScriptExecutorUtils;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    private String textToSearch = "//*[@class='gs-title']//*[contains(text(),'%s')]";

    public CalculatorPage goToCalculatorPage(String textToSearch) {
        String fullName = String.format(this.textToSearch, textToSearch);
        DriverUtils.waitUntilVisibilityOfElement(driver, fullName);
        driver.findElement(By.xpath(fullName)).click();
        return new CalculatorPage(driver);
    }
}

