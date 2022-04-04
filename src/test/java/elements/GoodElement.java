package elements;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ItemGoodsPage;

import java.time.Duration;

public class GoodElement extends LoadableComponent<GoodElement> {
    private static final long WAIT_TIME = 1;

    protected WebDriver driver;
    protected final WebElement item;
    @Getter
    private String nameGood;
    @Getter
    private String priceGood;

    protected By PRICE = By.xpath("//*[@class='mall-price __rub']");
    protected By NAME = By.xpath("//*[@class='mall-title __item lp']");
    protected By LINK = By.xpath("//*[@class='mall-item_section']/a");

    public GoodElement(WebDriver driver, WebElement item) {
        this.driver = driver;
        this.item = item;

        this.nameGood = driver.findElement(NAME).getText();
        this.priceGood = driver.findElement(PRICE).getText();
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        check();
    }

    private void check() {
        assertionDisplayed(NAME, "Имя товара не доступно");
        assertionDisplayed(PRICE, "Цена товара не доступна");
        waitClickable(LINK);
        assertionDisplayed(LINK, "Ссылка на товар не доступна");
    }

    public void assertionDisplayed(By by, String error) {
        Assertions.assertTrue(new WebDriverWait(driver, Duration.ofMinutes(WAIT_TIME)).
                until((ExpectedCondition<Boolean>) d -> driver.findElement(by).isDisplayed()), error);
    }

    public void waitClickable(By by) {
        WebElement wait = new WebDriverWait(driver, Duration.ofMinutes(WAIT_TIME)).
                until(ExpectedConditions.elementToBeClickable(by));
    }

    public ItemGoodsPage openGoodsLink() {
        waitClickable(LINK);
        driver.findElement(LINK).click();
        return new ItemGoodsPage(driver).get();
    }
}
