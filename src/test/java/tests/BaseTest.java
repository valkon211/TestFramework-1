package tests;

import core.DriverManager;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(AllureTestNg.class)
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;


    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome")String browser, ITestContext context) {
        DriverManager.setDriver(browser);
        driver = DriverManager.getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://artnow.ru/");

        context.setAttribute("WebDriver", driver); // Передаём WebDriver в TestNG Context
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}