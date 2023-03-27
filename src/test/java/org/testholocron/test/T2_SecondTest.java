package org.testholocron.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

/**
 * Introduce TestNG.
 * Script will use beforeAll, aftereach , and test
 */
class T2_ExplarotoryTest {

    static WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() throws InterruptedException {
        // STEPS:
        // 1. Open https://finviz.com
        // 2. navigate to screener , input a few search
        // 3. capture some data

        driver.get("https://finviz.com");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.contains("Stock Screener"), "Title is showing ->"+title);
        WebElement el = driver.findElement(new By.ByXPath("//*[text()='Screener']"));
        el.click();
        WebElement tickerInput = driver.findElement(By.id("tickersInput"));
        tickerInput.sendKeys("META");
        WebElement searchButton = driver.findElement(new By.ByXPath("//*[@value='>']"));
        searchButton.click();

        // wait for Meta Platforms, Inc. quote to ba available
        boolean isMetaCompanyNameExist = driver.getPageSource().contains("Meta Platforms, Inc");
        Assert.assertTrue(isMetaCompanyNameExist,"Meta PLatform Name not showing");

        // Capture the data
        // Get the marketcap
        WebElement marketCap = driver.findElement(new By.ByXPath("//tbody/tr/td[@class='screener-body-table-nw'][7]"));
        String marketCapText = marketCap.getText();
        System.out.println(marketCapText);

        // Open the Meta Quote
        WebElement metaQuote = driver.findElement(new By.ByXPath("//a[@class='screener-link-primary']"));
        metaQuote.click();
        //  Verfify quote is showing correctly
        String quoteTitle = driver.getTitle();
        System.out.println(quoteTitle);
        Assert.assertTrue(quoteTitle.contains("Meta Platforms, Inc"), "Title is not Meta Platform is showing ->"+title);
        Thread.sleep(1000);

    }



}