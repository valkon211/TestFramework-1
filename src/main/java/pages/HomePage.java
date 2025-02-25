package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(name = "qs")
    private WebElement searchInput;

    @FindBy(css = "button.control.scLarge")
    private WebElement searchButton;

    @FindBy(className = "fa-bars")
    private WebElement menuControl;

    @FindBy(css = "li.menu-group.gids")
    private WebElement menuGroupGids;

    @FindBy(className = "menu-extra-item")
    private List<WebElement> menuExtraItems;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void inputSearchQuery(String query) {
        searchInput.sendKeys(query);
    }

    public void clickSearchBth() {
        searchButton.click();
    }

    public void clickMenuControl() {
        menuControl.click();
    }

    public void clickMenuGroupGids() {
        menuGroupGids.click();
    }
}
