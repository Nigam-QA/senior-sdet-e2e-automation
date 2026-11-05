package io.nigam.sdet.driver;

import io.nigam.sdet.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private DriverFactory() {}
    public static void createDriver() {
        String browser = Config.browser().toLowerCase();
        WebDriver driver;
        switch (browser) {
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (Config.headless()) options.addArguments("-headless");
                driver = new FirefoxDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                if (Config.headless()) options.addArguments("--headless=new");
                driver = new EdgeDriver(options);
            }
            default -> {
                ChromeOptions options = new ChromeOptions();
                if (Config.headless()) options.addArguments("--headless=new");
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1440,1000");
                driver = new ChromeDriver(options);
            }
        }
        DRIVER.set(driver);
    }
    public static WebDriver getDriver() {
        if (DRIVER.get() == null) throw new IllegalStateException("WebDriver has not been initialized");
        return DRIVER.get();
    }
    public static void quitDriver() {
        if (DRIVER.get() != null) { DRIVER.get().quit(); DRIVER.remove(); }
    }
}
