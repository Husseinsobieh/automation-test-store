package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By deleteItemBtns = By.className("fa-trash-o");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void deleteAllItems() {
        List<WebElement> deleteBtns = driver.findElements(deleteItemBtns);
        deleteBtns.get(0).click();
    }
}
