package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage {
    private WebDriver driver;
    private By accountCreatedMessage = By.className("heading1");

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getAccountCreatedMessage(){
        return driver.findElement(accountCreatedMessage).getText();
    }
}
