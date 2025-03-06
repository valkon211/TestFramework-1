package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;

public class CategoryTests extends BaseTest {
    private HomePage homePage;
    private CategoryPage categoryPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
    }

    @Test
    public void testCheckCategoryPostExists() {
        var categoryTitle = "Вышитые картины";
        var title = "Трамвайный путь";
        var postExists = false;

        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(categoryTitle);

        while(true){
            postExists = categoryPage.isPostWithTitleExists(title);

            if (postExists || !categoryPage.isNextBthShow())
                break;

            categoryPage.clickNextPageBtn();
        }

        Assert.assertTrue(postExists, String.format("Выборка не содержит картину с названием '%s'!", title));
    }
}
