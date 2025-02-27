package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmbroideredPaintingsPage extends BasePage {

    @FindBy(linkText = "»")
    private WebElement nextPageButton;

    @FindBy(className = "post")
    private List<WebElement> postList;

    public EmbroideredPaintingsPage(WebDriver driver){
        super(driver);
    }

    public boolean nextBthShow() {
        return nextPageButton != null;
    }

    public void clickNextPageBtn() {
        nextPageButton.click();
    }

    public boolean checkPostWithTitleExists(String title) {
        WebElement post = findElementByTitle(postList, title, By.className("ssize"));
        return post != null;
    }

}
