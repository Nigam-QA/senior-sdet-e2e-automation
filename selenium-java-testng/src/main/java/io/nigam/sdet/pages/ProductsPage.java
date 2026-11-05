package io.nigam.sdet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private final By title = By.cssSelector(".title");
    private final By backpackAdd = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartLink = By.className("shopping_cart_link");
    public ProductsPage(WebDriver driver) { super(driver); }
    public boolean isLoaded() { return "Products".equals(text(title)); }
    public ProductsPage addBackpackToCart() { click(backpackAdd); return this; }
    public CartPage openCart() { click(cartLink); return new CartPage(driver); }
}
