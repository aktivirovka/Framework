package tests;

import driver.DriverSingleton;
//import org.junit.After;
//import org.junit.Before;
//import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.URL;

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
     //   driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)                             //отличия after from afterMethod
    public void closeDriver() {
        DriverSingleton.closeDriver();
    }
}
