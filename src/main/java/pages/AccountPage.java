package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    private WebDriver driver;
    private By welcomeMessage = By.id("customer_menu_top");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getWelcomeMessage(){
        return driver.findElement(welcomeMessage).getText();
    }
}
