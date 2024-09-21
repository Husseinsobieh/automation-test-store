package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private By loginBtn = By.linkText("Login or register");
    private By logoffBtn = By.partialLinkText("Logoff");
    private By productsAddToCart = By.className("productcart");
    private By productsAddedToCart = By.xpath("//a[contains(., 'Items')]/span");
    private By cartBtn = By.className("topcart");
    private By booksBtn = By.partialLinkText("BOOKS");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    public LoginPage clickLoginBtn(){
        driver.findElement(loginBtn).click();
        return new LoginPage(driver);
    }
    public void clickLogoff(){
        driver.findElement(logoffBtn).click();
    }
    public void addToCartProducts(){
        List<WebElement> productsAddToCartBtn = driver.findElements(productsAddToCart);
        productsAddToCartBtn.get(1).click();
    }
    public int getProductsAddedToCart(){
        return Integer.parseInt(driver.findElement(productsAddedToCart).getText());
    }
    public CartPage clickCartBtn(){
        driver.findElement(cartBtn).click();
        return new CartPage(driver);
    }
    public BooksPage clickBooksBtn(){
        driver.findElement(booksBtn).click();
        return new BooksPage(driver);
    }
}
