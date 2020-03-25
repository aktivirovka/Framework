package pageobject;

import model.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DropdownSelector;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalculatorPage extends BasePage {
    private final Logger logger = LogManager.getRootLogger();
    private final DropdownSelector dropdownSelector = new DropdownSelector();

    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[text()='Compute Engine'][1]")
    private WebElement xpathComputeEngine;
    @FindBy(xpath = "//*[@name='quantity']")
    private WebElement xpathNumberOfInstance;
    @FindBy(xpath = "//*[@name='label']")
    private WebElement xpathInstancesFor;
    @FindBy(xpath = "//*[contains(text(), 'Software')]/..//span[@class='md-select-icon']")
    private WebElement xpathSoftware;
    @FindBy(xpath = "//*[contains(text(), 'Machine Class')]/..//span[@class='md-select-icon']")
    private WebElement xpathMachineClass;
    @FindBy(xpath = "//*[contains(text(), 'Machine type')]/..//span[@class='md-select-icon']")
    private WebElement xpathMachineType;
    @FindBy(xpath = "//*[contains(text(), 'Add GPUs')]/../div")
    private WebElement xpathAddGPUs;
    @FindBy(xpath = "//*[contains(text(),'Number of GPUs')]/..//span[@class='md-select-icon']")
    private WebElement xpathChooseNumberOfGPUs;
    @FindBy(xpath = "//*[contains(text(),'GPU type')]/..//span[@class='md-select-icon']")
    private WebElement xpathChooseGPUType;
    @FindBy(xpath = "//*[contains(text(), 'Local SSD')]/..//span[@class='md-select-icon']")
    private WebElement xpathChooseLocalSSD;
    @FindBy(xpath = "//*[contains(text(), 'Datacenter location')]/..//span[@class='md-select-icon']")
    private WebElement xpathChooseDatacenterLocation;
    @FindBy(xpath = "//*[contains(text(), 'Committed usage')]/..//span[@class='md-select-icon']")
    private WebElement xpathChooseCommitedUsage;
    @FindBy(xpath = "//form[@name = 'ComputeEngineForm']//*[contains(text(), 'Add to Estimate')]")
    private WebElement xpathButtonAddToEstimate;
    private String xpathChoiceToPaste = "//*[@class='md-select-menu-container md-active md-clickable']//*[contains(text(), '%s')]";
    private String xpathGPUProperties = "//div[@class='md-select-menu-container md-active md-clickable']//div[contains(text(),'%s')]";

    private By frameLocator = By.xpath("//section[@class='devsite-wrapper']//iframe");


    public CalculatorPage activateComputeEngine() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        driver.switchTo().frame("myFrame");

       // dropdownSelector.switchToFrame(driver);

        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(xpathComputeEngine));
        xpathComputeEngine.click();
        logger.info("Frame was activated");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return this;
    }


    public CalculatorPage pasteNumberOfInstance(String number) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!pasteNumberOfInstance");
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.elementToBeClickable(xpathNumberOfInstance));
            xpathNumberOfInstance.click();
            xpathNumberOfInstance.sendKeys(number);
        return this;
    }

    public CalculatorPage clearFieldInstancesFor() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!ClearField");
        xpathInstancesFor.click();
        xpathInstancesFor.clear();
        return this;
    }

    public CalculatorPage chooseSoftware(String choice) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!chooseSoftware");
            //WebElement element = driver.findElement(By.xpath("//md-select[@ng-model = 'listingCtrl.computeServer.os']"));
            //xpathSoftware.click();

            String fullName = String.format(xpathChoiceToPaste, choice);
            dropdownSelector.trySelectingOption(driver, xpathSoftware, fullName);

            //new WebDriverWait(driver, 5)
            //        .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(fullName))));
            //driver.findElement(By.xpath(fullName)).click();
        return this;
    }

    public CalculatorPage chooseMachineClass(String choice) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!chooseMachineClass");
            //xpathMachineClass.click();
            String fullName = String.format(xpathChoiceToPaste, choice);
        dropdownSelector.trySelectingOption(driver, xpathMachineClass, fullName);
            //new WebDriverWait(driver, 5)
            //        .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(fullName))));
            //driver.findElement(By.xpath(fullName)).click();

        return this;
    }

    public CalculatorPage chooseMachineType(String choice) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!chooseMachineType");
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", xpathMachineClass);
       /* JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!scrolllllllllllllllllllllllllllll");*/
       // xpathMachineType.click();
        String fullName = String.format(xpathChoiceToPaste, choice);
        dropdownSelector.trySelectingOption(driver, xpathMachineType, fullName);

                //new WebDriverWait(driver, 15)
                //.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(fullName))));
        //driver.findElement(By.xpath(fullName)).click();
        return this;
    }

    public CalculatorPage tickAddGPUs() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!tickAddGPUs");
       /* JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", xpathAddGPUs);*/
        //xpathAddGPUs.click();
        dropdownSelector.clickElement(driver,xpathAddGPUs);
        return this;
    }

    public CalculatorPage chooseNumberOfGPUs(String choice) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
               , xpathMachineType);
        //xpathChooseNumberOfGPUs.click();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!chooseNumberOfGPUs");
        String fullName = String.format(xpathGPUProperties, choice);
        dropdownSelector.trySelectingOption(driver, xpathChooseNumberOfGPUs, fullName);
        //new WebDriverWait(driver, 15)
        //        .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(fullName))));
        //driver.findElement(By.xpath(fullName)).click();
        return this;
    }

    public CalculatorPage chooseGPUType(String choice) {
        //xpathChooseGPUType.click();
        String fullName = String.format(xpathGPUProperties, choice);
        dropdownSelector.trySelectingOption(driver, xpathChooseGPUType, fullName);
        //driver.findElement(By.xpath(fullName)).click();

        return this;
    }

    public CalculatorPage chooseLocalSSD(String choice) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , xpathMachineType);
        //xpathChooseLocalSSD.click();
        String fullName = String.format(xpathChoiceToPaste, choice);
        dropdownSelector.trySelectingOption(driver, xpathChooseLocalSSD, fullName);
        //new WebDriverWait(driver, 15)
        //        .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(fullName))));
        //driver.findElement(By.xpath(fullName)).click();
        return this;
    }

    public CalculatorPage chooseDatacenterLocation(String choice) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! chooseDatacenterLocation");
        //xpathChooseDatacenterLocation.click();
        String fullName = String.format(xpathChoiceToPaste, choice);
        dropdownSelector.trySelectingOption(driver, xpathChooseDatacenterLocation, fullName);
        //driver.findElement(By.xpath(fullName)).click();
        return this;
    }

    public CalculatorPage chooseCommitedUsage(String choice) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! chooseCommitedUsage");

        //xpathChooseCommitedUsage.click();
        String fullName = String.format(xpathChoiceToPaste, choice);
        dropdownSelector.trySelectingOption(driver, xpathChooseCommitedUsage, fullName);
        //driver.findElement(By.xpath(fullName)).click();
        return this;
    }

    public ResultPage clickAddToEstimate()  {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! clickAddToEstimate");

        // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", xpathButtonAddToEstimate);

        //new WebDriverWait(driver, 15)
        //        .until(ExpectedConditions.visibilityOf(xpathButtonAddToEstimate));
        //new WebDriverWait(driver, 15)
        //        .until(ExpectedConditions.elementToBeClickable(xpathButtonAddToEstimate));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
//                , xpathButtonAddToEstimate);
//        Thread.sleep(5000);
       //JavascriptExecutor executor = (JavascriptExecutor) driver;
        //executor.executeScript("arguments[0].scrollIntoView();", xpathButtonAddToEstimate);
//        executor.executeScript("arguments[0].click();", xpathButtonAddToEstimate);
// dropdownSelector.pressButton(driver, xpathButtonAddToEstimate);

       /* JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", xpathButtonAddToEstimate);*/

        dropdownSelector.clickElement(driver, xpathButtonAddToEstimate);


        return new ResultPage(driver);
    }


    public boolean isTitleTrue(String textToSearch) {
        return driver.getTitle().equals(textToSearch);
    }

    public boolean isComputeEngineButtonExist() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//section[@class='devsite-wrapper']//iframe")));
        driver.switchTo().frame("myFrame");
        List<WebElement> webElementList = driver.findElements(By.xpath("//*[text()='Compute Engine']"));
        // new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(xpathComputeEngine));
        return webElementList.size() != 0;
    }

    public ResultPage createNewEngine(Engine testEngine) throws InterruptedException {
        pasteNumberOfInstance(testEngine.getNumberOfInstances())
                .clearFieldInstancesFor()
                .chooseSoftware(testEngine.getSoftware())
                .chooseMachineClass(testEngine.getMachineClass())
                .chooseMachineType(testEngine.getMachineType())
                .tickAddGPUs().chooseNumberOfGPUs(testEngine.getNumberOfGPUs()).chooseGPUType(testEngine.getGPUType())
                .chooseLocalSSD(testEngine.getLocalSSD()).chooseDatacenterLocation(testEngine.getDataCenterLocation())
                .chooseCommitedUsage(testEngine.getCommitedUsage());
        logger.info("Calculator was filled");
        return clickAddToEstimate();
    }
}
