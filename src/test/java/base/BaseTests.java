package base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BaseTests {
    private WebDriver driver;
    protected HomePage homePage;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptions());
        goHome();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }
    @BeforeMethod
    public void goHome(){
        driver.get("https://automationteststore.com/");
    }
    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
//        options.addArguments("--headless");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return options;
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    @DataProvider(name = "loginDataProvider")
    public Object[] loginData() throws IOException {
        HashMap<String, String> map1 = new ObjectMapper().
                readValue(new File(System.getProperty("user.dir")+"/resources/data-files/loginData.json")
                        , new TypeReference<HashMap<String, String>>() {});

        return new Object[]{map1};
    }
    @DataProvider(name = "registerDataProvider")
    public Object[] registerData() throws IOException {
        HashMap<String, String> map1 = new ObjectMapper().
                readValue(new File(System.getProperty("user.dir")+"/resources/data-files/registeringData.json")
                        , new TypeReference<HashMap<String, String>>() {});

        return new Object[]{map1};
    }
    protected void login(){
        LoginPage loginPage = homePage.clickLoginBtn();
        loginPage.setLoginNameInput("hussein.sobieh");
        loginPage.setPasswordInput("Test@123");
        AccountPage accountPage = loginPage.clickLoginBtn();
        goHome();
    }
    protected void logOff(){
        goHome();
        homePage.clickLogoff();
    }
}
