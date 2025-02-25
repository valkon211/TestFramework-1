package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;


public class SearchTests extends BaseTest {
    private HomePage homePage;
    private SearchResultPage searchResultPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test
    public void testSearchForGiraffe() {
        String searchQuery = "Жираф";
        homePage.inputSearchQuery(searchQuery);
        homePage.clickSearchBth();

        String firstPostTitle = searchResultPage.getSearchResultElementTitle(0);

        Assert.assertTrue(firstPostTitle.contains("жираф"), "Первая картина не содержит слово 'Жираф'!");
    }
}
