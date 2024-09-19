package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoffPage {
    private WebDriver driver;
    private By logoffMessage = By.className("maintext");

    public LogoffPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoffMessage(){
        return driver.findElement(logoffMessage).getText();
    }
}
