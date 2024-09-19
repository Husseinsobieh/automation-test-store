package register;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountCreatedPage;
import pages.RegisterPage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RegisterTests extends BaseTests {
    @Test(dataProvider = "registerDataProvider")
    public void testValidRegister(HashMap<String, String> registerData) throws InterruptedException {
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
    }
}
