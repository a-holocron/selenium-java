package org.testholocron.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testholocron.base.BaseSelenium;
import org.testholocron.pages.FinVizPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Check for dropdown values
 */
@Test(testName = "FinViz screener dropdown test", description = "Check all exchanges oaptions")
public class T6_FifthDropdownTest extends BaseSelenium {

    String[] expectedExchanges = {"Any","AMEX","NASDAQ","NYSE","Custom (Elite only)"};

    @Test(priority =2)
    public void finFizTest() {

        driver.get("https://finviz.com");
        FinVizPage fv = new FinVizPage(driver);
        String title = fv.getTitle();
        Assert.assertTrue(title.contains("Stock Screener"), "Title is showing ->"+title);
        fv.goToScreener();
        WebElement exchangesDropDown = driver.findElement(By.id("fs_exch"));
        Select s = new Select(exchangesDropDown);
        List< WebElement> exchangesOptions = s.getOptions();
        for (WebElement exchange:exchangesOptions) {
            System.out.println(exchange.getText());
            Assert.assertTrue(Arrays.asList(expectedExchanges).contains(exchange.getText()),exchange.getText());

        }
    }



}