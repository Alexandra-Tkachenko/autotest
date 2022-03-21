package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    public static final long WAIT_TIME = 2;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        check(driver);
    }

    public void clear(By by) {
        driver.findElement(by).clear();
    }

    public void sendKeys(By by, String keys) {
        driver.findElement(by).sendKeys(keys);
    }

    public void click(By by) {
        WebElement element = driver.findElement(by);
        if (element.isDisplayed()) {
            element.click();
        }
    }

    public void assertion(By by, long time, String error) {
        Assertions.assertTrue(new WebDriverWait(driver, Duration.ofMinutes(time)).
                until((ExpectedCondition<Boolean>) d -> driver.findElement(by).isDisplayed()), error);
    }

    abstract protected void check(WebDriver driver);
}

