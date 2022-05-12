package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    public final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement element(By by) {
        return driver.findElement(by);
    }

    public By WINDOW(String windowName) {
        return By.xpath("//body[contains(@class, 'x-body')]//div[contains(@class, 'x-window')]" +
                "//div[contains(@class, 'x-title-text') and text()='" + windowName + "']");
    }

    public String ARIA_OWNS = "aria-owns";

}
