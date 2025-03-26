package tests;

import data.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.HomePage;
import pages.SearchResultPage;

import java.util.Map;


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
    @Severity(SeverityLevel.CRITICAL)  // Добавлена важность теста
    @Test(dataProvider = "SearchTestsData", dataProviderClass = TestDataProvider.class)
    @Description("Проверка результатов поиска")
    public void testCheckSearchResult(Map<String, Object> testData) {
        var searchQuery = (String) testData.get("searchQuery");
        var postIndex = (Integer) testData.get("postIndex");

        enterSearchQuery(searchQuery);
        clickSearchButton();
        verifySearchResult(searchQuery, postIndex);
    }

    @Step("Ввод запроса '{searchQuery}' в поисковую строку")
    private void enterSearchQuery(String searchQuery) {
        homePage.inputSearchQuery(searchQuery);
    }

    @Step("Нажатие на кнопку поиска")
    private void clickSearchButton() {
        homePage.clickSearchBth();
    }

    @Step("Проверка, что результат под номером {postIndex+1} содержит запрос '{searchQuery}'")
    private void verifySearchResult(String searchQuery, int postIndex) {
        boolean result = searchResultPage.getPostTitleByIndex(postIndex).contains(searchQuery.toLowerCase());
        if (!result) {
            takeScreenshot();
        }
        Assert.assertTrue(result, String.format("Картина под номером %d не содержит слово '%s'!", postIndex + 1, searchQuery));
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    private byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
