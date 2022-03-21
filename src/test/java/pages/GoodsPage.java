package pages;

import elements.GoodsElement;
import elements.ItemElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsPage extends BasePage {
    private static final By GOODS_ELEMENTS = By.xpath("//*[@class='ugrid_i']");
    private static final By BASKET = By.xpath("//*[contains(@href, 'shopcart')]");
    private static final By BOOKMARKS = By.xpath("//*[contains(@href, 'bookmarks')]");
    private static final By DELETE = By.xpath("//*[text()='Удалить']");
    private static final By ITEMS = By.xpath("//*[contains(@class, 'mall-item __separator-basket __basket __flex')]");

    public GoodsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        assertion(BASKET, WAIT_TIME, "Ссылка на товары не загрузилась");
        //TODO: проверить на загрузку всех элементов-товаров?
    }

    public List<GoodsElement> getGoods() {
        List<WebElement> elements = driver.findElements(GOODS_ELEMENTS);
        if (!elements.isEmpty()) {
            return elements.stream().map(goods -> new GoodsElement(driver, goods)).collect(Collectors.toList());
        }
        return null;
    }

    public List<ItemElement> getItems() {
        List<WebElement> elements = driver.findElements(ITEMS);
        if (!elements.isEmpty()) {
            return elements.stream().map(element -> new ItemElement(driver, element)).collect(Collectors.toList());
        }
        return null;
    }

    //TODO: why T_T
    public GoodsPage openBasket() {
//        WebElement wait = new WebDriverWait(driver, Duration.ofMinutes(WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(BASKET));
        WebElement waiting = new WebDriverWait(driver, Duration.ofMinutes(WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(BASKET));
        WebElement element = driver.findElement(BASKET);
        Assert.assertTrue(element.isDisplayed() && element.isEnabled());
        element.click();
        return this;
    }

    public void openBookmarks() {
        WebElement wait = new WebDriverWait(driver, Duration.ofMinutes(WAIT_TIME)).
                until(ExpectedConditions.visibilityOfElementLocated(BOOKMARKS));
        click(BOOKMARKS);
    }

    public void deleteBookmark() {
        click(DELETE);
    }
}
