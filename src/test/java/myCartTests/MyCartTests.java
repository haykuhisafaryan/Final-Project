package myCartTests;

import Pages.BookPage;
import Pages.MyCartPage;
import Pages.SearchResultPage;
import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyCartTests extends BaseTests {


    //Please note that you need to run the tests from this page separately

    @Test
    public void addToCartSuccessMessage() {
        SearchResultPage searchResultPage = homePage.searchByValue("Կախվածություն");
        BookPage book = searchResultPage.selectABookByTitle("Կախվածություն");
        Assert.assertEquals(book.addToMyCart(), "Կախվածություն Ավելացվել է զամբյուղում");
    }


    @Test
    public void addToCartCheck() {
        SearchResultPage searchResultPage = homePage.searchByValue("Что такое жизнь?");
        BookPage book = searchResultPage.selectABookByTitle("Что такое жизнь?");
        book.addToMyCart();
        int price = book.getBookPrice();
        MyCartPage myCartPage = book.goToMyCart();
        Assert.assertEquals(myCartPage.getTotalPrice(), price);
    }

    @Test
    public void addMultipleItemsToCart() {
        SearchResultPage searchResultPage = homePage.searchByValue("vampire");
        BookPage bookOne = searchResultPage.selectABookByTitle("Little Vampire Women");
        bookOne.addToMyCart();
        int priceOne = bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("The last vampire The eternal dawn");
        bookTwo.addToMyCart();
        int priceTwo = bookTwo.getBookPrice();
        BookPage bookThree = bookTwo.backToSearchResults().selectABookByTitle("Vampire Academy Graphic Novel");
        int priceThree = bookThree.getBookPrice();
        bookThree.addToMyCart();
        MyCartPage myCartPage = bookThree.goToMyCart();
        int sum = priceOne + priceTwo + priceThree;
        Assert.assertEquals(myCartPage.getTotalPrice(), sum);
    }

    @Test
    public void removeFromCart() {
        SearchResultPage searchResultPage = homePage.searchByValue("Sherlock");
        BookPage bookOne = searchResultPage.selectABookByTitle("Adventures Of Sherlock Holmes, T");
        bookOne.addToMyCart();
        int priceOne = bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Sherlock Holmes. Gods of War");
        bookTwo.addToMyCart();
        int priceTwo = bookTwo.getBookPrice();
        MyCartPage myCartPage = bookTwo.goToMyCart();
        int sum = priceOne + priceTwo - priceTwo;
        myCartPage.deleteByTitle("Sherlock Holmes. Gods of War");
        Assert.assertEquals(myCartPage.getTotalPrice(), sum);

    }

    @Test
    public void removeMultipleItems() {
        SearchResultPage searchResultPage = homePage.searchByValue("Շիրազ");
        BookPage bookOne = searchResultPage.selectABookByTitle("Անտիպ էջեր․ գիրք 3 (Շիրազ)");
        bookOne.addToMyCart();
        int priceOne = bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Ընտիր երկեր Հովհաննես Շիրազ․ Հատոր առաջին");
        bookTwo.addToMyCart();
        int priceTwo = bookTwo.getBookPrice();
        BookPage bookThree = bookOne.backToSearchResults().selectABookByTitle("Քնար Հայաստանի Գիրք 3");
        bookThree.addToMyCart();
        int priceThree = bookThree.getBookPrice();
        MyCartPage myCartPage = bookThree.goToMyCart();
        myCartPage.deleteByTitle("Քնար Հայաստանի Գիրք 3");
        myCartPage.deleteByTitle("Անտիպ էջեր․ գիրք 3 (Շիրազ)");
        int sum = priceOne + priceTwo + priceThree - priceThree - priceOne;
        Assert.assertEquals(myCartPage.getTotalPrice(), sum);

    }

    @Test
    public void removeAll() {
        SearchResultPage searchResultPage = homePage.searchByValue("Շիրազ");
        BookPage bookOne = searchResultPage.selectABookByTitle("Անտիպ էջեր․ գիրք 3 (Շիրազ)");
        bookOne.addToMyCart();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Ընտիր երկեր Հովհաննես Շիրազ․ Հատոր առաջին");
        bookTwo.addToMyCart();
        BookPage bookThree = bookOne.backToSearchResults().selectABookByTitle("Քնար Հայաստանի Գիրք 3");
        bookThree.addToMyCart();
        MyCartPage myCartPage = bookTwo.goToMyCart();
        myCartPage.deleteByTitle("Քնար Հայաստանի Գիրք 3");
        myCartPage.deleteByTitle("Ընտիր երկեր Հովհաննես Շիրազ․ Հատոր առաջին");
        myCartPage.deleteByTitle("Անտիպ էջեր․ գիրք 3 (Շիրազ)");
        Assert.assertEquals(myCartPage.getMessage(), "Ձեր զամբյուղը դատարկ է\n" + "Սեղմեք ԱՅՍՏԵՂ գնումներին վերադառնալու համար");

    }

    @Test
    public void incrementBookCountTest() {
        SearchResultPage searchResultPage = homePage.searchByValue("Harry Potter");
        BookPage bookOne = searchResultPage.selectABookByTitle("Harry Potter Half Blood Prince");
        bookOne.addToMyCart();
        int priceOne = bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Harry Potter and the Deathly Hallows");
        bookTwo.addToMyCart();
        int priceTwo = bookTwo.getBookPrice();
        BookPage bookThree = bookTwo.backToSearchResults().selectABookByTitle("Harry Potter and the Prisoner of Azkaban");
        int priceThree = bookThree.getBookPrice();
        bookThree.addToMyCart();
        MyCartPage myCartPage = bookThree.goToMyCart();
        myCartPage.incrementBookCount("Harry Potter and the Prisoner of Azkaban");
        System.out.println(priceThree);
        int sum = priceOne + priceTwo + priceThree + priceThree;
        Assert.assertEquals(myCartPage.getTotalPrice(), sum);

    }

    @Test
    public void incrementMultipleItems() {
        SearchResultPage searchResultPage = homePage.searchByValue("Sherlock");
        BookPage bookOne = searchResultPage.selectABookByTitle("Adventures Of Sherlock Holmes, T");
        bookOne.addToMyCart();
        int priceOne = bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("The Adventures and Memoirs of Sherlock Holmes");
        bookTwo.addToMyCart();
        int priceTwo = bookTwo.getBookPrice();
        BookPage bookThree = bookTwo.backToSearchResults().selectABookByTitle("The Greatest Cases of Sherlock Holmes");
        bookThree.addToMyCart();
        int priceThree = bookThree.getBookPrice();
        MyCartPage myCartPage = bookThree.goToMyCart();
        myCartPage.incrementBookCount("Adventures Of Sherlock Holmes, T");
        myCartPage.incrementBookCount("The Greatest Cases of Sherlock Holmes");
        int sum = priceOne + priceTwo + priceThree + priceOne + priceThree;
        Assert.assertEquals(myCartPage.getTotalPrice(), sum);
    }

    @Test
    public void decrementBookCount() {
        SearchResultPage searchResultPage = homePage.searchByValue("Ռեմարկ");
        BookPage bookOne = searchResultPage.selectABookByTitle("Սև կոթողը");
        bookOne.addToMyCart();
        int priceOne = bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Ապրելու ժամանակը և մեռնելու ժամանակը");
        bookTwo.addToMyCart();
        int priceTwo = bookTwo.getBookPrice();
        MyCartPage myCartPage = bookTwo.goToMyCart();
        myCartPage.incrementBookCount("Ապրելու ժամանակը և մեռնելու ժամանակը");
        myCartPage.incrementBookCount("Ապրելու ժամանակը և մեռնելու ժամանակը");
        myCartPage.decrementBookCount("Ապրելու ժամանակը և մեռնելու ժամանակը");
        int sum = priceOne + priceTwo + priceTwo + priceTwo - priceTwo;
        Assert.assertEquals(myCartPage.getTotalPrice(), sum);

    }


    @Test
    public void incrementDelete() {
        SearchResultPage searchResultPage = homePage.searchByValue("Մարկ Արեն");
        BookPage bookOne = searchResultPage.selectABookByTitle("Վիլ-Էվրար (Մարկ Արենի պատմությունը)");
        bookOne.addToMyCart();
        int priceOne = bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Ռեքվիեմ Հուդայի համար");
        bookTwo.addToMyCart();
        int priceTwo = bookOne.getBookPrice();
        MyCartPage myCartPage = bookTwo.goToMyCart();
        myCartPage.incrementBookCount("Վիլ-Էվրար (Մարկ Արենի պատմությունը)");
        myCartPage.deleteByTitle("Ռեքվիեմ Հուդայի համար");
        int sum = priceOne + priceTwo + priceOne + -priceTwo;
        Assert.assertEquals(myCartPage.getTotalPrice(), sum);

    }


}
