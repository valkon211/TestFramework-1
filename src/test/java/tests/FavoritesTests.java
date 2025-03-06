package tests;

import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.FavoritesPage;
import pages.HomePage;

public class FavoritesTests extends BaseTest {
    private HomePage homePage;
    private CategoryPage categoryPage;
    private FavoritesPage favoritesPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        favoritesPage = new FavoritesPage(driver);
    }

    @Test
    public void testAddToFavorite() {
        var categoryTitle = "Батик";

        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(categoryTitle);
        categoryPage.addPostToFavorite(0);

        var postTitle = categoryPage.getPostTitleByIndex(0);

        categoryPage.clickFavoriteBtn();
        var postExists = favoritesPage.isPostWithTitleExists(postTitle);

        Assert.assertTrue(postExists, String.format("В 'Избраное' отсутсвует картина с названием '%s'!", postTitle));
    }
}
