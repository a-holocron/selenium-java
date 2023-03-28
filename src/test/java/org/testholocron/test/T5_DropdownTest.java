package org.testholocron.test;

import java.util.Arrays;
import java.util.List;

import org.testholocron.base.BaseSelenium;
import org.testholocron.pages.FinVizPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Check dropdown option match expected options
 * QA : chee
 */
@Test(testName = "FinViz screener dropdown test", description = "Check all exchanges oaptions")
public class T5_DropdownTest extends BaseSelenium {

    String[] expectedExchanges = {"Any","AMEX","NASDAQ","NYSE","Custom (Elite only)"};

    @Test(priority =2)
    public void finFizTest() {

        driver.get("https://finviz.com");
        FinVizPage fv = new FinVizPage(driver);
        String title = fv.getTitle();
        Assert.assertTrue(title.contains("Stock Screener"), "Title is showing ->"+title);
        fv.goToScreener();
        List<String> exchangesDropDownList = fv.getEchangesDropdownItemList();
        
        for (String exchangesDropDown:exchangesDropDownList) {
            System.out.println(exchangesDropDown);
            Assert.assertTrue(Arrays.asList(expectedExchanges).contains(exchangesDropDown),exchangesDropDown);

        }
    }



}