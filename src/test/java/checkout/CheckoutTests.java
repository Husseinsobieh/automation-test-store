package checkout;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.OrderProcessedPage;

public class CheckoutTests extends BaseTests {
    @Description("given I'm on the checkout page, when I click on confirm order, " +
            "then my order should be successfully processed")
    @Story("checkout")
    @Severity(SeverityLevel.CRITICAL)
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
