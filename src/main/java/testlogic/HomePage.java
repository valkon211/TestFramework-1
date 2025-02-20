package testlogic;

import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Вышитые картины']")
    private WebElement embroideredPaintingsCategory;

    public void navigateToCategory() {
        embroideredPaintingsCategory.click();
    }
}
