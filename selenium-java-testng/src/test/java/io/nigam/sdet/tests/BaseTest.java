package io.nigam.sdet.tests;

import io.nigam.sdet.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setUp() { DriverFactory.createDriver(); }
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            if (!result.isSuccess()) {
                Path dir = Path.of("target", "screenshots");
                Files.createDirectories(dir);
                byte[] bytes = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                Files.write(dir.resolve(result.getMethod().getMethodName() + ".png"), bytes);
            }
        } catch (Exception e) { System.err.println("Could not save failure screenshot: " + e.getMessage()); }
        finally { DriverFactory.quitDriver(); }
    }
}
