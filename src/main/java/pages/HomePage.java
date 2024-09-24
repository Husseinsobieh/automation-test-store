package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Waiter;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private By loginBtn = By.linkText("Login or register");
    private By logoffBtn = By.partialLinkText("Logoff");
    private By wishlistBtn = By.xpath("//i[contains(@class, 'fa-star')]");
    private By productsAddToCart = By.className("productcart");
    private By productsAddedToCart = By.xpath("//a[contains(., 'Items')]/span");
    private By cartBtn = By.className("topcart");
    private By booksBtn = By.partialLinkText("BOOKS");
    private By accountDropdown = By.id("customer_menu_top");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    public void hoverOverDropdown(WebElement dropdown, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdown).build().perform();
        actions.moveToElement(element).build().perform();
    }
    public LoginPage clickLoginBtn(){
        driver.findElement(loginBtn).click();
        return new LoginPage(driver);
    }
    public void clickLogoff(){
        driver.findElement(logoffBtn).click();
    }
    public WishlistPage clickWishlistBtn(){
        hoverOverDropdown(driver.findElement(accountDropdown), driver.findElement(wishlistBtn));
        driver.findElement(wishlistBtn).click();
        return new WishlistPage(driver);
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
