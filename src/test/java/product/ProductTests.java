package product;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Book1Page;
import pages.BooksPage;
import pages.CartPage;

public class ProductTests extends BaseTests {
    @Description("given I'm on the product page, when i click add to cart, then the product is added to the cart")
    @Story("product")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testAddToCart() {
        login();
        homePage.addToCartProducts();
        int numberOfAddedItemsToCart = homePage.getProductsAddedToCart();
        Assert.assertTrue(numberOfAddedItemsToCart==2, "invalid add to cart process");
        goHome();
        homePage.clickCartBtn().deleteAllItems();
        logOff();
    }

    @Description("given I'm on the product page, when i click change the quantity," +
            " then the product's total price is changed to the appropriate price")
    @Story("product")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testAddToCart2() throws InterruptedException {
        String quantity = "5";
        login();
        Book1Page book1Page = homePage.clickBooksBtn().clickBookItem();
        book1Page.setProductQuantity(quantity);
        double totalPrice = Double.parseDouble(quantity) * book1Page.getProductPrice();
        Thread.sleep(500);
        Assert.assertEquals(book1Page.getTotalPrice(), totalPrice, "invalid price evaluation");
        book1Page.clickAddToCartBtn();
        goHome();
        homePage.clickCartBtn().deleteAllItems();
        logOff();
    }
}
