package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EmbroideredPaintingsPage;
import pages.HomePage;

public class EmbroideredPaintingsTests extends BaseTest {
    private HomePage homePage;
    private EmbroideredPaintingsPage epPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        epPage = new EmbroideredPaintingsPage(driver);
    }

    @Test
    public void testCheckCategoryPostExists() {
        String itemTitle = "Вышитые картины";
        String title = "Трамвайный путь";
        boolean postExists = false;

        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(itemTitle);

        do {
            postExists = epPage.checkPostWithTitleExists(title);
            epPage.clickNextPageBtn();
        }
        while(!postExists && epPage.nextBthShow());

        Assert.assertTrue(postExists, String.format("Выборка не содержит картину с названием '%s'!", title));
    }
}
