package tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import pageobject.*;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class HardcoreTest extends BaseTest {
    private String textToSearch = "Google Cloud Platform Pricing Calculator";

    @Test
    public void fillCalculatorSendEmailAndCheckSum() throws IOException, UnsupportedFlavorException {
        GooglePage googlePage = new GooglePage(driver);
        googlePage.goToPage();
        googlePage.clickOnIconSearch();
        SearchResultsPage searchResultsPage = googlePage.PasteTextInSearchField(textToSearch);
        CalculatorPage calculatorPage = searchResultsPage.goToCalculatorPage(textToSearch)
                .activateComputeEngine().pasteNumberOfInstance(numberOfInstances);


        ResultPage resultPage = calculatorPage.clearFieldInstancesFor()
                .chooseSoftware(software)
                .chooseMachineClass(machineClass)
                .chooseMachineType(machineType)
                .tickAddGPUs().chooseNumberOfGPUs(numberOfGPUs).chooseGPUType(GPUType)
                .chooseLocalSSD(localSSD).chooseDatacenterLocation(dataCenterLocation)
                .chooseCommitedUsage(commitedUsage)
                .clickAddToEstimate();

        FillEmailPage emailPage3 = resultPage.clickEmailEstimate();
        TimeSite timeSite = new TimeSite(driver);
        String emailAddress = timeSite.openSiteInNewTab().copyEmailAddress();
        emailPage3.switchAndUseEmailAddress(emailAddress).clickButtonSendEmail();
        timeSite.getMessage();

        Assert.assertTrue("Total estimated cost is wrong", timeSite.isCostTrue(totalCost));

    }
}
