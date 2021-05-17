package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExtendedSearchPage {

    private WebDriver driver;
    private By ISBNBar = By.name("isbn");
    private By searchButton = By.xpath("//*[@id=\"form-validate\"]/div[8]/button");

    public ExtendedSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterBookISBN(String searchValue) {
        driver.findElement(ISBNBar).sendKeys(searchValue);
    }

    public SearchResultPage searchForValue() {
        driver.findElement(searchButton).click();
        return new SearchResultPage(driver);
    }
}
