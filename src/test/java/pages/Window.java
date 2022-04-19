package pages;

import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Window extends BasePage {
    public final String windowName;
    public final WebElement windowWebElement;

    public Window(WebDriver driver, String windowName, WebElement windowWebElement) {
        super(driver);
        this.windowName = windowName;
        this.windowWebElement = windowWebElement;
    }

    private By BUTTON(String buttonTitle) {
        return By.xpath("//a[contains(@class, 'x-btn')]//span[contains(@class, 'x-btn-inner') and text()= '" + buttonTitle + "']");
    }

    private By INPUT(String inputName) {
        return By.xpath("//div[contains(@class, 'x-field') and contains(., '" + inputName + "')]//input");
    }

    private By TEXTAREA(String textareaName) {
        return By.xpath("//div[contains(@class, 'x-field') and contains(., " + textareaName + ")]//textarea");
    }

    private By DROPDOWN_TRIGGER(String inputName) {
        return By.xpath("//div[contains(@class, 'x-field') and contains(., '" + inputName + "')]//div[contains(@class, 'x-form-arrow-trigger')]");
    }

    private By DROPDOWN_VALUE(String areaOwns, String value) {
        return By.xpath("//*[@id='" + areaOwns + "']//*[text()='" + value + "']");
    }

    private By ELEMENT_PROPERTY_LIST(String property) {
        return By.xpath("//div[contains(@class, 'x-boundlist-floating')][last()]//ul[contains(@class, 'x-list-plain')]//li[text()='" + property + "']");
    }

    private By ELEMENT_OPERATOR_LIST(String operator) {
        return By.xpath("//div[contains(@class, 'x-boundlist-floating')][last()]//ul[contains(@class, 'x-list-plain')]//li[text()='" + operator + "']");
    }

    private final By FILTER_TRIGGER = By.xpath("//div[contains(@aria-label, 'Expand Filter')]//div[contains(@class, 'x-tool-img')]");
    private final By CLEAR_FILTER_BUTTON = By.xpath("//a[contains(@class, 'clearButton')]");
    private final By APPLY_FILTER_BUTTON = By.xpath("//a[contains(@class, 'findButton')]");


    private final By XMASK = By.xpath("//div[contains(@class, 'xmask')]");
    private final By XMASK_VISIBLE = By.xpath("//div[contains(@aria-hidden, 'true')]");
    private final By AMOUNT_ELEMENTS_IN_GRID = By.xpath("//div[contains(@class, 'x-grid table')]");

    private final By FIRST_FILTER_VALUE_FIELD = By.xpath("//div[contains(@class, 'FilterForm ')][1]" +
            "//div[contains(@class, 'FilterValue')]//div[contains(@class, 'x-form-text-field-body')]" +
            "//input[contains(@class, 'x-form-field')]");

    private final By FILTER_PANEL_LAST = By.xpath("//div[contains(@role, 'button')]" +
            "//div[contains(@class, 'x-tool-expand-bottom')][last()]");  //ToDo может не работать

    private final By COLLAPSE_FILTER_BUTTON = By.xpath("//div[contains(@role, 'button')]" +
            "//div[contains(@class, 'x-tool-collapse-top')]");

    private final By FILTER_PROPERTY_TRIGGER = By.xpath(("(//div[contains(@class, 'x-container-default')]" +
            "//a[contains(@class, 'filterCheckbox')]/../../..)[last()]//input[contains(@name, 'filterField')]" +
            "/../..//div[contains(@class, 'x-form-trigger')][1]")); //ToDo может не работать

    private final By FILTER_OPERATOR_TRIGGER = By.xpath(("(//div[contains(@class, 'x-container-default')]" +
            "//a[contains(@class, 'filterCheckbox')]/../../..)[last()]//input[contains(@name, 'filterField')]" +
            "/../..//div[contains(@class, 'x-form-trigger')][last()]")); //ToDo может не работать


    private final By FILTER_OPERATOR_TRIGGER1 = By.xpath("//div[contains(@class, 'FilterOperator')]" +
            "//div[contains(@class, 'x-form-item-body')]//div[contains(@class, 'x-form-trigger-wrap')]//div[contains(@class, 'x-form-trigger')]");

    public void pressButton(String buttonName) {
        windowWebElement.findElement(BUTTON(buttonName)).click();
    }

    public void setTextValue(String inputName, String value) {
        windowWebElement.findElement(INPUT(inputName)).sendKeys(value);
    }

    public void setFieldValue(String inputName, String value) {
        windowWebElement.findElement(TEXTAREA(inputName)).sendKeys(value);
    }

    public void setDropdownValue(String inputName, String value) {
        windowWebElement.findElement(DROPDOWN_TRIGGER(inputName)).click();
        WebElement input = windowWebElement.findElement(INPUT(inputName));
        String ariaOwns = input.getAttribute("aria-owns");
        windowWebElement.findElement(DROPDOWN_VALUE(ariaOwns, value)).click();
    }

    public void windowClosed() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        Awaitility.await()
                .atMost(2, TimeUnit.SECONDS)
                .pollInterval(5, TimeUnit.MILLISECONDS)
                .until(() -> driver.findElements(WINDOW(windowName)).isEmpty());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void applyFilter(String property, String operator, String value) {
        expandFilter();
        clearFilter();

        selectValueOfPropertyField(property);
        selectValueOfOperatorField(operator);
        fillSingleValueOfFilter(value);

        applyFilter();
        collapseFilter();
    }

    public void expandFilter() {
        windowWebElement.findElement(FILTER_TRIGGER).click();
        if (!windowWebElement.findElement(FILTER_PANEL_LAST).isDisplayed()) {
            windowWebElement.findElement(FILTER_TRIGGER).click();
        }
    }

    public void clearFilter() {
        windowWebElement.findElement(CLEAR_FILTER_BUTTON).click();
    }

    public void selectValueOfPropertyField(String property) {
        windowWebElement.findElement(FILTER_PROPERTY_TRIGGER).click();
        windowWebElement.findElement(ELEMENT_PROPERTY_LIST(property)).click();
    }

    public void selectValueOfOperatorField(String operator) {
        windowWebElement.findElement(FILTER_OPERATOR_TRIGGER1).click();
        windowWebElement.findElement(ELEMENT_OPERATOR_LIST(operator)).click();
    }

    public void fillSingleValueOfFilter(String primValue) {
            windowWebElement.findElement(FIRST_FILTER_VALUE_FIELD).sendKeys(primValue);
    }

    public void applyFilter() {
        windowWebElement.findElement(APPLY_FILTER_BUTTON).isDisplayed();
        windowWebElement.findElement(APPLY_FILTER_BUTTON).click();
    }

    public void collapseFilter() {
        windowWebElement.findElement(COLLAPSE_FILTER_BUTTON).isDisplayed();
        windowWebElement.findElement(COLLAPSE_FILTER_BUTTON).click();
    }

    public void inWindowDisplayedOneRecord() {
        waitLoadingForm();
        assert 1 == checkGrid();
    }

    public void waitLoadingForm() {
        if (!windowWebElement.findElements(XMASK).isEmpty()) {

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            Awaitility.await()
                    .atMost(2, TimeUnit.SECONDS)
                    .pollInterval(5, TimeUnit.MILLISECONDS)
                    .until(() -> driver.findElements(XMASK_VISIBLE).isEmpty());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

    public Integer checkGrid() {
       List<WebElement> records = windowWebElement.findElements(AMOUNT_ELEMENTS_IN_GRID);
       return records.size();
    }


}
