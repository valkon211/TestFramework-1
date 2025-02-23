package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testlogic.HomePage;

import java.util.List;

public class SearchTests extends BaseTest {
    private HomePage homePage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
    }

    @Test
    public void testSearchForGiraffe() {
        String searchQuery = "Жираф";
        homePage.inputSearchQuery(searchQuery);
        homePage.clickSearchBth();

        // Получаем результаты поиска
        List<WebElement> searchResults = driver.findElements(By.cssSelector(".search-result-item"));

        // Проверяем, что есть хотя бы один результат
        Assert.assertFalse(searchResults.isEmpty(), "Результаты поиска отсутствуют!");

        // Проверяем, что название первой картины содержит слово "Жираф"
        String firstResultTitle = searchResults.get(0).getText().toLowerCase();
        Assert.assertTrue(firstResultTitle.contains("жираф"), "Первая картина не содержит слово 'Жираф'!");
    }
}
