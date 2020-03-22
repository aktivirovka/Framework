package tests;

import driver.DriverSingleton;
//import org.junit.After;
//import org.junit.Before;
//import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import util.TestListener;


@Listeners({TestListener.class})
abstract public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod()
    public void createDriver() {
        /*URL url = this.getClass().getClassLoader().getResource("drivers/geckodriver.exe");
        File file = new File(url.getFile());
        GeckoDriverService.Builder bldr = (new GeckoDriverService.Builder())
                .usingDriverExecutable(file)
                .usingAnyFreePort();*/

        driver = DriverSingleton.getDriver();

    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverSingleton.closeDriver();
    }
}
