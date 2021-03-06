package infra.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static utils.TestRunner.printToLog;
import static utils.TestRunner.writeToCsvFile;

public class SearchResultsPage extends pages.BasePage {


    @FindAll({
            @FindBy(css = "div.s-include-content-margin.s-border-bottom.s-latency-cf-section")})
    public List<WebElement> itemsInPage;

    @FindBy(css = "ul.a-pagination li.a-last")
    public WebElement nextResultsPageBtn;


    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * this method scrap the search results data into a csv file
     */

    public void scrapSearchResults() {
        String title;
        String date;
        String prize;
        String ranking;
        int pagesScrapped = 0;
        List<SearchResultItem> searchResultItems = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        try {
            do {
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.s-include-content-margin.s-border-bottom.s-latency-cf-section"), 1));
                printToLog(itemsInPage.size() + " items found in results page #" + (pagesScrapped + 1));
                for (WebElement item : itemsInPage) {
                    // Item's title
                    title = item.findElement(By.cssSelector("span.a-size-medium.a-color-base.a-text-normal")).getText();

                    // Items date
                    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("span.a-size-base.a-color-secondary.a-text-normal"), 1));
                    date = item.findElement(By.cssSelector("span.a-size-base.a-color-secondary.a-text-normal")).getText();

                    // Item's prize
                    List<WebElement> prizes = item.findElements(By.cssSelector("span.a-price"));
                    prize = prizes.get(0).getText().replaceAll(System.lineSeparator(), ".");

                    // Item's ranking  (number of stars)
                    //TODO: not done yet
//                moveToElement(item.findElement(By.cssSelector("div.a-row.a-size-small")));
//                ranking = item.findElement(By.cssSelector("span.a-icon-alt")).getText();

                    searchResultItems.add(new SearchResultItem(title, date, prize));
//                searchResultItems.add(new SearchResultItem(title, date, prize, ranking));
                }

                printToLog(searchResultItems.toString());
                writeToCsvFile(searchResultItems);
                wait.until(ExpectedConditions.elementToBeClickable(nextResultsPageBtn)).click();
                printToLog("Moving to next results page");
                pagesScrapped++;
                Thread.sleep(3000);

            }
            while (pagesScrapped < 4);    // pagination
        } catch (Exception ex) {
            printToLog("SearchResultsPage.scrapSearchResults: " + ex.getMessage());
        }
    }


}
