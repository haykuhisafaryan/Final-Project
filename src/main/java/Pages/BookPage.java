package Pages;

import Utilities.WindowManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage {
    private WebDriver driver;
    private By addToCartButton = By.xpath("//*[@id=\"product_addtocart_form\"]/div[2]/div[3]/div[1]/div/a");
    private By bookPrice = By.className("price");
    private By messages = By.className("messages");
    private By myCartButton = By.className("fullcart");

    public BookPage(WebDriver driver) {
        this.driver = driver;
    }

    public String addToMyCart() {
        driver.findElement(addToCartButton).click();
        return driver.findElement(messages).getText();
    }

    public int getBookPrice() {
        String letterPrice = driver.findElement(bookPrice).getText();
        letterPrice = letterPrice.substring(0, letterPrice.length() - 2);
        int price = Integer.parseInt(letterPrice);
        return price;
    }

    public MyCartPage goToMyCart() {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(myCartButton)));
        driver.findElement(myCartButton).click();
        return new MyCartPage(driver);
    }

    public SearchResultPage backToSearchResults() {
        WindowManager windowManager = new WindowManager(driver);
        windowManager.goBack();
        return new SearchResultPage(driver);
    }

}
