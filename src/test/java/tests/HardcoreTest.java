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
        Engine testEngine = EngineCreator.withCredentialsFromProperty();
        GooglePage googlePage = new GooglePage();
        googlePage.goToPage();
        CalculatorPage calculatorPage = googlePage.clickOnIconSearch()
                .pasteTextInSearchField(TEXT_TO_SEARCH)
                .goToCalculatorPage(TEXT_TO_SEARCH)
                .activateComputeEngine();

        FillEmailPage emailPage3 = calculatorPage.createNewEngine(testEngine)
                .clickButtonEmailEstimate();
        TimeSitePage timeSitePage = new TimeSitePage();
        String emailAddress = timeSitePage.openSiteInNewTab().copyEmailAddress();
        emailPage3.switchAndUseEmailAddress(emailAddress).clickButtonSendEmail();
        String emailMessage = timeSitePage.getMessage();
        String resultOfCalculation = timeSitePage.getResultOfCalculation(emailMessage);

        Assert.assertEquals("Total estimated cost is wrong. Expected result is "
                        + testEngine.getRequaredCost() + ", current result is " + resultOfCalculation,
                testEngine.getRequaredCost(), resultOfCalculation);
    }
}
