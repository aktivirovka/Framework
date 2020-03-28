package tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import pageobject.CalculatorPage;
import pageobject.GooglePage;
import pageobject.SearchResultsPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class OpenCalculatorPageTest extends BaseTest {

    @Test
    public void openCalculatorPage(){
        GooglePage googlePage = new GooglePage(driver);
        googlePage.goToPage();
        googlePage.clickOnIconSearch();
        SearchResultsPage searchResultsPage = googlePage.pasteTextInSearchField(HardcoreTest.TEXT_TO_SEARCH);
        CalculatorPage calculatorPage = searchResultsPage.goToCalculatorPage(HardcoreTest.TEXT_TO_SEARCH);

        Assert.assertTrue("Title of the page is wrong",calculatorPage.isTitleTrue(HardcoreTest.TEXT_TO_SEARCH));
        Assert.assertTrue("There is no compute engine button", calculatorPage.isComputeEngineButtonExist());

    }
}
