package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.ShoppingCartPage;

public class ShoppingCartTests extends BaseTest {
    private HomePage homePage;
    private CategoryPage categoryPage;
    private ShoppingCartPage shoppingCartPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @Test
    public void testAddToShoppingCart() {
        var categoryTitle = "Ювелирное искусство";

        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(categoryTitle);
        categoryPage.addToShoppingCart(0);
        categoryPage.goToShoppingCartInModal();
        categoryPage.goToShoppingCart();

    }

}
