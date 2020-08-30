package infra.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.TestRunner.printToLog;

public class AmazonHomePage extends pages.BasePage {

    @FindBy(id = "twotabsearchtextbox")
    public WebElement inputSearchItem;

    private static final String AMAZON_HOME_PAGE = "http://www.amazon.com";


    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    public void openAmazonHomePage(){
        if (driver != null){
            driver.get(AMAZON_HOME_PAGE);
        } else {
            printToLog("There was an issue with the webDriver");
        }
    }

    public SearchResultsPage searchItem(String searchTerm){

        try{
            wait.until(ExpectedConditions.elementToBeClickable(inputSearchItem));
                inputSearchItem.sendKeys(searchTerm);
                inputSearchItem.sendKeys(Keys.ENTER);

        } catch (Exception ex){
            printToLog("AmazonHomePage.searchItem: " + ex.getMessage());
        }
        return new SearchResultsPage(driver);
    }

}
