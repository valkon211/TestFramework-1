package tests;

import data.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.FavoritesPage;
import pages.HomePage;

import java.util.Map;

@Epic("Тестирование раздела 'Избранное'")
@Feature("Проверка содержания раздела 'Избранное'")
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
    @Story("Проверка наличия картины в разделе 'Избранное'")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка наличия выбранной картины в разделе 'Избранное'")
    public void testAddToFavorite(Map<String, Object> testData) {
        var categoryTitle = (String) testData.get("categoryName");
        var index = (Integer) testData.get("index");

        clickMenuItemByTitle(categoryTitle);
        addPostToFavorite(index);

        var postTitle = getPostTitle(index);

        clickFavoriteButton();
        var postExists = isPostWithTitleExists(postTitle);

        Assert.assertTrue(postExists, String.format("В 'Избраное' отсутсвует картина с названием '%s'!", postTitle));
    }

    @Step("Нажатие на элемент меню с названием: {categoryName}")
    private void clickMenuItemByTitle(String categoryName) {
        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(categoryName);
    }

    @Step("Добавление в 'Избранное' картины под номером: {index+1}")
    private void addPostToFavorite(int index) {
        categoryPage.addPostToFavorite(index);
    }

    @Step("Взятие название картины под номером: {index+1}")
    private String getPostTitle(int index) {
        return categoryPage.getPostTitleByIndex(index);
    }

    @Step("Переход в раздел 'Избранное'")
    private void clickFavoriteButton() {
        categoryPage.clickFavoriteBtn();
    }

    @Step("Проверка, что картина с названием {postTitle} есть в разделе 'Избранное'")
    private boolean isPostWithTitleExists(String postTitle) {
        return favoritesPage.isPostWithTitleExists(postTitle);
    }
}
