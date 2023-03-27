package org.testholocron.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinVizPage {

    WebDriver driver;
    @FindBy(xpath="//*[text()='Screener']")
    WebElement screenerButton;
    @FindBy(id = "tickersInput")
    WebElement tickerInput;
    @FindBy(xpath = "//*[@value='>']")
    WebElement tickerSearch;
    @FindBy(xpath = "//tbody/tr/td[@class='screener-body-table-nw'][7]")
    WebElement marketCap;
    @FindBy(xpath = "//a[@class='screener-link-primary']")
    WebElement detailQuote;
    public FinVizPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void goToScreener(){
        screenerButton.click();
    }

    public void enterTicker(String ticker){
        tickerInput.sendKeys(ticker);
    }
    public void getQuoteForTicker(String ticker){
        tickerInput.sendKeys(ticker);
        tickerSearch.click();
    }
    public String getMarketCap(){
        return marketCap.getText();
    }
    public void openDetailedQuote(){
        detailQuote.click();
    }
}
