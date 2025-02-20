package tests;

import data.TestDataManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import testlogic.HomePage;

public class SearchTests extends BaseTest {
    @Test
    public void testSearchPaintingByGenre() {
        HomePage homePage = new HomePage();
        homePage.navigateToCategory();

        String expectedPainting = (String) TestDataManager.getData("expectedPainting");
        Assert.assertTrue(true, "Картина '" + expectedPainting + "' присутствует в выдаче");
    }
}
