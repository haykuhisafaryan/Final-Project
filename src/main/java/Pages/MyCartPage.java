package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MyCartPage {

    public WebDriver driver;
    private By cartText = By.className("choose_block");
    private By totalPrice = By.xpath(" //*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span");
    private By bookName = By.className("choose_book_name");
    public String[] stringTitles;


    public MyCartPage(WebDriver driver) {
        this.driver = driver;
        List<WebElement> titles = driver.findElements(bookName);
        stringTitles = new String[titles.size()];
        for (int i = 0; i < stringTitles.length; i++) {
            stringTitles[i] = titles.get(i).getText();
        }
    }

    public int getTotalPrice() {
        String letterPrice = driver.findElement(totalPrice).getText();
        letterPrice = letterPrice.substring(0, letterPrice.length() - 2);
        int price = Integer.parseInt(letterPrice);
        return price;
    }


    public void decrementBookCount(String title) {
        for (int t = 0; t < stringTitles.length; t++) {
            if (stringTitles[t].equals(title)) {
                int index = t + 1;
                String decXpath = "//*[@id=\"shopping-cart-table\"]/form/div[" + index + "]/div[2]/div[1]/div/a[1]";
                String refreshXpath = "//*[@id=\"shopping-cart-table\"]/form/div[" + index + "]/div[2]/div[2]/button";
                driver.findElement(By.xpath(decXpath)).click();
                driver.findElement(By.xpath(refreshXpath)).click();
            }
        }

    }

    public void incrementBookCount(String title) {
        for (int t = 0; t < stringTitles.length; t++) {
            if (stringTitles[t].equals(title)) {
                int index = t + 1;
                String incXpath = "//*[@id=\"shopping-cart-table\"]/form/div[" + index + "]/div[2]/div[1]/div/a[2]";
                String refreshXpath = "//*[@id=\"shopping-cart-table\"]/form/div[" + index + "]/div[2]/div[2]/button";
                driver.findElement(By.xpath(incXpath)).click();
                driver.findElement(By.xpath(refreshXpath)).click();

            }
        }

    }

    public void deleteByTitle(String title) {
        for (int t = 0; t < stringTitles.length; t++) {
            if (stringTitles[t].equals(title)) {
                int index = t + 1;
                String xpath = "//*[@id=\"shopping-cart-table\"]/form/div[" + index + "]/div[2]/div[2]/a";
                driver.findElement(By.xpath(xpath)).click();
            }
        }
    }


    public String getMessage() {
        return driver.findElement(cartText).getText();
    }
}

