package tests;

import data.TestDataProvider;
import io.qameta.allure.*;
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

        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(categoryTitle);
        categoryPage.addToShoppingCart(postIndex);

        var postTitle = categoryPage.getPostTitleByIndex(postIndex);
        var postPrice = categoryPage.getPostPriceByIndex(postIndex);

        categoryPage.goToShoppingCartInModal();
        categoryPage.goToShoppingCart();

        var productExists = shoppingCartPage.isProductExists(postTitle, postPrice);

        Assert.assertTrue(productExists, String.format("В корзине нет картины с названием '%s'!", postTitle));
    }

}
