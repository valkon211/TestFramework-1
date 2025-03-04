package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.PicturePage;

import java.util.List;

public class PicturePageTests extends BaseTest{
    private HomePage homePage;
    private CategoryPage categoryPage;
    private PicturePage picturePage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        picturePage = new PicturePage(driver);
    }

    @Test
    public void testCheckPictureStyleTest() {
        String itemTitle = "Вышитые картины";
        var genre = "Городской пейзаж";
        var title = "Трамвайный путь";
        var style = "Реализм";

        var postExists = false;

        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(itemTitle);

        categoryPage.useGenres(List.of(genre.toLowerCase()));

        while(true) {
            postExists = categoryPage.isPostWithTitleExists(title);

            if (postExists || !categoryPage.isNextBthShow())
                break;

            categoryPage.clickNextPageBtn();
        }

        Assert.assertTrue(postExists, String.format("Выборка не содержит картину с названием '%s'!", title));

        categoryPage.clickPostByTitle(title);
        var styleName = picturePage.getStyleName();

        Assert.assertTrue(styleName.contains(
                style.toLowerCase()), String.format("Картина не содержит стиль '%s'!", style));
    }
}
