package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(className = "post")
    private List<WebElement> searchResults;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchResultElementTitle(int index) {
        WebElement post = searchResults.get(index);
        return getElementTitle(post, By.className("ssize"));
    }
}
