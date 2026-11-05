package io.nigam.sdet.tests;

import io.nigam.sdet.config.Config;
import io.nigam.sdet.driver.DriverFactory;
import io.nigam.sdet.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @Test
    public void userCanAddBackpackToCart() {
        var cart = new LoginPage(DriverFactory.getDriver()).open()
                .loginAs(Config.username(), Config.password())
                .addBackpackToCart().openCart();
        Assert.assertEquals(cart.firstItemName(), "Sauce Labs Backpack");
    }
}
