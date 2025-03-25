package reporting;

import io.qameta.allure.*;
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
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");

        if (driver != null) {
            System.out.println("[Allure] Saving screenshot for failed test: " + result.getName());
            try {
                saveScreenshot(driver);
            } catch (Exception e) {
                System.err.println("[Allure] Error while taking screenshot: " + e.getMessage());
            }
        } else {
            System.err.println("[Allure] WebDriver is null! Screenshot not taken.");
        }
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png", fileExtension = "png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}