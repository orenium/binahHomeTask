import infra.pages.AmazonHomePage;
import infra.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ScrapperTest extends BaseTest {


    public String SEARCH_TERM = "software automation testing";


    @Test
    public void scrapperTest() throws InterruptedException {
        // Step 1: open http://www.amazon.com
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver);
        amazonHomePage.openAmazonHomePage();

        // Step 2: enter a search term: software automation testing
        SearchResultsPage searchResultsPage = amazonHomePage.searchItem(SEARCH_TERM);

        // Step 3: 'Scrap' the search results into csv file
        searchResultsPage.scrapSearchResults();

        // Verify CSV file was created
        Assert.assertTrue(new File("items.csv").exists());
    }
}
