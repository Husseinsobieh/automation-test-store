package register;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountCreatedPage;
import pages.RegisterPage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RegisterTests extends BaseTests {
    @Description("given I'm on the login page, when I enter valid login data and click continue, " +
            "then I am successfully logged in")
    @Story("register")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "registerDataProvider")
    public void testValidRegister(HashMap<String, String> registerData) {
        String date = new SimpleDateFormat("ddMyyyyhhmmss").format(new Date());
        String email = registerData.get("E-Mail") + date + "@gmail.com";
        String loginName = registerData.get("Login Name") + date;
        String password = registerData.get("Password");

        RegisterPage registerPage = homePage.clickLoginBtn().clickRegisterPageBtn();

        registerPage.setFirstnameInput(registerData.get("First Name"));
        registerPage.setLastnameInput(registerData.get("Last Name"));
        registerPage.setEmailInput(email);
        registerPage.setAddress1Input(registerData.get("Address 1"));
        registerPage.setCityInput(registerData.get("City"));
        registerPage.setCountryInput(registerData.get("Country"));
        registerPage.setRegionInput(registerData.get("Region"));
        registerPage.setPostcodeInput(registerData.get("ZIP Code"));
        registerPage.setLoginNameInput(loginName);
        registerPage.setPasswordInput(password);
        registerPage.setConfirmPasswordInput(registerData.get("Password Confirm"));
        registerPage.clickToAgreePrivacyPolicy();

        try {
            registerPage.storeRegisteredData(loginName, password);
        } catch (IOException err){
            System.out.println(err);
        }

        AccountCreatedPage accountCreatedPage = registerPage.clickRegisterBtn();

        Assert.assertEquals(accountCreatedPage.getAccountCreatedMessage(),
                "YOUR ACCOUNT HAS BEEN CREATED!", "Invalid register");
        logOff();
    }
    @Description("given I'm on the login page, when I enter invalid login data and click continue, " +
            "then I should get an error message")
    @Story("register")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void testInvalidRegister(){
        RegisterPage registerPage = homePage.clickLoginBtn().clickRegisterPageBtn();
        registerPage.clickToAgreePrivacyPolicy();
        registerPage.clickRegisterBtn();
        Assert.assertTrue(!registerPage.getErrorAlert().isEmpty(), "Invalid register evaluation");
    }
}
