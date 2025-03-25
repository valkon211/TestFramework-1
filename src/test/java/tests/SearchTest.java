package tests;

import data.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;
import reporting.AllureListener;

import java.util.Map;

@Listeners(AllureListener.class)
public class SearchTest extends BaseTest {
    private HomePage homePage;
    private SearchResultPage searchResultPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Epic("Main Epic")
    @Feature("Feature 1")
    @Story("Story 1")
    @Test(dataProvider = "SearchTestsData", dataProviderClass = TestDataProvider.class)
    @Description("Проверка результатов поиска")
    public void testCheckSearchResult(Map<String, Object> testData) {
        var searchQuery = (String) testData.get("searchQuery");
        var postIndex = (Integer) testData.get("postIndex");

        Allure.step("Ввод запроса {searchQuery} в поисковую строку", step -> {
            homePage.inputSearchQuery(searchQuery);
        });
        Allure.step("Нажатие на кнопку поиска", step -> {
            homePage.clickSearchBth();
        });

        Allure.step("Проверка результата поиска под номером {postIndex+1}", step -> {
            Assert.assertTrue(searchResultPage.getPostTitleByIndex(postIndex).contains(
                    searchQuery.toLowerCase()), String.format("Первая картина не содержит слово '%s'!", searchQuery));
        });
    }
}
