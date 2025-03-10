package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class PostsPage extends BasePage {
    @FindBy(className = "post")
    protected List<WebElement> postList;

    @FindBy(linkText = "»")
    protected WebElement nextPageButton;

    @FindBy(className = "ok-button")
    protected WebElement goToShoppingCart;

    public PostsPage(WebDriver driver) {
        super(driver);
    }

    public void clickNextPageBtn() {
        nextPageButton.click();
    }

    public void clickPostByTitle(String title) {
        var postLink = getPostByTitle(title).findElement(By.tagName("a"));
        postLink.click();
    }

    public boolean isPostWithTitleExists(String title) {
        return getPostByTitle(title) != null;
    }

    public String getPostTitleByIndex(int index) {
        var post = getPostByIndex(index);

        if (post == null)
            return "";

        var titleWithAuthor = getElementTitle(post, By.className("ssize"));

        return titleWithAuthor.substring(titleWithAuthor.indexOf("\n") + 1);
    }

    public void addPostToFavorite(int index) {
        var post = getPostByIndex(index);

        if (post == null)
            return;

        var heart = post.findElement(By.className("heart"));
        heart.click();
    }

    public void addToShoppingCart(int index) {
        var post = getPostByIndex(index);

        if (post != null) {
            var shoppingCartBtn = post.findElement(By.className("oclick"));
            shoppingCartBtn.click();
        }
    }

    public void goToShoppingCartInModal() {
        goToShoppingCart.click();
    }

    protected WebElement getPostByIndex(int index) {
        if (postList == null || postList.isEmpty() || postList.size() <= index)
            return null;

        return postList.get(index);
    }

    protected WebElement getPostByTitle(String title) {
        return findElementByTitle(postList, title, By.className("ssize"));
    }
}
