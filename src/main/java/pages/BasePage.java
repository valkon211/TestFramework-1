package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/span[6]/img")
    protected WebElement favoriteButton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFavoriteBtn() {
        favoriteButton.click();
    }

    protected WebElement findElementByTitle(List<WebElement> elements, String title, By titleLocator) {
        if (elements == null || elements.isEmpty())
            return null;

        for (WebElement element: elements) {
            var elementTitle = getElementTitle(element, titleLocator);
            if (elementTitle.contains(title.toLowerCase())) {
                return element;
            }
        }

        return null;
    }

    protected String getElementTitle(WebElement element, By titleLocator) {
        if (titleLocator == null)
            return element.getText().toLowerCase();

        var titleElement = element.findElement(titleLocator);
        return titleElement.getText().toLowerCase();
    }
}

