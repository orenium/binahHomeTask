package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public WebDriverWait wait;
    protected WebDriver driver;
    private Actions action;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }


    public void moveToElement(WebElement element){
        if (driver != null) {
            action.moveToElement(element).perform();
        }
    }


}
