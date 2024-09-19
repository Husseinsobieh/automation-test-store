package login;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.LoginPage;

import java.util.HashMap;

public class LoginTests extends BaseTests {
    @Test(dataProvider = "loginDataProvider")
    public void testSuccessfulLogin(HashMap<String, String> loginData){
        LoginPage loginPage = homePage.clickLoginBtn();
        loginPage.setLoginNameInput(loginData.get("Login Name"));
        loginPage.setPasswordInput(loginData.get("Password"));
        AccountPage accountPage = loginPage.clickLoginBtn();
        Assert.assertTrue(accountPage.getWelcomeMessage().contains("Welcome back"),"Invalid login");
    }
}
