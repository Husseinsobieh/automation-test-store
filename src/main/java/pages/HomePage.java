package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By loginBtn = By.linkText("Login or register");
    private By logoffBtn = By.partialLinkText("Logoff");

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

    public void scrollInto (){

    }
}
