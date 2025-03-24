package tests;

import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

import java.util.Map;


public class SearchTest extends BaseTest {
    private HomePage homePage;
    private SearchResultPage searchResultPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test(dataProvider = "SearchTestsData", dataProviderClass = TestDataProvider.class)
    public void testCheckSearchResult(Map<String, Object> testData) {
        var searchQuery = (String) testData.get("searchQuery");
        var postIndex = (Integer) testData.get("postIndex");

        homePage.inputSearchQuery(searchQuery);
        homePage.clickSearchBth();

        var firstPostTitle = searchResultPage.getPostTitleByIndex(postIndex);

        Assert.assertTrue(firstPostTitle.contains(
                searchQuery.toLowerCase()), String.format("Первая картина не содержит слово '%s'!", searchQuery));
    }
}
