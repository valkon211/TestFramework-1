package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement findElementByTitle(List<WebElement> elements, String title, By titleLocator) {
        if (!elements.isEmpty()) {
            for (WebElement element: elements) {
                String elementTitle = getElementTitle(element, titleLocator);
                if (elementTitle.contains(title.toLowerCase())) {
                    return element;
                }
            }
        }

        return null;
    }

    protected String getElementTitle(WebElement element, By titleLocator) {
        if (titleLocator == null)
            return element.getText().toLowerCase();

        WebElement titleElement = element.findElement(titleLocator);
        return titleElement.getText().toLowerCase();
    }
}

