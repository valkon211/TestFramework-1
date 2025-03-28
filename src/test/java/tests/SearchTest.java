package tests;

import data.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.HomePage;
import pages.SearchResultPage;

import java.util.Map;

@Epic("Тестирование поиска")
@Feature("Проверка результатов поиска")
public class SearchTest extends BaseTest {
    private HomePage homePage;
    private SearchResultPage searchResultPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @Test(dataProvider = "SearchTestsData", dataProviderClass = TestDataProvider.class)
    @Story("Проверка результата поиска")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка результатов поиска на определённый запрос")
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
