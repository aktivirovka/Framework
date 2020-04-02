package tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import pageobject.CalculatorPage;
import pageobject.GooglePage;

public class OpenCalculatorPageTest extends BaseTest {

    @Test
    public void openCalculatorPageAndCompareTitleOfPage() {
        boolean result = new GooglePage()
                .openPage()
                .clickOnIconSearch()
                .pasteTextInSearchField(HardcoreTest.TEXT_TO_SEARCH)
                .goToCalculatorPage(HardcoreTest.TEXT_TO_SEARCH)
                .isTitleTrue(HardcoreTest.TEXT_TO_SEARCH);

        Assert.assertTrue("Title of the page is wrong", result);
    }

    @Test
    public void openCalculatorPageAndFindComputeEngineButton() {
        boolean result = new GooglePage()
                .openPage()
                .clickOnIconSearch()
                .pasteTextInSearchField(HardcoreTest.TEXT_TO_SEARCH)
                .goToCalculatorPage(HardcoreTest.TEXT_TO_SEARCH)
                .isComputeEngineButtonExist();

        Assert.assertTrue("There is no compute engine button", result);
    }
}