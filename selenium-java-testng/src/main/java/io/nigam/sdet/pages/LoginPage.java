package io.nigam.sdet.pages;

import io.nigam.sdet.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");
    public LoginPage(WebDriver driver) { super(driver); }
    public LoginPage open() { driver.get(Config.baseUrl()); return this; }
    public ProductsPage loginAs(String user, String pass) {
        type(username, user); type(password, pass); click(loginButton); return new ProductsPage(driver);
    }
    public String errorMessage() { return text(error); }
    public boolean isErrorVisible() { return displayed(error); }
}
