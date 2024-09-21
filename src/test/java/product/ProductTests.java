package product;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Book1Page;
import pages.BooksPage;
import pages.CartPage;

public class ProductTests extends BaseTests {
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
