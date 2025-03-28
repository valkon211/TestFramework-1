package tests;

import data.TestDataProvider;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.ShoppingCartPage;

import java.util.Map;

@Epic("Тестирование раздела 'Корзина'")
@Feature("Проверка содержания раздела 'Корзина'")
public class ShoppingCartTest extends BaseTest {
    private HomePage homePage;
    private CategoryPage categoryPage;
    private ShoppingCartPage shoppingCartPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @Test(dataProvider = "ShoppingCartTestsData", dataProviderClass = TestDataProvider.class)
    @Story("Проверка наличия товара в разделе 'Карзина'")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка наличия выбранного товара в разделе 'Карзина'")
    public void testAddToShoppingCart(Map<String, Object> testData) {
        var categoryTitle = (String) testData.get("categoryName");
        var postIndex = (Integer) testData.get("postIndex");

        clickMenuItemByTitle(categoryTitle);
        addToShoppingCart(postIndex);

        var postTitle = getPostTitleByIndex(postIndex);
        var postPrice = getPostPriceByIndex(postIndex);

        goToShoppingCartInModal();

        var productExists = isProductExists(postTitle, postPrice);

        Assert.assertTrue(productExists, String.format("В корзине нет картины с названием '%s'!", postTitle));
    }

    @Step("Нажатие на элемент меню с названием: {categoryName}")
    private void clickMenuItemByTitle(String categoryName) {
        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(categoryName);
    }

    @Step("Добавление в 'Корзину' картины под номером: {index+1}")
    private void addToShoppingCart(int index) {
        categoryPage.addToShoppingCart(index);
    }

    @Step("Получение название картины под номером: {index+1}")
    private String getPostTitleByIndex(int index) {
        return categoryPage.getPostTitleByIndex(index);
    }

    @Step("Получение цены картины под номером: {index+1}")
    private float getPostPriceByIndex(int index) {
        return categoryPage.getPostPriceByIndex(index);
    }

    @Step("Переход в 'Корзину' через модальное окно")
    private void goToShoppingCartInModal() {
        categoryPage.goToShoppingCartInModal();
    }

    @Step("Проверка наличия в 'Корзине' картины с названием: {title} и ценой: {price}")
    private boolean isProductExists(String title, float price) {
        return shoppingCartPage.isProductExists(title, price);
    }
}
