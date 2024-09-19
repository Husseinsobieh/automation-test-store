package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    private WebDriver driver;
    private By welcomeMessage = By.id("customer_menu_top");
    private By logoffBtn = By.partialLinkText("Logoff");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getWelcomeMessage(){
        return driver.findElement(welcomeMessage).getText();
    }
    public LogoffPage clickLogoffBtn(){
        driver.findElement(logoffBtn).click();
        return new LogoffPage(driver);
    }
}
