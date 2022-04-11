package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage<T extends BasePage<T>> extends LoadableComponent<T> {

    protected WebDriver driver;
    public static final long WAIT_TIME = 60;
    public static final String BASE_URL = "https://ok.ru/";


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clear(By by) {
        driver.findElement(by).clear();
    }

    public void sendKeys(By by, String keys) {
        driver.findElement(by).sendKeys(keys);
    }

    public void click(By by) {
        if (isElementDisplayed(by)) {
            driver.findElement(by).click();
        } else {
            Assertions.fail("Элемент не был найден для клика");
        }
    }

    public boolean isElementDisplayed(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void assertionDisplayed(By by, String error) {
        Assertions.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME)).
                until((ExpectedCondition<Boolean>) d -> isElementDisplayed(by)), error);
    }

    public void waitClickable(By by) {
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME)).
                until(ExpectedConditions.elementToBeClickable(by));
    }

    @Override
    protected void load() {
    }
}

