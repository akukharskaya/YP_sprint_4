package com.github.akukharskaya;

import com.github.akukharskaya.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import static com.github.akukharskaya.config.WebDriverConfig.WAIT_SECOND_TIMEOUT;

public class BaseTest {
    protected static final String FIREFOX = "firefox";
    protected WebDriver driver;

    private static WebDriver newDriver(String browserName) {
        if (FIREFOX.equals(browserName)) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

            return new FirefoxDriver(options);
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }

    @Before
    public void setup() {
        String browser = System.getProperty("BROWSER");
        driver = newDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WAIT_SECOND_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.URL);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
