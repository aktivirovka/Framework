package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

abstract public class BasePage {
    protected WebDriver driver;
    protected String url;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPage() {
        driver.get(url);
    }

    public void createNewTab() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.open();");
    }

    public void switchTabByIndex(int index) {
        ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(index));
    }
}
