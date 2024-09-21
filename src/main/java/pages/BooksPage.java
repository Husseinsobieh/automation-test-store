package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BooksPage {
    private WebDriver driver;
    private By bookItem = By.linkText("ALLEGIANT BY VERONICA ROTH");

    public BooksPage(WebDriver driver) {
        this.driver = driver;
    }

    public Book1Page clickBookItem(){
        driver.findElement(bookItem).click();
        return new Book1Page(driver);
    }
}
