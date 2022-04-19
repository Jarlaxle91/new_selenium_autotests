package tests;

import com.github.dockerjava.api.model.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestBase {
    private final Duration LOADING_DURATION = Duration.ofSeconds(90);

    protected WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://test.bocbs.cardpay-test.com/");
        driver.manage().timeouts().implicitlyWait(LOADING_DURATION);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
