package tests;

import data.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;

import java.util.Map;

public class CategoryTests extends BaseTest {
    private HomePage homePage;
    private CategoryPage categoryPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
    }

    @Test(dataProvider = "CategoryTestsData", dataProviderClass = TestDataProvider.class)
    public void testCheckCategoryPostExists(Map<String, Object> testData) {
        var postTitle = (String) testData.get("postTitle");
        var categoryName = (String) testData.get("categoryName");
        var postExists = false;

        homePage.clickMenuGroupGids();
        homePage.clickMenuItemByTitle(categoryName);

        while(true){
            postExists = categoryPage.isPostWithTitleExists(postTitle);

            if (postExists || !categoryPage.isNextBthShow())
                break;

            categoryPage.clickNextPageBtn();
        }

        Assert.assertTrue(postExists, String.format("Выборка не содержит картину с названием '%s'!", postTitle));
    }
}
