package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemGoodsPage extends BasePage {
    private final By ADD_BOOKMARK = By.xpath("//*[contains(@title, 'Добавить')]");
    private final By CLOSE = By.xpath("//*[contains(@class, 'modal-new_close')]/a");

    public ItemGoodsPage(WebDriver driver) {
        super(driver);
    }

    //TODO: hmm
    @Override
    protected void check(WebDriver driver) {
    }

    public ItemGoodsPage addBookmark() {
        WebElement wait = new WebDriverWait(driver, Duration.ofMinutes(WAIT_TIME)).
                until(ExpectedConditions.elementToBeClickable(ADD_BOOKMARK));
        click(ADD_BOOKMARK);
        return this;
    }

    public void close() {
        click(CLOSE);
    }
}
