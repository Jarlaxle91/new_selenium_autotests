package pages;

import io.qameta.allure.Step;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class MainPageCbs extends BasePage {

    private final By TOP_TOOLBAR = By.id("topToolbar");

    private By ELEMENT_IN_SCOPE(String elementName) {
        return By.xpath("//a[contains(.,'" + elementName + "')]");
    }

    public MainPageCbs(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Go to window by path {0}")
    public Window openWindow(String menuPath, String windowName) {
        String finalWindowName;
        if (windowName == null) {
            List<String> menu = Arrays.stream(menuPath.split(">")).map(String::trim).collect(Collectors.toList());
            int size = menu.size();
            finalWindowName = menu.get(size - 1);
        } else {
            finalWindowName = windowName;
        }

        openMenuItem(menuPath);
        return new Window(driver, finalWindowName, driver.findElement(WINDOW(finalWindowName)));
    }

    @Step(value = "Window {0} is displayed")
    public Window findWindow(String windowName) {
        return new Window(driver, windowName, driver.findElement(WINDOW(windowName)));
    }

    private void openMenuItem(String menuPath) {
        clickMenuItem(driver.findElement(TOP_TOOLBAR),
                Arrays.stream(menuPath.split(">")).map(String::trim).collect(Collectors.toList()));
    }

    private void clickMenuItem(WebElement scope, List<String> pathElements) {
        WebElement item = scope.findElement(ELEMENT_IN_SCOPE(pathElements.get(0)));
        item.click();
        if (pathElements.size() > 1) {
            Awaitility.await()
                    .pollInterval(100, TimeUnit.MILLISECONDS)
                    .until(() -> item.getAttribute(ARIA_OWNS) != null);
        } else {
            return;
        }

        pathElements.remove(0);
        clickMenuItem(driver.findElement(By.id(item.getAttribute(ARIA_OWNS))), pathElements);
    }
}
