package org.testholocron.test;

import org.testholocron.base.BaseSelenium;
import org.testholocron.pages.FinVizPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Refactoring.
 * implement Page Object Model
 * Elements and actions  are moved into page classes
 * Test class should only contain step and assertions
 */
@Test(testName = "FinViz test", description = "Finviz Login test with POM for better maintainability")
public class T4_WithPOMTest extends BaseSelenium {

    @Test(priority =2)
    public void finFizTest() {
        // STEPS:
        // 1. Open https://finviz.com
        // 2. navigate to screener , input a few search
        // 3. capture some data

        driver.get("https://finviz.com");

        FinVizPage fv = new FinVizPage(driver);
        String title = fv.getTitle();
        Assert.assertTrue(title.contains("Stock Screener"), "Title is showing ->"+title);

        fv.goToScreener();
        fv.getQuoteForTicker("META");

        // wait for Meta Platforms, Inc. quote to ba available
        boolean isMetaCompanyNameExist = driver.getPageSource().contains("Meta Platforms, Inc");
        Assert.assertTrue(isMetaCompanyNameExist,"Meta PLatform Name not showing");

        // Capture the data - Get the marketcap
        String marketCapText = fv.getMarketCap();
        System.out.println(marketCapText);

        // Open the Meta Quote
        fv.openDetailedQuote();

        //  Verfify quote is showing correctly
        String quoteTitle = fv.getTitle();
        System.out.println(quoteTitle);
        Assert.assertTrue(quoteTitle.contains("Meta Platforms, Inc"), "Title is not Meta Platform is showing ->"+title);

    }



}