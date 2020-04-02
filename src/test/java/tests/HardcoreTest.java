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
        String resultOfCalculation = new GooglePage()
                .openPage()
                .clickOnIconSearch()
                .pasteTextInSearchField(TEXT_TO_SEARCH)
                .goToCalculatorPage(TEXT_TO_SEARCH)
                .activateComputeEngine()
                .createNewEngine(testEngine)
                .clickButtonEmailEstimate()
                .openTimeSiteInNewTab()
                .copyEmailAddress()
                .switchAndUseEmailAddress()
                .clickButtonSendEmail()
                .getMessage()
                .getResultOfCalculation();

        Assert.assertEquals("Total estimated cost is wrong. Expected result is "
                        + testEngine.getRequaredCost() + ", current result is " + resultOfCalculation,
                testEngine.getRequaredCost(), resultOfCalculation);
    }
}
