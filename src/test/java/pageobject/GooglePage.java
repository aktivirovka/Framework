package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DropdownSelector;

public class GooglePage extends BasePage {
    private final DropdownSelector dropdownSelector = new DropdownSelector();
    public GooglePage(WebDriver driver) {
        super(driver);
        url = "https://cloud.google.com/";
    }

    @FindBy(xpath = "//*[@class='devsite-searchbox']/input")
    private WebElement iconSearch;


    public GooglePage clickOnIconSearch() {
        /*new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(iconSearch));
        iconSearch.click();*/
        dropdownSelector.clickElement(driver, iconSearch);
        return this;
    }

    public SearchResultsPage PasteTextInSearchField(String textToSearch) {
        iconSearch.sendKeys(textToSearch);
        iconSearch.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
