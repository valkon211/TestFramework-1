package tests;

import core.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    @Parameters("browser")
    @Step("Инициализация WebDriver для браузера {browser}")
    public void setUp(String browser) {
        DriverManager.setDriver(browser);
        driver = DriverManager.getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://artnow.ru/");
    }

    @AfterMethod
    @Step("Закрытие WebDriver")
    public void tearDown() {
        DriverManager.quitDriver();
    }
}