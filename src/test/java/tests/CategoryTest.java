package tests;

import data.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;

import java.util.Map;

@Epic("Тестирование категорий")
@Feature("Проверка наличия картин в категориях")
public class CategoryTest extends BaseTest {
    private HomePage homePage;
    private CategoryPage categoryPage;

    @BeforeMethod
    @Description("Инициализация страниц перед каждым тестом")
    public void setUpPages() {
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
    }

    @Test(dataProvider = "CategoryTestsData", dataProviderClass = TestDataProvider.class)
    @Story("Проверка наличия картины в категории")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка наличия картины с указанным заголовком в категории")
    public void testCheckCategoryPostExists(Map<String, Object> testData) {
        var postTitle = (String) testData.get("postTitle");
        var categoryName = (String) testData.get("categoryName");
        var postExists = false;

        clickMenuGroupGids();
        clickMenuItemByTitle(categoryName);

        while (true) {
            postExists = isPostWithTitleExists(postTitle);

            if (postExists || !isNextBtnShow()) {
                break;
            }

            clickNextPageBtn();
        }

        Assert.assertTrue(postExists, String.format("Выборка не содержит картину с названием '%s'!", postTitle));
    }

    @Step("Нажатие на меню групп GIDS")
    private void clickMenuGroupGids() {
        homePage.clickMenuGroupGids();
    }

    @Step("Нажатие на элемент меню с названием: {categoryName}")
    private void clickMenuItemByTitle(String categoryName) {
        homePage.clickMenuItemByTitle(categoryName);
    }

    @Step("Проверка наличия поста с заголовком: {postTitle}")
    private boolean isPostWithTitleExists(String postTitle) {
        return categoryPage.isPostWithTitleExists(postTitle);
    }

    @Step("Проверка видимости кнопки 'Следующая страница'")
    private boolean isNextBtnShow() {
        return categoryPage.isNextBthShow();
    }

    @Step("Нажатие на кнопку 'Следующая страница'")
    private void clickNextPageBtn() {
        categoryPage.clickNextPageBtn();
    }
}
