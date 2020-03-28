package pageobject;

import model.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.BrowserSelector;
import util.DriverUtils;
import util.JavaScriptExecutorUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalculatorPage extends BasePage {
    private final Logger logger = LogManager.getRootLogger();
    private final BrowserSelector browserSelector = new BrowserSelector();

    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[text()='Compute Engine'][1]")
    private WebElement computeEngine;
    @FindBy(xpath = "//*[@name='quantity']")
    private WebElement numberOfInstance;
    @FindBy(xpath = "//*[@name='label']")
    private WebElement instancesFor;
    @FindBy(xpath = "//*[contains(text(), 'Software')]/..//span[@class='md-select-icon']")
    private WebElement software;
    @FindBy(xpath = "//*[contains(text(), 'Machine Class')]/..//span[@class='md-select-icon']")
    private WebElement machineClass;
    @FindBy(xpath = "//*[contains(text(), 'Machine type')]/..//span[@class='md-select-icon']")
    private WebElement machineType;
    @FindBy(xpath = "//*[contains(text(), 'Add GPUs')]/../div")
    private WebElement addGPUs;
    @FindBy(xpath = "//*[contains(text(),'Number of GPUs')]/..//span[@class='md-select-icon']")
    private WebElement chooseNumberOfGPUs;
    @FindBy(xpath = "//*[contains(text(),'GPU type')]/..//span[@class='md-select-icon']")
    private WebElement chooseGPUType;
    @FindBy(xpath = "//*[contains(text(), 'Local SSD')]/..//span[@class='md-select-icon']")
    private WebElement chooseLocalSSD;
    @FindBy(xpath = "//*[contains(text(), 'Datacenter location')]/..//span[@class='md-select-icon']")
    private WebElement chooseDatacenterLocation;
    @FindBy(xpath = "//*[contains(text(), 'Committed usage')]/..//span[@class='md-select-icon']")
    private WebElement chooseCommitedUsage;
    @FindBy(xpath = "//form[@name = 'ComputeEngineForm']//*[contains(text(), 'Add to Estimate')]")
    private WebElement buttonAddToEstimate;
    private String xpathChoiceToPaste = "//*[@class='md-select-menu-container md-active md-clickable']//*[contains(text(), '%s')]";
    private String xpathGPUProperties = "//div[@class='md-select-menu-container md-active md-clickable']//div[contains(text(),'%s')]";

    public CalculatorPage activateComputeEngine() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        DriverUtils.switchToFrame(driver);
        DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, computeEngine);
        computeEngine.click();
        logger.info("Frame was activated");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return this;
    }

    public CalculatorPage pasteNumberOfInstance(String number) {
        DriverUtils.waitUntilElementToBeClickableUsingWebElement(driver, numberOfInstance);
        numberOfInstance.click();
        numberOfInstance.sendKeys(number);
        return this;
    }

    public CalculatorPage clearFieldInstancesFor() {
        instancesFor.click();
        instancesFor.clear();
        return this;
    }

    public CalculatorPage chooseSoftware(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, software, fullName);
        return this;
    }

    public CalculatorPage chooseMachineClass(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, machineClass, fullName);
        return this;
    }

    public CalculatorPage chooseMachineType(String choice) {
        JavaScriptExecutorUtils.scrollToElement(driver, machineClass);
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, machineType, fullName);
        return this;
    }

    public CalculatorPage tickAddGPUs() {
        browserSelector.clickElement(driver, addGPUs);
        return this;
    }

    public CalculatorPage chooseNumberOfGPUs(String choice) {
        JavaScriptExecutorUtils.scrollToElement(driver, machineType);
        String fullName = String.format(xpathGPUProperties, choice);
        browserSelector.trySelectingOption(driver, chooseNumberOfGPUs, fullName);
        return this;
    }

    public CalculatorPage chooseGPUType(String choice) {
        String fullName = String.format(xpathGPUProperties, choice);
        browserSelector.trySelectingOption(driver, chooseGPUType, fullName);
        return this;
    }

    public CalculatorPage chooseLocalSSD(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, chooseLocalSSD, fullName);
        return this;
    }

    public CalculatorPage chooseDatacenterLocation(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, chooseDatacenterLocation, fullName);
        return this;
    }

    public void chooseCommitedUsage(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, chooseCommitedUsage, fullName);
    }

    public ResultPage clickAddToEstimate() {
        browserSelector.clickElement(driver, buttonAddToEstimate);
        return new ResultPage(driver);
    }

    public boolean isTitleTrue(String textToSearch) {
        return driver.getTitle().equals(textToSearch);
    }

    public boolean isComputeEngineButtonExist() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        DriverUtils.switchToFrame(driver);
        List<WebElement> webElementList = driver.findElements(By.xpath("//*[text()='Compute Engine']"));
        return webElementList.size() != 0;
    }

    public ResultPage createNewEngine(Engine testEngine) {
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
