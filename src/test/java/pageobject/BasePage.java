package pageobject;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public abstract class BasePage {
    protected WebDriver driver;
    protected String url;

    public BasePage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void goToPage() {
        driver.get(url);
    }

    public void switchTabByIndex(int index) {
        ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(index));
    }
}
