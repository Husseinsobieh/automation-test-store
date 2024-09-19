package login;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.LoginPage;
import pages.LogoffPage;

import java.util.HashMap;

public class LoginTests extends BaseTests {
    @Description("given I'm on the signup page, " +
            "when I enter valid data and click continue, then I'm successfully registered")
    @Story("login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "loginDataProvider")
    public void testValidLogin(HashMap<String, String> loginData){
        LoginPage loginPage = homePage.clickLoginBtn();
        loginPage.setLoginNameInput(loginData.get("Login Name"));
        loginPage.setPasswordInput(loginData.get("Password"));
        AccountPage accountPage = loginPage.clickLoginBtn();
        Assert.assertTrue(accountPage.getWelcomeMessage().contains("Welcome back"),"Invalid login");
    }

    @Description("given I'm on the signup page, " +
            "when I enter invalid data and click continue, then I should get an error message")
    @Story("login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "loginDataProvider")
    public void testInvalidLogin(HashMap<String, String> loginData){
        LoginPage loginPage = homePage.clickLoginBtn();
        loginPage.setLoginNameInput("dddelxm.xs");
        loginPage.setPasswordInput(loginData.get("Password"));
        loginPage.clickLoginBtn();
        Assert.assertTrue(loginPage.getErrorAlert().endsWith("Error: Incorrect login or password provided."),
                 "invalid login evaluation");
    }

    @Description("given I'm logged in, when I click on logoff button, then I should logoff successfully")
    @Story("login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "loginDataProvider")
    public void testLogoff(HashMap<String, String> loginData){
        LoginPage loginPage = homePage.clickLoginBtn();
        loginPage.setLoginNameInput(loginData.get("Login Name"));
        loginPage.setPasswordInput(loginData.get("Password"));
        AccountPage accountPage = loginPage.clickLoginBtn();
        LogoffPage logoffPage = accountPage.clickLogoffBtn();
        Assert.assertTrue(logoffPage.getLogoffMessage().endsWith("ACCOUNT LOGOUT"), "incomplete logoff");
    }
}
