package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private By header = By.xpath("//*[@id=\"top\"]/body/div[2]/div/div[2]/div/div[2]/div/div");
    private By titles = By.className("book_name");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeader() {
        return driver.findElement(header).getText();
    }

    public BookPage selectABookByTitle(String title) {
        driver.findElement(By.linkText(title)).click();
        return new BookPage(driver);
    }


    public ArrayList<String> getTitles() {
        List<WebElement> elements = driver.findElements(titles);
        ArrayList<String> titles = new ArrayList<>();
        for (WebElement element : elements) {
            System.out.println("Book title:" + element.getText());
            titles.add(element.getText());
        }
        return titles;
    }


}
