package org.testholocron.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Refactoring. Move setup class into a different class and have individual test inherit from basetest class
 */
public class BaseSelenium {

    protected WebDriver driver;

    @BeforeSuite
    static void setupDriver() {
        System.out.println("Before Suite");


    }

    @BeforeClass
    protected void setup() {
        System.out.println("Before Class");

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //WebDriverContext.setDriver(driver);
    }

    @BeforeTest
    void setupWebDriver(){
        System.out.println("Before Test");

        if (driver.toString()==null) {
            setupDriver();
        }
    }

    @AfterClass
    public void testTearDown() {
        System.out.println("After Class");
            driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        System.out.println("After Suite");
        if (driver != null) {
            driver.quit();
        }
    }


}