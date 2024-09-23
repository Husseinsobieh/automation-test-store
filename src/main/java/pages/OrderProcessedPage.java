package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Waiter;

public class OrderProcessedPage {
    private WebDriver driver;
    private By orderProcessedText = By.xpath("//span[@class='maintext']" +
            "/text()[contains(.,\"Your Order Has Been Processed!\")]/..");

    public OrderProcessedPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getOrderProcessedText(){
        Waiter.waitFor(driver).until(ExpectedConditions.presenceOfElementLocated(orderProcessedText));
        return driver.findElement(orderProcessedText).getText();
    }
}
