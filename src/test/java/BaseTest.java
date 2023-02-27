import config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import static config.WebDriverConfig.WAIT_SECOND_TIMEOUT;

public class BaseTest {
    protected WebDriver driver;
    protected static final String FIREFOX = "firefox";

    @Before
    public void setup() {
        driver = newDriver(System.getenv("BROWSER"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WAIT_SECOND_TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(AppConfig.URL);
    }

    private static WebDriver newDriver(String browserName) {
        if (FIREFOX.equals(browserName)){
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

            return new FirefoxDriver(options);
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }


    @After
    public void teardown() {
        driver.quit();
    }
}
