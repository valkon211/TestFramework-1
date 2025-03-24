package tests;

import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.FavoritesPage;
import pages.HomePage;

import java.util.Map;

public class FavoritesTest extends BaseTest {
    private HomePage homePage;
    private CategoryPage categoryPage;
    private FavoritesPage favoritesPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        favoritesPage = new FavoritesPage(driver);
    }

    @Test(dataProvider = "FavoritesTestsData", dataProviderClass = TestDataProvider.class)
    public void testAddToFavorite(Map<String, Object> testData) {
        var categoryTitle = (String) testData.get("categoryName");

        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(categoryTitle);
        categoryPage.addPostToFavorite(0);

        var postTitle = categoryPage.getPostTitleByIndex(0);

        categoryPage.clickFavoriteBtn();
        var postExists = favoritesPage.isPostWithTitleExists(postTitle);

        Assert.assertTrue(postExists, String.format("В 'Избраное' отсутсвует картина с названием '%s'!", postTitle));
    }
}
