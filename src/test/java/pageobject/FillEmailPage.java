package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DropdownSelector;

public class FillEmailPage extends BasePage {
    private final DropdownSelector dropdownSelector = new DropdownSelector();
    public FillEmailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'Email')]/../input[@name='description']")
    private WebElement xpathFieldEmailAddress;
    @FindBy (xpath = "//*[contains(text(),'Send Email')]/.")
    private WebElement buttonSendEmail;
    @FindBy(xpath = "//*[contains(text(),'Email Your Estimate')]")
    private WebElement fillingFormWindow;

    public FillEmailPage switchAndUseEmailAddress(String emailAddress) throws InterruptedException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! switchAndUseEmailAddress");

        switchTabByIndex(0);
       /* System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SWITCH TO FRAME");
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//section[@class='devsite-wrapper']//iframe")));
        driver.switchTo().frame("myFrame");*/
        dropdownSelector.switchToFrame(driver);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SCROLL DOWN");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", fillingFormWindow);
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");



        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!TO FIND ELEMENT");
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(xpathFieldEmailAddress));
        //xpathFieldEmailAddress.getLocation();
        //Actions builder = new Actions(driver);
        //builder.moveToElement(xpathFieldEmailAddress, 0, 0).click().build().perform();

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CLICK");
        xpathFieldEmailAddress.sendKeys(emailAddress);
       // JavascriptExecutor executor = (JavascriptExecutor) driver;

//        executor.executeScript("arguments[0].click();", xpathFieldEmailAddress);
//         Thread.sleep(500);
//      //  dropdownSelector.pressButton(driver, xpathFieldEmailAddress);
//        String fullemail = "arguments[0].value='" + emailAddress+ "';";
//
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PASTE");
//        executor.executeScript(fullemail, xpathFieldEmailAddress);


      //  xpathFieldEmailAddress.sendKeys(emailAddress);

        return this;
    }

    public void clickButtonSendEmail() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!! clickButtonSendEmail");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", xpathFieldEmailAddress);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonSendEmail);
        //buttonSendEmail.click();
    }
}
