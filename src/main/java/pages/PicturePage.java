package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PicturePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"main_container\"]/div[3]/div[2]/div[5]/a")
    private WebElement styleElement;

    public PicturePage(WebDriver driver){
        super(driver);
    }

    public String getStyleName(){
        return styleElement
                .findElement(By.xpath(".."))
                .getText()
                .toLowerCase()
                .replace("стиль: ", "");
    }
}
