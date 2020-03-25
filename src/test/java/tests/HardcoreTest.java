package tests;

import model.Engine;
import org.junit.Assert;
import org.testng.annotations.Test;
import pageobject.*;
import service.EngineCreator;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class HardcoreTest extends BaseTest {
    protected static final String TEXT_TO_SEARCH = "Google Cloud Platform Pricing Calculator";

    @Test
    public void fillCalculatorSendEmailAndCheckSum() throws IOException, UnsupportedFlavorException, InterruptedException {
        GooglePage googlePage = new GooglePage(driver);
        googlePage.goToPage();
        googlePage.clickOnIconSearch();
        SearchResultsPage searchResultsPage = googlePage.PasteTextInSearchField(TEXT_TO_SEARCH);
        CalculatorPage calculatorPage = searchResultsPage.goToCalculatorPage(TEXT_TO_SEARCH)
                .activateComputeEngine();

        Engine testEngine = EngineCreator.withCredentialsFromProperty();
        ResultPage resultPage = calculatorPage.createNewEngine(testEngine);

        FillEmailPage emailPage3 = resultPage.clickButtonEmailEstimate();
        TimeSite timeSite = new TimeSite(driver);
        String emailAddress = timeSite.openSiteInNewTab().copyEmailAddress();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! emailAddress is ------" + emailAddress);
        emailPage3.switchAndUseEmailAddress(emailAddress).clickButtonSendEmail();
        String emailMessage = timeSite.getMessage();

        Assert.assertTrue("Total estimated cost is wrong", timeSite.isCostTrue(testEngine.getTotalCost(), emailMessage));

    }
}
