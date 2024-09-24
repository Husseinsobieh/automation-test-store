package e2e;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class E2ETests extends BaseTests {
    private String date = new SimpleDateFormat("ddMyyyyhhmmss").format(new Date());
    private String email = "hussein" + date + "@gmail.com";
    private String loginName = "hussein" + date;
    private String password = "Test@123";

    private String itemName;

    @Description("given I'm use a valid username and password, when I login and make a purchase, then a can successfully complete the purchase successfully")
    @Story("endtoend")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 1)
    public void testsCompleteFunctionality() throws InterruptedException {
        register(email, loginName, password);
        login(loginName, password);
        purchaseItem();
        testWishlist(itemName);
        logOff();
    }

    public void register(String email, String loginName, String password) {
        RegisterPage registerPage = homePage.clickLoginBtn().clickRegisterPageBtn();

        registerPage.setFirstnameInput("Hussein");
        registerPage.setLastnameInput("Sobieh");
        registerPage.setEmailInput(email);
        registerPage.setAddress1Input("cairo");
        registerPage.setCityInput("cairo");
        registerPage.setCountryInput("Egypt");
        registerPage.setRegionInput("Al Qahirah");
        registerPage.setPostcodeInput("11865");
        registerPage.setLoginNameInput(loginName);
        registerPage.setPasswordInput(password);
        registerPage.setConfirmPasswordInput(password);
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
    public void login(String loginName, String password){
        LoginPage loginPage = homePage.clickLoginBtn();
        loginPage.setLoginNameInput(loginName);
        loginPage.setPasswordInput(password);
        AccountPage accountPage = loginPage.clickLoginBtn();
        Assert.assertTrue(accountPage.getWelcomeMessage().contains("Welcome back"),"Invalid login");
    }
    public void purchaseItem() throws InterruptedException {
        String quantity = "5";
        Book1Page book1Page = homePage.clickBooksBtn().clickBookItem();
        book1Page.setProductQuantity(quantity);
        Thread.sleep(1000);
        double totalPrice = Double.parseDouble(quantity) * book1Page.getProductPrice();
        double newTotalPrice = book1Page.getTotalPrice();
        Assert.assertEquals(newTotalPrice, totalPrice, "invalid price evaluation");
        book1Page.clickWishlistBtn();
        itemName = book1Page.getItemName();
        CartPage cartPage = book1Page.clickAddToCartBtn();
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();
        OrderProcessedPage orderProcessedPage = checkoutPage.clickConfirmOrderBtn();
        Assert.assertEquals(orderProcessedPage.getOrderProcessedText(),"YOUR ORDER HAS BEEN PROCESSED!",
                "checkout invalid");
        goHome();
    }
    public void testWishlist(String itemName){
        WishlistPage wishlistPage = homePage.clickWishlistBtn();
        String wishItemName = wishlistPage.getItemName();
        wishlistPage.clickRemoveBtn();
        Assert.assertTrue(itemName.contains(wishItemName), "invalid wishlist process");
    }
}

