package checkout;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.OrderProcessedPage;

public class CheckoutTests extends BaseTests {
    @Test
    public void testsCheckoutFunction() {
        login();
        homePage.addToCartProducts();
        CheckoutPage checkoutPage = homePage.clickCartBtn().clickCheckoutBtn();
        OrderProcessedPage orderProcessedPage = checkoutPage.clickConfirmOrderBtn();
        Assert.assertEquals(orderProcessedPage.getOrderProcessedText(),"YOUR ORDER HAS BEEN PROCESSED!",
                "checkout invalid");
        logOff();
    }
}
