package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageCbs extends BasePage {

    private final By LOGIN_FIELD = By.id("name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.name("login");
    private final By WRONG_LOG_IN_ALERT = By.xpath("//div[contains(@class, 'alert-error')]/span");

    public LoginPageCbs(WebDriver driver) {
        super(driver);
    }

    public void login() {
        assertEquals("Log in to CBS", driver.getTitle());

        element(LOGIN_FIELD).sendKeys("cbs-tester-1");
        element(PASSWORD_FIELD).sendKeys("321_Qverti");
        element(LOGIN_BUTTON).click();

        assertEquals("CBS", driver.getTitle());
        element(By.id("topToolbar"));
    }

    public void success() {
        assertEquals("CBS", driver.getTitle());
    }

    public void error() {
        assertEquals("Log in to CBS", driver.getTitle());
        assertEquals("Invalid username or password.", driver.findElement(WRONG_LOG_IN_ALERT).getText());
    }


}
