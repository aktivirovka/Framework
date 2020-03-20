package tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import pageobject.CalculatorPage;
import pageobject.GooglePage;
import pageobject.SearchResultsPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class OpenCalculatorPageTest extends BaseTest {
    private String textToSearch = "Google Cloud Platform Pricing Calculator";
    private String computeEngineButton = "Compute Engine";
    @Test
    public void openCalculatorPage(){
        GooglePage googlePage = new GooglePage(driver);
        googlePage.goToPage();
        googlePage.clickOnIconSearch();
        SearchResultsPage searchResultsPage = googlePage.PasteTextInSearchField(textToSearch);
        CalculatorPage calculatorPage = searchResultsPage.goToCalculatorPage(textToSearch);

        Assert.assertTrue("Title of the page is wrong",calculatorPage.isTitleTrue(textToSearch));
        Assert.assertTrue("There is no compute engine button", calculatorPage.isComputeEngineButtonExist());


    }
}
