package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TimeSite extends BasePage {
    public TimeSite(WebDriver driver) {
        super(driver);
        url = "https://10minutemail.com";
    }

    @FindBy(className = "copy_icon")
    private WebElement copyEmailAddress;
    @FindBy(xpath = "//*[@class='small_message_icon']")
    private WebElement getMessage;
    @FindBy(id = "mail_address")
    private WebElement mailAddress;
    @FindBy(xpath = "//*[contains(text(), 'USD')]")
    private WebElement xpathTotalCost;

    public TimeSite openSiteInNewTab() {
        createNewTab();
        switchTabByIndex(1);
        driver.get(url);
        return this;
    }

    public String copyEmailAddress() throws IOException, UnsupportedFlavorException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver).executeScript(
                "return document.readyState"
        ).equals("complete"));

        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(copyEmailAddress));
        copyEmailAddress.click();
        return (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public void getMessage() {
        switchTabByIndex(1);
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='small_message_icon']")));
        getMessage.click();
    }

    public boolean isCostTrue(String totalCost) {
        String textFromXpath = xpathTotalCost.getText();
        String[] arrayWords = textFromXpath.split("USD", 2);
        return arrayWords[1].trim().equals(totalCost);
    }
}
