package io.nigam.sdet.tests;

import io.nigam.sdet.config.Config;
import io.nigam.sdet.driver.DriverFactory;
import io.nigam.sdet.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void validUserCanLogin() {
        var products = new LoginPage(DriverFactory.getDriver()).open()
                .loginAs(Config.username(), Config.password());
        Assert.assertTrue(products.isLoaded(), "Products page should be displayed after login");
    }
    @Test
    public void invalidUserSeesError() {
        var login = new LoginPage(DriverFactory.getDriver()).open();
        login.loginAs("invalid_user", "wrong_password");
        Assert.assertTrue(login.isErrorVisible(), "An error should be shown for invalid credentials");
    }
}
