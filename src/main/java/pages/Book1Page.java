package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Waiter;

import java.util.function.Function;

public class Book1Page {
    private WebDriver driver;
    private By productPrice = By.className("productfilneprice");
    private By productQuantity = By.id("product_quantity");
    private By totalPrice = By.className("total-price");
    private By addToCartBtn = By.partialLinkText("Add to Cart");

    public Book1Page(WebDriver driver) {
        this.driver = driver;
    }

    public double getProductPrice(){
        String price = driver.findElement(productPrice).getText();
        return Double.parseDouble(price.replace("$", ""));
    }
    public void setProductQuantity(String quantity){
        WebElement quantityInput = driver.findElement(productQuantity);
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
    }
    public double getTotalPrice(){
        WebElement originalPrice = driver.findElement(productPrice);

        Waiter.waitFor(driver).until((Function<WebDriver, Boolean>) d ->
                !driver.findElement(totalPrice).getText().equals(originalPrice));

        String price = driver.findElement(totalPrice).getText();
        return Double.parseDouble(price.replace("$", ""));
    }
    public CartPage clickAddToCartBtn(){
        driver.findElement(addToCartBtn).click();
        return new CartPage(driver);
    }
}
