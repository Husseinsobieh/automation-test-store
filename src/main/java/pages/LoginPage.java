package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By loginNameInput = By.id("loginFrm_loginname");
    private By passwordInput = By.id("loginFrm_password");
    private By loginBtn = By.cssSelector("button[title='Login']");
    private By registerPageBtn = By.cssSelector("button[title='Continue']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void setLoginNameInput(String loginName){
        driver.findElement(loginNameInput).sendKeys(loginName);
    }
    public void setPasswordInput(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }
    public AccountPage clickLoginBtn(){
        driver.findElement(loginBtn).click();
        return new AccountPage(driver);
    }
    public RegisterPage clickRegisterPageBtn(){
        driver.findElement(registerPageBtn).click();
        return new RegisterPage(driver);
    }
}
