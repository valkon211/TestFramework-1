package reporting;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver"); // Получаем WebDriver

        if (driver != null) {
            saveScreenshot(driver);
        }
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png", fileExtension = "png")
    public byte[] saveScreenshot(WebDriver driver) {
        return  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}