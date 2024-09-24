package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistPage {
    private WebDriver driver;
    private By removeBtn = By.className("btn-remove");
    private By itemName = By.linkText("Allegiant by Veronica Roth");

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRemoveBtn(){
        driver.findElement(removeBtn).click();
    }
    public String getItemName(){
        return driver.findElement(itemName).getText();
    }
}
