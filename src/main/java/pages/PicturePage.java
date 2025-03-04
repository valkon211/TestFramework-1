package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PicturePage extends BasePage {

    @FindBy(linkText = "Стиль: ")
    private WebElement styleElement;

    public PicturePage(WebDriver driver){
        super(driver);
    }

    public String getStyleName(){
        return "";
//        return styleElement
//                .findElement(By.xpath(".."))
//                .getText()
//                .toLowerCase()
//                .replace("Стиль: ", "");
    }
}
