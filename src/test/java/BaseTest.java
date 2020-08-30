import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import static utils.TestRunner.printToLog;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void setup() {
        driver = getNewWebDriver();
    }

    private WebDriver getNewWebDriver() {
        String BROWSER_NAME = System.getProperty("browser");

        if (driver == null) {
            switch (BROWSER_NAME) {
                case "chrome":
                    printToLog("Setting new ChromeWebDriver...");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    printToLog("Setting new FirefoxWebDriver...");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                default:
                    printToLog("Setting default browser...");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        return driver;
    }


    @AfterSuite
    public void afterAllTests() {
        if (driver != null) {
            driver.quit();
        }
    }


}
