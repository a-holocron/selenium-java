package org.testholocron.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
    @FindBy(id = "fs_exch")
    WebElement exchangesDropdown;
    
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
    
	public List<String> getEchangesDropdownItemList() {
    	List<String> dropDownList = new ArrayList<String>();
    	Select s = new Select(exchangesDropdown);
    	List<WebElement> items = s.getOptions();
    	for (WebElement item: items) {
    		dropDownList.add(item.getText());
    	}
    	
    	return dropDownList;
    	
    }
}
