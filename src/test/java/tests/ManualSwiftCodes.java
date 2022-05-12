package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPageCbs;
import pages.MainPageCbs;
import pages.Window;

public class ManualSwiftCodes extends TestBase {

    @Test
    @DisplayName(value = "Login success")
    @Story(value = "Login success")
    @Description("Успешная авторизация в CBS")
    void loginSuccessful() {
        LoginPageCbs loginPageCbs = new LoginPageCbs(driver);
        loginPageCbs.fillLoginField("cbs-tester-1");
        loginPageCbs.fillPasswordField("321_Qverti");
        loginPageCbs.clickLoginButton();
        loginPageCbs.successAuthorization();
    }

    @Test
    @DisplayName(value = "Invalid credentials")
    @Story(value = "Invalid credentials")
    @Description("Отображение ошибки при неверных данных пользователя")
    void loginFailed() {
        LoginPageCbs loginPageCbs = new LoginPageCbs(driver);
        loginPageCbs.fillLoginField("invalid_credentials");
        loginPageCbs.fillPasswordField("invalid_credentials");
        loginPageCbs.clickLoginButton();
        loginPageCbs.error();
    }

    @Test
    @DisplayName(value = "Failed test for example")
    @Story(value = "Failed test for example")
    @Description("Этот тест должен упасть")
    void failedTestForExample() {
        LoginPageCbs loginPageCbs = new LoginPageCbs(driver);
        loginPageCbs.fillLoginField("cbs-tester-1");
        loginPageCbs.fillPasswordField("321_Qverti");
        loginPageCbs.clickLoginButton();
        loginPageCbs.error();
    }

    @Test
    @DisplayName(value = "Create SWIFT code")
    @Story(value = "Test for creation manual swift code")
    @Description("Тест на создание новой записи в Manual SWIFT codes")
    void createManualSwiftCode() {
        LoginPageCbs loginPageCbs = new LoginPageCbs(driver);
        loginPageCbs.login();
        MainPageCbs mainPageCbs = new MainPageCbs(driver);

        Window swiftCodesManualWindow = mainPageCbs.openWindow("Dictionaries > SWIFT codes > SWIFT codes manual", null);
        swiftCodesManualWindow.pressButton("Add");

        Window addSwiftCodeWindow = mainPageCbs.findWindow("Add SWIFT code");
        addSwiftCodeWindow.setTextValue("Code", "BNS123ZASXX");
        addSwiftCodeWindow.setTextValue("Bank name", "Test bank");
        addSwiftCodeWindow.setTextValue("Country", "Greece");
        addSwiftCodeWindow.setTextValue("Address", "Test address");
        addSwiftCodeWindow.setDropdownValue("Enabled", "N");
        addSwiftCodeWindow.pressButton("Save");
        addSwiftCodeWindow.windowClosed();
        swiftCodesManualWindow.applySingleFilter("Code", "LIKE", "BNS123ZASXX");
        swiftCodesManualWindow.inWindowDisplayedRecords(1);
    }
}
