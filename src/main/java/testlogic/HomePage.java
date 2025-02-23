package testlogic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(name = "qs")
    private WebElement searchInput;

    @FindBy(css = ".search-bar.button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this); // Инициализируем элементы
    }

    public void inputSearchQuery(String query) {
        searchInput.sendKeys(query);
    }

    public void clickSearchBth() {
        searchButton.click();
    }
}
