package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageCbs extends BasePage {

    private final By LOGIN_FIELD = By.id("name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.name("login");
    private final By WRONG_LOG_IN_ALERT = By.xpath("//div[contains(@class, 'notification error')]//div//div//div");

    public LoginPageCbs(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Successfully authorization in CBS")
    public void login() {
        assertEquals("Log in to CBS", driver.getTitle());

        element(LOGIN_FIELD).sendKeys("cbs-tester-1");
        element(PASSWORD_FIELD).sendKeys("321_Qverti");
        element(LOGIN_BUTTON).click();

        assertEquals("CBS", driver.getTitle());
        element(By.id("topToolbar"));
    }

    @Step(value = "Type in login field {0}")
    public void fillLoginField(String login) {
        element(LOGIN_FIELD).sendKeys(login);
    }

    @Step(value = "Type in password field {0}")
    public void fillPasswordField(String password) {
        element(PASSWORD_FIELD).sendKeys(password);
    }

    @Step(value = "Click login button")
    public void clickLoginButton() {
        element(LOGIN_BUTTON).click();
    }

    @Step(value = "Successful authorization")
    public void successAuthorization() {
        assertEquals("CBS", driver.getTitle());
    }

    @Step(value = "Message about error was displayed")
    public void error() {
        assertEquals("Log in to CBS", driver.getTitle());
        assertEquals("Invalid username or password.", driver.findElement(WRONG_LOG_IN_ALERT).getText());
    }
}
