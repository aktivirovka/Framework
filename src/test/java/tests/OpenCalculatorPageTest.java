package tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import pageobject.CalculatorPage;
import pageobject.GooglePage;

public class OpenCalculatorPageTest extends BaseTest {

    @Test
    public void openCalculatorPageAndCompareTitleOfPage() {
        GooglePage googlePage = new GooglePage(driver);
        googlePage.goToPage();
        CalculatorPage calculatorPage = googlePage.clickOnIconSearch()
                .pasteTextInSearchField(HardcoreTest.TEXT_TO_SEARCH)
                .goToCalculatorPage(HardcoreTest.TEXT_TO_SEARCH);

        Assert.assertTrue("Title of the page is wrong", calculatorPage.isTitleTrue(HardcoreTest.TEXT_TO_SEARCH));
    }

    @Test
    public void openCalculatorPageAndFindComputeEngineButton() {
        GooglePage googlePage = new GooglePage(driver);
        googlePage.goToPage();
        CalculatorPage calculatorPage = googlePage.clickOnIconSearch()
                .pasteTextInSearchField(HardcoreTest.TEXT_TO_SEARCH)
                .goToCalculatorPage(HardcoreTest.TEXT_TO_SEARCH);

        Assert.assertTrue("There is no compute engine button", calculatorPage.isComputeEngineButtonExist());
    }
}