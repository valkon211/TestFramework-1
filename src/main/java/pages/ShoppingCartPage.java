package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends BasePage {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductExists(String title, float price) {
        List<WebElement> productList = driver.findElements(By.className("c_row"));
        var product = findElementByTitle(productList, title, By.className("c_name"));

        if (product == null)
            return false;

        var priceElement = product.findElement(By.className("price"));
        var productPriceText = priceElement.getText().replaceAll("\\s+", "");
        var productPrice = Float.parseFloat(productPriceText.substring(0, productPriceText.indexOf("р")));

        return price == productPrice;
    }
}
