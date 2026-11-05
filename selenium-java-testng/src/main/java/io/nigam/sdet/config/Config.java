package io.nigam.sdet.config;

public final class Config {
    private Config() {}
    public static String baseUrl() { return System.getProperty("baseUrl", "https://www.saucedemo.com/"); }
    public static String browser() { return System.getProperty("browser", "chrome"); }
    public static boolean headless() { return Boolean.parseBoolean(System.getProperty("headless", "true")); }
    public static String username() { return System.getProperty("username", "standard_user"); }
    public static String password() { return System.getProperty("password", "secret_sauce"); }
}
