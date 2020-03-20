package tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import pageobject.*;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class HardcoreTest extends BaseTest {
    private String textToSearch = "Google Cloud Platform Pricing Calculator";
    private String textToMachineClass = "Regular";
    private String textToMachineType = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    private String textToDatacenterLocation = "Frankfurt (europe-west3)";
    private String textToLocalSSD = "2x375 GB";
    private String textToCommitedUsage = "1 Year";
    private String totalCost = "1,082.77";

    @Test
    public void fillCalculatorSendEmailAndCheckSum() throws IOException, UnsupportedFlavorException {
        GooglePage googlePage = new GooglePage(driver);
        googlePage.goToPage();
        googlePage.clickOnIconSearch();
        SearchResultsPage searchResultsPage = googlePage.PasteTextInSearchField(textToSearch);
        CalculatorPage calculatorPage = searchResultsPage.goToCalculatorPage(textToSearch)
                .activateComputeEngine().pasteNumberOfInstance("4");

        ResultPage resultPage = calculatorPage.clearFieldInstancesFor()
                .chooseSoftware("Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
                .chooseMachineClass(textToMachineClass)
                .chooseMachineType(textToMachineType)
                .tickAddGPUs().chooseNumberOfGPUs("1").chooseGPUType("NVIDIA Tesla V100")
                .chooseLocalSSD(textToLocalSSD).chooseDatacenterLocation(textToDatacenterLocation)
                .chooseCommitedUsage(textToCommitedUsage)
                .clickAddToEstimate();

        FillEmailPage emailPage3 = resultPage.clickEmailEstimate();
        TimeSite timeSite = new TimeSite(driver);
        String emailAddress = timeSite.openSiteInNewTab().copyEmailAddress();
        emailPage3.switchAndUseEmailAddress(emailAddress).clickButtonSendEmail();
        timeSite.getMessage();

        Assert.assertTrue("Total estimated cost is wrong", timeSite.isCostTrue(totalCost));

    }
}
