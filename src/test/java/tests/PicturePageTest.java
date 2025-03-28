package tests;

import data.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.PicturePage;

import java.util.List;
import java.util.Map;

@Epic("Тестирование страницы описания картины")
@Feature("Проверка содержания страницы описания картины")
public class PicturePageTest extends BaseTest{
    private HomePage homePage;
    private CategoryPage categoryPage;
    private PicturePage picturePage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        picturePage = new PicturePage(driver);
    }

    @Test(dataProvider = "PicturePageTestsData", dataProviderClass = TestDataProvider.class)
    @Story("Проверка характеристик картины")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка наличия указанного стиля картины")
    public void testCheckPictureStyleTest(Map<String, Object> testData) {
        var categoryName = (String) testData.get("categoryName");
        var genre = (String) testData.get("postGenre");
        var title = (String) testData.get("postTitle");
        var style =(String) testData.get("postStyle");

        var postExists = false;

        clickMenuItemByTitle(categoryName);

        useGenres(List.of(genre.toLowerCase()));

        while(true) {
            postExists = isPostWithTitleExists(title);

            if (postExists || !categoryPage.isNextBthShow())
                break;

            clickNextPageButton();
        }

        Assert.assertTrue(postExists, String.format("Выборка не содержит картину с названием '%s'!", title));

        clickPostByTitle(title);
        var styleName = getPostStyleName();

        Assert.assertTrue(styleName.contains(
                style.toLowerCase()), String.format("Картина не содержит стиль '%s'!", style));
    }

    @Step("Нажатие на элемент меню с названием: {categoryName}")
    private void clickMenuItemByTitle(String categoryName) {
        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(categoryName);
    }

    @Step("Выбор жанров")
    private void useGenres(List<String> genres) {
        categoryPage.useGenres(genres);
    }

    @Step("Проверка, что картина с названием {postTitle} есть в разделе 'Избранное'")
    private boolean isPostWithTitleExists(String postTitle) {
        return categoryPage.isPostWithTitleExists(postTitle);
    }

    @Step("Нажатие на кнопку 'Следующая страница'")
    private void clickNextPageButton() {
        categoryPage.clickNextPageBtn();
    }

    @Step("Нажатие на картину с названием: {postTitle}")
    private void clickPostByTitle(String postTitle) {
        categoryPage.clickPostByTitle(postTitle);
    }

    @Step("Получение значение стиля картины")
    private String getPostStyleName() {
        return picturePage.getStyleName();
    }
}
