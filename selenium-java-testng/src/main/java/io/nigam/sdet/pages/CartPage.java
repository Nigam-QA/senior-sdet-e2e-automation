package io.nigam.sdet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By item = By.className("inventory_item_name");
    public CartPage(WebDriver driver) { super(driver); }
    public String firstItemName() { return text(item); }
}
