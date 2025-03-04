package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CategoryPage extends BasePage {

    @FindBy(id = "genrebox")
    private WebElement genreBox;

    @FindBy(linkText = "»")
    private WebElement nextPageButton;

    @FindBy(css = "button.a_button")
    private WebElement useGenresButton;

    @FindBy(className = "post")
    private List<WebElement> postList;

    public CategoryPage(WebDriver driver){
        super(driver);
    }

    public boolean isNextBthShow() {
        return nextPageButton != null;
    }

    public void clickNextPageBtn() {
        nextPageButton.click();
    }

    public void clickPostByTitle(String title) {
        var postLink = getPostByTitle(title).findElement(By.tagName("a"));
        postLink.click();
    }

    public void useGenres(List<String> genres) {
        for(String genre: genres) {
            var genreInputItem = findElementByTitle(genreBox.findElements(By.tagName("label")), genre, null)
                    .findElement(By.tagName("input"));
            genreInputItem.click();
        }

        useGenresButton.click();
    }

    public boolean isPostWithTitleExists(String title) {
        return getPostByTitle(title) != null;
    }

    private WebElement getPostByTitle(String title) {
        return findElementByTitle(postList, title, By.className("ssize"));
    }
}
