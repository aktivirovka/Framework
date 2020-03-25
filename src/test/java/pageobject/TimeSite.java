package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DropdownSelector;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TimeSite extends BasePage {
    private final Logger logger = LogManager.getRootLogger();
    private final DropdownSelector dropdownSelector = new DropdownSelector();

    public TimeSite(WebDriver driver) {
        super(driver);
        url = "https://dropmail.me/ru/";
      //  url = "https://10minutemail.com";
    }

   /* @FindBy(className = "copy_icon")
    private WebElement copyEmailAddress;
    @FindBy(xpath = "//*[@class='small_message_icon']")
    private WebElement getMessage;
    @FindBy(id = "mail_address")
    private WebElement mailAddress;
    @FindBy(xpath = "//*[contains(text(), 'USD')]")
    private WebElement xpathTotalCost;*/



    @FindBy(className = "email")
    private WebElement mailAddress;
    @FindBy(xpath = "//*[contains(text(), 'Compute User')]")
    private WebElement getMessage;


    public TimeSite openSiteInNewTab() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! openSiteInNewTab");
        createNewTab();
        switchTabByIndex(1);
        driver.get(url);
        return this;
    }
    public String copyEmailAddress()  {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!COPY EMAIL ADDRESS");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver).executeScript(
                "return document.readyState"
        ).equals("complete"));

        /*System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! GO TO MY METHOD COPY EMAIL ADDRESS");
        dropdownSelector.getTextFromWebElement(driver,mailAddress);*/
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(mailAddress));

        return mailAddress.getText();
    }


    /*public String copyEmailAddress() throws IOException, UnsupportedFlavorException {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver).executeScript(
                "return document.readyState"
        ).equals("complete"));


       // html(driver.Key.chord(driver.Key.CONTROL, "a");

        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(mailAddress));
        WebElement input = driver.findElement(By.id("mail_address"));
        WebElement html = driver.findElement(By.tagName("html"));
        //html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        html.sendKeys(Keys.chord(Keys.CONTROL + "c"));
      //  copyEmailAddress.click();

       *//* WebElement txtProductSearch1 = null;
        txtProductSearch1.SendKeys(Keys.Control + "c");*//*


        String address = input.getAttribute("value");
        logger.info("Email address was copy");
       // html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
        if(address==null){
            return (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
        }else return address;
    }*/

    public String getMessage() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!GET MESSAGE");
        switchTabByIndex(1);
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Compute User')]")));
        return getMessage.getText();
    }

    public boolean isCostTrue(String totalCost, String message) {
        String[] arrayWords = message.split(":", 2);
        String[] arrayWords2 = arrayWords[1].split(":", 2);
        String[] arrayWords3 = arrayWords2[1].split("\n\n", 2);
        return arrayWords3[0].trim().equals(totalCost);
    }
}
