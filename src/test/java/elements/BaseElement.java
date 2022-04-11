package elements;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseElement<T extends BaseElement<T>> extends LoadableComponent<T> {
    private static final long WAIT_TIME = 60;

    protected WebDriver driver;
    protected final WebElement item;

    protected By PRICE = By.xpath("//*[@class='mall-price __rub']");
    protected By NAME = By.xpath("//div[contains(@class,'mall-title')]");

    public BaseElement(WebDriver driver, WebElement item) {
        this.driver = driver;
        this.item = item;
    }

    public void assertionDisplayed(By by, String error) {
        Assertions.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME)).
                until((ExpectedCondition<Boolean>) d -> driver.findElement(by).isDisplayed()), error);
    }

    public void waitClickable(By by) {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME)).
                until(ExpectedConditions.elementToBeClickable(by));
    }

    public String getPrice() {
        return driver.findElement(PRICE).getText();
    }

    public String getName() {
        return driver.findElement(NAME).getText();
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        check();
    }

    protected void check() {
        assertionDisplayed(NAME, "Имя товара не доступно");
        assertionDisplayed(PRICE, "Цена товара не доступна");
    }
}
