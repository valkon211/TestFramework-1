package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class TestDataProvider {
    private static final String DATA_PATH = "src/test/resources/test-data/";

    @DataProvider(name = "CategoryTestsData")
    public static Object[][] loadCategoryTestsData(Method method) {
        return new Object[][]{loadTestData("category-tests-data.json", method.getName())};
    }

    @DataProvider(name = "FavoritesTestsData")
    public static Object[][] loadFavoritesTestsData(Method method) {
        return new Object[][]{loadTestData("favorites-tests-data.json", method.getName())};
    }

    @DataProvider(name = "PicturePageTestsData")
    public static Object[][] loadPicturePageTestsData(Method method) {
        return new Object[][]{loadTestData("picture-page-tests-data.json", method.getName())};
    }

    @DataProvider(name = "SearchTestsData")
    public static Object[][] loadSearchTestsData(Method method) {
        return new Object[][]{loadTestData("search-tests-data.json", method.getName())};
    }

    @DataProvider(name = "ShoppingCartTestsData")
    public static Object[][] loadShoppingCartTestsData(Method method) {
        return new Object[][]{loadTestData("shopping-cart-tests-data.json", method.getName())};
    }

    private static Object[] loadTestData(String fileName, String testMethod) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(DATA_PATH + fileName);
            Map<String, Map<String, Object>> testData = objectMapper.readValue(file, Map.class);
            Map<String, Object> testMethodData = testData.get(testMethod);

            if (testMethodData != null) {
                return new Object[]{testMethodData};
            } else {
                throw new RuntimeException("Данные для метода " + testMethod + " не найдены.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки тестовых данных из " + fileName, e);
        }
    }
}
