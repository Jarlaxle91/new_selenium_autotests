package tests;

import org.junit.jupiter.api.Test;
import pages.LoginPageCbs;
import pages.MainPageCbs;

public class LoginTest extends TestBase {

    @Test
    public void loginToCbs() {
        new LoginPageCbs(driver).login();
//        new MainPageCbs(driver).goToInMainMenu("Dictionaries > SWIFT codes manual");
    }

}
