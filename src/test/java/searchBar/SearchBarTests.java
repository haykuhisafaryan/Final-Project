package searchBar;

import Pages.SearchResultPage;
import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchBarTests extends BaseTests {

    @Test
    public void searchFromHomePageExisting() {
        SearchResultPage searchResultPage = homePage.searchByValue("Անկումը");
        Assert.assertTrue(searchResultPage.getTitles().contains("Անկումը"));
    }

    @Test
    public void searchFromHomePageNonExisting() {
        SearchResultPage searchResultPage = homePage.searchByValue("aasdfghjk");
        Assert.assertEquals(searchResultPage.getHeader(), "Ապարդյուն որոնում");
    }

    @Test
    public void searchFromHomePageSigns() {
        SearchResultPage searchResultPage = homePage.searchByValue("///");
        Assert.assertEquals(searchResultPage.getHeader(), "Ապարդյուն որոնում");
    }

    @Test
    public void searchFromHomePageFailing() {
        SearchResultPage searchResultPage = homePage.searchByValue("' OR 1=1");
        Assert.assertEquals(searchResultPage.getHeader(), "Ապարդյուն որոնում");
    }

}
