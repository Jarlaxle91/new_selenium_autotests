package tests;

import org.junit.jupiter.api.Test;
import pages.LoginPageCbs;
import pages.MainPageCbs;
import pages.Window;

public class ManualSwiftCodes extends TestBase {

    @Test
    void createManualSwiftCode() {
        new LoginPageCbs(driver).login();
        MainPageCbs mainPageCbs = new MainPageCbs(driver);

        Window swiftCodesManualWindow = mainPageCbs.openWindow("Dictionaries > SWIFT codes > SWIFT codes manual", null);
//        swiftCodesManualWindow.pressButton("Add");
//
//        Window addSwiftCodeWindow = mainPageCbs.findWindow("Add SWIFT code");
//        addSwiftCodeWindow.setTextValue("Code", "BNS123ZASXX");
//        addSwiftCodeWindow.setTextValue("Bank name", "Test bank");
//        addSwiftCodeWindow.setTextValue("Country", "Greece");
//        addSwiftCodeWindow.setTextValue("Address", "Test address");
//        addSwiftCodeWindow.setDropdownValue("Enabled", "N");
//        addSwiftCodeWindow.pressButton("Save");
//        addSwiftCodeWindow.windowClosed();
        swiftCodesManualWindow.applyFilter("Code", "LIKE", "BNS123ZASXX");
//        swiftCodesManualWindow.inWindowDisplayedOneRecord();
    }

    @Test
    void createManualSwiftCode_1() {
        new LoginPageCbs(driver).login();
        MainPageCbs mainPageCbs = new MainPageCbs(driver);

        Window swiftCodesManualWindow = mainPageCbs.openWindow("Dictionaries > SWIFT codes > SWIFT codes manual", null);
        swiftCodesManualWindow.applyFilter("Code", "LIKE", "BNS123ZASXX");

    }

}
