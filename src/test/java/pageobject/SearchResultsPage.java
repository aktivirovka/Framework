package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.DriverUtils;

public class SearchResultsPage extends BasePage {
    private String textToSearch = "//*[@class='gs-title']//*[contains(text(),'%s')]";

    public CalculatorPage goToCalculatorPage(String textToSearch) {
        String fullName = String.format(this.textToSearch, textToSearch);
        DriverUtils.waitUntilVisibilityOfElement(driver, fullName);
        driver.findElement(By.xpath(fullName)).click();
        return new CalculatorPage();
    }
}

