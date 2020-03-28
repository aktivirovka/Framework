package tests;

import model.Engine;
import org.junit.Assert;
import org.testng.annotations.Test;
import pageobject.*;
import service.EngineCreator;

public class HardcoreTest extends BaseTest {
    protected static final String TEXT_TO_SEARCH = "Google Cloud Platform Pricing Calculator";

    @Test
    public void fillCalculatorSendEmailAndCheckSum() {
        GooglePage googlePage = new GooglePage(driver);
        googlePage.goToPage();

        SearchResultsPage searchResultsPage = googlePage.clickOnIconSearch().pasteTextInSearchField(TEXT_TO_SEARCH);
        CalculatorPage calculatorPage = searchResultsPage.goToCalculatorPage(TEXT_TO_SEARCH)
                .activateComputeEngine();

        Engine testEngine = EngineCreator.withCredentialsFromProperty();
        ResultPage resultPage = calculatorPage.createNewEngine(testEngine);

        FillEmailPage emailPage3 = resultPage.clickButtonEmailEstimate();
        TimeSitePage timeSitePage = new TimeSitePage(driver);
        String emailAddress = timeSitePage.openSiteInNewTab().copyEmailAddress();
        emailPage3.switchAndUseEmailAddress(emailAddress).clickButtonSendEmail();
        String emailMessage = timeSitePage.getMessage();

        Assert.assertTrue("Total estimated cost is wrong", timeSitePage.isCostTrue(testEngine.getTotalCost(), emailMessage));

    }
}
