package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CategoryPage extends PostsPage {

    @FindBy(id = "genrebox")
    private WebElement genreBox;

    @FindBy(css = "button.a_button")
    private WebElement useGenresButton;

    public CategoryPage(WebDriver driver){
        super(driver);
    }

    public void useGenres(List<String> genres) {
        for(String genre: genres) {
            var genreInputItem = findElementByTitle(genreBox.findElements(By.tagName("label")), genre, null)
                    .findElement(By.tagName("input"));
            genreInputItem.click();
        }

        useGenresButton.click();
    }
}
