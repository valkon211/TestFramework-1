package data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestDataManager {
    private static Map<String, Object> testData;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            testData = objectMapper.readValue(new File("src/test/resources/testData.json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки тестовых данных", e);
        }
    }

    public static Object getData(String key) {
        return testData.get(key);
    }
}
