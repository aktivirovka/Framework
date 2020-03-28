package pageobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.BrowserSelector;

public class GooglePage extends BasePage {
    private final BrowserSelector browserSelector = new BrowserSelector();

    public GooglePage(WebDriver driver) {
        super(driver);
        url = "https://cloud.google.com/";
    }

    @FindBy(xpath = "//*[@class='devsite-searchbox']/input")
    private WebElement iconSearch;

    public GooglePage clickOnIconSearch() {
        browserSelector.clickElement(driver, iconSearch);
        return this;
    }

    public SearchResultsPage pasteTextInSearchField(String textToSearch) {
        iconSearch.sendKeys(textToSearch);
        iconSearch.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
