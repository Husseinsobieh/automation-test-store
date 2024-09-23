package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;
    private By confirmOrderBtn = By.id("checkout_btn");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderProcessedPage clickConfirmOrderBtn(){
        driver.findElement(confirmOrderBtn).click();
        return new OrderProcessedPage(driver);
    }
}
