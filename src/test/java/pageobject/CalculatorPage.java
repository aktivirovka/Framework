package pageobject;

import model.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.BrowserSelector;
import util.ExecutorUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalculatorPage extends BasePage {
    private final Logger logger = LogManager.getRootLogger();
    private final BrowserSelector browserSelector = new BrowserSelector();

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
    @FindBy(xpath = "//*[@id ='resultBlock']//*[contains(text(),'Estimate')]")
    private WebElement resultCalculatorWindow;

    private By frameLocator = By.xpath("//section[@class='devsite-wrapper']//iframe");


    public CalculatorPage activateComputeEngine() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        driver.switchTo().frame("myFrame");


        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(xpathComputeEngine));
        xpathComputeEngine.click();
        logger.info("Frame was activated");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return this;
    }


    public CalculatorPage pasteNumberOfInstance(String number) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(xpathNumberOfInstance));
        xpathNumberOfInstance.click();
        xpathNumberOfInstance.sendKeys(number);
        return this;
    }

    public CalculatorPage clearFieldInstancesFor() {
        xpathInstancesFor.click();
        xpathInstancesFor.clear();
        return this;
    }

    public CalculatorPage chooseSoftware(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, xpathSoftware, fullName);
        return this;
    }

    public CalculatorPage chooseMachineClass(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, xpathMachineClass, fullName);
        return this;
    }

    public CalculatorPage chooseMachineType(String choice) {
        ExecutorUtils.scrollToElement(driver, xpathMachineClass);
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, xpathMachineType, fullName);
        return this;
    }

    public CalculatorPage tickAddGPUs() {
        browserSelector.clickElement(driver, xpathAddGPUs);
        return this;
    }

    public CalculatorPage chooseNumberOfGPUs(String choice) {

        ExecutorUtils.scrollToElement(driver, xpathMachineType);
        String fullName = String.format(xpathGPUProperties, choice);
        browserSelector.trySelectingOption(driver, xpathChooseNumberOfGPUs, fullName);
        return this;
    }

    public CalculatorPage chooseGPUType(String choice) {
        String fullName = String.format(xpathGPUProperties, choice);
        browserSelector.trySelectingOption(driver, xpathChooseGPUType, fullName);
        return this;
    }

    public CalculatorPage chooseLocalSSD(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, xpathChooseLocalSSD, fullName);
        return this;
    }

    public CalculatorPage chooseDatacenterLocation(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, xpathChooseDatacenterLocation, fullName);
        return this;
    }

    public CalculatorPage chooseCommitedUsage(String choice) {
        String fullName = String.format(xpathChoiceToPaste, choice);
        browserSelector.trySelectingOption(driver, xpathChooseCommitedUsage, fullName);
        return this;
    }

    public ResultPage clickAddToEstimate() {

        ExecutorUtils.scrollToElement(driver,resultCalculatorWindow);
        browserSelector.clickElement(driver, xpathButtonAddToEstimate);
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
