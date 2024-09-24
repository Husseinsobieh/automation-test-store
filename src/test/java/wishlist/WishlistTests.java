package wishlist;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Book1Page;
import pages.WishlistPage;

public class WishlistTests extends BaseTests {
    @Description("given I on a product page, when I click add to wishlist, then the item is added to my wish list")
    @Severity(SeverityLevel.NORMAL)
    @Story("wishlist")
    @Test
    public void testsWishlist(){
        login();
        Book1Page book1Page = homePage.clickBooksBtn().clickBookItem();
        book1Page.clickWishlistBtn();
        String itemName = book1Page.getItemName();
        goHome();
        WishlistPage wishlistPage = homePage.clickWishlistBtn();
        String wishItemName = wishlistPage.getItemName();
        wishlistPage.clickRemoveBtn();
        Assert.assertTrue(itemName.contains(wishItemName), "invalid wishlist process");
        logOff();
    }
}
